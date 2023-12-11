package com.example.login_page

import android.content.Context
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset
import kotlin.collections.mapOf as mapOf1

class categoriesImgMap {
  val image = mapOf1(
    "smartphones" to "https://cdn.dxomark.com/wp-content/uploads/medias/post-90166/MicrosoftTeams-image-7-1024x691.jpg",
    "laptops" to "https://www.inside-digital.de/img/asus-laptops-header.jpg?class=1200x900",
    "fragrances" to "https://www.nextcareindia.com/cdn/shop/products/1_7a7c26aa-2f28-4dbc-9254-576cb3feb472.jpg?v=1680264967",
    "skincare" to "https://hips.hearstapps.com/hmg-prod/images/gettyimages-1214644132.jpg?crop=1.00xw:0.632xh;0,0.180xh&resize=1200:*",
    "groceries" to "https://www.bankrate.com/2020/08/01170557/How-to-save-money-on-groceries.jpeg",
    "home-decoration" to "https://www.bhg.com/thmb/sq8cqhphc0TdtATgElc6N9snT6E=/1792x0/filters:no_upscale():strip_icc()/living-room-rug-shelves-7b5d7a52-dcb3e3a7b7e04df99893aeaa76f57d08.jpg",
    "furniture" to "https://via.placeholder.com/150",
    "tops" to "https://via.placeholder.com/150",
    "womens-dresses" to "https://via.placeholder.com/150",
    "womens-shoes" to "https://via.placeholder.com/150",
    "mens-shirts" to "https://via.placeholder.com/150",
    "mens-shoes" to "https://via.placeholder.com/150",
    "mens-watches" to "https://via.placeholder.com/150",
    "womens-watches" to "https://via.placeholder.com/150",
    "womens-bags" to "https://via.placeholder.com/150",
    "womens-jewellery" to "https://via.placeholder.com/150",
    "sunglasses" to "https://via.placeholder.com/150",
    "automotive" to "https://via.placeholder.com/150",
    "motorcycle" to "https://via.placeholder.com/150",
    "lighting" to "https://via.placeholder.com/150"
  )
val categoryList= listOf<String>("smartphones",
  "laptops",
  "fragrances",
  "skincare",
  "groceries",
  "home-decoration",
  "furniture",
  "tops",
  "womens-dresses",
  "womens-shoes",
  "mens-shirts",
  "mens-shoes",
  "mens-watches",
  "womens-watches",
  "womens-bags",
  "womens-jewellery",
  "sunglasses",
  "automotive",
  "motorcycle",
  "lighting")
  companion object {
    fun readJSONFromAsset(context: Context, fileName: String): String? {
      return try {
        val inputStream: InputStream = context.assets.open(fileName)
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        String(buffer, Charset.defaultCharset())
      } catch (ex: IOException) {

        ex.printStackTrace()
        null
      }
    }

    // Function to parse JSON data into a list of Product objects
    fun parseJSONToProductList(jsonString: String?): List<Product> {
      val products = mutableListOf<Product>()
      jsonString?.let {
        try {
          val jsonArray = JSONArray(it)
          for (i in 0 until jsonArray.length()) {
            val jsonObj = jsonArray.getJSONObject(i)
            val id = jsonObj.getInt("id")
            val title = jsonObj.getString("title")
            val description = jsonObj.getString("description")
            val price = jsonObj.getInt("price")
            val discountPercentage=jsonObj.getDouble("discountPercentage")
            val rating =jsonObj.getDouble("rating")
            val stock = jsonObj.getInt("stock")
            val brand = jsonObj.getString("brand")
            val category = jsonObj.getString("category")
            val thumbnail = jsonObj.getString("thumbnail")
            val images = jsonObj.getJSONArray("images")

            val product =  createProduct( id,
              title,
              description,
              price,
              discountPercentage,
              rating,
              stock,
              brand,
              category,
              thumbnail,parseJsonArrayToStringList(images) )
            products.add(product)
          }
        } catch (e: JSONException) {
          e.printStackTrace()
        }
      }
      return products
    }



    fun parseJsonArrayToStringList(jsonArray: JSONArray?): List<String> {
      val stringList = mutableListOf<String>()
      jsonArray?.let {
        for (i in 0 until it.length()) {
          try {
            val element = it.getString(i)
            stringList.add(element)
          } catch (e: JSONException) {
            e.printStackTrace()
          }
        }
      }
      return stringList
    }



  }
}



