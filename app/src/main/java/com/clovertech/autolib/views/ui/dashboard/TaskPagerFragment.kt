package com.clovertech.autolib.views.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.clovertech.autolib.adapters.TaskDashboardAdapter
import com.clovertech.autolib.databinding.TaskPagerLayoutBinding
import com.clovertech.autolib.model.Task

class TaskPagerFragment: Fragment() {

    private lateinit var binding: TaskPagerLayoutBinding

    private lateinit var taskRecyclerView: RecyclerView
    private val taskAdapter = TaskDashboardAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = TaskPagerLayoutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskRecyclerView = binding.recyclerTaskDashboard
        taskRecyclerView.adapter = taskAdapter
        taskRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    fun updateTasks(tasks: List<Task>) {
        taskAdapter.setTasks(tasks)
    }


}