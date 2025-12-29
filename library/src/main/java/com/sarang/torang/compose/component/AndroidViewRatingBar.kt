package com.sarang.torang.compose.component

import android.content.res.ColorStateList
import android.widget.RatingBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
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
        modifier = modifier.layoutId("ratingBar"),
        factory = { context ->
            // Creates view
            if (isSmall) {
                RatingBar(context, null, android.R.attr.ratingBarStyleSmall).apply {
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
            } else {
                RatingBar(context).apply {
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
        },
        update = { view ->
            // View's been inflated or state read in this block has been updated
            // Add logic here if necessary

            // As selectedItem is read here, AndroidView will recompose
            // whenever the state changes
            // Example of Compose -> View communication
        }
    )
}

@Preview
@Composable
fun PreviewRatingBar() {
    RatingBar(
        Modifier,
        3.5f,
        isSmall = false,
        changAble = true,
        progressTintColor = Color.Yellow
    )
}