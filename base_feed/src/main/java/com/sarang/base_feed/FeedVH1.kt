package com.sarang.base_feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.torang_core.data.model.Favorite
import com.example.torang_core.data.model.Feed
import com.example.torang_core.data.model.Like
import com.example.torang_core.data.model.ReviewImage
import com.google.android.material.tabs.TabLayoutMediator
import com.sarang.base_feed.databinding.ItemFeedTop1Binding
import com.sarang.base_feed.databinding.ItemTimeLine1Binding
import com.sarang.base_feed.databinding.ItemTimeLineBinding

/**
 * [ItemTimeLineBinding]
 */
class FeedVH1(
    val itemTimeLineBinding: ItemTimeLine1Binding,
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
    private val clickPicture: ((ReviewImage) -> Unit)? = null,
    private val getReviewImage: ((Int) -> LiveData<List<ReviewImage>>)? = null,
    private val getLike: ((Int) -> LiveData<Like>)? = null,
    private val getFavorite: ((Int) -> LiveData<Favorite>)? = null
) : RecyclerView.ViewHolder(itemTimeLineBinding.root) {

    init {
        itemTimeLineBinding.lifecycleOwner = lifeCycleOwner
        val timeLinePictureRvAdt = FeedPictureVpAdt(clickPicture)
        itemTimeLineBinding.viewpager.adapter = timeLinePictureRvAdt
    }

    fun setFeed(
        feed: Feed
    ) {
        itemTimeLineBinding.feed = feed
        itemTimeLineBinding.itemFeedTop

        /** 리뷰 사진 설정 */
        getReviewImage?.invoke(feed.review_id)?.observe(lifeCycleOwner) {
            (itemTimeLineBinding.viewpager.adapter as FeedPictureVpAdt).setPictures(it)
            TabLayoutMediator(
                itemTimeLineBinding.include.tl,
                itemTimeLineBinding.viewpager
            ) { _, _ ->

            }.attach()
        }

        getLike?.invoke(feed.review_id)?.observe(lifeCycleOwner) {
            itemTimeLineBinding.like = it
        }

        getFavorite?.invoke(feed.review_id)?.observe(lifeCycleOwner) {
            itemTimeLineBinding.favorite = it
        }

        itemTimeLineBinding.include.btnLike.setOnClickListener {
            clickLike?.invoke(it, feed.review_id)
        }

        itemTimeLineBinding.include.button6.setOnClickListener {
            clickComment?.invoke(feed.review_id)
        }

        itemTimeLineBinding.include.button7.setOnClickListener {
            clickShare?.invoke(feed.review_id)
        }

        itemTimeLineBinding.include.button8.setOnClickListener {
            clickFavorite?.invoke(it, feed.review_id)
        }

        itemTimeLineBinding.itemFeedTop.setData(
            review_id = feed.review_id,
            userId = feed.userId,
            restaurantId = feed.restaurantId,
            profile_pic_url = feed.profile_pic_url,
            userName = feed.userName,
            restaurantName = feed.restaurantName,
            rating = feed.rating,
            menuClickListener = CustomClick { clickMenu?.invoke(feed) },
            profileClickListener = CustomClick { clickProfile?.invoke(it) },
            clickRestaurantListener = CustomClick { clickRestaurant?.invoke(it) }
        )
    }

    companion object {
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
            clickPicture: ((ReviewImage) -> Unit)? = null,
            getReviewImage: ((Int) -> LiveData<List<ReviewImage>>)? = null,
            getLike: ((Int) -> LiveData<Like>)? = null,
            getFavorite: ((Int) -> LiveData<Favorite>)? = null
        ): FeedVH1 {
            return FeedVH1(
                itemTimeLineBinding = ItemTimeLine1Binding.inflate(
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
                clickPicture = clickPicture,
                getReviewImage = getReviewImage,
                getLike = getLike,
                getFavorite = getFavorite
            )
        }
    }
}

fun ItemFeedTop1Binding.setData(
    review_id: Int? = null,
    userId: Int? = null,
    restaurantId: Int? = null,
    profile_pic_url: String? = null,
    userName: String? = null,
    restaurantName: String? = null,
    rating: Float? = null,
    menuClickListener: CustomClick<Int>,
    profileClickListener: CustomClick<Int>,
    clickRestaurantListener: CustomClick<Int>
) {
    this.reviewId = review_id
    this.userId = userId
    this.restaurantId = restaurantId
    this.profilePicUrl = profile_pic_url
    this.userName = userName
    this.restaurantName = restaurantName
    this.rating = rating
    this.menuClickListener = menuClickListener
    this.profileClickListener = profileClickListener
    this.clickRestaurantListener = clickRestaurantListener
}