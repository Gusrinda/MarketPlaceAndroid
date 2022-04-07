package com.gusrinda.marketplace.core.di

import UpdateViewModel
import com.gusrinda.marketplace.ui.auth.AuthViewModel
import com.gusrinda.marketplace.ui.home.HomeViewModel
import com.gusrinda.marketplace.ui.toko.TokoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { AuthViewModel(get()) }

    viewModel { UpdateViewModel(get()) }

    viewModel { HomeViewModel() }

    viewModel { TokoViewModel(get()) }

}