package com.example.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "githubdetail")
data class GithubDetailEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo("followers")
    val followers: Int,

    @field:SerializedName("following")
    val following: Int,

   @ColumnInfo("name")
    val name: String,

    @ColumnInfo(name = "login")
    var login: String,

    @ColumnInfo(name = "avatar_url")
    var avatarUrl: String,
    )