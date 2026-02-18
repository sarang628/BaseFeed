package com.sarang.torang.compose.component

import android.content.Context
import android.content.res.ColorStateList
import android.widget.RatingBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
internal fun RatingBar(
    modifier            : Modifier  = Modifier,
    rating              : Float     = 0.0f,
    isSmall             : Boolean   = true,
    changAble           : Boolean   = false,
    progressTintColor   : Color?    = null
) {
    AndroidView(
        modifier = modifier.semantics {
            progressBarRangeInfo = ProgressBarRangeInfo(
                current = rating,
                range = 0f..5f
            )
        },
        factory = { context ->
            if (isSmall) {
                smallRatingBar(context, changAble, progressTintColor)
            } else {
                mediumRatingBar(context, changAble, progressTintColor)
            }
        },
        update = { view ->
            if (view.rating != rating) {
                view.rating = rating
            }

            view.setIsIndicator(!changAble)
        }
    )
}

private fun smallRatingBar(context : Context,
                           changAble : Boolean,
                           progressTintColor : Color?) : RatingBar{
    return RatingBar(context, null, android.R.attr.ratingBarStyleSmall).apply {
        // Sets up listeners for View -> Compose communication
        this.rating = rating
        setIsIndicator(!changAble)

        progressTintColor?.let {
            progressTintList = ColorStateList(
                arrayOf(
                    intArrayOf(android.R.attr.state_enabled), // enabled
                ), intArrayOf(
                    it.hashCode(),
                )
            )

            secondaryProgressTintList = ColorStateList(
                arrayOf(
                    intArrayOf(android.R.attr.state_enabled), // enabled
                ), intArrayOf(
                    it.hashCode(),
                )
            )
        }
    }
}

private fun mediumRatingBar(context : Context,
                            changAble : Boolean,
                            progressTintColor : Color?) : RatingBar{
    return RatingBar(context).apply {
        // Sets up listeners for View -> Compose communication
        this.rating = rating
        setIsIndicator(!changAble)

        progressTintColor?.let {
            progressTintList = ColorStateList(
                arrayOf(
                    intArrayOf(android.R.attr.state_enabled), // enabled
                ), intArrayOf(
                    it.hashCode(),
                )
            )

            secondaryProgressTintList = ColorStateList(
                arrayOf(
                    intArrayOf(android.R.attr.state_enabled), // enabled
                ), intArrayOf(
                    it.hashCode(),
                )
            )
        }
    }
}

@Preview
@Composable
fun PreviewRatingBar() {
    var rating : String by remember { mutableStateOf("3.5") }
    Column {
        RatingBar(
            modifier = Modifier,
            rating = try { rating.toFloat() }catch (e : Exception){ 0f },
            isSmall = false,
            changAble = true,
            progressTintColor = Color.Yellow
        )

        HorizontalDivider()

        OutlinedTextField(value = rating,
                          onValueChange = { rating = it },
                          label = { Text("Rating") })
    }

}