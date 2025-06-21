package com.example.dailypulse.articles.data

class ArticleRepository(
    private val dataSource: ArticleDataSource,
    private val service: ArticleService
) {
    suspend fun getArticles(forceRefresh: Boolean): List<ArticleRaw>? {
        if (forceRefresh) {
            dataSource.clearArticles()
            return articleRaws()
        }
        val articlesList = dataSource.getAllArticles()
        println("Articles Db has ${articlesList.size} records")

        if (articlesList.isEmpty()) {
            return articleRaws()
        }
        return articlesList
    }

    private suspend fun articleRaws(): List<ArticleRaw>? {
        val fetchedArticles = service.fetchArticles()
        if (!fetchedArticles.isNullOrEmpty()) {
            dataSource.insertArticle(fetchedArticles)
            return fetchedArticles
        }
        return null
    }
}