package com.example.login_page

import android.app.AlertDialog
import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login_page.databinding.FragmentMessagesBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://fakestoreapi.com/"


class MessagesFragment : Fragment(),MyAdapter.OnItemClickListener {
    private var _binding: FragmentMessagesBinding? = null
    private val binding get() = _binding!!
    var isDeletScreen:Boolean=true
//       lateinit var dataList: Set<String>
  var listOfItem: MutableList<MyDataItem> = mutableListOf()
  var listOfDeletedItems: MutableList<MyDataItem> = mutableListOf()
    lateinit var myAdapter: MyAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMessagesBinding.inflate(inflater, container, false)
        binding.recycleView.setHasFixedSize(true)

        linearLayoutManager=LinearLayoutManager(requireContext())
        binding.recycleView.layoutManager=linearLayoutManager
        isDeletScreen=true

        if( listOfItem.isEmpty()){
            getMyData()


        } else {
            myAdapter=MyAdapter(requireContext(),listOfItem,this@MessagesFragment,this@MessagesFragment)
            binding.recycleView.adapter=myAdapter
            binding.progressBar.visibility=View.GONE
        }
        binding.btnDelete.setOnClickListener({
        if (!listOfDeletedItems.isEmpty()){
                myAdapter=MyAdapter(requireContext(),listOfDeletedItems,this@MessagesFragment,this@MessagesFragment)
                binding.recycleView.adapter=myAdapter
                binding.btnDelete.backgroundTintList= ColorStateList.valueOf(R.drawable.baseline_edit_24)
            isDeletScreen=false
        }else{
            Toast.makeText(
                requireContext(),
                "No delete Items",
                Toast.LENGTH_SHORT
            ).show()
        }  })
        return binding.root
    }

    private fun getMyData() {
        val retrofitBuilder =
            Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL)
                .build().create(ApiInterFace::class.java)
        val retrofitData = retrofitBuilder.getData()
        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>
            ) {
                val responseBody =response.body()!!
               listOfItem  = responseBody.toMutableList()
                binding.progressBar.visibility=View.GONE

                 myAdapter=MyAdapter(requireContext(),listOfItem,this@MessagesFragment,this@MessagesFragment)
                binding.recycleView.adapter=myAdapter


            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                requireActivity().finish()
                Toast.makeText(requireContext(),"Api is timed out",Toast.LENGTH_SHORT)
                Log.d("Messages Fragment","onFailure "+t.message)
            }
        })

    }

    override fun onItemClick(item: MyDataItem) {
        val detailsFragment = ProductFragment().newInstance(item)

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.flvfragements, detailsFragment)
            .addToBackStack(null)
            .commit()
    }
     fun onItemLongClick(item: MyDataItem){
         if(isDeletScreen){
             val builder = AlertDialog.Builder(requireContext())
             builder.setTitle("Delete Item")
             builder.setMessage("Are you sure you want to delete this item?")
             builder.setPositiveButton("Yes") { _, _ ->
                 myAdapter.notifyItemRemoved(listOfItem.lastIndexOf(item))

                 listOfItem.remove(item)

                 listOfDeletedItems.add(item)

             }
             builder.setNegativeButton("No") { dialog, _ ->
                 dialog.dismiss()
             }
             val dialog = builder.create()
             dialog.show()
         }

    }

    override fun onResume() {
        super.onResume()
        // Disable back button when progress bar is visible
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if (!binding.progressBar.isVisible) {
                isEnabled = false // Disable back button when progress bar is visible
                activity?.onBackPressed() // Allow back button action if progress bar is not visible
            }
        }
    }
}
