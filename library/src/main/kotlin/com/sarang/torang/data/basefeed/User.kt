package com.sarang.torang.data.basefeed

data class User(
    val name: String,
    val profilePictureUrl: String
) {
    companion object {
        fun empty(): User = User(name = "", profilePictureUrl = "")
    }
}
