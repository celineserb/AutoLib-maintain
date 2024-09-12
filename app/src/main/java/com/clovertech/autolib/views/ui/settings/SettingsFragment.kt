package com.clovertech.autolib.views.ui.settings

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.clovertech.autolib.R
import com.clovertech.autolib.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentSettingsBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val prefs = requireContext().getSharedPreferences("AUTOLIB_MAINTAIN", MODE_PRIVATE)

        if (prefs.getString("THEME_MODE","SYSTEM") =="DARK"){
            binding.nightModeStateText.text = "Dark theme"
        }else{
            if(prefs.getString("THEME_MODE","SYSTEM") =="LIGHT"){
                binding.nightModeStateText.text = "Light theme"
            }
            else{
                binding.nightModeStateText.text = "Follow system"
            }
        }
        binding.modeNuitLayout.setOnClickListener{
            findNavController().navigate(R.id.nav_to_themes)

        }

    }

}