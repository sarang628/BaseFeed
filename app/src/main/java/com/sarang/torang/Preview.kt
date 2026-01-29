package com.sarang.torang

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.sarang.torang.compose.feed.PreviewFeed
import com.sarang.torang.compose.feed.internal.components.PreViewImagePager
import com.sryang.torang.ui.TorangTheme

@Preview
@Composable
fun ImagePagerScrollTest(){
    LazyColumn {
        items(100){
            PreViewImagePager()
        }
    }
}

@PreviewLightDark
@Composable
fun PreviewFeed1() {
    TorangTheme {
        Surface(Modifier.background(MaterialTheme.colorScheme.background)) {
            PreviewFeed()
        }
    }
}