package com.gusrinda.marketplace.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gusrinda.marketplace.NavigationActivity
import com.gusrinda.marketplace.core.data.source.remote.network.State
import com.gusrinda.marketplace.core.data.source.remote.request.LoginRequest
import com.gusrinda.marketplace.databinding.ActivityLoginBinding
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel: AuthViewModel by viewModel()

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()

    }

    private fun setupViewModel() {

        binding.btnLogin.setOnClickListener {
            login()
        }

        binding.btnDaftar.setOnClickListener {
            intentActivity(RegisterActivity::class.java)
        }
    }

    private fun login() {

        if (binding.edtEmail.isEmpty()) return
        if (binding.edtPassword.isEmpty()) return

        val loginRequest =
            LoginRequest(binding.edtEmail.text.toString(), binding.edtPassword.text.toString())

        viewModel.loginUser(loginRequest).observe(this) {
            when (it.state) {
                State.LOADING -> {
                    showLoading()
                }
                State.SUCCESS -> {
                    dismisLoading()
                    showToast("Selamat datang " + it.data?.name)
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