package com.sarang.torang.compose.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.sarang.torang.compose.component.type.ExpandableTextData
import com.sarang.torang.compose.component.type.LocalExpandableTextType

@Composable
fun Contents(modifier   : Modifier  = Modifier,
             userName   : String    = "",
             contents   : String    = "",
             onContents : ()->Unit  = {}){
    LocalExpandableTextType.current.invoke(
        ExpandableTextData(modifier = modifier.testTag("txtContents"),
            nickName = userName,
            contents = contents,
            onNickName = onContents)
    )
}

@Preview
@Composable
fun PreviewContent(){
    Surface(color = MaterialTheme.colorScheme.background) {
        Contents(userName = "userName", contents = "contents")
    }
}