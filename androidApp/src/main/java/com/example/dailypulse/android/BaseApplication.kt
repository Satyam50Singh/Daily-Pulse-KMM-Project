package com.example.dailypulse.android

import android.app.Application
import com.example.dailypulse.android.di.viewModelModule
import com.example.dailypulse.di.sharedModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        val modules = sharedModules + viewModelModule

        startKoin {
            androidContext(this@BaseApplication)
            modules(modules)
        }
    }
}