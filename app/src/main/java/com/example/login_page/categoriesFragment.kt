package com.example.login_page

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login_page.databinding.FragmentCategoriesBinding
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class categoriesFragment : Fragment(),categoryAdapter.OnItemClickListener,productAdapter.OnProductClickListener {
    val TAG="categoriesFragment"
    private var _binding: FragmentCategoriesBinding? = null

    var listofCategories: List<String> = listOf()
     lateinit  var LayoutManager:LinearLayoutManager
    lateinit var adapter: categoryAdapter
    lateinit var caegoryName:String
    lateinit var productAdapter: productAdapter
    var messagesFragment=MessagesFragment()
    lateinit var lisofProducts: List<Product>
    private val binding get() = _binding!!
    val TOPIC="/topics/myTopic2"

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        binding.recycleView.setHasFixedSize(true)
        binding.productsRecycleView.setHasFixedSize(true)
FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
        LayoutManager= LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.recycleView.layoutManager=LayoutManager
        listofCategories=categoriesImgMap().categoryList
              adapter = categoryAdapter(
                  requireContext(),
                  listofCategories,this
              )
              binding.recycleView.adapter = adapter



        binding.productsRecycleView.layoutManager=GridLayoutManager(requireContext(),2)
        val jsonString = categoriesImgMap.readJSONFromAsset(requireContext(), "products.json")
        val productList = categoriesImgMap.parseJSONToProductList(jsonString)

        lisofProducts=filter(productList,"smartphones")
        productAdapter=productAdapter(requireContext(),lisofProducts,this)
        binding.productsRecycleView.adapter=productAdapter

        return binding.root
    }
   fun filter(input : List<Product>,categoryName:String):List<Product> {
       var result:List<Product> = listOf()
       input.forEach{ product ->
           if(product.category== categoryName){
               result+=(product)
           }
       }
       return result
   }

private fun sendNotification(notification: pushNotification)= CoroutineScope(Dispatchers.IO).launch {
    try {
        val response =RetrofitInstance.api.postNotification(notification)
       Log.i(TAG,"${response}")

        if(response.isSuccessful){
            Log.d(TAG,"Response:${Gson().toJson(response)}")
        }else{
            Log.e(TAG," ${response.errorBody().toString()}")

        }
    }catch (e:Exception){
        Log.e(TAG,e.toString())
    }
}

    override fun onItemClick(item: String) {

         caegoryName=item
        Toast.makeText(requireContext(),"this are ${caegoryName}",Toast.LENGTH_SHORT  ).show()
        val jsonString = categoriesImgMap.readJSONFromAsset(requireContext(), "products.json")
        val productList = categoriesImgMap.parseJSONToProductList(jsonString)
        lisofProducts=filter(productList,"${caegoryName}")

        productAdapter=productAdapter(requireContext(),lisofProducts,this)
        binding.productsRecycleView.adapter=productAdapter

    }

     override fun OnProductClickListener(item : Product){
            val title=item.title.toString()
         val message="dadasd"
         createNotificationChannel()
         var builder = NotificationCompat.Builder(requireContext(),
             com.example.login_page.LocalNotification.CHANNEL_ID
         )

         builder.setSmallIcon(R.drawable.baseline_add_shopping_cart_24)
             .setContentTitle("BUY NOW")
             .setContentText(title)
             .setPriority(NotificationManager.IMPORTANCE_HIGH)

         with(NotificationManagerCompat.from(requireContext())){
             if(ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.POST_NOTIFICATIONS)!= PackageManager.PERMISSION_GRANTED){
                 return
             }

                 notify(1,builder.build())


         }

    }




        private fun createNotificationChannel() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    com.example.login_page.LocalNotification.CHANNEL_ID,
                    "first channel",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                channel.description = "test description"
                val notificationManager =requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                notificationManager.createNotificationChannel(channel)

//                val notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//                notificationManager.createNotificationChannel(channel)
//
//                notificationManager.createNotificationChannel(channel)
            }


//        private fun getSystemService(notificationService: String): Any {
//            return notificationService
//        }
    }
}

//suspend fun gettingData(){
//    val retrofitBuilder =  Retrofit.Builder()
//        .addConverterFactory(GsonConverterFactory.create())
//        .baseUrl(BASE_URL)
//        .build()
//    val apiInterface = retrofitBuilder.create<ApiInterFace>()
//    listofLoadCategories.forEachIndexed{ index, categoryName ->
//
//        val retrofitData=apiInterface.getProductsByCategory(categoryName)
//        retrofitData.enqueue(object : Callback<ProductsResponse> {
//            override fun onResponse(call: Call<ProductsResponse>, response: Response<ProductsResponse>) {
//                if (response.isSuccessful) {
//                    val dataList = response.body()?.products
//                    dataList?.let {
//                        val existingList = listofLists[index].toMutableList() // Convert to MutableList
//                        existingList.addAll(it) // Add new elements
//
//                        val updatedLists = listofLists.toMutableList() // Convert listofLists to a MutableList
//                        updatedLists[index] = existingList.toList() // Update the specific list in the MutableList
//
//                        listofLists = updatedLists.toList() // Reassign the updated MutableList to listofLists
//
//                        // Notify adapter after updating the list
//                        productAdapter.notifyDataSetChanged()
//                    }
//
//
//                } else {
//                    Toast.makeText(requireContext(),"onFailure  maybe Timeout",Toast.LENGTH_SHORT).show()
//                }
//            }
//            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
//                Toast.makeText(requireContext(),"onFailure ${t.message}",Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
//}