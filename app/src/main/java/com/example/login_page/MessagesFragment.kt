package com.example.login_page

import android.app.Activity
import android.app.AlertDialog
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup

import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.login_page.databinding.FragmentMessagesBinding
import com.google.firebase.messaging.FirebaseMessaging


//const val BASE_URL = "https://fakestoreapi.com/"
const val BASE_URL = "https://dummyjson.com/"


class MessagesFragment : Fragment(),MyAdapter.OnItemClickListener {
    private var _binding: FragmentMessagesBinding? = null
    private val binding get() = _binding!!
    var isDeletScreen:Boolean=true
//       lateinit var dataList: Set<String>
  var listOfItem: MutableList<Product> = mutableListOf()
  var listOfDeletedItems: MutableList<Product> = mutableListOf()

    lateinit var myAdapter: MyAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val jsonString = categoriesImgMap.readJSONFromAsset(requireContext(), "products.json")
        val productList = categoriesImgMap.parseJSONToProductList(jsonString)
        _binding = FragmentMessagesBinding.inflate(inflater, container, false)
        binding.recycleView.setHasFixedSize(true)
        listOfItem=productList.toMutableList()

//        linearLayoutManager=LinearLayoutManager(requireContext())
        binding.recycleView.layoutManager=GridLayoutManager(requireContext(),2)
        isDeletScreen=true
        binding.progressBar.visibility=View.GONE
        myAdapter=MyAdapter(requireContext(),listOfItem,this,this)
        binding.recycleView.adapter=myAdapter
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




    override fun onItemClick(item: Product) {
        val detailsFragment = ProductFragment().newInstance(item)

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.flvfragements, detailsFragment)
            .addToBackStack(null)
            .commit()
    }
     fun onItemLongClick(item: Product){
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
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        val searchItem = menu.findItem(R.id.actionSearch)
        val searchView = searchItem.actionView as SearchView

        // Set listener for search view
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Handle search query submission
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText.toString())
                return false
            }
        })
    }
   private fun filter(inputText:String){
       var filterList: MutableList<Product> = mutableListOf()
       for(items in listOfItem){
           if(items.title.lowercase().contains(inputText.lowercase())){
               filterList.add(items)
           }
       }
       if(filterList.isEmpty()){
           Toast.makeText(requireContext(), "No Data Found..", Toast.LENGTH_SHORT).show()
           myAdapter=MyAdapter(requireContext(),filterList,this@MessagesFragment,this@MessagesFragment)
           binding.recycleView.adapter=myAdapter

       }else{
           myAdapter=MyAdapter(requireContext(),filterList,this@MessagesFragment,this@MessagesFragment)
           binding.recycleView.adapter=myAdapter
       }
   }
}
