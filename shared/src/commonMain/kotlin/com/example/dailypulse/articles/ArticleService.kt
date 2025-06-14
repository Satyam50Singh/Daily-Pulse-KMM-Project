package com.example.dailypulse.articles

import com.example.dailypulse.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticleService(private val httpClient: HttpClient) {

    private val country = "us"
    private val category = "business"

    suspend fun fetchArticles(): List<ArticleRaw>? {
        val response: ArticleResponse =
            httpClient.get("https://newsapi.org/v2/top-headlines?country=$country&category=${category}&apiKey=${Constants.API_KEY}")
                .body()
        if (response.status == "ok") {
            return response.articles
        }
        return null
    }
}