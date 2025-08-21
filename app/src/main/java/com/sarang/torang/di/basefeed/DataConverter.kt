package com.sarang.torang.di.basefeed

import com.sarang.torang.BuildConfig
import com.sarang.torang.data.basefeed.FeedItemUiState
import com.sarang.torang.data.entity.ReviewAndImageEntity

fun ReviewAndImageEntity.toReview(): FeedItemUiState {
    return FeedItemUiState(
        reviewImages = this.images.map { BuildConfig.REVIEW_IMAGE_SERVER_URL + it.pictureUrl },
        userName = this.review.userName,
        profilePictureUrl = BuildConfig.PROFILE_IMAGE_SERVER_URL + this.review.profilePicUrl,
        restaurantName = this.review.restaurantName ?: "",
        rating = this.review.rating,
        likeAmount = this.review.likeAmount,
        commentAmount = this.review.commentAmount,
        comments = listOf(),
        isLike = this.like != null,
        isFavorite = this.favorite != null,
        contents = this.review.contents,
        createDate = this.review.createDate
    )
}