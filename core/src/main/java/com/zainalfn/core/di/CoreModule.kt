package com.zainalfn.core.di

import androidx.room.Room
import com.zainalfn.core.BuildConfig.BASE_URL
import com.zainalfn.core.BuildConfig.DEBUG
import com.zainalfn.core.data.CatalogueRepository
import com.zainalfn.core.data.source.local.LocalDataSource
import com.zainalfn.core.data.source.local.room.CatalogueDatabase
import com.zainalfn.core.data.source.remote.RemoteDataSource
import com.zainalfn.core.domain.repository.ICatalogueRepository
import com.zainalfn.core.data.source.remote.network.ApiService
import com.zainalfn.core.util.SSLCertificateConfigurator
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.X509TrustManager

val databaseModule = module {
    factory { get<CatalogueDatabase>().catalogueDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("zainalfn".toCharArray())
        val factory = SupportFactory(passphrase)

        Room.databaseBuilder(
            androidContext(),
            CatalogueDatabase::class.java,
            "movie_catalogue.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val trustManagerFactory = SSLCertificateConfigurator.getTrustManager(androidContext())
        val trustManagers = trustManagerFactory.trustManagers
        if (trustManagers.size != 1 || trustManagers[0] !is X509TrustManager) {
            throw IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers))
        }
        val trustManager = trustManagers[0] as X509TrustManager

        val loggingInterceptor =
            if (DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        OkHttpClient.Builder()
            .sslSocketFactory(SSLCertificateConfigurator.getSSLConfiguration(androidContext()).socketFactory, trustManager)
            .addInterceptor(HttpLoggingInterceptor().setLevel(loggingInterceptor))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<ICatalogueRepository> { CatalogueRepository(get(), get()) }
}