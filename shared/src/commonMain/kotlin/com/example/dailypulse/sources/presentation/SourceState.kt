package com.example.dailypulse.sources.presentation

import com.example.dailypulse.sources.domain.Source

data class SourceState(
    val sources: List<Source>? = listOf(),
    val loading: Boolean = false,
    val error: String? = null
)
