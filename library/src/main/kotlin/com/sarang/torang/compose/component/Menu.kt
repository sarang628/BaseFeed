package com.sarang.torang.compose.component

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true, backgroundColor = 0x000000)
@Composable
internal fun Menu(modifier   : Modifier = Modifier,
         onMenu     : ()->Unit = {}){
    IconButton(modifier = modifier,
        onClick  = onMenu) {
        Icon(imageVector        = Icons.Default.MoreVert,
            tint               = Color.White,
            contentDescription = "menu",
            modifier           = Modifier.background(Color.Transparent))
    }
}