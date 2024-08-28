package com.example.core.data

import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.DetailUserGithubResponse
import com.example.core.data.source.remote.response.ItemsItem
import com.example.core.domain.model.Github
import com.example.core.domain.model.GithubDetail
import com.example.core.domain.repository.IGithubRepository
import com.example.core.utils.AppExecutors
import com.example.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GithubRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IGithubRepository {

    override fun getAllGithub(querry: String): Flow<Resource<List<Github>>> =
        object : NetworkBoundResource<List<Github>, List<ItemsItem>>() {
            override fun loadFromDB(): Flow<List<Github>> {
                return localDataSource.getAllGithub().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Github>?): Boolean =
//                data == null || data.isEmpty()
                true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<ItemsItem>>> {
                return remoteDataSource.getAllGithub(querry)
            }

            override suspend fun deleteOldData() {
               localDataSource.deleteAllGithub()
            }


            override suspend fun saveCallResult(data: List<ItemsItem>) {
                val githubList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertGithub(githubList)
            }
        }.asFlow()

    override fun getDetailUser(username: String) : Flow<Resource<List<GithubDetail>>> =
        object : NetworkBoundResource<List<GithubDetail>, DetailUserGithubResponse>(){
            override fun loadFromDB(): Flow<List<GithubDetail>> {
                return localDataSource.getDetailGithub().map {
                    DataMapper.mapEntitiesToDomain2(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<DetailUserGithubResponse>> {
              return remoteDataSource.getDetailUSer(username)
            }

            override suspend fun deleteOldData() {
                localDataSource.deleteGithubDetail()
            }

            override suspend fun saveCallResult(data:DetailUserGithubResponse) {
                val githubdetail = DataMapper.mapResponsesToEntities2(data)
                localDataSource.insertGithubDetail(githubdetail)
            }

            override fun shouldFetch(data: List<GithubDetail>?): Boolean = true

        }.asFlow()

    override fun getFavoriteGithub(): Flow<List<GithubDetail>> {
        return localDataSource.getFavoriteGithub().map {
            DataMapper.mapEntitiesToDomainFav(it)
        }
    }

    override suspend fun setFavoriteGithub(githubDetail: GithubDetail) {
       val githubfavEntity = DataMapper.mapDomainToEntityFav(githubDetail)
        localDataSource.insertFavGithub(githubfavEntity)
    }

    override fun getFavoriteUserByUsername(username: String): Flow<List<GithubDetail>> {
        return localDataSource.getFavoriteUserByUsername(username).map {
            DataMapper.mapEntitiesToDomainFav(it)
        }
    }

    override fun deleteFavoriteUserByUsername(username: String) {
        appExecutors.diskIO().execute{localDataSource.deleteFavoriteUserByUsername(username)}
    }


}
