package com.sarang.torang

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.google.samples.apps.sunflower.ui.TorangTheme
import com.sarang.torang.compose.feed.PreViewFeed
import com.sarang.torang.ui.theme.ThemePreviews


@ThemePreviews
@Composable
fun PreviewFeed() {
    TorangTheme {
        Surface {
            PreViewFeed()
        }
    }
}

@ThemePreviews
@Composable
fun test1() {
    TorangTheme {
        Surface {

        }
    }
}