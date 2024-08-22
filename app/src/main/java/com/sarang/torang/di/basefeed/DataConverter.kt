package com.sarang.torang.di.basefeed

import com.sarang.torang.BuildConfig
import com.sarang.torang.data.basefeed.Restaurant
import com.sarang.torang.data.basefeed.Review
import com.sarang.torang.data.basefeed.User
import com.sarang.torang.data.entity.ReviewAndImageEntity

fun ReviewAndImageEntity.toReview(): Review {
    return Review(
        reviewId = this.review.reviewId,
        reviewImages = this.images.map { BuildConfig.REVIEW_IMAGE_SERVER_URL + it.pictureUrl },
        user = User(
            name = this.review.userName,
            profilePictureUrl = BuildConfig.PROFILE_IMAGE_SERVER_URL + this.review.profilePicUrl,
            userId = this.review.userId
        ),
        restaurant = Restaurant(
            restaurantId = this.review.restaurantId ?: 0,
            restaurantName = this.review.restaurantName ?: ""
        ),
        rating = this.review.rating,
        likeAmount = this.review.likeAmount,
        commentAmount = this.review.commentAmount,
        comments = null,
        isLike = this.like != null,
        isFavorite = this.favorite != null,
        contents = this.review.contents,
        createDate = this.review.createDate
    )
}