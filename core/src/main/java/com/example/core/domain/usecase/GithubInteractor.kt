package com.example.core.domain.usecase

import com.example.core.domain.model.GithubDetail
import com.example.core.domain.repository.IGithubRepository

class GithubInteractor(private val githubRepository: IGithubRepository): GithubUseCase {
    override fun getAllGithub(querry: String) = githubRepository.getAllGithub(querry)
    override fun getDetailUser(username: String) = githubRepository.getDetailUser(username)
    override fun getFavoriteGithub() = githubRepository.getFavoriteGithub()
    override suspend fun setFavoriteGithub(githubfav: GithubDetail) = githubRepository.setFavoriteGithub(githubfav)
    override fun getFavoriteUserByUsername(username: String) = githubRepository.getFavoriteUserByUsername(username)
    override fun deleteFavoriteUserByUsername(username: String) = githubRepository.deleteFavoriteUserByUsername(username)
}