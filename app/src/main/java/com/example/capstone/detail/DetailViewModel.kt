package com.example.capstone.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.GithubDetail
import com.example.core.domain.usecase.GithubUseCase
import kotlinx.coroutines.launch

class DetailViewModel(private val githubUseCase: GithubUseCase): ViewModel() {
    fun setFavoriteUserGithub(githubDetail: GithubDetail) =
        viewModelScope.launch {
            githubUseCase.setFavoriteGithub(githubDetail)
        }

    fun getFavoriteUserbyUsername(username: String) = githubUseCase.getFavoriteUserByUsername(username).asLiveData()

    fun getGithub(username: String) = githubUseCase.getDetailUser(username).asLiveData()

    fun deleteFavorite(username: String) = githubUseCase.deleteFavoriteUserByUsername(username)
}