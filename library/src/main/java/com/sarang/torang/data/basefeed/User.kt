package com.sarang.torang.data.basefeed

data class User(
    val userId: Int,
    val name: String,
    val profilePictureUrl: String
) {
    companion object {
        fun empty(): User = User(userId = 0, name = "", profilePictureUrl = "")
    }
}
