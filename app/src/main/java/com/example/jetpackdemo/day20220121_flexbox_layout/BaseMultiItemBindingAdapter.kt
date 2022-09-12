package com.example.jetpackdemo.day20220121_flexbox_layout

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import java.lang.reflect.ParameterizedType

abstract class BaseMultiItemBindingAdapter<T :MultiItemEntity, VB : ViewBinding> (data: MutableList<T>? = null) :
    BaseMultiItemQuickAdapter<T,BaseViewHolder>( data) {



    override fun convert(holder: BaseViewHolder, item: T) {
        onBinding(holder, item)
    }


    abstract fun onBinding(viewBinding: BaseViewHolder, item: T)
}


//class BaseMultiVBViewHolder<VB : ViewBinding>(val vb: VB) : BaseViewHolder(vb.root)
