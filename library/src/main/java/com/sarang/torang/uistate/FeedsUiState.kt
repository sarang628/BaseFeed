package com.sarang.torang.uistate

import com.sarang.torang.data.basefeed.Review

sealed interface FeedsUiState {
    object Loading : FeedsUiState
    object Empty : FeedsUiState
    data class Success(val reviews: List<Review>) : FeedsUiState
    data class Error(val message: String) : FeedsUiState
}