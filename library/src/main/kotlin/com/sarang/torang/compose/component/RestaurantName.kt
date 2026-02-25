package com.sarang.torang.compose.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sarang.torang.compose.component.util.nonEffectClickable

@Composable
fun RestaurantName(modifier         : Modifier  = Modifier,
                   onRestaurant     : ()->Unit  = {},
                   restaurantName   : String    = ""){
    Text(modifier   = modifier.widthIn(0.dp, 250.dp)
        .nonEffectClickable(onRestaurant),
        text       = restaurantName,
        overflow   = TextOverflow.Ellipsis,
        maxLines   = 1,
        color      = Color.White,
        style      = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
    )
}

@Preview
@Composable
fun PreviewRestaurantName(){
    val restaurantName = "restaurantName restaurantName restaurantName restaurantName"
    Column {
        RestaurantName(restaurantName = restaurantName)
        Text(text = restaurantName,
            color = Color.White,
            maxLines = 1,
            //style      = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
        )
    }
}