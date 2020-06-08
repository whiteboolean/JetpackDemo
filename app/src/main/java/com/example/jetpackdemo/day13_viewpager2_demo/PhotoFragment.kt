package com.example.jetpackdemo.day13_viewpager2_demo

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.jetpackdemo.R
import com.example.jetpackdemo.day11_image_lang.Hit
import kotlinx.android.synthetic.main.fragment_photo.*
import kotlinx.android.synthetic.main.photo_pager_view.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


private const val TAG = "PhotoFragment"
const val REQUEST_WRITE_EXTERNAL_STORAGE = 1

class PhotoFragment : Fragment() {

    //记录上一次滑动的positionOffsetPixels值
    var lastValue = -1
    var isLeft = true
    var isCurrentPage = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_photo, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        EventBus.getDefault().register(this)
        println("onActivityCreate")
        val parcelableArrayList = arguments?.getParcelableArrayList<Hit>("PHOTO_LIST")
        val currentPosition = arguments?.getInt("PHOTO_POSITION")

//        banner.addBannerLifecycleObserver(this)//添加生命周期观察者
////        adapter = PagerPhotoListAdapter(parcelableArrayList)
//        banner.adapter = PagerPhotoListAdapter(parcelableArrayList)
//        banner.indicator = CircleIndicator(requireContext())
//        banner.start();
        button28.setOnClickListener {
            it.findNavController().navigate(R.id.action_photoFragment2_to_viewPagerInsideFragment)
        }

        PagerPhotoListAdapter().apply {
            viewpager2.adapter = this
            submitList(parcelableArrayList)
        }
        viewpager2.setCurrentItem(currentPosition ?: 0, false)
        textView36.text = "${currentPosition.toString()}/${parcelableArrayList?.size}"

        imageView18.setOnClickListener {
            if (Build.VERSION.SDK_INT < 29 && ContextCompat.checkSelfPermission(
                            requireContext(),
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        REQUEST_WRITE_EXTERNAL_STORAGE
                )
            } else {
                viewLifecycleOwner.lifecycleScope.launch {
                    savePhoto()
                }
            }
        }

