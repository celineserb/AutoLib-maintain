package com.clovertech.autolib.views.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.clovertech.autolib.R
import com.clovertech.autolib.adapters.MaterialAdapter
import com.clovertech.autolib.adapters.TaskStepsAdapter
import com.clovertech.autolib.databinding.FragmentDetailTaskBinding
import com.clovertech.autolib.viewmodel.TaskViewModel


class TaskDetailFragment : Fragment() {

    private lateinit var adapterSteps: TaskStepsAdapter
    private lateinit var adapterMateriels: MaterialAdapter
    private lateinit var binding: FragmentDetailTaskBinding
    private val taskViewModel: TaskViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentDetailTaskBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val taskStepsRecycler = binding.taskStepsRecycler
        val materialRecycler = binding.materialsRecycler

        binding.taskDetailTitle.text = taskViewModel.task.taskTitle
        binding.taskDescriptionDetail.text = taskViewModel.task.description
        binding.vehicleId.text = taskViewModel.task.idVehicule.toString()

        adapterSteps = TaskStepsAdapter(taskViewModel)
        taskStepsRecycler.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        taskStepsRecycler.adapter = adapterSteps
        taskViewModel.task.steps?.let { adapterSteps.setListSteps(it) }

        adapterMateriels = MaterialAdapter(requireActivity(), taskViewModel)
        materialRecycler.layoutManager = LinearLayoutManager(requireActivity()
            , LinearLayoutManager.VERTICAL, false)
        materialRecycler.adapter = adapterMateriels

        if (taskViewModel.task.usedEquipements !=null){
            taskViewModel.task.usedEquipements?.let { adapterMateriels.setListMaterial(it) }
        }

        binding.addMaterialButton.setOnClickListener{
            findNavController().navigate(R.id.nav_to_addmaterial)
        }

        binding.vehicleId.setOnClickListener {
            findNavController().navigate(TaskDetailFragmentDirections.navDetailToVehicle(taskViewModel.task.idVehicule))
        }
    }


}