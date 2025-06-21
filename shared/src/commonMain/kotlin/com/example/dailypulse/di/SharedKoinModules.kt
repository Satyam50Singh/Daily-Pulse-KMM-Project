package com.example.dailypulse.di

import com.example.dailypulse.articles.di.articlesModule
import com.example.dailypulse.sources.di.sourceModule

val sharedModules = listOf(
    articlesModule,
    networkModule,
    sourceModule
)