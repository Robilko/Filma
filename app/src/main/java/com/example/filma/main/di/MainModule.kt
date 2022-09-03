package com.example.filma.main.di

import com.example.filma.main.data.MainRepositoryImpl
import com.example.filma.main.domain.MainRepository
import com.example.filma.main.domain.MainUseCases
import com.example.filma.main.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single<MainRepository> { MainRepositoryImpl(apiService = get()) }
    single { MainUseCases(repository = get()) }
    viewModel { MainViewModel(useCases = get()) }
}