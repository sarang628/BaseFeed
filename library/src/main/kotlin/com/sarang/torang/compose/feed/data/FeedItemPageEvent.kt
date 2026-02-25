package com.sarang.torang.compose.feed.data

data class FeedItemPageEvent(
    val page : Int,
    val isFirst : Boolean,
    val isLast : Boolean
)