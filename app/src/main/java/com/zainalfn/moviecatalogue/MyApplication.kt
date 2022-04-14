package com.zainalfn.moviecatalogue

import android.app.Application
import com.zainalfn.core.di.databaseModule
import com.zainalfn.core.di.networkModule
import com.zainalfn.core.di.repositoryModule
import com.zainalfn.moviecatalogue.di.useCaseModule
import com.zainalfn.moviecatalogue.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@Suppress("unused")
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}