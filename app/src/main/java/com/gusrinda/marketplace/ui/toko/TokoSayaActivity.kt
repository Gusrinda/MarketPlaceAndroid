package com.gusrinda.marketplace.ui.toko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isInvisible
import com.gusrinda.marketplace.R
import com.gusrinda.marketplace.databinding.ActivityTokoSayaBinding
import com.gusrinda.marketplace.ui.alamatToko.AlamatTokoActivity
import com.gusrinda.marketplace.ui.alamatToko.AlamatTokoViewModel
import com.gusrinda.marketplace.util.Constant
import com.gusrinda.marketplace.util.Prefs
import com.inyongtisto.myhelper.extension.getInitial
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.setToolbar
import com.inyongtisto.myhelper.extension.toGone
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class TokoSayaActivity : AppCompatActivity() {


    private lateinit var binding : ActivityTokoSayaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTokoSayaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar(binding.lyToolbar.toolbar, "Toko Saya")

        setButton()
        setDataToko()

    }

    private fun setButton() {

        binding.btnAlamat.setOnClickListener {
            intentActivity(AlamatTokoActivity::class.java)
        }

    }

    private fun setDataToko() {

        val user = Prefs.getUser()

        if (user != null) {
            binding.apply {

                if (user.toko != null) {
                    tvName.text = user.toko?.name
                    tvInisial.text = user.toko?.name.getInitial()

                    Picasso.get().load(Constant.BASE_IMAGE_URL + user.toko?.image).into(imageProfile)

                }
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}