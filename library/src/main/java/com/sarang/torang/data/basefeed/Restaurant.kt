package com.sarang.torang.data.basefeed

data class Restaurant(
    val restaurantId: Int,
    val restaurantName: String
){
    companion object{
        fun empty() : Restaurant = Restaurant(restaurantId = 0, restaurantName = "")
    }
}
