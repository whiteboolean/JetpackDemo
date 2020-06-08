package com.example.jetpackdemo.day11_image_lang

import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.jetpackdemo.R
import kotlinx.android.synthetic.main.gallery_fragment.*

/**
 * 右上角添加menu几个步骤
 * 1.在资源文件中添加menuLayout布局文件
 * 2.重写onCreateOptionsMenu方法 - 并且将刚才创建的menu文件引入进来 inflater.inflate()
 * 3.在onCreate中添加setHashOptionsMenu(true)
 * 4.点击事件在onOptionsItemSelected()方法中
 */

class GalleryFragment : Fragment() {

    companion object {
        fun newInstance() = GalleryFragment()
    }

    private lateinit var viewModel: GalleryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.gallery_fragment, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_gallery, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.indicator -> {
                swipeRefreshLayout1.isRefreshing = true
                Handler().postDelayed({ viewModel.fetchData() }, 1000)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)
        val galleryAdapter = GalleryAdapter()
        recycler_view.apply {
            adapter = galleryAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        swipeRefreshLayout1.setOnRefreshListener {
            viewModel.fetchData()
        }

        viewModel.photoList.observe(viewLifecycleOwner, Observer {
            galleryAdapter.submitList(it)
            swipeRefreshLayout1.isRefreshing = false
        })
        viewModel.photoList.value ?: viewModel.fetchData()
    }




}
