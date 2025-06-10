package com.example.dailypulse.android

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.dailypulse.Platform

@Composable
fun AboutScreen() {
    Column {
        TopBar()
        ContentView()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = "About Device") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Black,
            titleContentColor = Color.White
        )
    )
}

@Composable
fun ContentView() {
    val items = makeItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(items) { row ->
            RowView(title = row.first, subtitle = row.second)
        }
    }
}

@Composable
fun RowView(title: String, subtitle: String) {
    Column(Modifier.padding(8.dp)) {
        Text(
            text = title,
            color = Color.Black,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = subtitle,
            color = Color.Gray,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.W600
        )
    }
}

fun makeItems(): List<Pair<String, String>> {
    val platform = Platform()
    platform.logSystemInfo()

    return listOf(
        Pair("Operating System", platform.osName),
        Pair("API Level", platform.osVersion),
        Pair("Device Model", platform.deviceModel),
        Pair(
            "Density", platform.density.toString()
        )
    )

}
