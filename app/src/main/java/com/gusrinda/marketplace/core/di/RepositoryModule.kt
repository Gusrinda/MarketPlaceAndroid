package com.gusrinda.marketplace.core.di

import com.gusrinda.marketplace.core.data.repository.AppRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AppRepository(get(), get()) }

}