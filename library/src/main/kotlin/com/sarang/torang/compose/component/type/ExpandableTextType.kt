package com.sarang.torang.compose.component.type

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier

typealias ExpandableTextType = @Composable (ExpandableTextData) -> Unit

data class ExpandableTextData(val modifier      : Modifier  = Modifier,
                              val nickName      : String    = "",
                              val contents      : String    = "",
                              val onNickName    : ()->Unit  = {})

val LocalExpandableTextType = compositionLocalOf<ExpandableTextType> {
    @Composable {
        Text(modifier   = it.modifier,
             text       = "${it.nickName} ${it.contents}")
    }
}