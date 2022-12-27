package com.gusrinda.marketplace.util

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.chibatching.kotpref.KotprefModel
import com.gusrinda.marketplace.core.data.source.model.User
import com.inyongtisto.myhelper.extension.toJson
import com.inyongtisto.myhelper.extension.toModel

object Prefs : KotprefModel() {

    var isLogin by booleanPref(false)
    var userData by stringPref()

    fun setUser(user: User?) {
        userData = user.toJson()
    }

    fun getUser() : User? {

        if (userData.isEmpty()) return null

        return userData.toModel(User::class.java)
    }

}

fun getTokoId() = Prefs.getUser()?.toko?.id