package com.zainalfn.core.data.source.local.room

import androidx.room.Database
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
}