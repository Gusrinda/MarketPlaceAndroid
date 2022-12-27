package com.gusrinda.marketplace.ui.alamatToko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gusrinda.marketplace.core.data.source.remote.network.State
import com.gusrinda.marketplace.databinding.ActivityAlamatTokoBinding
import com.gusrinda.marketplace.databinding.ActivityTokoSayaBinding
import com.gusrinda.marketplace.ui.alamatToko.adapter.AlamatTokoAdapter
import com.gusrinda.marketplace.ui.home.adapter.CategoryAdapter
import com.gusrinda.marketplace.ui.navigation.NavigationActivity
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlamatTokoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlamatTokoBinding
    private val viewModel: AlamatTokoViewModel by viewModel()

    private val alamatTokoAdapter = AlamatTokoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlamatTokoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar(binding.lyToolbar.toolbar, "Daftar Alamat")

        setupToolbar()
        mainButton()
        setupAdapter()
        getData()

    }

    override fun onResume() {
        getData()
        super.onResume()
    }

    private fun setupToolbar() {
        binding.lyToolbar.btnTambah.toVisible()
    }

    private fun setupAdapter() {
        binding.rvAlamat.adapter = alamatTokoAdapter
    }

    private fun getData() {
        viewModel.getAlamatToko().observe(this) {
            when (it.state) {
                State.LOADING -> {
                    showLoading()
                }
                State.SUCCESS -> {
                    binding.tvError.toGone()
                    dismisLoading()
                    val data = it.data ?: emptyList()
                    alamatTokoAdapter.addItems(data)

                    if (data.isEmpty()) {
                        binding.tvError.toVisible()
                    }
                }
                State.ERROR -> {
                    dismisLoading()
                    binding.tvError.toVisible()
                    toastError(it.message ?: "Error")
                }
            }
        }
    }

    private fun mainButton() {
        binding.lyToolbar.btnTambah.setOnClickListener {
            intentActivity(TambahAlamatTokoActivity::class.java)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}