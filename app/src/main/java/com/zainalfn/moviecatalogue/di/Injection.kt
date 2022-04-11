package com.zainalfn.moviecatalogue.di

import android.content.Context
import com.zainalfn.core.data.CatalogueRepository
import com.zainalfn.core.data.source.local.LocalDataSource
import com.zainalfn.core.data.source.local.room.CatalogueDatabase
import com.zainalfn.core.data.source.remote.RemoteDataSource
import com.zainalfn.core.util.AppExecutors

object Injection {
    fun provideRepository(context: Context): CatalogueRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        val database = CatalogueDatabase.getInstance(context)
        val localDataSource = LocalDataSource.getInstance(database.catalogueDao())
        val appExecutors = AppExecutors()
        return CatalogueRepository.getInstance(remoteDataSource,
        localDataSource,
        appExecutors)
    }
}