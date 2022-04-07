package com.gusrinda.marketplace.ui.toko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gusrinda.marketplace.NavigationActivity
import com.gusrinda.marketplace.R
import com.gusrinda.marketplace.core.data.source.remote.network.State
import com.gusrinda.marketplace.core.data.source.remote.request.DaftarTokoRequest
import com.gusrinda.marketplace.core.data.source.remote.request.RegisterRequest
import com.gusrinda.marketplace.databinding.ActivityBukaTokoBinding
import com.gusrinda.marketplace.ui.auth.AuthViewModel
import com.gusrinda.marketplace.util.Prefs
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BukaToko : AppCompatActivity() {

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
                    pushActivity(TokoSayaActivity::class.java)
                }
                State.ERROR -> {
                    dismisLoading()
                    toastError(it.message ?: "Error")
                }
            }

        }

    }
}