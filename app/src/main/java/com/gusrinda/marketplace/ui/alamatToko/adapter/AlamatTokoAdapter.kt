package com.gusrinda.marketplace.ui.alamatToko.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gusrinda.marketplace.core.data.source.model.AlamatToko
import com.gusrinda.marketplace.core.data.source.model.Category
import com.gusrinda.marketplace.databinding.ItemAlamatTokoBinding
import com.gusrinda.marketplace.databinding.ItemHomeCategoryBinding

@SuppressLint("NotifyDataSetChanged")
class AlamatTokoAdapter : RecyclerView.Adapter<AlamatTokoAdapter.ViewHolder>() {

    private var data = ArrayList<AlamatToko>()

    inner class ViewHolder(private val binding : ItemAlamatTokoBinding) : RecyclerView.ViewHolder(binding.root){
            fun bind(item : AlamatToko, position: Int) {

                binding.apply {
                    tvKota.text = item.kota
                    var kecamatan = ""
                    if (item.kecamatan != null) kecamatan = ", Kec. ${item.kecamatan}"

                    tvAlamat.text =
                        "${item.alamat}$kecamatan, ${item.kota}, ${item.provinsi}, ${item.kodepos}"
                    tvEmail.text = item.email
                    tvPhone.text = item.phone
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemAlamatTokoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addItems(items:List<AlamatToko>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

}