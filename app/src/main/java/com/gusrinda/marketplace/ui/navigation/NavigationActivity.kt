package com.gusrinda.marketplace.ui.navigation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.gusrinda.marketplace.R
import com.gusrinda.marketplace.core.data.source.remote.network.State
import com.gusrinda.marketplace.databinding.ActivityNavigationBinding
import com.gusrinda.marketplace.ui.auth.LoginActivity
import com.gusrinda.marketplace.ui.toko.TokoSayaActivity
import com.gusrinda.marketplace.util.Prefs
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding
    private val viewModel: NavigationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavBar()
        getUser()


    }

    private fun getUser() {

        val id: Int = Prefs.getUser()?.id ?: 0

        viewModel.getTokoUser(id).observe(this) {}
    }

    private fun setupNavBar() {
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_navigation)


        navView.setupWithNavController(navController)
        navView.setOnItemSelectedListener {

            if (it.itemId == R.id.navigation_akun) {

                if (Prefs.isLogin) {
                    navController.navigate(it.itemId)
                } else {
                    startActivity(Intent(this, LoginActivity::class.java))
                    return@setOnItemSelectedListener false
                }

            } else {
                navController.navigate(it.itemId)
                Log.d("TAG", "onCreate: item id lain = " + it.itemId)
            }

            return@setOnItemSelectedListener true
        }
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}