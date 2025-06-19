package com.example.dailypulse.di

import com.example.dailypulse.articles.presentation.ArticlesViewModel
import com.example.dailypulse.sources.presentation.SourcesViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin() {
    val module = sharedModules + databaseModule

    startKoin { modules(module) }
}

class BaseInjector : KoinComponent {
    val articlesViewModel: ArticlesViewModel by inject()
    val sourcesViewModel: SourcesViewModel by inject()
}