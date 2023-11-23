package com.sryang.base.feed.data

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
)


fun testReviewData(): Review {
    return Review(
        reviewId = 0,
        reviewImages = ArrayList<String>().apply {
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
            add("333/333/2023-06-16/12_52_44_122.jpeg")
        },
        user = User(
            userId = 0,
            name = "name",
            profilePictureUrl = "profilePictureUrl"
        ),
        restaurant = Restaurant(
            restaurantId = 1,
            restaurantName = "restaurantName",
        ),
        rating = 3.5f,
        contents = "contents",
        comments = ArrayList<Comment>().apply {
            add(Comment("author", "comment"))
        },
        commentAmount = 0,
        isLike = false,
        isFavorite = false,
        likeAmount = 100
    )
}