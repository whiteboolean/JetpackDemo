package com.example.jetpackdemo.day13_viewpager2_demo

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.jetpackdemo.R
import kotlinx.android.synthetic.main.fragment_view_pager_inside.*


private const val TAG = "ViewPagerInsideFragment"

class ViewPagerInsideFragment : Fragment() {
    private lateinit var viewModel: MyPagerListViewModel
    lateinit var adapter: GalleryAdapter
    lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_view_pager_inside, container, false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item1 -> {
                swipeRefreshLayout1.isRefreshing = true
                swipeRefreshLayout1.postDelayed({
                    viewModel.resetFetchData()
                }, 1000)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_my_gallery,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProvider(this).get(MyPagerListViewModel::class.java)
        adapter = GalleryAdapter(viewModel,viewLifecycleOwner,swipeRefreshLayout1)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


    }
}
