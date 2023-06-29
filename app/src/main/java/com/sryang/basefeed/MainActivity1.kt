package com.sryang.basefeed

import android.os.Bundle
import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import com.sarang.base_feed.test.FeedTop

class MainActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //XML
        val feedTop: View = findViewById(R.id.ic)

        feedTop.findViewById<TextView>(com.example.basefeed.R.id.tv_user).text = "토랑"
        feedTop.findViewById<TextView>(com.example.basefeed.R.id.tv_restaurant).text = "맛집"
        feedTop.findViewById<RatingBar>(com.example.basefeed.R.id.rb_restaurant).rating = 3.0f

        //Compose
        findViewById<ComposeView>(R.id.cv).setContent {
            FeedTop(
                userName = "토랑",
                restaurantName = "맛집",
                rating = 3.0f
            )
        }
    }
}