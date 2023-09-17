package com.sarang.base_feed.ui.itemfeed

import TorangAsyncImage
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.basefeed.R
import com.example.library.RatingBar
import com.sarang.base_feed.uistate.FeedTopUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.random.Random

@Composable
fun ItemFeedTop(
    uiState: FeedTopUIState? = null,
    onProfile: (() -> Unit)? = null,
    onMenu: (() -> Unit)? = null,
    onName: (() -> Unit)? = null,
    onRestaurant: (() -> Unit)? = null,
    profileImageServerUrl: String = ""
) {
    if (uiState == null) {
        return
    }

    Row(
        Modifier
            .padding(start = Dp(15f))
            .height(IntrinsicSize.Min) // IntrinsicSize.Min 으로 설정
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val model =
            if (uiState.profilePictureUrl != null) {
                profileImageServerUrl + uiState.profilePictureUrl
            } else R.drawable.ic_baseline_person_24
        // 프로필 이미지
        TorangAsyncImage(
            model = model,
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .clickable {
                    onProfile?.invoke()
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
                uiState.name?.let {
                    Text(text = it, modifier = Modifier.clickable {
                        onName?.invoke()
                    })
                }

                uiState.rating?.let {
                    Row(Modifier.padding(start = Dp(5f))) {
                        RatingBar(it)
                    }
                }
            }

            //식당명
            uiState.restaurantName?.let {
                Text(
                    text = it,
                    color = Color.DarkGray,
                    modifier = Modifier.clickable {
                        onRestaurant?.invoke()
                    }
                )
            }
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
                .clickable {
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

val names = arrayListOf(
    ".",
    "홍길동",
    "James",
    "Luffy",
    "가나다라",
    "CCC"
)
val pictures =
    arrayListOf(
        //"http://sarang628.iptime.org:88/1.png",
        R.drawable.a
    )


class DummyFeedTopUiState : PreviewParameterProvider<FeedTopUIState> {
    override val values: Sequence<FeedTopUIState>
        get() = sequenceOf(
            FeedTopUIState(
                name = "강아지",
                //profilePictureUrl = "http://sarang628.iptime.org:88/1.png",
                profilePictureUrl = R.drawable.a,
                restaurantName = "치킨카레",
                rating = 2.5f
            )
        )
}

@Preview
@Composable
fun PreviewFeedTop() {

    val aa = remember {
        MutableStateFlow(
            FeedTopUIState(
                name = "abc",
                //profilePictureUrl = "http://sarang628.iptime.org:88/1.png",
//        profilePictureUrl = R.drawable.a,
                profilePictureUrl = pictures.random(),
                restaurantName = "맥도날드",
                rating = 5f
            )
        )
    }
    val a by aa.collectAsState()

    LaunchedEffect(key1 = "", block = {
        while (true) {
            delay(2000)
            aa.emit(
                aa.value.copy(
                    name = names.random(),
                    profilePictureUrl = pictures.random(),
                    rating = Random(5).nextFloat()
                )
            )
        }
    })

    ItemFeedTop(a)
}

@Preview
@Composable
fun PreviewFeedTop1() {

    val data = FeedTopUIState(
        name = "Mr.Grack",
//        profilePictureUrl = "http://sarang628.iptime.org:88/1.png",
//        profilePictureUrl = R.drawable.a,
        restaurantName = "The Five Cousins",
        rating = 4.5f
    )

    ItemFeedTop(data)
}