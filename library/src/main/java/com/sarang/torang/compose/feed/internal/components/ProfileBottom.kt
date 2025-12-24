package com.sarang.torang.compose.feed.internal.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sarang.torang.data.basefeed.FeedItemClickEvents
import com.sarang.torang.data.basefeed.FeedItemUiState

@Composable
fun ProfileBottom(modifier : Modifier                       = Modifier,
                  uiState: FeedItemUiState                  = FeedItemUiState(),
                  feedItemClickEvents: FeedItemClickEvents  = FeedItemClickEvents(),
                  favoriteColor: Color                      = MaterialTheme.colorScheme.primary){
    Box(modifier = modifier.fillMaxWidth()){
        Row(Modifier.align(Alignment.CenterStart), verticalAlignment = Alignment.CenterVertically)
        {
            Spacer(Modifier.width(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Like(isLike      = uiState.isLike,
                    onLike      = feedItemClickEvents.onLike,
                    animation   = uiState.isLogin)
                if(uiState.likeAmount > 0)
                    LikeCount(modifier = Modifier.padding(start = 2.dp),
                        count   = uiState.likeAmount        ,
                        onLikes = feedItemClickEvents.onLike)
            }
            Spacer(Modifier.width(12.dp))
            Comment                 (onComment = feedItemClickEvents.onComment)
            Spacer(Modifier.width(12.dp))
            Share                   (onShare = feedItemClickEvents.onShare)
        }

        Favorite(modifier   = Modifier.align(Alignment.CenterEnd),
            isFavorite = uiState.isFavorite,
            onFavorite = feedItemClickEvents.onFavorite,
            color      = favoriteColor)
    }
}

@Preview(showBackground = true, backgroundColor = 0x111111)
@Composable
fun PreviewProfileBottom(){
    ProfileBottom()
}