package com.example.filma.details.di

import com.example.filma.details.data.DetailsRepositoryImpl
import com.example.filma.details.domain.DetailsRepository
import org.koin.dsl.module

val detailsModule = module {
    single<DetailsRepository> { DetailsRepositoryImpl(apiService = get()) }
}