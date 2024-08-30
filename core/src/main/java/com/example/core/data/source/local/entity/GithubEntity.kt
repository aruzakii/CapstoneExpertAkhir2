package com.example.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "github")
data class GithubEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "repos_url")
    var reposUrl: String,

    @ColumnInfo(name = "following_url")
    var followingUrl: String,

    @ColumnInfo(name = "login")
    var login: String,

    @ColumnInfo(name = "followers_url")
    var followersUrl: String,

    @ColumnInfo(name = "avatar_url")
    var avatarUrl: String,
)