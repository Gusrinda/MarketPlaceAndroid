package com.gusrinda.marketplace.ui.toko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gusrinda.marketplace.core.data.source.model.Toko
import com.gusrinda.marketplace.core.data.source.remote.network.State
import com.gusrinda.marketplace.core.data.source.remote.request.DaftarTokoRequest
import com.gusrinda.marketplace.databinding.ActivityBukaTokoBinding
import com.gusrinda.marketplace.util.Prefs
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BukaTokoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBukaTokoBinding
    private val viewModel: TokoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBukaTokoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar(binding.lyToolbar.toolbar, "Buka Toko")
        setupMainButton()

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setupMainButton() {

        binding.btnBukaToko.setOnClickListener {
            daftarToko()
        }

    }

    private fun daftarToko() {
        if (binding.edtName.isEmpty()) return
        if (binding.edtLokasi.isEmpty()) return

        val daftarTokoRequest = DaftarTokoRequest(
            userId = Prefs.getUser()?.id ?: 0,
            name = binding.edtName.text.toString(),
            alamat = binding.edtLokasi.text.toString()
        )

        viewModel.daftarToko(daftarTokoRequest).observe(this) {
            when (it.state) {
                State.LOADING -> {
                    showLoading()
                }
                State.SUCCESS -> {
                    dismisLoading()
                    showToast("Berhasil Membuat Toko " + it.data?.name)
                    intentActivity(TokoSayaActivity::class.java)

                    val user = Prefs.getUser()
                    user?.toko = Toko(
                        name = it.data?.name,
                        alamat = it.data?.alamat,
                        userId = it.data?.userId,
                        alamatId = it.data?.alamatId,
                        id = it.data?.id,
                        image = it.data?.image,
                        created_at = it.data?.created_at,
                        updated_at = it.data?.updated_at
                    )

                    Prefs.setUser(user)

                    finish()

                }
                State.ERROR -> {
                    dismisLoading()
                    toastError(it.message ?: "Error")
                }
            }

        }

    }
}