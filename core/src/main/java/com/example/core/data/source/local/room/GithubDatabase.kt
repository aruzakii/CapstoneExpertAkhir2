package com.example.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.source.local.entity.GithubDetailEntity
import com.example.core.data.source.local.entity.GithubEntity
import com.example.core.data.source.local.entity.GithubFavoriteEntity

@Database(entities = [GithubEntity::class, GithubDetailEntity::class, GithubFavoriteEntity::class], version = 3, exportSchema = false)
abstract class GithubDatabase: RoomDatabase() {
     abstract fun githubDao(): GithubDao
}