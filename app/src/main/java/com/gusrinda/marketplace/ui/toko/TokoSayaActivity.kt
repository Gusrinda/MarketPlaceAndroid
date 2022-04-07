package com.gusrinda.marketplace.ui.toko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gusrinda.marketplace.R
import com.gusrinda.marketplace.databinding.ActivityTokoSayaBinding
import com.inyongtisto.myhelper.extension.setToolbar

class TokoSayaActivity : AppCompatActivity() {


    private lateinit var binding : ActivityTokoSayaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTokoSayaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar(binding.lyToolbar.toolbar, "Toko Saya")

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}