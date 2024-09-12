package com.clovertech.autolib.views.ui.panne

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.clovertech.autolib.databinding.FragmentAddPanneBinding
import com.clovertech.autolib.model.Panne
import com.clovertech.autolib.viewmodel.PanneViewModel
import java.text.SimpleDateFormat
import java.util.*


class PanneAddFragment : Fragment() {

    private val panneModel: PanneViewModel by activityViewModels()
    private lateinit var binding: FragmentAddPanneBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentAddPanneBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sdf = SimpleDateFormat("yyyy-mm-dd")
        val currentDate= sdf.format(Date())

        binding.sendSignalButton.setOnClickListener{

        val panne = Panne(currentDate
            , null
            , "ready"
            , binding.chasisNumber.text.toString().toInt()
            , binding.panneDescription.text.toString()
            , null)

        panneModel.addPanne(panne)

        panneModel.responsePanne.observe(viewLifecycleOwner, {
            if (it.isSuccessful) {
                Toast.makeText(requireContext(), "Panne added successfully", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(requireContext(), "Error adding the panne", Toast.LENGTH_SHORT).show()
            }

        })

        }
    }
}