package com.example.dailypulse.sources.data

class SourcesRepository(private val sourcesService: SourcesService) {

    suspend fun getSources(): List<SourcesRaw>? {
        val sourcesRaw = sourcesService.fetchSources()
        if (!sourcesRaw.isNullOrEmpty()) {
            return sourcesRaw
        }
        return null
    }
}