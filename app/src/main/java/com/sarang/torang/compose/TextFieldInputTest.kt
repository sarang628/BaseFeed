package com.sarang.torang.compose

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import kotlin.reflect.KProperty

@Preview
@Composable
fun TextFieldInputTest(){

    var input : String by remember { mutableStateOf("0") }

    TextField(value = input,
              onValueChange = { input = it })
}

operator fun <String> MutableState<String>.setValue(thisObj: Any?, property: KProperty<*>, value: String) {
    this.value = value
}

operator fun <String> State<String>.getValue(thisObj: Any?, property: KProperty<*>): String = value