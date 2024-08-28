package com.example.capstone.di

import com.example.capstone.detail.DetailViewModel
import com.example.capstone.home.MainViewModel
import com.example.core.domain.usecase.GithubInteractor
import com.example.core.domain.usecase.GithubUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GithubUseCase> { GithubInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }

}