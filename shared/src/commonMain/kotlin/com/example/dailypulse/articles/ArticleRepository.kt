package com.example.dailypulse.articles

class ArticleRepository(
    private val dataSource: ArticleDataSource,
    private val service: ArticleService
) {
    suspend fun getArticles(): List<ArticleRaw> {
        val articlesList = dataSource.getAllArticles()
        if (articlesList.isEmpty()) {
            val fetchedArticles = service.fetchArticles()
            dataSource.insertArticle(fetchedArticles!!)
            return fetchedArticles
        }
        return articlesList
    }
}