package com.clovertech.autolib.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.clovertech.autolib.R
import com.clovertech.autolib.databinding.CardViewLayoutBinding
import com.clovertech.autolib.model.Task


class ListTasksAdapter(private val navController: NavController) :
    RecyclerView.Adapter<ListTasksAdapter.MyViewHolder>() {

    var data = listOf<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardViewLayoutBinding.inflate(inflater,parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var progress = 0
        holder.description.text = data[position].description
        holder.idVehicle.text = data[position].idVehicule.toString()
        holder.taskTitle.text = data[position].taskTitle
        holder.progressBar.max = data[position].steps?.size ?: 0
        holder.progressBar.progress = data[position].steps?.filter { it.completed }?.size ?: 0

        holder.itemView.setOnClickListener{
            navController.navigate(R.id.nav_to_taskdetail)
        }

        if (holder.progressBar.max != 0) {
            progress = holder.progressBar.progress * 100 / holder.progressBar.max
        }

        holder.progress.text = "$progress %"
    }

    fun setTaskList(list: List<Task>) {
        data = list
        notifyDataSetChanged()
    }

    inner class MyViewHolder(binding :CardViewLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val taskTitle: TextView = binding.Titre
        val description: TextView = binding.description
        val idVehicle: TextView = binding.vehicleId
        val progress: TextView = binding.avancement
        val progressBar: ProgressBar = binding.progressTaskHome
    }

}


