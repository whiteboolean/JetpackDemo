package com.example.jetpackdemo.day20220121_flexbox_layout

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.R

class MyMultiAdapter : BaseMultiItemQuickAdapter<MultiDataBean, BaseViewHolder>() {

    init {
        addItemType(TEXT_WITH_DATA_TYPE, R.layout.item_full_data)
        addItemType(TEXT_WITH_NO_DATA_TYPE, R.layout.item_no_data)
    }

    override fun convert(holder: BaseViewHolder, item: MultiDataBean) {

        when (holder.itemViewType) {
            TEXT_WITH_NO_DATA_TYPE -> {

            }

            TEXT_WITH_DATA_TYPE -> {

            }

        }
    }
}