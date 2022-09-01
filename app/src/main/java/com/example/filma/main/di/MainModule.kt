package com.example.filma.main.di

import com.example.filma.main.data.MainRepositoryImpl
import com.example.filma.main.domain.MainRepository
import org.koin.dsl.module

val mainModule = module {
    single<MainRepository> { MainRepositoryImpl(apiService = get()) }
}