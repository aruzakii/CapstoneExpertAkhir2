package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.response.DetailUserGithubResponse
import com.example.core.data.source.remote.response.GithubResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    suspend fun getListUsers(@Query("q") q: String): GithubResponse

    @GET("users/{username}")
    suspend fun getDetailUser(@Path("username") username: String): DetailUserGithubResponse


}