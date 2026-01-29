package com.sarang.torang

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Preview
@Composable
fun Menu(navController: NavHostController = rememberNavController()) {
    Column {
        Button({ navController.navigate("Feed") }) {
            Text("Feed")
        }
        Button({ navController.navigate("FeedList") }) {
            Text("FeedList")
        }
        Button({ navController.navigate("FeedRepository") }) {
            Text("FeedRepository")
        }
    }
}