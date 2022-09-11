package com.example.filma._core.di

import com.example.filma._core.data.api.ApiService
import org.koin.dsl.module

val appModule = module {
    single { ApiService.getInstance() }
}