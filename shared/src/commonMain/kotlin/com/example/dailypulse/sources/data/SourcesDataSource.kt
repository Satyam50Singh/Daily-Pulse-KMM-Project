package com.example.dailypulse.sources.data

import com.example.dailypulse.db.DailyPulseDatabase
import com.example.dailypulse.sources.domain.Source

class SourcesDataSource(private val dailyPulseDatabase: DailyPulseDatabase) {

    fun getAllSources(): List<SourcesRaw> =
        dailyPulseDatabase.dailyPulseDatabaseQueries.selectAllSources(::mapToSourcesRaw)
            .executeAsList()

    fun insertSource(sources: List<SourcesRaw>) {
        dailyPulseDatabase.dailyPulseDatabaseQueries.transaction {
            sources.forEach { source ->
                insertSourceRaw(source)
            }
        }
    }

    fun clearSourcesDb() {
        dailyPulseDatabase.dailyPulseDatabaseQueries.removeAllSources()
    }

    private fun insertSourceRaw(source: SourcesRaw) {
        dailyPulseDatabase.dailyPulseDatabaseQueries.insertSource(
            source.id,
            source.name,
            source.description,
            source.url,
            source.category,
            source.language,
            source.country
        )
    }

    private fun mapToSourcesRaw(
        id: String,
        name: String,
        desc: String,
        url: String,
        category: String,
        lang: String,
        country: String
    ): SourcesRaw = SourcesRaw(
        id, name, desc, url, category, lang, country
    )
}