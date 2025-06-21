package com.example.dailypulse.sources.data

import com.example.dailypulse.Constants.API_KEY
import com.example.dailypulse.Constants.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class SourcesService(private val httpClient: HttpClient) {

    suspend fun fetchSources(): List<SourcesRaw>? {
        return try {
            val url = "$BASE_URL/top-headlines/sources?apiKey=$API_KEY"
            println("URL - $url")
            val response: SourcesResponse = httpClient.get(url).body()
            println("Status: ${response.status} \nSourcesRaw: ${response.sourcesRaw}")
            if (response.status == "ok") response.sourcesRaw else null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}