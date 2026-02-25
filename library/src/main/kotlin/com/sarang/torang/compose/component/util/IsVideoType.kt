package com.sarang.torang.compose.component.util

val String.isVideoType : Boolean get() = this.substring(this.lastIndexOf(".")) == ".m3u8"