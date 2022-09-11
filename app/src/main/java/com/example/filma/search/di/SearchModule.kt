package com.example.filma.search.di

import com.example.filma.search.data.SearchMovieRepo
import com.example.filma.search.data.SearchRepositoryImpl
import com.example.filma.search.domain.SearchRepository
import com.example.filma.search.domain.SearchUseCases
import com.example.filma.search.ui.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    single<SearchRepository> { SearchRepositoryImpl(apiService = get()) }
    single { SearchUseCases(repository = get()) }
    single { SearchMovieRepo(useCases = get()) }
    viewModel {SearchViewModel(repository = get())}
}