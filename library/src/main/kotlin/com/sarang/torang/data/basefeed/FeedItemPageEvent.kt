package com.sarang.torang.data.basefeed

data class FeedItemPageEvent(
    val page : Int,
    val isFirst : Boolean,
    val isLast : Boolean
)