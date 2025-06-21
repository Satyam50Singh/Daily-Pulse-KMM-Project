package com.example.dailypulse.sources.data

class SourcesRepository(
    private val sourcesService: SourcesService,
    private val dataSource: SourcesDataSource
) {

    suspend fun getSources(forceRefresh: Boolean): List<SourcesRaw>? {

        if (forceRefresh) {
            dataSource.clearSourcesDb()
            return fetchSources()
        }

        val sourcesRaw: List<SourcesRaw> = dataSource.getAllSources()

        if (sourcesRaw.isEmpty()) {
            return fetchSources()
        }

        return sourcesRaw
    }

    suspend fun fetchSources(): List<SourcesRaw>? {
        val fetchedSources: List<SourcesRaw>? = sourcesService.fetchSources();
        fetchedSources?.let { dataSource.insertSource(it) }
        return fetchedSources
    }

}