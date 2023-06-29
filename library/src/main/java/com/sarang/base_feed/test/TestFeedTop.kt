package com.sarang.base_feed.test

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basefeed.R
import com.example.library.RatingBar

@Composable
fun FeedTop(userName: String, restaurantName: String, rating: Float) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.b3s),
            contentDescription = "",
            Modifier.padding(end = 5.dp)
        )
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = userName)
                RatingBar(rating = rating)
            }
            Text(text = restaurantName)
        }
    }
}