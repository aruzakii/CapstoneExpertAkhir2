package com.example.core.data.source.local

import com.example.core.data.source.local.entity.GithubDetailEntity
import com.example.core.data.source.local.entity.GithubEntity
import com.example.core.data.source.local.entity.GithubFavoriteEntity
import com.example.core.data.source.local.room.GithubDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val githubDao: GithubDao) {

    fun getAllGithub(): Flow<List<GithubEntity>> = githubDao.getAllGithub()

    suspend fun insertGithub(githubList: List<GithubEntity>) {
        githubDao.insertGithub(githubList)
    }

    suspend fun deleteAllGithub(){
        githubDao.deleteAllGithub()
    }

    fun getDetailGithub(): Flow<List<GithubDetailEntity>> = githubDao.getDetailGithub()

    suspend fun insertGithubDetail(githuDetail: List<GithubDetailEntity>) {
        githubDao.insertGithubDetail(githuDetail)
    }

    suspend fun deleteGithubDetail(){
        githubDao.deleteGithubDetail()
    }

    fun getFavoriteGithub(): Flow<List<GithubFavoriteEntity>> = githubDao.getFavoriteGithub()

    suspend fun insertFavGithub(gitvav:GithubFavoriteEntity) {
        githubDao.insertFavGithub(gitvav)
    }

    fun deleteFavoriteUserByUsername(username: String) {
        githubDao.deleteFavoriteUserByUsername(username)
    }

    fun getFavoriteUserByUsername(username: String):Flow<List<GithubFavoriteEntity>> = githubDao.getFavoriteUserByUsername(username)

}