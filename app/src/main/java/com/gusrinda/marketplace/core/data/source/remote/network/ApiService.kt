package com.gusrinda.marketplace.core.data.source.remote.network

import com.gusrinda.marketplace.core.data.source.remote.request.DaftarTokoRequest
import com.gusrinda.marketplace.core.data.source.remote.request.LoginRequest
import com.gusrinda.marketplace.core.data.source.remote.request.RegisterRequest
import com.gusrinda.marketplace.core.data.source.remote.request.UpdateRequest
import com.gusrinda.marketplace.core.data.source.remote.response.*
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

    // @Headers(API)
    @POST("register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): Response<RegisterResponse>

    @PUT("update-user/{id}")
    suspend fun updateUser(
        @Path("id") id: Int,
        @Body updateRequest: UpdateRequest
    ): Response<UpdateUserResponse>

    @Multipart
    @POST("upload-photo-user/{id}")
    suspend fun updatePhotoProfile(
        @Path("id") id: Int? = null,
        @Part data: MultipartBody.Part? = null
    ): Response<UpdateUserResponse>

    // @Headers(API)
    @POST("toko")
    suspend fun registerToko(
        @Body daftarTokoRequest: DaftarTokoRequest
    ): Response<BaseResponse<TokoResponse>>

    // @Headers(API)
    @POST("toko")
    suspend fun registerProduct(
        @Body daftarTokoRequest: DaftarTokoRequest
    ): Response<BaseResponse<ProductResponse>>

    @GET("toko-user/{id}")
    suspend fun getTokoUser(
        @Path("id") id: Int? = null
    ): Response<LoginResponse>

}