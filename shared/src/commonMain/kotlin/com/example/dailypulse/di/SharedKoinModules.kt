package com.example.dailypulse.di

import com.example.dailypulse.articles.di.articlesModule

val sharedModules = listOf(
    articlesModule,
    networkModule
)