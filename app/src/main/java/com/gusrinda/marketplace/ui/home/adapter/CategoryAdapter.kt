package com.gusrinda.marketplace.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gusrinda.marketplace.core.data.source.model.Category
import com.gusrinda.marketplace.databinding.ItemHomeCategoryBinding

@SuppressLint("NotifyDataSetChanged")
class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var data = ArrayList<Category>()

    inner class ViewHolder(private val binding : ItemHomeCategoryBinding) : RecyclerView.ViewHolder(binding.root){
            fun bind(item : Category, position: Int) {
                binding.apply {
                    tvName.text = item.name
                    imageView.setImageResource(item.image!!)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHomeCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addItems(items:List<Category>) {
        data.addAll(items)
        notifyDataSetChanged()
    }

}