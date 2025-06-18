package com.example.dailypulse.articles

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.math.abs

class ArticleUseCase(private val repository: ArticleRepository) {

    suspend fun getArticles(forceRefresh: Boolean): List<Article>? {
        val articleRaw = repository.getArticles(forceRefresh)
        return mapArticles(articleRaw)
    }

    private fun mapArticles(articleRaw: List<ArticleRaw>?): List<Article>? {
        return articleRaw?.map { raw ->
            Article(
                raw.title, raw.desc ?: "", getDaysAgo(raw.date), raw.imageUrl ?: ""
            )
        }
    }

    private fun getDaysAgo(date: String): String {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val days = today.daysUntil(
            Instant.parse(date).toLocalDateTime(TimeZone.currentSystemDefault()).date
        )

        val result = when {
            abs(days) > 1 -> "${abs(days)} days ago"
            abs(days) == 1 -> "Yesterday"
            else -> "Today"
        }

        return result
    }
}