package com.example.dailypulse.sources.data

class SourcesRepository(
    private val sourcesService: SourcesService,
    private val dataSource: SourcesDataSource
) {

    suspend fun getSources(): List<SourcesRaw>? {

        val sourcesRaw: List<SourcesRaw> = dataSource.getAllSources()

        if (sourcesRaw.isEmpty()) {
            val fetchedSources: List<SourcesRaw>? = sourcesService.fetchSources();
            fetchedSources?.let { dataSource.insertSource(it) }
            return fetchedSources
        }

        return sourcesRaw
    }

}