package com.sarang.base_feed.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.basefeed.R

@Composable
fun FeedReaction(
    id: Int,
    onLike: ((Int) -> Unit),
    onComment: ((Int) -> Unit),
    onShare: ((Int) -> Unit),
    onFavorite: ((Int) -> Unit),
    isLike: Boolean? = null,
    isFavorite: Boolean? = null
) {
    Row {
        Spacer(modifier = Modifier.padding(start = 8.dp))
        Image(
            painter = if (isLike != null && isLike) painterResource(id = R.drawable.selected_heart) else painterResource(
                id = R.drawable.b3s
            ),
            contentDescription = "",
            modifier = Modifier
                .size(25.dp)
                .clickable {
                    onLike.invoke(id)
                }
        )
        Spacer(modifier = Modifier.padding(start = 12.dp))
        Image(
            painter = painterResource(id = R.drawable.chat),
            contentDescription = "",
            modifier = Modifier
                .size(25.dp)
                .clickable {
                    onComment.invoke(0)
                }
        )
        Spacer(modifier = Modifier.padding(start = 12.dp))
        Image(
            painter = painterResource(id = R.drawable.message),
            contentDescription = "",
            modifier = Modifier
                .size(25.dp)
                .clickable {
                    onShare.invoke(0)
                }
        )

        Text(text = "", modifier = Modifier.weight(1f))

        Image(
            painter = if (isFavorite != null && isFavorite) painterResource(id = R.drawable.selected_star) else painterResource(
                id = R.drawable.star
            ),
            contentDescription = "",
            modifier = Modifier
                .size(25.dp)
                .clickable {
                    onFavorite.invoke(id)
                }
        )
        Spacer(modifier = Modifier.padding(start = 4.dp))
    }
}
