package com.sarang.base_feed.ui.itemfeed

import TorangAsyncImage
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
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
import com.example.basefeed.R
import com.sarang.base_feed.uistate.FeedTopUIState
import com.sarang.base_feed.uistate.testTopUiState

@Composable
fun ItemFeedTop(
    uiState: FeedTopUIState,
    onProfile: ((Int) -> Unit)? = null,
    onMenu: (() -> Unit)? = null,
    onName: (() -> Unit)? = null,
    onRestaurant: ((Int) -> Unit),
    profileImageServerUrl: String = "",
    ratingBar: @Composable (Float) -> Unit
) {
    // 클릭 시 리플 애니메이션을 없애기 위한 변수
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        Modifier
            .padding(start = Dp(15f))
            .height(IntrinsicSize.Min) // IntrinsicSize.Min 으로 설정
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val model =
            profileImageServerUrl + uiState.profilePictureUrl
        // 프로필 이미지
        TorangAsyncImage(
            model = model,
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onProfile?.invoke(uiState.userId)
                }
                .clip(RoundedCornerShape(20.dp)),
            progressSize = 20.dp,
            errorIconSize = 20.dp
        )

        // 사용자명 + 평점 + 식당명
        Column(
            Modifier
                .padding(start = Dp(8f))
                .fillMaxHeight(), verticalArrangement = Arrangement.SpaceAround
        ) {
            // 사용자명 + 평점
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = uiState.name, modifier = Modifier.clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onName?.invoke()
                })

                Row(Modifier.padding(start = Dp(5f))) {
                    ratingBar.invoke(uiState.rating)
                }
            }

            //식당명
            Text(
                text = uiState.restaurantName,
                color = Color.DarkGray,
                modifier = Modifier.clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onRestaurant.invoke(uiState.restaurantId)
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
                ) {
                    onMenu?.invoke()
                }) {
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
    ItemFeedTop(uiState = testTopUiState(), onRestaurant = {}) {

    }
}
