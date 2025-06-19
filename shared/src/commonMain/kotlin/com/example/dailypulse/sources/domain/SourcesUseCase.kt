package com.example.dailypulse.sources.domain

import com.example.dailypulse.sources.data.SourcesRaw
import com.example.dailypulse.sources.data.SourcesService

class SourcesUseCase(private val sourcesService: SourcesService) {

    suspend fun getSources(): List<Source>? {
        val sourcesRaw = sourcesService.fetchSources()
        return mapSources(sourcesRaw)
    }

    private fun mapSources(raws: List<SourcesRaw>?): List<Source>? {
        return raws?.map { raw ->
            Source(
                raw.id, raw.name, raw.description, raw.url, raw.category, raw.language, raw.country
            )
        }
    }
}