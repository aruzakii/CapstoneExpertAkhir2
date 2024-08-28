package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.Github
import com.example.core.domain.model.GithubDetail
import kotlinx.coroutines.flow.Flow

interface IGithubRepository {
    fun getAllGithub(querry: String): Flow<Resource<List<Github>>>

    fun getDetailUser(username: String) : Flow<Resource<List<GithubDetail>>>

    fun getFavoriteGithub(): Flow<List<GithubDetail>>

    suspend fun setFavoriteGithub(githubDetail: GithubDetail)

    fun getFavoriteUserByUsername(username: String): Flow<List<GithubDetail>>

    fun deleteFavoriteUserByUsername(username: String)
}