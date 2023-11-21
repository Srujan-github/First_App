package com.example.login_page

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.login_page.databinding.FragmentMessagesBinding
import com.example.login_page.databinding.FragmentProductBinding


class ProductFragment : Fragment() {
    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        val getFromBundle=arguments
        if(getFromBundle!=null){
        binding.productName.text=getFromBundle.getString("title")
        binding.productDescrip.text=getFromBundle.getString("descrip")
        binding.productPrice.text="$ ${getFromBundle.getString("price")} USD"
        Glide.with(this).load(getFromBundle.getSerializable("image")).into(binding.productImg)
        }
        return binding.root
    }



    fun newInstance(selectedItem: MyDataItem):ProductFragment{
        Log.d("tielt","${selectedItem.title} this is title")
       val fragment=ProductFragment()
        val storeInBudle=Bundle()
        storeInBudle.putString("title",selectedItem.title)
        storeInBudle.putString("descrip",selectedItem.description)
        storeInBudle.putString("price",selectedItem.price.toString())
        storeInBudle.putSerializable("image",selectedItem.image)
        fragment.arguments =storeInBudle
        return fragment
    }


}