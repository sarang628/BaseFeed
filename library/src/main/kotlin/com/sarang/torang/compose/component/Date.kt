package com.sarang.torang.compose.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.sarang.torang.compose.feed.data.formatedDate

@Composable
internal fun Date(modifier : Modifier = Modifier, date : String = ""){
    Text(modifier = modifier.testTag("txtDate"),
        text = date.formatedDate(),
        color = Color.Gray,
        fontWeight = W500,
        fontSize = 12.sp)
}

@Preview
@Composable
fun PreviewDate(){
    Date(date = "2025-12-30 12:00:00")
}