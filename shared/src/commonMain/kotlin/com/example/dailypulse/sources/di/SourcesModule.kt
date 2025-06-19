package com.example.dailypulse.sources.di

import com.example.dailypulse.sources.presentation.SourcesViewModel
import org.koin.dsl.module

val sourceModule = module {
    single<SourcesViewModel> { SourcesViewModel() }
}