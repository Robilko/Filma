package com.example.filma

import android.app.Application
import com.example.filma._core.di.appModule
import com.example.filma.details.di.detailsModule
import com.example.filma.main.di.mainModule
import com.example.filma.search.di.searchModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        instance = this
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(appModule, mainModule, detailsModule, searchModule)
        }
    }

    companion object {
        var instance: App? = null
            private set
    }
}