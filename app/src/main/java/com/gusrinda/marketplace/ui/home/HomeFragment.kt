package com.gusrinda.marketplace.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gusrinda.marketplace.databinding.FragmentHomeBinding
import com.gusrinda.marketplace.ui.home.adapter.CategoryAdapter
import com.gusrinda.marketplace.ui.home.adapter.ProductTerbaruAdapter
import com.gusrinda.marketplace.ui.home.adapter.ProductTerlarisAdapter
import com.gusrinda.marketplace.ui.home.adapter.SliderAdapter

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val adapterKategori = CategoryAdapter()
    private val adapterProdukTerlaris = ProductTerlarisAdapter()
    private val adapterProdukTerbaru = ProductTerbaruAdapter()
    private val adapterSlider = SliderAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupAdapter()
        setData()


        return root
    }

    private fun setupAdapter() {

        binding.rvCategory.adapter = adapterKategori
        binding.rvSlider.adapter = adapterSlider
        binding.rvProductTerlaris.adapter = adapterProdukTerlaris
        binding.rvProductTerbaru.adapter = adapterProdukTerbaru

    }

    private fun setData() {
        viewModel.listKategori.observe(requireActivity()) {
            adapterKategori.addItems(it)
        }

        viewModel.listProduct.observe(requireActivity()) {
            adapterProdukTerlaris.addItems(it)
            adapterProdukTerbaru.addItems(it)
        }

        viewModel.listSlider.observe(requireActivity()) {
            adapterSlider.addItems(it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}