package com.example.jetpackdemo.day1027_custom_view

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackdemo.R

private const val TAG = "MyFragmentDialog"
class MyFragmentDialog : DialogFragment {

    lateinit var recyclerView:RecyclerView

    constructor(){
        Log.d(TAG, "constructor: ")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = View.inflate(context, R.layout.cell_card,null)
//         recyclerView = view.findViewById(R.id.mr_cast_list)
        Log.d(TAG, "onCreateView: ")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        Log.d(TAG, "onCancel: ")
    }


    public fun getRecycleView():RecyclerView{
        Log.d(TAG, "getRecycleView: ")
        return recyclerView
    }
}