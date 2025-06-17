package com.example.dailypulse.di

import app.cash.sqldelight.db.SqlDriver
import com.example.dailypulse.db.DailyPulseDatabase
import com.example.dailypulse.db.DatabaseDriverFactory
import org.koin.dsl.module

val databaseModule = module {
    single<SqlDriver> { DatabaseDriverFactory().createDriver() }

    single<DailyPulseDatabase> { DailyPulseDatabase(get()) }
}