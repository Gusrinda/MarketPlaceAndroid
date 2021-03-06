package com.gusrinda.marketplace.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gusrinda.marketplace.core.data.source.local.DummyData
import com.gusrinda.marketplace.core.data.source.model.Category
import com.gusrinda.marketplace.core.data.source.model.Product
import com.gusrinda.marketplace.core.data.source.model.Slider

class HomeViewModel : ViewModel() {

    val listKategori : LiveData<List<Category>> = MutableLiveData<List<Category>>().apply {
        value = DummyData.listCategory
    }

    val listProduct : LiveData<List<Product>> = MutableLiveData<List<Product>>().apply {
        value = DummyData.listProduct
    }

    val listSlider : LiveData<List<Slider>> = MutableLiveData<List<Slider>>().apply {
        value = DummyData.listSlider
    }

}