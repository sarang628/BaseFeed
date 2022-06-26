package com.sarang.base_feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.torang_core.data.model.Favorite
import com.example.torang_core.data.model.Feed
import com.example.torang_core.data.model.Like
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

    fun setFeed(feed: Feed) {


        //TODO livedata merge 해서 uistateBottom 완성하기

        itemTimeLineBinding.itemFeedTop.uiState = FeedTopUiState(
            profileImageUrl = feed.profile_pic_url,
            userName = feed.userName,
            restaurantName = feed.restaurantName,
            rating = feed.rating,
            clickProfileImage = clickProfile,
            clickUserName = clickProfile,
            clickRestaurant = clickRestaurant
        )

        /** 리뷰 사진 설정 */
        getReviewImage?.invoke(feed.review_id)?.observe(lifeCycleOwner) {
            (itemTimeLineBinding.viewpager.adapter as FeedPictureVpAdt).setPictures(it)
            TabLayoutMediator(
                itemTimeLineBinding.include.tl,
                itemTimeLineBinding.viewpager
            ) { _, _ ->

            }.attach()
        }

        itemTimeLineBinding.itemFeedTop.toolbar.setOnClickListener {
            clickMenu?.invoke(feed)
        }

        itemTimeLineBinding.itemFeedTop.imageView2.setOnClickListener {
            clickProfile?.invoke(feed.userId)
        }

        itemTimeLineBinding.itemFeedTop.textView22.setOnClickListener {
            clickProfile?.invoke(feed.userId)
        }

        itemTimeLineBinding.itemFeedTop.tvRestaurant.setOnClickListener {
            feed.restaurantId?.let { clickRestaurant?.invoke(it) }
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
            clickPicture: ((ReviewImage) -> Unit)? = null,
            getReviewImage: ((Int) -> LiveData<List<ReviewImage>>)? = null,
            getLike: ((Int) -> LiveData<Like>)? = null,
            getFavorite: ((Int) -> LiveData<Favorite>)? = null
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
                clickPicture = clickPicture,
                getReviewImage = getReviewImage,
                getLike = getLike,
                getFavorite = getFavorite
            )
        }
    }
}