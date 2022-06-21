package com.sarang.base_feed

import androidx.recyclerview.widget.RecyclerView
import com.example.torang_core.data.model.ReviewImage
import com.sarang.base_feed.databinding.FragmentSimplePictureBinding

/**
 * [FragmentSimplePictureBinding]
 */
class FeedPictureVH(
    private val binding: FragmentSimplePictureBinding,
    private val clickPicture : ((ReviewImage) -> Unit)? = null
) : RecyclerView.ViewHolder(binding.root) {
    fun setPicture(image: ReviewImage) {
        binding.picture = image

        binding.ivPicture.setOnClickListener {
            clickPicture?.invoke(image)
        }
    }
}