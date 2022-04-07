package com.gusrinda.marketplace.core.data.repository

import android.util.Log
import com.gusrinda.marketplace.core.data.source.local.LocalDataSource
import com.gusrinda.marketplace.core.data.source.remote.RemoteDataSource
import com.gusrinda.marketplace.core.data.source.remote.network.Resource
import com.gusrinda.marketplace.core.data.source.remote.request.DaftarTokoRequest
import com.gusrinda.marketplace.core.data.source.remote.request.LoginRequest
import com.gusrinda.marketplace.core.data.source.remote.request.RegisterRequest
import com.gusrinda.marketplace.core.data.source.remote.request.UpdateRequest
import com.gusrinda.marketplace.util.Prefs
import com.inyongtisto.myhelper.extension.getErrorBody
import com.inyongtisto.myhelper.extension.logs
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import java.lang.Exception

class AppRepository(val local: LocalDataSource, val remote: RemoteDataSource) {

    fun loginUser(data : LoginRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.login(data).let {
                if (it.isSuccessful) {
                    Prefs.isLogin = true
                    val body = it.body()
                    val user = body?.data
                    Prefs.setUser(user)
                    emit(Resource.success(body?.data))
                    logs("Success :: " + it.body().toString())
                } else {
                    emit(Resource.error(it.getErrorBody()?.message ?: "Default error", null))
                    logs("Error Not Success :: " + it.message())
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message?: "Terjadi Kesalahan !", null))
            logs("LOGIN ERROR :: " + e.message)
        }
    }

    fun registerUser(data : RegisterRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.register(data).let {
                if (it.isSuccessful) {
                    Prefs.isLogin = true
                    val body = it.body()
                    val user = body?.data
                    Prefs.setUser(user)
                    emit(Resource.success(body?.data))
                    logs("Success :: " + it.body().toString())
                } else {
                    emit(Resource.error(it.getErrorBody()?.message ?: "Default error", null))
                    logs("Error Not Success :: " + it.message())
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message?: "Terjadi Kesalahan !", null))
            logs("REGISTER ERROR :: " + e.message)
        }
    }

    fun updateUser(id : Int, data : UpdateRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.update(id, data).let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val user = body?.data
                    Prefs.setUser(user)
                    emit(Resource.success(body?.data))
                    logs("Success :: " + it.body().toString())
                } else {
                    emit(Resource.error(it.getErrorBody()?.message ?: "Default error", null))
                    logs("Error Not Success :: " + it.message())
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message?: "Terjadi Kesalahan !", null))
            logs("LOGIN ERROR :: " + e.message)
        }
    }

    fun updateProfilePhoto(id: Int? = null, fileImage: MultipartBody.Part? = null) = flow {
        emit(Resource.loading(null))
        try {
            remote.updateProfilePhoto(id, fileImage).let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val user = body?.data
                    Prefs.setUser(user)
                    emit(Resource.success(body?.data))
                    logs("Success :: " + it.body().toString())
                } else {
                    emit(Resource.error(it.getErrorBody()?.message ?: "Default error", null))
                    logs("Error Not Success :: " + it.message())
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message?: "Terjadi Kesalahan !", null))
            logs("LOGIN ERROR :: " + e.message)
        }
    }

    fun daftarToko(dataRequest : DaftarTokoRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.daftarToko(dataRequest).let {
                if (it.isSuccessful) {
                    val body = it.body()
                    emit(Resource.success(body?.data))
                    logs("Success :: " + it.body().toString())
                } else {
                    emit(Resource.error(it.getErrorBody()?.message ?: "Default error", null))
                    logs("Error Not Success :: " + it.message())
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message?: "Terjadi Kesalahan !", null))
            logs("DAFTAR TOKO ERROR :: " + e.message)
        }
    }

    fun getTokoUser(id : Int) = flow {
        emit(Resource.loading(null))
        try {
            remote.getTokoUser(id).let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val user = body?.data
                    Prefs.setUser(user)
                    emit(Resource.success(body?.data))
                    logs("Success :: " + it.body().toString())
                } else {
                    emit(Resource.error(it.getErrorBody()?.message ?: "Default error", null))
                    logs("Error Not Success :: " + it.message())
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message?: "Terjadi Kesalahan !", null))
            logs("DAFTAR TOKO ERROR :: " + e.message)
        }
    }

}