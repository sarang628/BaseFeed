package com.sarang.torang.compose.feed.internal.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.sarang.torang.compose.component.RatingBar
import com.sarang.torang.compose.feed.internal.util.nonEffectClickable

/**
 *
 */
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
    ConstraintLayout(
            modifier = modifier
                .fillMaxWidth()
                .nonEffectClickable(),
            constraintSet = ConstraintSet {
                val imgProfile = createRefFor("imgProfile")
                val containerUserAndRestaurantName = createRefFor("userName")
                val btnMenu = createRefFor("btnMenu")

                constrain(imgProfile){
                    start.linkTo(parent.start, margin = 4.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }

                constrain(containerUserAndRestaurantName){
                    start.linkTo(imgProfile.end, margin = 2.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }

                constrain(btnMenu){
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
            }
        ) {
            ProfileImage    (modifier   = Modifier.testTag("imgProfile")
                                                  .layoutId("imgProfile"),
                             url        = profilePictureUrl,
                             onProfile  = onProfile)

            Row(modifier = Modifier.layoutId("userName")) {
                Column(modifier = Modifier.layoutId("userName"),
                    verticalArrangement  = Arrangement.Center) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        UserName  (modifier          = Modifier.testTag("txtUserName"),
                                   userName          = userName,
                                   onName            = onName)
                        Spacer    (modifier          = Modifier.width(4.dp))
                        RatingBar (modifier          = Modifier.testTag("rbProfile"),
                                   rating            = rating,
                                   progressTintColor = ratingBarTintColor)
                    }
                    RestaurantName(modifier         = Modifier.testTag("txtRestaurantName"),
                                   restaurantName   = restaurantName,
                                   onRestaurant     = onRestaurant)
                }
            }

            Menu(modifier   = Modifier.layoutId("btnMenu")
                                      .testTag("btnMenu"),
                 onMenu     = onMenu)
        }
    
}

@Preview(showBackground = true, backgroundColor = 0x111111)
@Composable
fun PreviewFeedTop(){
    var userName : String by remember { mutableStateOf("userName userName userName userName userName userName userName userName userName userName userName ") }
    var rating : String by remember { mutableStateOf("4.5") }
    var restaurantName : String by remember { mutableStateOf("restaurantName restaurantName restaurantName restaurantName restaurantName") }

    Column() {
        FeedTop(userName          = userName,
                rating            = try{ rating.toFloat() }catch(e : Exception) { 0f } ,
                restaurantName    = restaurantName)

        HorizontalDivider(Modifier.height(10.dp))

        TextField(value         = userName,
                  onValueChange = {userName = it},
                  label         = { Text("userName") })
        TextField(value         = rating.toString(),
                  onValueChange = {rating = it},
                  label         = { Text("rating") })
        TextField(value         = restaurantName,
                  onValueChange = {restaurantName = it},
                  label         = { Text("restaurantName") })
    }
}