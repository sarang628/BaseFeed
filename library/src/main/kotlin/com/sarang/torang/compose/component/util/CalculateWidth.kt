package com.sarang.torang.compose.component.util

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun CalculateWidth(){
    val width : Int = with(LocalDensity.current){1024.toDp().roundToPx()}
    val height : Int = with(LocalDensity.current){824.toDp().roundToPx()}
    val screenWidthDp : Int = LocalConfiguration.current.screenWidthDp
    val screenHeightDp : Int = LocalConfiguration.current.screenHeightDp

    Column {
        Text("${LocalConfiguration.current.screenHeightDp},${LocalConfiguration.current.screenWidthDp}")
        if(width > height){
            val scale = 1.5
            Text("$width")
            Text("scale = ${width.coerceAtLeast(screenWidthDp).toFloat() / width.coerceAtMost(screenWidthDp).toFloat()}")
        }else{
            Text("scale = ${height.coerceAtLeast(screenHeightDp).toFloat() / width.coerceAtMost(screenHeightDp).toFloat()}")
        }
    }
}