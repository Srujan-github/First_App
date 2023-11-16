package com.example.login_page

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.example.login_page.databinding.FragmentSettingsBinding
import com.github.dhaval2404.imagepicker.ImagePicker

import java.util.Base64


class SettingsFragment : Fragment()  {
    private var _binding: FragmentSettingsBinding?=null
    private val binding get()=_binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val sharedPref =requireContext().getSharedPreferences("Local", Context.MODE_PRIVATE)
        _binding= FragmentSettingsBinding.inflate(inflater,container,false)
        binding.editBtn.setOnClickListener({
setButton()

            ImagePicker.with(this@SettingsFragment)
                .crop(1f,1f)	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        })

        binding.userName.addTextChangedListener {
            if(!binding.userName.text.toString().equals(sharedPref.getString("Newname","Sai Surjan"))){
                setButton()
            }
        }
        binding.userMailId.addTextChangedListener {
            if(!binding.userMailId.text.toString().equals(sharedPref.getString("NewmailId","saisrujan@1gmail.com"))){
                setButton()
            }
        }
        binding.userName.setText(sharedPref.getString("Newname","Sai Surjan"))
        binding.userMailId.setText(sharedPref.getString("NewmailId","saisrujan@1gmail.com"))
        binding.ProfileImg.setImageURI(Uri.parse(sharedPref.getString("image","iVBORw0KGgoAAAANSUhEUgAAAAUA" +
                "AAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO" +
                "9TXL0Y4OHwAAAABJRU5ErkJggg==")))
        binding.saveBtn.setOnClickListener {

            val editor = sharedPref.edit()
            editor.putString("Newname", binding.userName.text.toString())
            editor.putString("NewmailId", binding.userMailId.text.toString())
            editor.apply()
            binding.saveBtn.backgroundTintList= ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.ash))
            binding.saveBtn.isEnabled=false


        }
        return binding.root

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {

            val imageUri: Uri? = data.data
            binding.ProfileImg.setImageURI(imageUri)


            val sharedPref = requireContext().getSharedPreferences("Local", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("image", imageUri.toString())
            editor.apply()
        }
    }
private fun setButton(){
    binding.saveBtn.backgroundTintList= ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.lightblue))
    binding.saveBtn.isEnabled=true
}

}






