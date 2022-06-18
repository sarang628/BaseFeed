package com.sarang.base_feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.torang_core.data.model.Feed
import com.google.android.material.tabs.TabLayoutMediator
import com.sarang.base_feed.databinding.ItemTimeLineBinding


/** Compose test */
data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {
    // Add padding around our message
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.b3s),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                // Set image size to 40 dp
                .size(40.dp)
                // Clip image to be shaped as a circle
                .clip(CircleShape)
        )

        // Add a horizontal space between the image and the column
        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(text = msg.author)
            // Add a vertical space between the author and message texts
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = msg.body)
        }
    }
}

@Preview
@Composable
fun FeedProfile() {
    Row(modifier = Modifier.padding(all = 2.dp)) {
        Image(
            painter = painterResource(R.drawable.b3s),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                // Set image size to 40 dp
                .size(20.dp)
                // Clip image to be shaped as a circle
                .clip(CircleShape)
        )
        Column(modifier = Modifier.padding(start = 3.dp)) {
            Text(text = "name")
            Text(text = "restaurant name")
        }
    }

}

/**
 * [ItemTimeLineBinding]
 */
class FeedVH(
    val itemTimeLineBinding: ItemTimeLineBinding,
    val viewModel: BaseFeedViewModel,
    private val lifeCycleOwner: LifecycleOwner
) : RecyclerView.ViewHolder(itemTimeLineBinding.root) {

    init {
        itemTimeLineBinding.viewModel = viewModel
        itemTimeLineBinding.lifecycleOwner = lifeCycleOwner
        val timeLinePictureRvAdt = FeedPictureVpAdt(viewModel)
        itemTimeLineBinding.viewpager.adapter = timeLinePictureRvAdt

        itemTimeLineBinding.greeting.setContent{
            //MessageCard(Message("Android", "Jetpack Compose"))
        }
    }

    fun setFeed(feed: Feed) {
        itemTimeLineBinding.feed = feed

        /** 리뷰 사진 설정 */
        viewModel.getReviewImages(feed.review_id).observe(lifeCycleOwner) {
            (itemTimeLineBinding.viewpager.adapter as FeedPictureVpAdt).setPictures(it)
            TabLayoutMediator(
                itemTimeLineBinding.include.tl,
                itemTimeLineBinding.viewpager
            ) { _, _ ->

            }.attach()
        }

        viewModel.isLike(feed.review_id).observe(lifeCycleOwner) {
            itemTimeLineBinding.like = it
        }

        viewModel.isFavorite(feed.review_id).observe(lifeCycleOwner) {
            itemTimeLineBinding.favorite = it
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            baseFeedViewModel: BaseFeedViewModel,
            lifeCycleOwner: LifecycleOwner
        ): FeedVH {
            return FeedVH(
                itemTimeLineBinding = ItemTimeLineBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ), baseFeedViewModel, lifeCycleOwner
            )
        }
    }
}

@Preview
@Composable
fun PreViewMessageCard() {
    MessageCard(Message("Android1", "Jetpack Compose"))
}