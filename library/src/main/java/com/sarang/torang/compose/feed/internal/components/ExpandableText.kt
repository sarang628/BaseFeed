package com.sarang.torang.compose.feed.internal.components

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

const val TAG = "_ExpandableText"

val ExpandableTextColor: Color @Composable get() = if (isSystemInDarkTheme()) Color.White else Color.Black
val SeeMoreAndLessColor: Color @Composable get() = if (isSystemInDarkTheme()) Color.LightGray else Color.Gray

@Composable
fun ExpandableText(
    modifier: Modifier = Modifier,
    nickName: String? = null,
    text: String
) {
    var isExpanded by remember { mutableStateOf(false) }
    val textLayoutResultState = remember { mutableStateOf<TextLayoutResult?>(null) }
    var isClickable by remember { mutableStateOf(false) }
    val textLayoutResult = textLayoutResultState.value
    val seeMoreandLessColor = SeeMoreAndLessColor
    val expandableTextColor = ExpandableTextColor

    //first we match the html tags and enable the links
    val textWithLinks = buildAnnotatedString {
        withStyle(SpanStyle(color = expandableTextColor, fontWeight = FontWeight.Bold))
        {
            append(nickName)
        }
        append(" ")
        withStyle(SpanStyle(color = expandableTextColor)) {
            append(text)
        }
    }
    //then we create the Show more/less animation effect
    var textWithMoreLess by remember { mutableStateOf(textWithLinks) }

    LaunchedEffect(textLayoutResult) {
        if (textLayoutResult == null) return@LaunchedEffect

        when {
            isExpanded -> {
                textWithMoreLess = buildAnnotatedString {
                    append(textWithLinks)
                    pushStringAnnotation(tag = "show_more_tag", annotation = "")
                    withStyle(SpanStyle(color = seeMoreandLessColor)) {
                        append(" See less")
                    }
                    pop()
                }
            }

            !isExpanded && textLayoutResult.hasVisualOverflow -> {//Returns true if either vertical overflow or horizontal overflow happens.
                val lastCharIndex = textLayoutResult.getLineEnd(3 - 1)
                val showMoreString = "...See more"
                val adjustedText = textWithLinks
                    .substring(startIndex = 0, endIndex = lastCharIndex)
                    .dropLast(showMoreString.length)
                    .dropLastWhile { it == ' ' || it == '.' }

                textWithMoreLess = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = expandableTextColor,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append(
                            adjustedText.substring(0, nickName?.length ?: 0)
                        )
                    }
                    withStyle(SpanStyle(color = expandableTextColor)) {
                        append(adjustedText.substring(nickName?.length ?: 0, adjustedText.length))
                        append("...")
                    }
                    pushStringAnnotation(tag = "show_more_tag", annotation = "")
                    withStyle(SpanStyle(color = seeMoreandLessColor)) {
                        append("more")
                    }
                    pop()
                }

                isClickable = true
                //We basically need to assign this here so that the Text is only clickable if the state is not expanded,
                // but there is visual overflow. Otherwise, it means that the text given to the composable is not exceeding the max lines.
            }
        }
    }

    // UriHandler parse and opens URI inside AnnotatedString Item in Browse
    val uriHandler = LocalUriHandler.current

    //Composable container
    SelectionContainer(modifier = modifier) {
        ClickableText(
            text = textWithMoreLess,
            style = TextStyle(color = Color.DarkGray, fontSize = 15.sp),
            onClick = { offset ->
                Log.d(TAG, "offset : ${offset}")
                textWithMoreLess.getStringAnnotations(
                    tag = "link_tag",
                    start = offset,
                    end = offset
                ).firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
                if (isClickable) {
                    textWithMoreLess.getStringAnnotations(
                        tag = "show_more_tag",
                        start = offset,
                        end = offset
                    ).firstOrNull()?.let {
                        isExpanded = !isExpanded
                    }
                }
            },
            maxLines = if (isExpanded) Int.MAX_VALUE else 3,
            onTextLayout = { textLayoutResultState.value = it },
            modifier = modifier
                .animateContentSize()
        )
    }
}

@Composable
fun PreviewExpandableText() {
    ExpandableText(
        text = "aaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "bbbbbbbbbbbbbbbbbbbbbbb" +
                "cccccccccccccccccccccc" +
                "dddddddddddddddddddd" +
                "eeeeeeeeeeeeeeeeeeeeee" +
                "ffffffffffffffffffffff" +
                "ggggggggggggggggggggggg" +
                "hhhhhhhhhhhhhhhhhhhhhhh" +
                "jjjjjjjjjjjjjjjjjjjjjjjjjj" +
                "kkkkkkkkkkkkkkkkkkkkkkkkkk" +
                "llllllllllllllllllllllllll" +
                "mmmmmmmmmmmmmmmmmmmmmmmmmmm" +
                "nnnnnnnnnnnnnnnnnnnnnnnnnnn" +
                "ooooooooooooooooooooooooooo" +
                "ppppppppppppppppppppppppppp" +
                "qqqqqqqqqqqqqqqqqqqqqqqqqqq" +
                "rrrrrrrrrrrrrrrrrrrrrrrrrrr" +
                "sssssssssssssssssssssssssss" +
                "ttttttttttttttttttttttttttt" +
                "uuuuuuuuuuuuuuuuuuuuuuuuuuu" +
                "vvvvvvvvvvvvvvvvvvvvvvvvvvv"
    )
}