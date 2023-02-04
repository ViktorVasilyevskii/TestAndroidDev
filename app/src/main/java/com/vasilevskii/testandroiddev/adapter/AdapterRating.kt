package com.vasilevskii.testandroiddev.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vasilevskii.testandroiddev.R
import com.vasilevskii.testandroiddev.data.ItemRating
import com.vasilevskii.testandroiddev.databinding.ItemRatingBinding
import com.vasilevskii.testandroiddev.logic.ObservableProgress
import com.vasilevskii.testandroiddev.ui.fragment.TwoScreenFragment
import jp.wasabeef.picasso.transformations.MaskTransformation

class AdapterRating : ListAdapter<ItemRating, AdapterRating.RatingViewHolder>(RatingDiffCallback()) {

    inner class RatingViewHolder(
        val binding: ItemRatingBinding
    ): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingViewHolder {
        return RatingViewHolder(
            ItemRatingBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RatingViewHolder, position: Int) {
        val ratingItem = getItem(position)
        holder.binding.apply {
            Picasso.get()
                .load(ratingItem.image)
                .resize(imageCard.context.resources.getDimension(R.dimen.item_rating_image_width).toInt(),
                    imageCard.context.resources.getDimension(R.dimen.item_rating_image_height).toInt())
                .transform(MaskTransformation(imageCard.context, R.drawable.mask_transformation_card_image_circle))
                .into(imageCard)

            textCard.text = ratingItem.title
        }
    }


}