package com.example.dailypulse.android.di

import com.example.dailypulse.articles.presentation.ArticlesViewModel
import com.example.dailypulse.sources.presentation.SourcesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        ArticlesViewModel(get())
        SourcesViewModel(get())
    }
}