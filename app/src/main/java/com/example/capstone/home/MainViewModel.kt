package com.example.capstone.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.GithubUseCase

class MainViewModel(private val githubUseCase: GithubUseCase): ViewModel() {

    fun getAllGithub(querry: String) = githubUseCase.getAllGithub(querry).asLiveData()


}