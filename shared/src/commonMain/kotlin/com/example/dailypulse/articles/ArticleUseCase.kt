package com.example.dailypulse.articles

class ArticleUseCase(private val service: ArticleService) {

    suspend fun getArticles() : List<Article>? {
        val articleRaw = service.fetchArticles()
        return mapArticles(articleRaw)
    }

    private fun mapArticles(articleRaw: List<ArticleRaw>?): List<Article>? {
        return articleRaw?.map { raw ->
            Article(
                raw.title, raw.desc ?: "", raw.date, raw.imageUrl ?: ""
            )
        }
    }
}