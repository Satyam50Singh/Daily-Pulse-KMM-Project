package com.example.dailypulse.articles.data

import com.example.dailypulse.db.DailyPulseDatabase

class ArticleDataSource(private val database: DailyPulseDatabase) {

    fun getAllArticles(): List<ArticleRaw> =
        database.dailyPulseDatabaseQueries.selectAllArticles(::mapToArticleRaw).executeAsList()

    fun insertArticle(articles: List<ArticleRaw>) {
        database.dailyPulseDatabaseQueries.transaction {
            articles.forEach { article ->
                insertArticleRaw(article)
            }
        }
    }

    fun clearArticles() {
        database.dailyPulseDatabaseQueries.removeAllArticles()
    }

    private fun insertArticleRaw(raw: ArticleRaw) {
        database.dailyPulseDatabaseQueries.insertArticle(
            raw.title,
            raw.desc,
            raw.date,
            raw.imageUrl
        )
    }

    private fun mapToArticleRaw(
        title: String,
        desc: String?,
        date: String,
        imageUrl: String?
    ): ArticleRaw =
        ArticleRaw(title, desc, date, imageUrl)

}