package com.sarang.base_feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import com.sarang.base_feed.databinding.ItemTimeLineBinding
import com.example.torang_core.data.model.Feed

/**
 * [ItemTimeLineBinding]
 */
class FeedVH(
    val itemTimeLineBinding: ItemTimeLineBinding,
    val viewModel: BaseFeedViewModel,
    private val lifeCycleOwner: LifecycleOwner
) : RecyclerView.ViewHolder(itemTimeLineBinding.root) {

    init {
        itemTimeLineBinding.viewModel = viewModel
        itemTimeLineBinding.lifecycleOwner = lifeCycleOwner
        val timeLinePictureRvAdt = FeedPictureVpAdt(viewModel)
        itemTimeLineBinding.viewpager.adapter = timeLinePictureRvAdt
    }



    fun setFeed(feed: Feed) {
        itemTimeLineBinding.feed = feed

        /** 리뷰 사진 설정 */
        viewModel.getReviewImages(feed.review_id).observe(lifeCycleOwner) {
            (itemTimeLineBinding.viewpager.adapter as FeedPictureVpAdt).setPictures(it)
            TabLayoutMediator(
                itemTimeLineBinding.include.tl,
                itemTimeLineBinding.viewpager
            ) { _, _ ->

            }.attach()
        }

        viewModel.isLike(feed.review_id).observe(lifeCycleOwner) {
            itemTimeLineBinding.like = it
        }

        viewModel.isFavorite(feed.review_id).observe(lifeCycleOwner) {
            itemTimeLineBinding.favorite = it
        }


    }

    companion object {
        fun create(
            parent: ViewGroup,
            baseFeedViewModel: BaseFeedViewModel,
            lifeCycleOwner: LifecycleOwner
        ): FeedVH {
            return FeedVH(
                itemTimeLineBinding = ItemTimeLineBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ), baseFeedViewModel, lifeCycleOwner
            )
        }
    }
}