package com.gusrinda.marketplace.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.gusrinda.marketplace.core.data.repository.AppRepository
import com.gusrinda.marketplace.core.data.source.remote.request.LoginRequest
import com.gusrinda.marketplace.core.data.source.remote.request.RegisterRequest

class AuthViewModel(val repo : AppRepository) : ViewModel() {

    fun loginUser(data : LoginRequest) = repo.loginUser(data).asLiveData()

    fun registerUser(data: RegisterRequest) = repo.registerUser(data).asLiveData()

}