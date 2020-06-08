package com.example.jetpackdemo.day13_viewpager2_demo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jetpackdemo.R
import com.example.jetpackdemo.day11_image_lang.Hit
import kotlinx.android.synthetic.main.photo_pager_view.view.*

class PagerPhotoListAdapter : ListAdapter<Hit, PagerPhotoViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<Hit>() {
        override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem.photoId == newItem.photoId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerPhotoViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.photo_pager_view, parent, false).apply {
            parent.requestDisallowInterceptTouchEvent(true)
            return PagerPhotoViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: PagerPhotoViewHolder, position: Int) {
        Glide.with(holder.itemView)
                .load(getItem(position).previewUrl)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.itemView.pagerPhoto)
    }


}


class PagerPhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}