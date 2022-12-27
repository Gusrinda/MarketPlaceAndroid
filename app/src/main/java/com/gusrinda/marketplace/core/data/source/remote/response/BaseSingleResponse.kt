package com.gusrinda.marketplace.core.data.source.remote.response

import com.gusrinda.marketplace.core.data.source.model.User

data class BaseSingleResponse<T>(
    val code: Int? = null,
    val success: Boolean? = null,
    val message: String? = null,
    val data: T? = null
)