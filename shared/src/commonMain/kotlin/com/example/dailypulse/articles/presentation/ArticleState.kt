package com.example.dailypulse.articles.presentation

import com.example.dailypulse.articles.domain.Article

data class ArticleState(
    val article: List<Article>? = listOf(),
    val loading: Boolean = false,
    val error: String? = null
)
