package com.gusrinda.marketplace.core.data.source.remote.response

import com.gusrinda.marketplace.core.data.source.model.Toko

data class TokoResponse(
    val id: Int?,
    val userId: Int?,
    val alamatId: Int?,
    val name: String?,
    val alamat: String?,
    val image: String?,
    val created_at: String?,
    val updated_at: String?
)