        viewpager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            var position: Int = 0
            var oldPosition: Int = 0

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                this.position = position;
//                Log.e(TAG,"onPageScrolled")
//                Log.e(TAG,"isCurrentPage:${isCurrentPage}")
//                if (!isCurrentPage) return
//            Log.e(TAG,"position:${position},positionOffset:${positionOffset},positionOffsetPixels:${positionOffsetPixels},value:${lastValue},isLeft:${isLeft}")
//                if (positionOffset != 0f) {
//                    if (lastValue >= positionOffsetPixels) {
//                        //右滑
//                        isLeft = false
//                    } else if (lastValue < positionOffsetPixels) {
//                        //左滑
//                        isLeft = true
//                    }
//                }
//                lastValue = positionOffsetPixels;
            }

            /**
             * 手工拖动：1
             * 松开滑动：2
             * 停止 ： 0
             */
            override fun onPageScrollStateChanged(state: Int) {
                Log.e(TAG, "onPageScrollStateChanged")
//                Log.d(TAG, "是否能向左滑：" + viewpager2.canScrollHorizontally(-1))
//                Log.d(TAG, "是否能向右滑：" + viewpager2.canScrollHorizontally(1))

//                if (state == 1) {
//                    oldPosition = position;
//                }
//                if (state == 0) {
//                    if (position == oldPosition) {
//                        if (position == 0) {
//                            //滑动到第一页，继续向右滑
//                            Log.e(TAG, "滑动到第一页，继续向右滑")
//                            viewpager2.isUserInputEnabled = false
//                            (activity as MainActivity).viewPager.isUserInputEnabled = true
//                        } else if (position == viewpager2.adapter?.itemCount?.minus(1)) {
//                            //滑动到最后一页，继续向左滑
//                            Log.e(TAG, "滑动到最后一页，继续向左滑")
//                            viewpager2.isUserInputEnabled = false
//                            (activity as MainActivity).viewPager.isUserInputEnabled = true
//                        } else {
//                            //滑动到一半时停止滑动，当前停留在第position页
//                            Log.e(TAG, "滑动到一半时停止滑动，当前停留在第position页")
//                            viewpager2.isUserInputEnabled = true
//                            (activity as MainActivity).viewPager.isUserInputEnabled = false
//                        }
//                    } else {
//                        if (position < oldPosition) {
//                            //从左向右
//                            Log.e(TAG, "从左向右")
//
//                        } else {
//                            //从右向左
//                            Log.e(TAG, "从右向左")
//                        }
//                    }
//                }


//                Log.e(TAG, if (isLeft) "正在向左滑动" else "正在向右滑动")
//                Log.e(TAG, "是否模拟拖动:${viewpager2.isFakeDragging}")
////                viewpager2.scrollState
//                Log.e(TAG, "当前状态:${state}")


//                Log.e(TAG,"是否可以向左滑${viewpager2.canScrollHorizontally(-1)},是否在手动拖动:${state},是否再向左滑：${isLeft}")
//                if (!viewpager2.canScrollHorizontally(-1) && state == 1 && !isLeft) {
//                    //1.不能再向左滑动了
//                    //2.正在手动拖动
//                    //3.正在向右拖动
////                    Log.e(TAG, "进来了1")
//                    viewpager2.isUserInputEnabled = false
//                    (activity as MainActivity).viewPager.isUserInputEnabled = true
//                    isCurrentPage = false
//
////                    Log.e(TAG,"内部不可滑动，外部可滑动，可以向右拖动")\
//                    Log.e(TAG, "进来了1")
//                }else if(!viewpager2.canScrollHorizontally(-1) && state == 1 && isLeft){
//                    viewpager2.isUserInputEnabled = true
//                    (activity as MainActivity).viewPager.isUserInputEnabled = false
//                    isCurrentPage = true
//                    Log.e(TAG, "进来了2")
//                } else if (!viewpager2.canScrollHorizontally(1) && state == 1 && isLeft) {
//                    //1.不能再向右滑动了
//                    //2.手正在拖动
//                    //3.正在向左滑动
//                    Log.e(TAG, "进来了3")
//
//                    viewpager2.isUserInputEnabled = false
//                    (activity as MainActivity).viewPager.isUserInputEnabled = true
//                    isCurrentPage = false
////                    Log.e(TAG,"内部不可滑动，外部可滑动，可以向左拖动")
//                } else if (state == 1) {
//                    Log.e(TAG, "进来了4")
//                    viewpager2.isUserInputEnabled = true
//                    (activity as MainActivity).viewPager.isUserInputEnabled = false
////                    Log.e(TAG,"内部可滑动，外部不可滑动，可以左右拖动")
//                }

                super.onPageScrollStateChanged(state)
            }


            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
//                Log.e(TAG, "onPageSelected")
                textView36.text = "${position + 1}/${parcelableArrayList?.size}"
            }

        })

    }


    private suspend fun savePhoto() {
        withContext(Dispatchers.IO) {
//            launch(Dispatchers.Main) {
//
//            }
            val holder =
                    (viewpager2[0] as RecyclerView).findViewHolderForAdapterPosition(viewpager2.currentItem)
                            as PagerPhotoViewHolder
            val bitmap = holder.itemView.pagerPhoto.drawable.toBitmap()

            val saveUri = requireContext().contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    ContentValues()
            )?: kotlin.run {
                Toast.makeText(requireContext(), "存储失败", Toast.LENGTH_SHORT).show()
                return@withContext
            }
            requireContext().contentResolver.openOutputStream(saveUri).use {
                if (bitmap.compress(Bitmap.CompressFormat.JPEG,90,it)) {
                    MainScope().launch { Toast.makeText(requireContext(), "存储成功", Toast.LENGTH_SHORT).show() }
                } else {
                    MainScope().launch { Toast.makeText(requireContext(), "存储失败", Toast.LENGTH_SHORT).show() }
                }
            }
        }
    }

    @Subscribe
    public fun onBusEvent(scrollEvent: ScrollEvent) {
        when (scrollEvent.intLeft) {
            0 -> {
                viewpager2.isUserInputEnabled = false
//                Log.e(TAG, "内部不可滑动")
                isCurrentPage = false
            }
            1 -> {
                viewpager2.isUserInputEnabled = true
//                Log.e(TAG, "内部可滑动")
                isCurrentPage = true
            }
            2 -> {
                viewpager2.isUserInputEnabled = false
//                Log.e(TAG, "内部不可滑动")
//                isCurrentPage = false
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }


}

