package com.example.traveltales

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomePage() {
    Text("Welcome to the Home Page!")
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    HomePage()
}
