package com.example.dailypulse.articles.di

import com.example.dailypulse.articles.data.ArticleDataSource
import com.example.dailypulse.articles.data.ArticleRepository
import com.example.dailypulse.articles.data.ArticleService
import com.example.dailypulse.articles.domain.ArticleUseCase
import com.example.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {
    single<ArticleService> { ArticleService(get()) }
    single<ArticleUseCase> { ArticleUseCase(get()) }
    single<ArticlesViewModel> { ArticlesViewModel(get()) }
    single<ArticleDataSource> { ArticleDataSource(get()) }
    single<ArticleRepository> { ArticleRepository(get(), get()) }

}