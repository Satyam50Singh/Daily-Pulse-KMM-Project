package com.example.dailypulse.sources.di

import com.example.dailypulse.sources.data.SourcesRepository
import com.example.dailypulse.sources.data.SourcesService
import com.example.dailypulse.sources.domain.SourcesUseCase
import com.example.dailypulse.sources.presentation.SourcesViewModel
import org.koin.dsl.module
import kotlin.math.sin

val sourceModule = module {
    single<SourcesService> { SourcesService(get()) }
    single<SourcesRepository> { SourcesRepository(get()) }
    single<SourcesUseCase> { SourcesUseCase(get()) }
    single<SourcesViewModel> { SourcesViewModel(get()) }

}