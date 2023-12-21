package com.sryang.torang.compose.feed

import TorangAsyncImage
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
internal fun FeedTop(
    name: String,
    restaurantName: String,
    rating: Float,
    profilePictureUrl: String,
    onProfile: () -> Unit,                  // 프로필 이미지 클릭
    onMenu: () -> Unit,                     // 메뉴 클릭
    onName: () -> Unit,                     // 이름 클릭
    onRestaurant: () -> Unit,               // 음식점 클릭
    ratingBar: @Composable (Float) -> Unit  // 평점 바 compose
) {
    val interactionSource = remember { MutableInteractionSource() } // 클릭 시 리플 애니메이션을 없애기 위한 변수
    Row(
        Modifier
            .padding(start = Dp(15f))
            .height(IntrinsicSize.Min) // IntrinsicSize.Min 으로 설정
            .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    )
    {
        // 프로필 이미지
        TorangAsyncImage(modifier = Modifier
            .size(40.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { onProfile.invoke() }
            .clip(RoundedCornerShape(20.dp)),
            model = profilePictureUrl,
            progressSize = 20.dp,
            errorIconSize = 20.dp
        )

        // 사용자명 + 평점 + 식당명
        Column(
            Modifier
                .padding(start = 8.dp)
                .fillMaxHeight(), verticalArrangement = Arrangement.SpaceAround
        )
        {
            // 사용자명 + 평점
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) { onName.invoke() },
                    text = name
                )
                Spacer(modifier = Modifier.width(5.dp))
                ratingBar.invoke(rating)
            }

            //식당명
            Text(
                text = restaurantName,
                color = Color.DarkGray,
                modifier = Modifier.clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onRestaurant.invoke()
                }
            )
        }

        // 메뉴
        Box(
            Modifier
                .fillMaxWidth()
        ) {
            IconButton(
                modifier = Modifier.align(Alignment.CenterEnd),
                onClick = { onMenu.invoke() }
            ) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "")
            }
        }
    }
}

@Preview
@Composable
fun PreviewItemFeedTop() {
    FeedTop(
        name = "name",
        restaurantName = "restaurantName",
        profilePictureUrl = "",
        rating = 3.5f,
        ratingBar = {},
        onRestaurant = {},
        onProfile = {},
        onName = {},
        onMenu = {}
    )
}