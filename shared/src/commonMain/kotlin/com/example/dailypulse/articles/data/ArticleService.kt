package com.example.dailypulse.articles.data

import com.example.dailypulse.Constants
import com.example.dailypulse.Constants.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticleService(private val httpClient: HttpClient) {

    private val country = "us"
    private val category = "business" // sports

    suspend fun fetchArticles(): List<ArticleRaw>? {
        return try {
            val response: ArticleResponse =
                httpClient.get("$BASE_URL/top-headlines?country=$country&category=${category}&apiKey=${Constants.API_KEY}")
                    .body()
            if (response.status == "ok") response.articles else null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}