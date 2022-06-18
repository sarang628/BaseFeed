package com.sarang.base_feed

import androidx.recyclerview.widget.RecyclerView
import com.example.torang_core.data.model.ReviewImage
import com.sarang.base_feed.databinding.FragmentSimplePictureBinding
import com.sarang.base_feed.databinding.LayoutFeedVideoBinding

class FeedVideoVH(
    private val binding: LayoutFeedVideoBinding,
    val viewModel: BaseFeedViewModel
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.viewModel = viewModel
    }

    fun setPicture(image: ReviewImage) {
        binding.picture = image
        binding.tvv.setUrl(image.picture_url)
    }
}