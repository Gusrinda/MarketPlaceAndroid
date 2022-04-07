package com.gusrinda.marketplace.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gusrinda.marketplace.core.data.source.model.Product
import com.gusrinda.marketplace.databinding.ItemHomeProdukTerlarisBinding
import com.inyongtisto.myhelper.extension.*


@SuppressLint("NotifyDataSetChanged")
class ProductTerlarisAdapter : RecyclerView.Adapter<ProductTerlarisAdapter.ViewHolder>() {

    private var data = ArrayList<Product>()

    inner class ViewHolder(private val binding : ItemHomeProdukTerlarisBinding) : RecyclerView.ViewHolder(binding.root){
            fun bind(item : Product, position: Int) {
                binding.apply {

                    val harga = item.harga?: 0

                    tvName.text = item.name
                    imageView.setImageResource(item.image!!)
                    tvHarga.text = item.harga.toRupiah()
                    tvPengiriman.text = item.pengiriman
                    tvReting.text = "" + item.rating + " | Terjual "  + item.terjual

                    if (item.discount != 0) {
                        lyGrosir.toGone()
                        lyDiskon.toVisible()
                        tvDiscount.text = "${item.discount}%"

                        val persenDiskon = item.discount
                        val potonganDiskon = harga * persenDiskon!! / 100;

                        tvHarga.text = (harga - potonganDiskon).toRupiah()
                        tvHargaAsli.text = item.harga.toRupiah()
                        tvHargaAsli.coret()
                    }
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHomeProdukTerlarisBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addItems(items:List<Product>) {
        data.addAll(items)
        notifyDataSetChanged()
    }

}