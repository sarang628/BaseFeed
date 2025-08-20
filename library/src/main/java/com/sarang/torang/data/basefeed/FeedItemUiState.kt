package com.sarang.torang.data.basefeed

import android.util.Log
import java.text.SimpleDateFormat

data class FeedItemUiState(
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
    val createDate: String
){
    companion object {
        fun empty() : FeedItemUiState = FeedItemUiState(user = User.empty(), restaurant = Restaurant.empty(), reviewId = 0, rating = 0f, reviewImages = listOf(), contents = "", comments = null, likeAmount = 0, commentAmount = 0, isLike = false, isFavorite = false, createDate = "")
    }
}

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


fun testReviewData(): FeedItemUiState {
    return FeedItemUiState(
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
            name = "name",
            profilePictureUrl = "1/2023-09-14/10_44_39_302.jpeg"
        ),
        restaurant = Restaurant(
            restaurantId = 1,
            restaurantName = "restaurantName",
        ),
        rating = 3.5f,
        contents = "contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents contents ",
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
        createDate = ""
    )
}

fun testReviewList(): List<FeedItemUiState> {
    return ArrayList<FeedItemUiState>().apply {
        add(testReviewData())
        add(testReviewData())
        add(testReviewData())
        add(testReviewData())
        add(testReviewData())
        add(testReviewData())
        add(testReviewData())
    }
}