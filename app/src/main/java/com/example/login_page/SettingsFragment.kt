package com.example.login_page

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.login_page.databinding.FragmentSettingsBinding
import com.example.login_page.databinding.NavHeaderBinding
import com.github.dhaval2404.imagepicker.ImagePicker


class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding?=null


    private val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding= FragmentSettingsBinding.inflate(inflater,container,false)
        binding.editBtn.setOnClickListener({

            ImagePicker.with(this@SettingsFragment)
                .crop(1f,1f)	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        })
        
        return binding.root

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        binding.ProfileImg.setImageURI(data?.data)



    }
}