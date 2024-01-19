package com.sarang.torang.compose.feed.internal

import TorangAsyncImage
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet

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
    ratingBar: @Composable (Modifier, Float) -> Unit  // 평점 바 compose
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth(),
        constraintSet = decoupledConstraints()
    ) {
        // 프로필 이미지
        TorangAsyncImage(
            modifier = Modifier
                .layoutId("profileImage")
                .size(40.dp)
                .clickable1(onProfile::invoke)
                .clip(RoundedCornerShape(20.dp)),
            model = profilePictureUrl,
            progressSize = 20.dp,
            errorIconSize = 20.dp
        )
        // 사용자명
        Text(
            modifier = Modifier
                .layoutId("userName")
                .clickable1(onName::invoke),
            text = name,
        )
        // 평점
        ratingBar.invoke(Modifier.layoutId("ratingBar"), rating)
        // 음식점명
        Text(
            modifier = Modifier
                .layoutId("restaurantName")
                .clickable1(onRestaurant::invoke),
            text = restaurantName
        )
        // 메뉴
        IconButton(
            modifier = Modifier.layoutId("menuButton"),
            onClick = onMenu::invoke
        ) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
        }
    }
}

private fun decoupledConstraints(): ConstraintSet {
    return ConstraintSet {
        val profileImage = createRefFor("profileImage")
        val userName = createRefFor("userName")
        val restaurantName = createRefFor("restaurantName")
        val menuButton = createRefFor("menuButton")
        val ratingBar = createRefFor("ratingBar")

        constrain(profileImage) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start, margin = 12.dp)
        }
        constrain(userName) {
            top.linkTo(profileImage.top)
            bottom.linkTo(restaurantName.top)
            start.linkTo(profileImage.end, margin = 8.dp)
        }
        constrain(restaurantName) {
            top.linkTo(userName.bottom)
            bottom.linkTo(profileImage.bottom)
            start.linkTo(profileImage.end, margin = 8.dp)
        }
        constrain(menuButton) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            end.linkTo(parent.end)
        }
        constrain(ratingBar) {
            start.linkTo(userName.end, margin = 3.dp)
            top.linkTo(userName.top)
            bottom.linkTo(userName.bottom)
        }
    }
}

@Composable
fun Modifier.clickable1(onClick: () -> Unit): Modifier {
    val interactionSource = remember { MutableInteractionSource() } // 클릭 시 리플 애니메이션을 없애기 위한 변수
    this.clickable(
        interactionSource = interactionSource,
        indication = null,
        onClick = onClick
    )
    return this
}

@Preview
@Composable
fun PreviewItemFeedTop() {
    FeedTop(
        name = "name",
        restaurantName = "restaurantName",
        profilePictureUrl = "",
        rating = 3.5f,
        ratingBar = { modifier, fl -> },
        onRestaurant = {},
        onProfile = {},
        onName = {},
        onMenu = {}
    )
}