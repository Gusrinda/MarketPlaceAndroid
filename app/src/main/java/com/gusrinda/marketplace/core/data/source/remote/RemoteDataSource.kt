package com.gusrinda.marketplace.core.data.source.remote

import com.github.drjacky.imagepicker.ImagePicker
import com.gusrinda.marketplace.core.data.source.remote.network.ApiService
import com.gusrinda.marketplace.core.data.source.remote.request.DaftarTokoRequest
import com.gusrinda.marketplace.core.data.source.remote.request.LoginRequest
import com.gusrinda.marketplace.core.data.source.remote.request.RegisterRequest
import com.gusrinda.marketplace.core.data.source.remote.request.UpdateRequest
import okhttp3.MultipartBody

class RemoteDataSource(private val api: ApiService) {

    suspend fun login(dataLogin: LoginRequest) = api.login(dataLogin)

    suspend fun register(dataRegister: RegisterRequest) = api.register(dataRegister)

    suspend fun update(id: Int, dataUpdate: UpdateRequest) = api.updateUser(id, dataUpdate)

    suspend fun updateProfilePhoto(id: Int? = null, fileImage: MultipartBody.Part? = null) =
        api.updatePhotoProfile(id, fileImage)

    suspend fun daftarToko(dataDaftarTokoRequest: DaftarTokoRequest) =
        api.registerToko(dataDaftarTokoRequest)

    suspend fun getTokoUser(id: Int) = api.getTokoUser(id)

}