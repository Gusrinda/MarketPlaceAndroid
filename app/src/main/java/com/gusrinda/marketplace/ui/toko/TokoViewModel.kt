package com.gusrinda.marketplace.ui.toko

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.gusrinda.marketplace.core.data.repository.AppRepository
import com.gusrinda.marketplace.core.data.source.remote.request.DaftarTokoRequest
import com.gusrinda.marketplace.core.data.source.remote.request.LoginRequest
import com.gusrinda.marketplace.core.data.source.remote.request.RegisterRequest

class TokoViewModel(val repo : AppRepository) : ViewModel() {

    fun daftarToko(dataToko : DaftarTokoRequest) = repo.daftarToko(dataToko).asLiveData()

}