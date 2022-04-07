package com.gusrinda.marketplace.core.data.source.remote.request

data class RegisterRequest(
    val name : String,
    val phone : String,
    val email : String,
    val password : String
)
