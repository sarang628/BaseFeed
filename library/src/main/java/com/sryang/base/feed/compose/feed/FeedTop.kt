package com.sryang.base.feed.compose.feed

import TorangAsyncImage
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sryang.base.feed.R

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
    profileImageServerUrl: String,          // 프로필 이미지 서버 url
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
            model = profileImageServerUrl + profilePictureUrl,
            progressSize = 20.dp,
            errorIconSize = 20.dp
        )

        // 사용자명 + 평점 + 식당명
        Column(
            Modifier
                .padding(start = Dp(8f))
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
        Divider(
            Modifier
                .weight(1f)
                .height(0.dp)
        )

        // 메뉴
        Column(
            Modifier
                .padding(end = Dp(10f))
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) { onMenu.invoke() }) {
            Image(
                painter = painterResource(id = R.drawable.dot),
                contentDescription = "",
                modifier = Modifier.size(Dp(29f))
            )
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
        profileImageServerUrl = "http://sarang628.iptime.org:89/profile_images/",
        onProfile = {},
        onName = {},
        onMenu = {}
    )
}