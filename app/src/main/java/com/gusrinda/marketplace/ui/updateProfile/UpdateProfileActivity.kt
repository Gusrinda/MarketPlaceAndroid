package com.gusrinda.marketplace.ui.updateProfile

import UpdateViewModel
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isInvisible
import com.github.drjacky.imagepicker.ImagePicker
import com.gusrinda.marketplace.ui.navigation.NavigationActivity
import com.gusrinda.marketplace.core.data.source.remote.network.State
import com.gusrinda.marketplace.core.data.source.remote.request.UpdateRequest
import com.gusrinda.marketplace.databinding.ActivityUpdateProfileBinding
import com.gusrinda.marketplace.util.Constant
import com.gusrinda.marketplace.util.Prefs
import com.inyongtisto.myhelper.extension.*
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class UpdateProfileActivity : AppCompatActivity() {

    private val viewModel: UpdateViewModel by viewModel()

    private var _binding: ActivityUpdateProfileBinding? = null
    private val binding get() = _binding!!
    private var fileImage: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.toolbar, "Edit Akun")

        setupViewModel()
        setupDataAwalUser()

    }

    private fun setupDataAwalUser() {

        val user = Prefs.getUser()

        if (user != null) {
            binding.apply {
                edtNama.setText(user.name)
                edtPhone.setText(user.phone)
                edtEmail.setText(user.email)
                txtInisialUser.text = user.name.getInitial()

                if (user.image != null) {
                    binding.txtInisialUser.isInvisible = true
                }

                Picasso.get().load(Constant.BASE_IMAGE_URL + user.image).into(binding.imgUser)
            }
        }
    }

    private fun setupViewModel() {
        binding.btnSimpan.setOnClickListener {

            if (fileImage != null) {
                uploadFile()
            } else {
                updateUser()
            }

        }

        binding.imgUser.setOnClickListener {
            pickImage()
        }

    }

    private fun updateUser() {
        if (binding.edtNama.isEmpty()) return
        if (binding.edtPhone.isEmpty()) return
        if (binding.edtEmail.isEmpty()) return

        val updateUser = UpdateRequest(
            binding.edtNama.text.toString(),
            binding.edtPhone.text.toString(),
            binding.edtEmail.text.toString(),
        )

        viewModel.updateUser(Prefs.getUser()?.id!!, updateUser).observe(this) {
            when (it.state) {
                State.LOADING -> {
                    showLoading()
                }
                State.SUCCESS -> {
                    dismisLoading()
                    showToast("Data berhasil diupdate " + it.data?.name)
                    pushActivity(NavigationActivity::class.java)
                }
                State.ERROR -> {
                    dismisLoading()
                    toastError(it.message ?: "Error")
                }
            }

        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun pickImage() {
        ImagePicker.with(this)
            .crop()
            .maxResultSize(1080, 1080, keepRatio = true)
            .createIntentFromDialog { launcher.launch(it) }
    }

    private fun uploadFile() {

        val file = fileImage.toMultipartBody("image")

        viewModel.updateProfilePhoto(Prefs.getUser()?.id!!, file).observe(this) {
            when (it.state) {
                State.LOADING -> {
                    showLoading()
                }
                State.SUCCESS -> {
                    dismisLoading()
                    showToast("Berhasil kirim gambar " + it.data?.name)
                    updateUser()
                }
                State.ERROR -> {
                    dismisLoading()
                    toastError(it.message ?: "Error")
                }
            }

        }

    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val uri = it.data?.data!!
                fileImage = File(uri.path ?: "")
                // Use the uri to load the image
                Picasso.get().load(uri).into(binding.imgUser)

            }
        }


}