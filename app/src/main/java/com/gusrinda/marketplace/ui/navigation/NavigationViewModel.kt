package com.gusrinda.marketplace.ui.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.gusrinda.marketplace.core.data.repository.AppRepository
import com.gusrinda.marketplace.core.data.source.remote.request.DaftarTokoRequest
import com.gusrinda.marketplace.core.data.source.remote.request.LoginRequest
import com.gusrinda.marketplace.core.data.source.remote.request.RegisterRequest

class NavigationViewModel(val repo : AppRepository) : ViewModel() {

    fun getTokoUser(id : Int) = repo.getTokoUser(id).asLiveData()

}