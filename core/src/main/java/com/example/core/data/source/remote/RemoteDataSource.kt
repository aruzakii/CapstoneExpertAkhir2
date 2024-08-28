package com.example.core.data.source.remote

import android.util.Log
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.response.DetailUserGithubResponse
import com.example.core.data.source.remote.response.ItemsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getAllGithub(querry : String): Flow<ApiResponse<List<ItemsItem>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getListUsers(querry)
                val dataArray = response.items

                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.items))
                } else {
                    emit(ApiResponse.Empty)
                }

            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailUSer(username: String): Flow<ApiResponse<DetailUserGithubResponse>> {
        return flow {
            try {
                val response = apiService.getDetailUser(username)
                val data = response
                if (data != null){
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}