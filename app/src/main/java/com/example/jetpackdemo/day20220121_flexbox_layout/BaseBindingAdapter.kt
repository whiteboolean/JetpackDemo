package com.example.jetpackdemo.day20220121_flexbox_layout

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import java.lang.reflect.ParameterizedType

abstract class BaseBindingAdapter<T, VB : ViewBinding> (data: MutableList<T>? = null) :
    BaseQuickAdapter<T, BaseVBViewHolder<VB>>(0, data) {

    constructor(mContext: Context): this() {
//        enableDefaultEmptyView(mContext)
    }

//    var _emptyView: EmptyView? = null
//    fun enableDefaultEmptyView(context: Context, imgResId: Int = R.mipmap.ic_not_result, text: String = "暂无数据") {
//        _emptyView = EmptyView(context).apply {
//            if (imgResId > 0) {
//                setEmptyImg(imgResId)
//            }
//            setEmptyText(text)
//        }
//        setEmptyView(_emptyView!!)
//    }
//    fun setEmptyImg(imgResId: Int) = _emptyView?.setEmptyImg(imgResId)
//    fun setEmptyText(text: String) = _emptyView?.setEmptyText(text)


    override fun onCreateDefViewHolder(parent: ViewGroup, viewType: Int): BaseVBViewHolder<VB> {
        val vbClass: Class<VB> = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VB>
        val inflate = vbClass.getDeclaredMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java)
        return BaseVBViewHolder(inflate.invoke(null, LayoutInflater.from(parent.context), parent, false) as VB)
    }

    override fun convert(helper: BaseVBViewHolder<VB>, item: T) {
        onBinding(helper.vb, item)
    }

    abstract fun onBinding(viewBinding: VB, item: T)
}

class BaseVBViewHolder<VB : ViewBinding>(val vb: VB) : BaseViewHolder(vb.root)
