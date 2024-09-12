package com.clovertech.autolib.views.ui.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import com.clovertech.autolib.databinding.FragmentThemeBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ThemeFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentThemeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentThemeBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val prefs = requireContext().getSharedPreferences("AUTOLIB_MAINTAIN", Context.MODE_PRIVATE)
        if (prefs.getString("THEME_MODE","SYSTEM") =="DARK"){
            binding.darkButton.isActivated=true
        }else{
            if(prefs.getString("THEME_MODE","SYSTEM") =="LIGHT"){
                binding.lightButton.isActivated=true
            }
            else{
                binding.systemButton.isActivated=true
            }
        }
        binding.systemButton.setOnClickListener{
            prefs.edit{putString("THEME_MODE","SYSTEM")}
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
        binding.lightButton.setOnClickListener{
            prefs.edit{putString("THEME_MODE","DARK")}
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        binding.darkButton.setOnClickListener{
            prefs.edit{putString("THEME_MODE","LIGHT")}
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

}