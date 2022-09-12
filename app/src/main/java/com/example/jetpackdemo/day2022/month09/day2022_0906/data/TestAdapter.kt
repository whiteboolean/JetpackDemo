//package com.example.jetpackdemo.day2022.month09.day2022_0906.data
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.chad.library.adapter.base.BaseMultiItemAdapter
//import com.example.jetpackdemo.databinding.DefSectionHeadBinding
//import com.example.jetpackdemo.databinding.HomeItemViewBinding
//import com.example.jetpackdemo.day2022.month09.day2022_0906.data.model.HomeEntity
//
//class TestAdapter(data: List<HomeEntity>) : BaseMultiItemAdapter<HomeEntity>(data) {
//
//    // 类型 1 的 viewholder
//    class ItemVH(viewBinding: HomeItemViewBinding) : RecyclerView.ViewHolder(viewBinding.root)
//
//    // 类型 2 的 viewholder
//    class HeaderVH(viewBinding: DefSectionHeadBinding) : RecyclerView.ViewHolder(viewBinding.root)
//
//    // 在 init 初始化的时候，添加多类型
//    init {
//        addItemType(ITEM_TYPE, object : OnMultiItemAdapterListener<HomeEntity, ItemVH> { // 类型 1
//            override fun onCreate(context: Context, parent: ViewGroup, viewType: Int): ItemVH {
//                // 创建 viewholder
//                val viewBinding = HomeItemViewBinding.inflate(LayoutInflater.from(context), parent, false)
//                return ItemVH(viewBinding)
//            }
//
//            override fun onBind(holder: ItemVH, position: Int, item: HomeEntity?) {
//                // 绑定 item 数据
//            }
//        }).addItemType(SECTION_TYPE, object : OnMultiItemAdapterListener<HomeEntity, HeaderVH> { // 类型 2
//            override fun onCreate(context: Context, parent: ViewGroup, viewType: Int): HeaderVH {
//                // 创建 viewholder
//                val viewBinding = DefSectionHeadBinding.inflate(LayoutInflater.from(context), parent, false)
//                return HeaderVH(viewBinding)
//            }
//
//            override fun onBind(holder: HeaderVH, position: Int, item: HomeEntity?) {
//                // 绑定 item 数据
//            }
//
//            override fun isFullSpanItem(itemType: Int): Boolean {
//                // 使用GridLayoutManager时，此类型的 item 是否是满跨度
//                return true;
//            }
//
//        }).onItemViewType { position, list -> // 根据数据，返回对应的 ItemViewType
//            if (list[position].isSection) {
//                SECTION_TYPE
//            } else {
//                ITEM_TYPE
//            }
//        }
//    }
//
//    companion object {
//        private const val ITEM_TYPE = 0
//        private const val SECTION_TYPE = 1
//    }
//}