package com.sarang.torang.compose.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sarang.torang.compose.component.type.FeedImageLoaderData
import com.sarang.torang.compose.component.type.LocalFeedImageLoader
import com.sarang.torang.compose.component.util.nonEffectClickable

@Preview
@Composable
fun ProfileImage(modifier : Modifier = Modifier, onProfile: () -> Unit = {}, url : String = ""){
    Box(modifier = modifier) {
        LocalFeedImageLoader.current.invoke(
            FeedImageLoaderData(
                modifier        = Modifier.size(40.dp)
                    .padding(6.dp)
                    .nonEffectClickable(onProfile)
                    .border(width = 0.5.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(20.dp))
                    .clip(RoundedCornerShape(20.dp)),
                url             = url,
                progressSize    = 20.dp,
                errorIconSize   = 20.dp,
                contentScale    = ContentScale.Crop,
                height          = 40.dp
            )
        )
    }
}