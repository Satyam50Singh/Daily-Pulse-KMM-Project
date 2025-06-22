package com.example.dailypulse.android.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailypulse.sources.domain.Source
import com.example.dailypulse.sources.presentation.SourcesViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import org.koin.androidx.compose.getViewModel

@Composable
fun SourcesScreen(
    onBackClick: () -> Unit,
    viewModel: SourcesViewModel = getViewModel()
) {

    val sourceState = viewModel.sourcesState.collectAsState()

    Column {
        CustomAppBar(onBackClick)

        /*if (sourceState.value.loading) Loader()*/
        if (sourceState.value.error != null) ErrorMessage(sourceState.value.error)
        if (sourceState.value.sources.isNullOrEmpty() == false) SourcesView(viewModel)
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SourcesView(viewModel: SourcesViewModel) {

    val sources = viewModel.sourcesState.value.sources

    SwipeRefresh(
        state = SwipeRefreshState(isRefreshing = viewModel.sourcesState.value.loading),
        onRefresh = { viewModel.getSources(true) }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (sources?.isNotEmpty() == true) {
                items(sources) { source ->
                    SourceItemView(source)
                }
            }
        }
    }
}

@Composable
fun SourceItemView(source: Source) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(8.dp))
            .padding(8.dp)
            .border(width = 2.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text(
            text = source.name, style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = source.desc)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = source.lang + "-" + source.country,
            style = TextStyle(color = Color.Gray),
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(onBackClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Sources") },
        windowInsets = WindowInsets(0),    // remove default top padding
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        }
    )
}