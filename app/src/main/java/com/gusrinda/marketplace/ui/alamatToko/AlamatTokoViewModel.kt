package com.gusrinda.marketplace.ui.alamatToko

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.gusrinda.marketplace.core.data.repository.AppRepository
import com.gusrinda.marketplace.core.data.source.local.DummyData
import com.gusrinda.marketplace.core.data.source.model.AlamatToko
import com.gusrinda.marketplace.core.data.source.model.Slider
import com.gusrinda.marketplace.core.data.source.remote.request.AlamatTokoRequest

class AlamatTokoViewModel(private val repo : AppRepository) : ViewModel() {

    fun getAlamatToko() = repo.getAlamatToko().asLiveData()

    fun tambahAlamatToko(dataReq : AlamatTokoRequest) = repo.tambahAlamatToko(dataReq).asLiveData()


}