package com.sarang.torang.compose.feed.internal.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sarang.torang.compose.component.AndroidViewRatingBar
import com.sarang.torang.data.basefeed.FeedItemClickEvents
import com.sarang.torang.data.basefeed.FeedItemUiState

@Composable
fun ProfileTop(modifier             : Modifier = Modifier,
               uiState              : FeedItemUiState,
               feedItemClickEvents  : FeedItemClickEvents,
               ratingBarTintColor   : Color){
    val interactionSource = remember { MutableInteractionSource() }
    Box(modifier.fillMaxWidth()
        .clickable(interactionSource = interactionSource,
            indication = null,
            onClick = {})){
        Row(Modifier.align(Alignment.CenterStart)) {
            Spacer          (modifier = Modifier.width(8.dp))
            ProfileImage    (url = uiState.profilePictureUrl ,
                onProfile = feedItemClickEvents.onProfile)
            Spacer          (modifier = Modifier.width(8.dp))
            Column {
                Row {
                    UserName                (userName = uiState.userName,
                        onName = feedItemClickEvents.onName)
                    AndroidViewRatingBar    (rating = uiState.rating,
                        progressTintColor = ratingBarTintColor)
                }
                RestaurantName(restaurantNeme = uiState.restaurantName,
                    onRestaurant = feedItemClickEvents.onRestaurant)
            }
        }
        Menu(modifier = Modifier.align(alignment = Alignment.CenterEnd),
            onMenu = feedItemClickEvents.onMenu)
    }
}
