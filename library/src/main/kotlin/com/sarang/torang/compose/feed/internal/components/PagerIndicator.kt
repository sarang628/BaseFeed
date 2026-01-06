package com.sarang.torang.compose.feed.internal.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PagerIndicator(modifier     : Modifier      = Modifier,
                   pagerState   : PagerState    = rememberPagerState { 0 })  {
    Row(modifier                = modifier,
        horizontalArrangement   = Arrangement.Center,
        verticalAlignment       = Alignment.CenterVertically) {
        repeat(pagerState.pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration)
                            Color.White
                        else
                            Color.LightGray
            Box(modifier = Modifier.padding(2.dp)
                                   .clip(CircleShape)
                                   .background(color)
                                   .size(5.dp))
        }
    }
}