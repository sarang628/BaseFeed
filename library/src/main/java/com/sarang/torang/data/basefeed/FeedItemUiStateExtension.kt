package com.sarang.torang.data.basefeed

import android.util.Log
import java.text.SimpleDateFormat

val FeedItemUiState.Companion.empty get() =  FeedItemUiState(user = User.empty(), restaurant = Restaurant.empty(), reviewId = 0, rating = 0f, reviewImages = listOf(), contents = "", comments = null, likeAmount = 0, commentAmount = 0, isLike = false, isFavorite = false, createDate = "")

val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
val sdf1 = SimpleDateFormat("MMM dd, YYYY")
fun FeedItemUiState.formatedDate(): String {
    return createDate.formatedDate()
}

fun String.formatedDate(): String {
    var result = ""
    try {
        result = sdf1.format(sdf.parse(this))
    } catch (e: Exception) {
        Log.e("_Review", e.toString())
    } finally {
        return result
    }
}


val FeedItemUiState.Companion.Sample: FeedItemUiState  get() =
    FeedItemUiState(
        reviewId = 0,
        reviewImages = ArrayList<String>().apply {
            add("https://assets.simpleviewinc.com/simpleview/image/upload/c_limit,h_1200,q_75,w_1200/v1/clients/phoenix/Kai_03_e0cec566-c290-4b68-8e7d-50ecda61055e.jpg")
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
            name = "peter pan",
            profilePictureUrl = "1/2023-09-14/10_44_39_302.jpeg"
        ),
        restaurant = Restaurant(
            restaurantId = 1,
            restaurantName = "Gorden Ramsey Burger",
        ),
        rating = 3.5f,
        contents = "\uD83C\uDF54 Gordon Ramsay’s Burger Review If you’re a burger lover, Gordon Ramsay’s burger is definitely something you want to try! Here’s my take on the iconic burger that combines classic flavors with a twist of Ramsay’s signature flair.",
        comments = ArrayList<Comment>().apply {
//            add(Comment("author", "comment"))
//            add(Comment("author", "comment"))
//            add(Comment("author", "comment"))
//            add(Comment("author", "comment"))
//            add(Comment("author", "comment"))
//            add(Comment("author", "comment"))
//            add(Comment("author", "comment"))
        },
        commentAmount = 10,
        isLike = false,
        isFavorite = false,
        likeAmount = 100,
        createDate = "2025-08-21 06:17:20"
    )

fun testReviewList(): List<FeedItemUiState> {
    return ArrayList<FeedItemUiState>().apply {
        add(FeedItemUiState.Sample)
        add(FeedItemUiState.Sample)
        add(FeedItemUiState.Sample)
        add(FeedItemUiState.Sample)
        add(FeedItemUiState.Sample)
        add(FeedItemUiState.Sample)
        add(FeedItemUiState.Sample)
    }
}