package com.sarang.base_feed

data class ItemTopUiState(
    val a: Int
)


class CustomClick<P>(val listener: ((P) -> Unit)) {
    fun invoke(p: P) {
        listener.invoke(p)
    }
}