package com.sarang.torang

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.sarang.torang.compose.feed.PreviewFeed
import com.sarang.torang.ui.theme.ThemePreviews
import com.sryang.torang.ui.TorangTheme


@ThemePreviews
@Composable
fun PreviewFeed() {
    TorangTheme {
        Surface {
            PreviewFeed()
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