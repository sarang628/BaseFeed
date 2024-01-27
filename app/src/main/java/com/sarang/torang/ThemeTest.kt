package com.sarang.torang

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.google.samples.apps.sunflower.ui.TorangTheme
import com.sarang.torang.compose.feed.internal.components.PreviewExpandableText
import com.sarang.torang.ui.theme.ThemePreviews


@ThemePreviews
@Composable
fun test1() {
    TorangTheme {
        Surface {
            PreviewExpandableText()
        }
    }
}

@ThemePreviews
@Composable
fun test2() {
    val color: Color = Color.Unspecified
    val local = LocalContentColor.current
    val style: TextStyle = LocalTextStyle.current
    TorangTheme {
        Surface {
            val textColor = color.takeOrElse {
                style.color.takeOrElse {
                    local
                }
            }

            Text(text = "ABCD", color = if (isSystemInDarkTheme()) Color.White else Color.Black)
        }
    }
}