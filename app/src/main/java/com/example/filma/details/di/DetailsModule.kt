package com.example.filma.details.di

import com.example.filma.details.data.DetailsRepositoryImpl
import com.example.filma.details.domain.DetailsRepository
import com.example.filma.details.domain.DetailsUseCases
import com.example.filma.details.ui.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsModule = module {
    single<DetailsRepository> { DetailsRepositoryImpl(apiService = get()) }
    single { DetailsUseCases(repository = get()) }
    viewModel {DetailsViewModel(useCases = get())}
}