package com.example.dailypulse.android.screens

import android.graphics.drawable.Icon
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.dailypulse.android.R
import com.example.dailypulse.articles.Article
import com.example.dailypulse.articles.ArticlesViewModel

@Composable
fun ArticleScreen(
    onAboutButtonClick: () -> Unit,
    articlesViewModel: ArticlesViewModel
) {

    val articleState = articlesViewModel.articleState.collectAsState()

    Column {
        AppBar(onAboutButtonClick)

        if (articleState.value.loading) Loader()
        if (articleState.value.error != null) ErrorMessage(articleState.value.error)
        if (!articleState.value.article.isNullOrEmpty()) ArticleListView(articleState.value.article)
    }

}

@Composable
fun ErrorMessage(error: String?) {
    if (!error.isNullOrEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Text(text = error, style = TextStyle(fontSize = 28.sp, textAlign = TextAlign.Center))
        }
    }
}

@Composable
fun Loader() {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.padding(64.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            trackColor = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun ArticleListView(articles: List<Article>?) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        if (articles != null) {
            items(articles) { article: Article ->
                ArticleItemView(article)
            }
        }
    }
}

@Composable
fun ArticleItemView(article: Article) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(8.dp))
            .padding(16.dp)
            .border(width = 2.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(article.imageUrl)
                .placeholder(R.drawable.img_placeholder)
                .error(R.drawable.err_placeholder)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.title, style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = article.description)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.date,
            style = TextStyle(color = Color.Gray),
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(onAboutButtonClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Article") },
        actions = {
            IconButton(onClick = onAboutButtonClick) {
                Icon(imageVector = Icons.Outlined.Info, contentDescription = null)
            }
        }
    )
}
