package com.example.jetpackdemo.day16_paging_more

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.jetpackdemo.R
import kotlinx.android.synthetic.main.fragment_view_pager_inside.*


private const val TAG = "ViewPagerInsideFragment"

class ViewPagerInsideFragment : Fragment() {
    private val viewModel by viewModels<MyPagerListViewModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_view_pager_inside, container, false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item1 -> {
                swipeRefreshLayout1.isRefreshing = true
                swipeRefreshLayout1.postDelayed({
                    viewModel.requestQuery()
                }, 1000)
            }
            R.id.retry -> {
                viewModel.retry()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_my_gallery, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val galleryAdapter = GalleryAdapter(viewModel)
        recyclerView.apply {
            adapter = galleryAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
        viewModel.pageListLiveData.observe(viewLifecycleOwner, Observer {
            galleryAdapter.submitList(it)
        })
        swipeRefreshLayout1.setOnRefreshListener {
            viewModel.requestQuery()
        }

        viewModel.netWorkStatus.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "onViewCreated: $it")
            galleryAdapter.updateNetWorkStatus(it)
            swipeRefreshLayout1.isRefreshing = it == NetWorkStatus.INITIAL_LOADING
        })
    }
}
