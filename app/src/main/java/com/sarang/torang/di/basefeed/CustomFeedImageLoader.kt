package com.sarang.torang.di.basefeed

import com.sarang.torang.compose.feed.internal.components.FeedImageLoader
import com.sarang.torang.di.image.provideZoomableTorangAsyncImage

val CustomFeedImageLoader: FeedImageLoader = provideZoomableTorangAsyncImage()