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
fun FeedBottom(modifier              : Modifier             = Modifier,
               isLike                : Boolean              = false,
               isLogin               : Boolean              = false,
               isFavorite            : Boolean              = false,
               likeAmount            : Int                  = 0,
               feedItemClickEvents   : FeedItemClickEvents  = FeedItemClickEvents(),
               favoriteColor         : Color                = MaterialTheme.colorScheme.primary){
    Box(modifier = modifier.fillMaxWidth()
                           .padding(all = 8.dp)){
        Row(modifier            = Modifier.align(Alignment.CenterStart),
            verticalAlignment   = Alignment.CenterVertically)
        {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Like(isLike      = isLike,
                     onLike      = feedItemClickEvents.onLike,
                     animation   = isLogin)
                if(likeAmount > 0)
                    LikeCount(modifier = Modifier.padding(start = 2.dp),
                              count    = likeAmount        ,
                              onLikes  = feedItemClickEvents.onLike)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Comment(onComment = feedItemClickEvents.onComment)
            Spacer(modifier = Modifier.width(12.dp))
            Share(onShare = feedItemClickEvents.onShare)
        }

        Favorite(modifier   = Modifier.align(Alignment.CenterEnd),
                 isFavorite = isFavorite,
                 onFavorite = feedItemClickEvents.onFavorite,
                 color      = favoriteColor)
    }
}

@Preview(showBackground = true, backgroundColor = 0x111111)
@Composable
fun PreviewFeedBottom(){
    FeedBottom()
}