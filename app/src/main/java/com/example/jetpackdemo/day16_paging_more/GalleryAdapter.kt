package com.example.jetpackdemo.day16_paging_more

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.jetpackdemo.R
import com.example.jetpackdemo.day11_image_lang.Hit
import kotlinx.android.synthetic.main.gallery_cell.view.*
import kotlinx.android.synthetic.main.gallery_footer.view.*

class GalleryAdapter(val viewModel: MyPagerListViewModel) :
        PagedListAdapter<Hit, RecyclerView.ViewHolder>(DiffCallback) {


    //延迟初始化
    lateinit var netWorStatus: NetWorkStatus

    var hasFooter = false

    /**
     * 私有构造执行完之后执行下面方法
     */
    init {
        viewModel.retry()
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
        return if (hasFooter && position == itemCount - 1) R.layout.gallery_footer else R.layout.gallery_cell
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter) 1 else 0
    }

    fun updateNetWorkStatus(netWorkStatus: NetWorkStatus) {
        this.netWorStatus = netWorkStatus
        if (netWorkStatus == NetWorkStatus.INITIAL_LOADING) hideFooter() else showFooter()
    }


    private fun hideFooter() {
        if (hasFooter) {
            notifyItemRemoved(itemCount - 1)
        }
        hasFooter = false
    }

    private fun showFooter() {
        if (hasFooter) {
            notifyItemChanged(itemCount - 1)
        } else {
            hasFooter = true
            notifyItemInserted(itemCount - 1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.gallery_cell -> MyGalleryViewHolder.newInstance(parent).also { holder ->
                holder.itemView.setOnClickListener {
                    Bundle().apply {
                        putParcelableArrayList("PHOTO_LIST", ArrayList(currentList!!))
                        putInt("PHOTO_POSITION", holder.bindingAdapterPosition)
                        holder.itemView.findNavController()
                                .navigate(R.id.action_viewPagerInsideFragment_to_photoFragment2, this)
                    }
                }
            }
            else -> FooterViewHolder.newInstance(parent).also {
                it.itemView.setOnClickListener {
                    viewModel.retry()
                }
            }
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            R.layout.gallery_footer -> {
                (holder as FooterViewHolder).bindWithWorkStatus(newWorkStatus = netWorStatus)
            }
            else -> {
                val photoItem = getItem(position) ?: return
                (holder as MyGalleryViewHolder).bindWithPhotoItem(photoItem)
            }
        }

    }

}

class MyGalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun newInstance(parent: ViewGroup): MyGalleryViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.gallery_cell, parent, false)
            return MyGalleryViewHolder(view)
        }
    }

    fun bindWithPhotoItem(item: Hit) {
        with(itemView) {
            //这里固定大小防止出现布局错乱的情况
            imageView11.layoutParams.height = item.webformatHeight
            shimmerlayout_cell.apply {
                setShimmerColor(0x55FFFFFF)
                setShimmerAngle(0)
                startShimmerAnimation()
            }
            Glide.with(this)
                    .load(item.previewUrl)
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
}

class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        fun newInstance(parent: ViewGroup): FooterViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.gallery_footer, parent, false)
            (view.layoutParams as StaggeredGridLayoutManager.LayoutParams).isFullSpan = true
            return FooterViewHolder(view)
        }
    }

    fun bindWithWorkStatus(newWorkStatus: NetWorkStatus) {
        with(itemView) {
            when (newWorkStatus) {
                NetWorkStatus.FAILED -> {
                    tv_text.text = "网络故障，点击重试"
                    pb_progress.visibility = View.GONE
                    isClickable = true
                }
                NetWorkStatus.COMPLETED -> {
                    tv_text.text = "加载完成"
                    pb_progress.visibility = View.GONE
                    isClickable = false
                }
                else -> {
                    tv_text.text = "正在加载"
                    pb_progress.visibility = View.VISIBLE
                    isClickable = false
                }
            }
        }
    }
}
