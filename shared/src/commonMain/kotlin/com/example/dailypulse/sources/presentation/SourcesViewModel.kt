package com.example.dailypulse.sources.presentation

import com.example.dailypulse.BaseViewModel
import com.example.dailypulse.sources.domain.Source
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SourcesViewModel : BaseViewModel() {

    private val _sourcesState: MutableStateFlow<SourceState> =
        MutableStateFlow(SourceState(loading = true))

    val sourceState: StateFlow<SourceState>
        get() = _sourcesState

    init {
        getSources()
    }

    fun getSources() {
        scope.launch {
            delay(1000)

            _sourcesState.emit(SourceState(loading = false, error = "Something went wrong!!"))

            delay(1000)

            val response = fetchSources()

            _sourcesState.emit(SourceState(error = null, sources = response))
        }
    }

    suspend fun fetchSources(): List<Source> {
        return mockSources
    }

    val mockSources = listOf(
        Source(
            id = "abc-news",
            name = "ABC News",
            desc = "Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.",
            url = "https://abcnews.go.com",
            category = "general",
            lang = "en",
            country = "us"
        ),
        Source(
            id = "bbc-news",
            name = "BBC News",
            desc = "Use BBC News for up-to-the-minute news, breaking news, video, audio and feature stories.",
            url = "https://www.bbc.co.uk/news",
            category = "general",
            lang = "en",
            country = "gb"
        ),
        Source(
            id = "cnn",
            name = "CNN",
            desc = "View the latest news and breaking news today for U.S., world, weather, entertainment, politics and health at CNN.com.",
            url = "https://www.cnn.com",
            category = "general",
            lang = "en",
            country = "us"
        ),
        Source(
            id = "techcrunch",
            name = "TechCrunch",
            desc = "Breaking technology news, analysis, and opinions from TechCrunch.",
            url = "https://techcrunch.com",
            category = "technology",
            lang = "en",
            country = "us"
        ),
        Source(
            id = "al-jazeera-english",
            name = "Al Jazeera English",
            desc = "News, analysis from the Middle East & worldwide, multimedia & interactives, opinions, documentaries, podcasts, long reads and broadcast schedule.",
            url = "https://www.aljazeera.com",
            category = "general",
            lang = "en",
            country = "qa"
        )
    )
}
