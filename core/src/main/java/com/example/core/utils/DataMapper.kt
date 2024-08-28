package com.example.core.utils

import com.example.core.data.source.local.entity.GithubDetailEntity
import com.example.core.data.source.local.entity.GithubEntity
import com.example.core.data.source.local.entity.GithubFavoriteEntity
import com.example.core.data.source.remote.response.DetailUserGithubResponse
import com.example.core.data.source.remote.response.ItemsItem
import com.example.core.domain.model.Github
import com.example.core.domain.model.GithubDetail

object DataMapper {
    fun mapResponsesToEntities(input: List<ItemsItem>): List<GithubEntity> {
        val githubList = ArrayList<GithubEntity>()
        input.map {
            val github = GithubEntity(
                id = it.id,
                reposUrl = it.reposUrl,
                followersUrl = it.followersUrl,
                followingUrl = it.followingUrl,
                login = it.login,
                avatarUrl = it.avatarUrl,
            )
           githubList.add(github)
        }
        return githubList
    }

    fun mapEntitiesToDomain(input: List<GithubEntity>): List<Github> =
        input.map {
            Github(
                id = it.id,
                login = it.login,
                avatarUrl = it.avatarUrl,
            )
        }




    fun mapResponsesToEntities2(input: DetailUserGithubResponse): List<GithubDetailEntity> {
        val githubList = ArrayList<GithubDetailEntity>()
            val github = GithubDetailEntity(
                id =  input.id ?: 0,
                followers = input.followers ?: 0,
                following = input.following ?: 0,
                avatarUrl = input.avatarUrl ?: "",
                login = input.login ?: "",
                name = input.name ?: ""
            )
            githubList.add(github)

        return githubList
    }
    fun mapEntitiesToDomain2(input:List<GithubDetailEntity>): List<GithubDetail> =
        input.map {
            GithubDetail (
                id =  it.id,
                followers = it.followers,
                following = it.following,
                avatarUrl = it.avatarUrl,
                login = it.login,
                name = it.name
            )
        }


    fun mapEntitiesToDomainFav(input: List<GithubFavoriteEntity>): List<GithubDetail> =
        input.map {
            GithubDetail(
                id =  it.id,
                followers = it.followers,
                following = it.following,
                avatarUrl = it.avatarUrl,
                login = it.login,
                name = it.name
            )
        }

    fun mapDomainToEntityFav(input: GithubDetail) = GithubFavoriteEntity(
        id =  input.id,
        followers = input.followers,
        following = input.following,
        avatarUrl = input.avatarUrl,
        login = input.login,
        name = input.name
    )


}