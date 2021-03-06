package com.gusrinda.marketplace.ui.akun

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gusrinda.marketplace.ui.navigation.NavigationActivity
import com.gusrinda.marketplace.databinding.FragmentAkunBinding
import com.gusrinda.marketplace.ui.toko.BukaTokoActivity
import com.gusrinda.marketplace.ui.toko.TokoSayaActivity
import com.gusrinda.marketplace.ui.updateProfile.UpdateProfileActivity
import com.gusrinda.marketplace.util.Constant
import com.gusrinda.marketplace.util.Prefs
import com.inyongtisto.myhelper.extension.getInitial
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.pushActivity
import com.inyongtisto.myhelper.extension.toGone
import com.squareup.picasso.Picasso

class AkunFragment : Fragment() {

    private lateinit var akunViewModel: AkunViewModel

    private var _binding: FragmentAkunBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val akunViewModel =
            ViewModelProvider(this).get(AkunViewModel::class.java)

        _binding = FragmentAkunBinding.inflate(inflater, container, false)
        val root: View = binding.root


        setupMainButton()

        return root
    }

    override fun onResume() {
        setupAkunUser()
        super.onResume()
    }

    private fun setupMainButton() {

        binding.btnEdit.setOnClickListener {
            intentActivity(UpdateProfileActivity::class.java)
        }

        binding.btnLogout.setOnClickListener {
            Prefs.isLogin = false
            pushActivity(NavigationActivity::class.java)
        }


    }

    private fun setupAkunUser() {

        val user = Prefs.getUser()

        if (user != null) {
            binding.apply {
                txtNamaUser.text = user.name
                txtPhoneUser.text = user.phone
                txtEmailUser.text = user.email
                txtInisialUser.text = user.name.getInitial()

                if (user.image != null) {
                    binding.txtInisialUser.isInvisible = true
                }

                Picasso.get().load(Constant.BASE_IMAGE_URL + user.image).into(binding.imgUser)

                if (user.toko != null) {
                    tvNamaToko.text = user.toko?.name
                    tvStatusToko.toGone()
                    btnBukaToko.setOnClickListener {
                        intentActivity(TokoSayaActivity::class.java)
                    }
                } else {
                    btnBukaToko.setOnClickListener {
                        intentActivity(BukaTokoActivity::class.java)
                    }
                }

            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}