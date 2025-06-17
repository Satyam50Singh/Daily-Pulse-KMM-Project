package com.example.dailypulse.articles.di

import com.example.dailypulse.articles.ArticleDataSource
import com.example.dailypulse.articles.ArticleRepository
import com.example.dailypulse.articles.ArticleService
import com.example.dailypulse.articles.ArticleUseCase
import com.example.dailypulse.articles.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {
    single<ArticleService> { ArticleService(get()) }
    single<ArticleUseCase> { ArticleUseCase(get()) }
    single<ArticlesViewModel> { ArticlesViewModel(get()) }
    single<ArticleDataSource> { ArticleDataSource(get()) }
    single<ArticleRepository> { ArticleRepository(get(), get()) }

}