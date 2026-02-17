package com.sarang.torang.compose.feed.internal.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeOff
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sarang.torang.compose.feed.internal.util.nonEffectClickable
import com.sarang.torang.data.basefeed.FeedBottomEvents

@Composable
fun FeedBottom(modifier              : Modifier             = Modifier,
               uiState               : FeedBottomUiState    = FeedBottomUiState(),
               events                : FeedBottomEvents     = remember { FeedBottomEvents() },
               isVideo               : Boolean              = false,
               favoriteColor         : Color                = MaterialTheme.colorScheme.primary, ){
    Column(modifier = modifier.fillMaxWidth()
                           .nonEffectClickable()){
        if(isVideo){
            Box(Modifier.fillMaxWidth()){
                Volume(modifier = Modifier.align(Alignment.CenterEnd),
                       isMute   = uiState.isVolumeOff,
                       onVolume = events.onVolume)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
        Box(Modifier.fillMaxWidth()){
            Row(modifier            = Modifier.align(Alignment.CenterStart),
                verticalAlignment   = Alignment.CenterVertically) {
                Like(modifier   = Modifier.testTag("btnLike"),
                     likeAmount  = uiState.likeAmount,
                     isLike      = uiState.isLike,
                     animation   = uiState.isLogin,
                     onLike      = events.onLike)
                Spacer(modifier = Modifier.width(12.dp))
                CommentIcon(modifier  = Modifier.testTag("btnComment"),
                            onComment = events.onComment)
                Spacer(modifier = Modifier.width(12.dp))
                Share(modifier = Modifier.testTag("btnShare"),
                      onShare  = events.onShare)
            }

            Favorite(modifier   = Modifier.align(Alignment.CenterEnd),
                     isFavorite = uiState.isFavorite,
                     onFavorite = events.onFavorite,
                     color      = favoriteColor)
        }
    }
}

@Composable
fun Volume(modifier : Modifier      = Modifier,
           isMute   : Boolean       = false,
           onVolume : () -> Unit    = {}){
    Icon(modifier           = modifier.clickable(enabled = true,
                                                 onClick = onVolume),
         imageVector        = if(isMute) Icons.AutoMirrored.Default.VolumeOff
                              else Icons.AutoMirrored.Default.VolumeUp,
         contentDescription = null,
         tint               = Color.White)
}

@Preview(showBackground = true, backgroundColor = 0x111111)
@Composable
fun PreviewFeedBottom(){
    FeedBottom(
        uiState = FeedBottomUiState(isVolumeOff = true),
        isVideo = true
    )
}