package com.clovertech.autolib.views.ui.userProfil

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.clovertech.autolib.databinding.FragmentProfileBinding
import com.clovertech.autolib.viewmodel.ProfileViewModel

class UserProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val profileViewModel: ProfileViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentProfileBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = requireContext().getSharedPreferences("AUTOLIB_MAINTAIN",MODE_PRIVATE)
        val id = sharedPreferences.getInt("USER_ID",0)
        if (id != 0) {
            profileViewModel.getThisProfile(id)
            profileViewModel.responseProfile.observe(viewLifecycleOwner, {

                if (it.isSuccessful) {
                    val profile = it.body()
                    if (profile != null) {
                        binding.usernameProfile.text = "${profile.firstName}  ${profile.lastName}"
                        binding.profileAdress.text = profile.address
                        binding.profileRole.text = profile.userType
                        binding.profilePhoneNum .text = profile.phoneNumber
                        binding.certificateRef.text = "Ref N=2938394903"
                    }
                } else {
                    Toast.makeText(requireContext(), "Error loading the profile please try again", Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }
    }

}