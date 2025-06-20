package com.example.dailypulse.sources.data

class SourcesRepository(
    private val sourcesService: SourcesService,
    private val dataSource: SourcesDataSource
) {

    suspend fun getSources(): List<SourcesRaw>? {

        val sourcesRaw = dataSource.getAllSources()

        if (sourcesRaw.isEmpty()) {
            val fetchedSources = sourcesService.fetchSources();
            fetchedSources?.let { dataSource.insertSource(it) }
            return fetchedSources
        }

        return sourcesRaw
    }

}