package com.gusrinda.marketplace.ui.akun

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gusrinda.marketplace.NavigationActivity
import com.gusrinda.marketplace.databinding.FragmentAkunBinding
import com.gusrinda.marketplace.ui.toko.BukaToko
import com.gusrinda.marketplace.ui.updateProfile.UpdateProfileActivity
import com.gusrinda.marketplace.util.Constant
import com.gusrinda.marketplace.util.Prefs
import com.inyongtisto.myhelper.extension.getInitial
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.pushActivity
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

        binding.btnBukaToko.setOnClickListener {
            intentActivity(BukaToko::class.java)
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

            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}