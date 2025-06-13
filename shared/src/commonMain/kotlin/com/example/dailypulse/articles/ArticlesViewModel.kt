package com.example.dailypulse.articles

import com.example.dailypulse.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticlesViewModel : BaseViewModel() {

    private val _articleState: MutableStateFlow<ArticleState> =
        MutableStateFlow(ArticleState(loading = true))

    val articleState: StateFlow<ArticleState>
        get() = _articleState

    init {
        getArticle()
    }

    private fun getArticle() {
        scope.launch {
            delay(2000)

            _articleState.emit(
                ArticleState(
                    article = listOf(),
                    loading = false,
                    error = "Something went wrong!!"
                )
            )

            delay(2000)

            val response = fetchArticles()
            _articleState.emit(ArticleState(article = response, loading = false, error = null))
        }
    }

    private suspend fun fetchArticles(): List<Article> {
        return mockArticles
    }

    private val mockArticles = listOf(
        Article(
            title = "Kotlin Multiplatform: The Future of Cross-Platform Apps",
            description = "Kotlin Multiplatform allows developers to share code across Android, iOS, web, and backend projects.",
            date = "2025-06-10",
            imageUrl = "https://images.unsplash.com/photo-1581092334428-7f7f3b06d7c5?auto=format&fit=crop&w=800&q=80"
        ),
        Article(
            title = "Jetpack Compose 1.6: What’s New?",
            description = "Google releases Jetpack Compose 1.6 with new UI components and improved performance.",
            date = "2025-06-09",
            imageUrl = "https://images.unsplash.com/photo-1610276347564-6b37d4f4e77c?auto=format&fit=crop&w=800&q=80"
        ),
        Article(
            title = "Understanding Coroutines in Kotlin",
            description = "Coroutines help manage background tasks in Kotlin. Learn how to use them effectively.",
            date = "2025-06-08",
            imageUrl = "https://images.unsplash.com/photo-1587620962725-abab7fe55159?auto=format&fit=crop&w=800&q=80"
        ),
        Article(
            title = "Android Studio Hedgehog Released",
            description = "Android Studio Hedgehog comes with better device mirroring, updated templates, and Compose previews.",
            date = "2025-06-07",
            imageUrl = "https://images.unsplash.com/photo-1558655146-d09347e92766?auto=format&fit=crop&w=800&q=80"
        ),
        Article(
            title = "Why Mobile Developers Love Kotlin",
            description = "Kotlin's conciseness, null safety, and coroutine support make it ideal for mobile development.",
            date = "2025-06-06",
            imageUrl = "https://images.unsplash.com/photo-1591696205602-2f950c417cb9?auto=format&fit=crop&w=800&q=80"
        )
    )
}