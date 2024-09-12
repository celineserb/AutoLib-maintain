package com.clovertech.autolib.views.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.clovertech.autolib.R
import com.clovertech.autolib.databinding.FragmentAddMaterialBinding
import com.clovertech.autolib.model.Equipment
import com.clovertech.autolib.model.Materiel
import com.clovertech.autolib.model.NewEquipment
import com.clovertech.autolib.viewmodel.EquipmentViewModel
import com.clovertech.autolib.viewmodel.TaskViewModel


class AddMaterialFragment : Fragment(){

    private val equipmentViewModel: EquipmentViewModel by activityViewModels()
    private val taskViewModel: TaskViewModel by activityViewModels()
    private lateinit var binding: FragmentAddMaterialBinding
    private var equipments = mutableListOf<Equipment>()
    private val equipmentNames = mutableListOf<String>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentAddMaterialBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var selectedEquipment: Equipment? = null
        val dropDownArray = ArrayAdapter(requireContext(), R.layout.drop_down_item,equipmentNames)
        equipmentViewModel.getAllEquipment()
        equipmentViewModel.responseEquipment.observe(viewLifecycleOwner, {
            if(it.isSuccessful){
                equipments = mutableListOf()
                equipmentNames.clear()
                for (equipment in it.body()!!){
                    equipments.add(equipment)
                    equipmentNames.add(equipment.equipmentName)
                    dropDownArray.notifyDataSetChanged()
                }
                selectedEquipment = equipments[0]
            }
        })

        binding.materialName.setAdapter(dropDownArray)

        binding.materialName.setOnItemClickListener { _, _, position, _ ->
            selectedEquipment = equipments[position]
        }

        binding.addMaterial.setOnClickListener {
            val newEquipment = NewEquipment(
                    binding.materialDescription.text.toString(),
                    binding.materialQuantity.text.toString().toInt(),
                    selectedEquipment?.uuid ?: "",
                    taskViewModel.task.uuid)

            val material = Materiel(binding.materialDescription.text.toString()
                , binding.materialQuantity.text.toString())

            equipmentViewModel.addMateriel(newEquipment)

            equipmentViewModel.response.observe(viewLifecycleOwner,{
                if (it.code() == 200) {
                    val listEquipment: MutableList<Materiel>? =
                        taskViewModel.task.usedEquipements as MutableList<Materiel>?
                    listEquipment?.add(material)
                    taskViewModel.task.usedEquipements = listEquipment
                    taskViewModel.updateTask(requireContext(), taskViewModel.task)
                    Toast.makeText(requireContext(), "Added material successfully", Toast.LENGTH_SHORT)
                        .show()
                    findNavController().popBackStack()

                } else {
                    Toast.makeText(requireContext(), it.message(), Toast.LENGTH_SHORT).show()
                }

            })
        }
    }

    override fun onResume() {
        super.onResume()
        val dropDownArray = ArrayAdapter(requireContext(), R.layout.drop_down_item,equipmentNames)
        binding.materialName.setAdapter(dropDownArray)
    }


}

