package com.gusrinda.marketplace

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.gusrinda.marketplace.databinding.ActivityNavigationBinding
import com.gusrinda.marketplace.ui.auth.LoginActivity
import com.gusrinda.marketplace.util.Prefs

class NavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
}