package com.sarang.base_feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.torang_core.data.FeedUiState
import com.example.torang_core.data.model.Feed
import com.example.torang_core.data.model.ReviewImage
import com.google.android.material.tabs.TabLayoutMediator
import com.sarang.base_feed.databinding.ItemTimeLineBinding

/**
 * [ItemTimeLineBinding]
 */
class FeedVH(
    val itemTimeLineBinding: ItemTimeLineBinding,
    @Deprecated("뷰모델 의존성 제거 예정 아래 파라미터 사용 요청")
    val viewModel: BaseFeedViewModel? = null,
    private val lifeCycleOwner: LifecycleOwner,
    private val clickMenu: ((Feed) -> Unit)? = null,
    private val clickProfile: ((Int) -> Unit)? = null,
    private val clickRestaurant: ((Int) -> Unit)? = null,
    private val clickLike: ((View, Int) -> Unit)? = null,
    private val clickComment: ((Int) -> Unit)? = null,
    private val clickShare: ((Int) -> Unit)? = null,
    private val clickFavorite: ((View, Int) -> Unit)? = null,
    private val clickPicture: ((ReviewImage) -> Unit)? = null
) : RecyclerView.ViewHolder(itemTimeLineBinding.root) {

    init {
        itemTimeLineBinding.lifecycleOwner = lifeCycleOwner
        val timeLinePictureRvAdt = FeedPictureVpAdt(clickPicture)
        itemTimeLineBinding.viewpager.adapter = timeLinePictureRvAdt
    }

    fun setFeed(
                reviewId : Int,
                profilePicUrl : String,
                userId : Int,
                userName : String,
                rating : Float,
                restaurantName : String,
                restaurantId : Int,
                likeAmount : Int,
                contents : String,
                commentAnount : Int,
                feed : Feed
    ) {
        val feedUiState = FeedUiState(
            profileImageUrl = profilePicUrl,
            userName = userName,
            rating = rating,
            restaurantName = restaurantName,
            likeAmount = likeAmount,
            contents = contents,
            commentAmount = commentAnount,
            isLike = false,
            isFavorite = false,
            reviewImages = ArrayList(),
            userId = userId,
            reviewId = reviewId,
            restaurantId = restaurantId
        )

        itemTimeLineBinding.itemFeedTop.profileImageUrl = feedUiState.profileImageUrl
        itemTimeLineBinding.itemFeedTop.userName = feedUiState.userName
        itemTimeLineBinding.itemFeedTop.rating = feedUiState.rating
        itemTimeLineBinding.itemFeedTop.restaurantName = feedUiState.restaurantName
        itemTimeLineBinding.include.likeAmount = feedUiState.likeAmount
        itemTimeLineBinding.include.userName = feedUiState.userName
        itemTimeLineBinding.include.contents = feedUiState.contents
        itemTimeLineBinding.include.commentAmount = feedUiState.commentAmount
        itemTimeLineBinding.itemFeedTop.toolbar.setOnClickListener { clickMenu?.invoke(feed) }
        itemTimeLineBinding.itemFeedTop.imageView2.setOnClickListener { clickProfile?.invoke(feedUiState.userId) }
        itemTimeLineBinding.itemFeedTop.textView22.setOnClickListener { clickProfile?.invoke(feedUiState.userId) }
        itemTimeLineBinding.itemFeedTop.tvRestaurant.setOnClickListener { clickRestaurant?.invoke(feedUiState.restaurantId) }
        itemTimeLineBinding.include.btnLike.setOnClickListener { clickLike?.invoke(it, feedUiState.reviewId) }
        itemTimeLineBinding.include.button6.setOnClickListener { clickComment?.invoke(feedUiState.reviewId) }
        itemTimeLineBinding.include.button7.setOnClickListener { clickShare?.invoke(feedUiState.reviewId) }
        itemTimeLineBinding.include.button8.setOnClickListener { clickFavorite?.invoke(it, feedUiState.reviewId) }
    }

    fun setLike(isLike : Boolean){
        itemTimeLineBinding.include.isLike = isLike
    }

    fun setFavorite(isFavorite: Boolean){
        itemTimeLineBinding.include.isFavorite = isFavorite
    }

    fun setReviewImages(reviewImages : List<ReviewImage>){
        (itemTimeLineBinding.viewpager.adapter as FeedPictureVpAdt).setPictures(reviewImages)
        TabLayoutMediator(itemTimeLineBinding.include.tl, itemTimeLineBinding.viewpager) { _, _ -> }.attach()
    }


    companion object {
        @Deprecated("뷰모델 의존성 제거 예정 아래 파라미터 사용 요청")
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

        fun create(
            parent: ViewGroup,
            lifeCycleOwner: LifecycleOwner,
            clickMenu: ((Feed) -> Unit)? = null,
            clickProfile: ((Int) -> Unit)? = null,
            clickRestaurant: ((Int) -> Unit)? = null,
            clickLike: ((View, Int) -> Unit)? = null,
            clickComment: ((Int) -> Unit)? = null,
            clickShare: ((Int) -> Unit)? = null,
            clickFavorite: ((View, Int) -> Unit)? = null,
            clickPicture: ((ReviewImage) -> Unit)? = null
        ): FeedVH {
            return FeedVH(
                itemTimeLineBinding = ItemTimeLineBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ),
                lifeCycleOwner = lifeCycleOwner,
                clickMenu = clickMenu,
                clickProfile = clickProfile,
                clickRestaurant = clickRestaurant,
                clickLike = clickLike,
                clickComment = clickComment,
                clickShare = clickShare,
                clickFavorite = clickFavorite,
                clickPicture = clickPicture
            )
        }
    }
}