package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubDetail (
    var id: Int,

    var followers: Int,

    var following: Int,

    var name: String,

    var login: String,

    var avatarUrl: String,
    ): Parcelable
