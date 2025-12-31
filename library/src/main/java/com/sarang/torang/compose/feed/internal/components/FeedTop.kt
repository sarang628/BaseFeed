package com.sarang.torang.compose.feed.internal.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sarang.torang.compose.component.RatingBar
import com.sarang.torang.compose.feed.internal.util.nonEffectClickable

@Composable
fun FeedTop(modifier             : Modifier    = Modifier,
            profilePictureUrl    : String      = "",
            userName             : String      = "",
            restaurantName       : String      = "",
            rating               : Float       = 0.0f,
            onName               : () -> Unit  = {},
            onProfile            : () -> Unit  = {},
            onRestaurant         : () -> Unit  = {},
            onMenu               : () -> Unit  = {},
            ratingBarTintColor   : Color       = MaterialTheme.colorScheme.primary){
    Box(modifier.fillMaxWidth()
                .nonEffectClickable({})){
        Row(Modifier.align(Alignment.CenterStart)) {
            Spacer          (modifier   = Modifier.width(8.dp))
            ProfileImage    (modifier   = Modifier.testTag("imgProfile"),
                             url        = profilePictureUrl,
                             onProfile  = onProfile)
            Spacer          (modifier   = Modifier.width(8.dp))
            Column(verticalArrangement  = Arrangement.Center) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    UserName  (userName = userName,
                               onName = onName)
                    Spacer    (modifier = Modifier.width(4.dp))
                    RatingBar (rating = rating,
                               progressTintColor = ratingBarTintColor)
                }
                RestaurantName(restaurantNeme = restaurantName,
                               onRestaurant = onRestaurant)
            }
        }
        Menu(modifier   = Modifier.align(alignment = Alignment.CenterEnd),
             onMenu     = onMenu)
    }
}

@Preview(showBackground = true, backgroundColor = 0x111111)
@Composable
fun PreviewFeedTop(){
    FeedTop(userName          = "userName userName userName userName userName userName userName userName userName userName userName ",
            rating            = 3.0f,
            restaurantName    = "restaurantName restaurantName restaurantName restaurantName restaurantName")
}