package com.example.jetpackdemo.day20220121_flexbox_layout

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.jetpackdemo.R
import com.google.android.flexbox.*
import kotlinx.android.synthetic.main.activity_main36.*
import com.google.android.flexbox.FlexboxLayoutManager
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import java.util.*


/**
 * https://www.jianshu.com/p/b3a9c4a99053
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main36)


        val layoutManager = FlexboxLayoutManager(this)
        layoutManager.apply {
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
            alignItems = AlignItems.STRETCH
            justifyContent = JustifyContent.FLEX_START
        }
        rcv.layoutManager = layoutManager


        val layoutManager2 = FlexboxLayoutManager(this)
        layoutManager2.apply {
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
            alignItems = AlignItems.STRETCH
            justifyContent = JustifyContent.FLEX_START
        }
        rcv2.layoutManager = layoutManager2

        val list = mutableListOf(
            "娱乐新闻", "体育新闻", "娱乐", "送挡风负面问额2房", "哈哈库", "拖孩吃饭新闻发生的",
            "娱乐新闻", "体育新闻", "娱乐", "送挡风负面问额2房", "哈哈库", "拖孩吃饭新闻发生的",
            "娱乐新闻", "体育新闻", "娱乐", "送挡风负面问额2房", "哈哈库", "拖孩吃饭新闻发生的",
            "娱乐新闻", "体育新闻", "娱乐", "送挡风负面问额2房", "哈哈库", "拖孩吃饭新闻发生的",
            "娱乐新闻", "体育新闻", "娱乐", "送挡风负面问额2房", "哈哈库", "拖孩吃饭新闻发生的",
            "娱乐新闻", "体育新闻", "娱乐", "送挡风负面问额2房", "哈哈库", "拖孩吃饭新闻发生的",
            "娱乐新闻", "体育新闻", "娱乐", "送挡风负面问额2房", "哈哈库", "拖孩吃饭新闻发生的",
        )

        val mDatas = mutableListOf<Int>()

        mDatas.add(R.drawable.cat_1)
        mDatas.add(R.drawable.cat_2)
        mDatas.add(R.drawable.cat_3)
        mDatas.add(R.drawable.cat_4)
        mDatas.add(R.drawable.cat_5)
        mDatas.add(R.drawable.cat_6)
        mDatas.add(R.drawable.cat_7)
        mDatas.add(R.drawable.cat_8)
        mDatas.add(R.drawable.cat_9)
        mDatas.add(R.drawable.bleach_9)
        mDatas.add(R.drawable.cat_10)
        mDatas.add(R.drawable.cat_11)
        mDatas.add(R.drawable.cat_12)
        mDatas.add(R.drawable.cat_13)
        mDatas.add(R.drawable.cat_14)
        mDatas.add(R.drawable.bleach_16)
        mDatas.add(R.drawable.bleach_15)
        mDatas.add(R.drawable.cat_15)
        mDatas.add(R.drawable.bleach_14)
        mDatas.add(R.drawable.cat_16)
        mDatas.add(R.drawable.cat_17)
        mDatas.add(R.drawable.bleach_15)
        mDatas.add(R.drawable.cat_18)
        mDatas.add(R.drawable.cat_19)



        rcv.adapter =
            object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_flexbox, list) {
                override fun convert(holder: BaseViewHolder, item: String) {
                    val textView1 = holder.getView<TextView>(R.id.textView1)
                    textView1.text = item
                    val lp: ViewGroup.LayoutParams = textView1.layoutParams
                    if (lp is FlexboxLayoutManager.LayoutParams) {
                        lp.flexGrow = 1.0f
                    }
                }
            }

        rcv2.adapter =
            object: BaseQuickAdapter<Int,BaseViewHolder>(R.layout.flexbox_item,mDatas){
                override fun convert(holder: BaseViewHolder, position: Int) {
                    val pos: Int = position % mDatas.size
                    val drawable: Drawable? = ResourcesCompat.getDrawable(
                        holder.itemView.context.resources,
                        mDatas[pos], null
                    )
                    val mImageView = holder.getView(R.id.imageview) as ImageView
                    mImageView.setImageDrawable(drawable)

                    val lp = mImageView.layoutParams
                    if (lp is FlexboxLayoutManager.LayoutParams) {
                        val random = Random()
                        //随机宽度
                        val width = random.nextInt(300) + 200
                        lp.width = width
                        lp.flexGrow = 1.0f
                    }
                }
            }
    }

}