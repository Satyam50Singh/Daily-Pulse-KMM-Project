package com.example.dailypulse.sources.domain

import com.example.dailypulse.sources.data.SourcesRaw
import com.example.dailypulse.sources.data.SourcesRepository

class SourcesUseCase(private val sourcesRepository: SourcesRepository) {

    suspend fun getSources(): List<Source>? {
        val sourcesRaw = sourcesRepository.getSources()
        println("Sources Db has ${sourcesRaw?.size} records")
        return sourcesRaw?.let { mapSources(it) }
    }

    private fun mapSources(raws: List<SourcesRaw>): List<Source>? {
        return raws.map { raw ->
            Source(
                raw.id, raw.name, raw.description, raw.url, raw.category, raw.language, raw.country
            )
        }
    }
}