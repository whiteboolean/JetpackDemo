package com.example.jetpackdemo.day13_viewpager2_demo

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.jetpackdemo.R
import com.example.jetpackdemo.day11_image_lang.Hit
import kotlinx.android.synthetic.main.gallery_cell.view.*
import kotlinx.android.synthetic.main.gallery_footer.view.*

class GalleryAdapter(viewModel: MyPagerListViewModel, viewLifecycleOwner: LifecycleOwner, swipeRefreshLayout1: SwipeRefreshLayout) :
        ListAdapter<Hit, GalleryAdapter.MyGalleryViewHolder>(DiffCallback) {
    //静态属性
    companion object {
        const val NORMAL_VIEW_TYPE = 0
        const val FOOTER_VIEW_TYPE = 1
    }

    //延迟初始化
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: MyPagerListViewModel
    private var footerViewStatus = DATA_STATUS_CAN_LOAD_MORE

    /**
     * 私有构造执行完之后执行下面方法
     */
    init {
        initViewModel(viewModel, viewLifecycleOwner, swipeRefreshLayout1)
    }

    private fun initViewModel(viewModel: MyPagerListViewModel, viewLifecycleOwner: LifecycleOwner, swipeRefreshLayout1: SwipeRefreshLayout) {
        this.viewModel = viewModel
        viewModel.fetchData()
        swipeRefreshLayout1.setOnRefreshListener {
            viewModel.resetFetchData()
        }
        viewModel.photoStateLive.observe(viewLifecycleOwner, Observer {
            footerViewStatus = it
            notifyItemChanged(itemCount - 1)
            if (it == DATA_STATUS_NETWORK_ERROR) swipeRefreshLayout1.isRefreshing = false
        })

        viewModel.photoListData.observe(viewLifecycleOwner, Observer { t ->
            if (viewModel.needScrollToTop) {
                recyclerView.scrollToPosition(0)
                viewModel.needScrollToTop = false
            }
            submitList(t)
            swipeRefreshLayout1.isRefreshing = false
        })
    }



    object DiffCallback : DiffUtil.ItemCallback<Hit>() {
        override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem.photoId == newItem.photoId
        }

        override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1) FOOTER_VIEW_TYPE else NORMAL_VIEW_TYPE
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        //recyclerView设置滑动监听器
        this.recyclerView = recyclerView
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                //判断滑动状态
                // 0 停止
                // 1 滑动
                // 2 自由滑动
//                Log.e(TAG,newState.toString())
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                Log.e(TAG,"dy:${dy}")
                if (dx < 0) return
                if (footerViewStatus == DATA_STATUS_NO_MORE) return
                val layoutManager = recyclerView.layoutManager as StaggeredGridLayoutManager
                val position = IntArray(2)
                layoutManager.findLastVisibleItemPositions(position)
                if (position[0] == itemCount - 1) {
                    viewModel.fetchData()
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyGalleryViewHolder {
        var holder: MyGalleryViewHolder
        if (viewType == NORMAL_VIEW_TYPE) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.gallery_cell, parent, false)
            holder = MyGalleryViewHolder(view)
            holder.itemView.setOnClickListener {
                Bundle().apply {
                    putParcelableArrayList("PHOTO_LIST", ArrayList(currentList))
                    putInt("PHOTO_POSITION", holder.layoutPosition)
                    it.findNavController().navigate(R.id.action_viewPagerInsideFragment_to_photoFragment2, this)
                }
            }
        } else {
            holder = MyGalleryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.gallery_footer, parent, false).also {
                (it.layoutParams as StaggeredGridLayoutManager.LayoutParams).isFullSpan = true
                it.setOnClickListener { itemView1 ->
                    itemView1.tv_text.text = "正在加载"
                    itemView1.pb_progress.visibility = View.VISIBLE
                    viewModel.fetchData()
                }
            })
        }
        return holder
    }


    override fun onBindViewHolder(holder: MyGalleryViewHolder, position: Int) {
        if (position == itemCount - 1) {
            with(holder.itemView) {
                when (footerViewStatus) {
                    DATA_STATUS_CAN_LOAD_MORE -> {
                        tv_text.text = "正在加载"
                        pb_progress.visibility = View.VISIBLE
                        isClickable = false
                    }
                    DATA_STATUS_NETWORK_ERROR -> {
                        tv_text.text = "网络故障，点击重试"
                        pb_progress.visibility = View.GONE
                        isClickable = true
                    }
                    DATA_STATUS_NO_MORE -> {
                        tv_text.text = "加载完成"
                        pb_progress.visibility = View.GONE
                        isClickable = false

                    }
                }
            }
            return
        }
        with(holder.itemView) {
            imageView11.layoutParams.height = getItem(position).webformatHeight
            shimmerlayout_cell.apply {
                setShimmerColor(0x55FFFFFF)
                setShimmerAngle(0)
                startShimmerAnimation()
            }
            Glide.with(this)
                    .load(getItem(position).previewUrl)
                    .placeholder(R.mipmap.ic_launcher)
                    .addListener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                            shimmerlayout_cell.stopShimmerAnimation()
                            return false
                        }

                        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                            shimmerlayout_cell.stopShimmerAnimation()
                            return false
                        }
                    })
                    .into(imageView11)
        }


    }

    class MyGalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}
