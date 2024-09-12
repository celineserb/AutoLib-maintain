package com.clovertech.autolib.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.clovertech.autolib.databinding.MaterialLayoutBinding
import com.clovertech.autolib.model.Materiel
import com.clovertech.autolib.viewmodel.TaskViewModel

class MaterialAdapter(val context: Context, val vm: TaskViewModel) :
    RecyclerView.Adapter<MaterialAdapter.MaterialViewHolder>() {
    var data = listOf<Materiel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterialViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = MaterialLayoutBinding.inflate(inflater,parent, false)
        return MaterialViewHolder(binding)

    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MaterialViewHolder, position: Int) {

        holder.materialName.text = data[position].description
        holder.quantity.text = data[position].quantity

    }

    fun setListMaterial(list: List<Materiel>) {
        data = list
        notifyDataSetChanged()
    }

    inner class MaterialViewHolder(binding: MaterialLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val materialName: TextView = binding.nomMateriel
        val quantity: TextView = binding.quantitMateriel
    }

}


