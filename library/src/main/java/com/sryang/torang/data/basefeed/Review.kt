package com.sryang.torang.data.basefeed

import android.util.Log

const val TAG = "_Review"

data class Review(
    val user: User,
    val restaurant: Restaurant,
    val reviewId: Int,
    val rating: Float,
    val reviewImages: List<String> = ArrayList(),
    val contents: String,
    val comments: List<Comment>?,
    val likeAmount: Int,
    val commentAmount: Int,
    val isLike: Boolean,
    val isFavorite: Boolean,
    val onProfile: (() -> Unit),
    val onLike: (() -> Unit),
    val onComment: (() -> Unit),
    val onShare: (() -> Unit),
    val onFavorite: (() -> Unit),
    val onMenu: (() -> Unit),
    val onName: (() -> Unit),
    val onRestaurant: (() -> Unit),
    val onImage: ((Int) -> Unit),
)


fun testReviewData(): Review {
    return Review(
        reviewId = 0,
        reviewImages = ArrayList<String>().apply {
            add("http://sarang628.iptime.org:89/review_images/333/333/2023-06-16/12_52_44_122.jpeg")
            add("333/333/2023-06-16/12_52_44_122.jpeg")
            add("333/333/2023-06-16/12_52_44_122.jpeg")
            add("333/333/2023-06-16/12_52_44_122.jpeg")
            add("333/333/2023-06-16/12_52_44_122.jpeg")
            add("333/333/2023-06-16/12_52_44_122.jpeg")
            add("333/333/2023-06-16/12_52_44_122.jpeg")
            add("333/333/2023-06-16/12_52_44_122.jpeg")
            add("333/333/2023-06-16/12_52_44_122.jpeg")
            add("333/333/2023-06-16/12_52_44_122.jpeg")
            add("333/333/2023-06-16/12_52_44_122.jpeg")
        },
        user = User(
            userId = 0,
            name = "name",
            profilePictureUrl = "1/2023-09-14/10_44_39_302.jpeg"
        ),
        restaurant = Restaurant(
            restaurantId = 1,
            restaurantName = "restaurantName",
        ),
        rating = 3.5f,
        contents = "" +
                "contentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontents" +
                "contentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontentscontents" +
                "",
        comments = ArrayList<Comment>().apply {
            add(Comment("author", "comment"))
            add(Comment("author", "comment"))
            add(Comment("author", "comment"))
            add(Comment("author", "comment"))
            add(Comment("author", "comment"))
            add(Comment("author", "comment"))
            add(Comment("author", "comment"))
        },
        commentAmount = 10,
        isLike = false,
        isFavorite = false,
        likeAmount = 100,
        onComment = {},
        onFavorite = { Log.d(TAG, "onFavorite") },
        onImage = {},
        onLike = {},
        onMenu = {},
        onName = {},
        onProfile = {},
        onRestaurant = {},
        onShare = {}
    )
}

fun testReviewList(): List<Review> {
    return ArrayList<Review>().apply {
        add(testReviewData())
        add(testReviewData())
        add(testReviewData())
        add(testReviewData())
        add(testReviewData())
        add(testReviewData())
        add(testReviewData())
    }
}