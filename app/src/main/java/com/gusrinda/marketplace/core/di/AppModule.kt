package com.gusrinda.marketplace.core.di

import com.gusrinda.marketplace.core.data.source.local.LocalDataSource
import com.gusrinda.marketplace.core.data.source.remote.RemoteDataSource
import com.gusrinda.marketplace.core.data.source.remote.network.ApiConfig
import org.koin.dsl.module

val appModule = module {
    single { ApiConfig.provideApiService }

    single { RemoteDataSource(get()) }

    single { LocalDataSource() }
}