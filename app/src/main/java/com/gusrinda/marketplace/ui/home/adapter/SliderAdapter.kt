package com.gusrinda.marketplace.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gusrinda.marketplace.core.data.source.model.Category
import com.gusrinda.marketplace.core.data.source.model.Slider
import com.gusrinda.marketplace.databinding.ItemHomeCategoryBinding
import com.gusrinda.marketplace.databinding.ItemHomeSliderBinding



class SliderAdapter : RecyclerView.Adapter<SliderAdapter.ViewHolder>() {

    private var data = ArrayList<Slider>()

    inner class ViewHolder(private val binding : ItemHomeSliderBinding) : RecyclerView.ViewHolder(binding.root){
            fun bind(item : Slider, position: Int) {
                binding.apply {
                    imageView.setImageResource(item.image!!)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHomeSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addItems(items:List<Slider>) {
        data.addAll(items)
        notifyDataSetChanged()
    }

}