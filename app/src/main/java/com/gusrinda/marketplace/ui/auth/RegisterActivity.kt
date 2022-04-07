package com.gusrinda.marketplace.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gusrinda.marketplace.ui.navigation.NavigationActivity
import com.gusrinda.marketplace.core.data.source.remote.network.State
import com.gusrinda.marketplace.core.data.source.remote.request.RegisterRequest
import com.gusrinda.marketplace.databinding.ActivityRegisterBinding
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private val viewModel: AuthViewModel by viewModel()

    private var _binding: ActivityRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()

    }

    private fun setupViewModel() {
        binding.btnDaftar.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        if (binding.edtNama.isEmpty()) return
        if (binding.edtPhone.isEmpty()) return
        if (binding.edtEmail.isEmpty()) return
        if (binding.edtPassword.isEmpty()) return

        val registerRequest = RegisterRequest(
            binding.edtNama.text.toString(),
            binding.edtPhone.text.toString(),
            binding.edtEmail.text.toString(),
            binding.edtPassword.text.toString()
        )

        viewModel.registerUser(registerRequest).observe(this) {
            when (it.state) {
                State.LOADING -> {
                    showLoading()
                }
                State.SUCCESS -> {
                    dismisLoading()
                    showToast("Selamat bergabung " + it.data?.name)
                    pushActivity(NavigationActivity::class.java)
                }
                State.ERROR -> {
                    dismisLoading()
                    toastError(it.message ?: "Error")
                }
            }

        }

    }
}