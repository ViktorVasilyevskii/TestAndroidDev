package com.vasilevskii.testandroiddev.adapter

import androidx.recyclerview.widget.DiffUtil
import com.vasilevskii.testandroiddev.data.ItemRating

class RatingDiffCallback : DiffUtil.ItemCallback<ItemRating>() {

    override fun areItemsTheSame(oldItem: ItemRating, newItem: ItemRating): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: ItemRating, newItem: ItemRating): Boolean {
        return oldItem.image == newItem.image
    }

}