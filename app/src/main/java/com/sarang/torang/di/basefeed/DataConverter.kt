package com.sarang.torang.di.basefeed

import com.sarang.torang.BuildConfig
import com.sarang.torang.data.basefeed.Restaurant
import com.sarang.torang.data.basefeed.Review
import com.sarang.torang.data.basefeed.User
import com.sryang.torang_repository.data.entity.ReviewAndImageEntity

fun Feed.review(): Review {
    return Review(
        reviewId = this.reviewId,
        reviewImages = this.reviewImages,
        user = User(
            name = this.name,
            profilePictureUrl = BuildConfig.PROFILE_IMAGE_SERVER_URL + this.profilePictureUrl,
            userId = this.userId
        ),
        restaurant = Restaurant(
            restaurantId = this.restaurantId,
            restaurantName = this.restaurantName
        ),
        rating = this.rating,
        likeAmount = this.likeAmount,
        commentAmount = this.commentAmount,
        comments = null,
        isLike = this.isLike,
        isFavorite = this.isFavorite,
        contents = this.contents,
        onShare = {},
        onRestaurant = {},
        onProfile = {},
        onName = {},
        onMenu = {},
        onLike = {},
        onImage = {},
        onFavorite = {},
        onComment = {}
    )
}

fun ReviewAndImageEntity.toFeedData(): Feed {
    return Feed(
        reviewId = this.review.reviewId,
        userId = this.review.userId,
        name = this.review.userName,
        restaurantName = this.review.restaurantName,
        rating = this.review.rating,
        profilePictureUrl = this.review.profilePicUrl,
        likeAmount = this.review.likeAmount,
        commentAmount = this.review.commentAmount,
        isLike = this.like != null,
        isFavorite = this.favorite != null,
        contents = this.review.contents,
        reviewImages = this.images.map { BuildConfig.REVIEW_IMAGE_SERVER_URL + it.pictureUrl },
        restaurantId = this.review.restaurantId
    )
}

data class Feed(
    val reviewId: Int,
    val restaurantId: Int,
    val userId: Int,
    val name: String,
    val restaurantName: String,
    val rating: Float,
    val profilePictureUrl: String,
    val likeAmount: Int,
    val commentAmount: Int,
    val isLike: Boolean,
    val isFavorite: Boolean,
    val contents: String,
    val reviewImages: List<String> = ArrayList()
)

val Feed.visibleLike: Boolean get() = this.likeAmount > 0