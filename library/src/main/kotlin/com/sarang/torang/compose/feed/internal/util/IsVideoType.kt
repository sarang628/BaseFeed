package com.sarang.torang.compose.feed.internal.util

val String.isVideoType : Boolean get() = this.substring(this.lastIndexOf(".")) == ".m3u8"