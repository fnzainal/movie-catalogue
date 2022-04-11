package com.zainalfn.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zainalfn.core.data.source.local.entity.CatalogueDetailEntity
import com.zainalfn.core.data.source.local.entity.CatalogueEntity

@Database(
    entities = [CatalogueDetailEntity::class, CatalogueEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CatalogueDatabase : RoomDatabase() {
    abstract fun catalogueDao(): CatalogueDao

    companion object {
        @Volatile
        private var INSTANCE: CatalogueDatabase? = null

        fun getInstance(context: Context): CatalogueDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: Room.databaseBuilder(
                        context.applicationContext,
                        CatalogueDatabase::class.java,
                        "movie_catalogue.db"
                    ).build()
            }
    }
}