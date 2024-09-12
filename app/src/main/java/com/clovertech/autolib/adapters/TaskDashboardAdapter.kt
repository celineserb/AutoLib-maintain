package com.clovertech.autolib.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.clovertech.autolib.databinding.TaskDashboardItemLayoutBinding
import com.clovertech.autolib.model.Task

class TaskDashboardAdapter: RecyclerView.Adapter<TaskDashboardAdapter.TaskViewHolder>() {

    private val tasks = mutableListOf<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding= TaskDashboardItemLayoutBinding.inflate(inflater,parent, false)
        return TaskViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.description.text = task.description
        holder.title.text = task.taskTitle
        val progress = task.steps!!.filter { it.completed }.size * 100 / task.steps!!.size
        holder.progress.text = "$progress%"
        holder.vehicle.text = task.idVehicule.toString()
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun setTasks(list: List<Task>){
        tasks.clear()
        tasks.addAll(list)
        notifyDataSetChanged()
    }

    class TaskViewHolder(binding: TaskDashboardItemLayoutBinding): RecyclerView.ViewHolder(binding.root){

        val title: TextView = binding.textTaskDashboardTitle
        val description: TextView = binding.textTaskDashboardDescruption
        val vehicle: TextView = binding.textTaskDashboardVehicule
        val progress: TextView = binding.textTaskDashboardProgress

    }
}