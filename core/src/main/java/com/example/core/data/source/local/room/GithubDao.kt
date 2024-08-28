package com.example.core.data.source.local.room

import androidx.room.*
import com.example.core.data.source.local.entity.GithubDetailEntity
import com.example.core.data.source.local.entity.GithubEntity
import com.example.core.data.source.local.entity.GithubFavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GithubDao {
    @Query("SELECT * FROM github")
    fun getAllGithub(): Flow<List<GithubEntity>>

    @Query("SELECT * FROM githubdetail")
    fun getDetailGithub(): Flow<List<GithubDetailEntity>>

    @Query("DELETE FROM github")
    suspend fun deleteAllGithub()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGithub(github: List<GithubEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGithubDetail(githubDetail:List<GithubDetailEntity>)

    //Favorite
    @Query("DELETE FROM githubdetail")
    suspend fun deleteGithubDetail()

    @Query("SELECT * FROM githubfav")
    fun getFavoriteGithub(): Flow<List<GithubFavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavGithub(favgit: GithubFavoriteEntity)

    @Query("DELETE  FROM githubfav WHERE login = :username")
    fun deleteFavoriteUserByUsername(username: String)

    @Query("SELECT * FROM githubfav WHERE login = :username")
    fun getFavoriteUserByUsername(username: String): Flow<List<GithubFavoriteEntity>>

}