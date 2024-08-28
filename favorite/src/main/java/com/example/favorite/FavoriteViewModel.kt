package com.example.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.GithubUseCase

class FavoriteViewModel(val githubUseCase: GithubUseCase): ViewModel() {
    fun gettAllFavorite() = githubUseCase.getFavoriteGithub().asLiveData()
}