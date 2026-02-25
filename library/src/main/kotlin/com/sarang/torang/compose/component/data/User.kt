package com.sarang.torang.compose.component.data

data class User(
    val name: String,
    val profilePictureUrl: String
) {
    companion object {
        fun empty(): User = User(name = "", profilePictureUrl = "")
    }
}