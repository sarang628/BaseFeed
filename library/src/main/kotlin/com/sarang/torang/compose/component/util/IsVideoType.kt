package com.sarang.torang.compose.component.util

val String.isVideoType : Boolean get() {
    val lastIndex = this.lastIndexOf(".")
    if(lastIndex == -1)
        return false

    return this.substring(this.lastIndexOf(".")) == ".m3u8"
}