package com.example.login_page

import com.example.login_page.Constants.Companion.CONTENT_TYPE
import com.example.login_page.Constants.Companion.SERVER_KEY
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterFace {

//    @GET("/products")
//    fun getData(): Call<ProductsResponse>
//
//    @GET("products/categories")
//    fun getDataCategories(): Call<List<String>>
//
//    @GET("products/category/{name}")
//    fun getProductsByCategory(@Path("name") name: String): Call<ProductsResponse>
//@GET("products/{id}")
//fun getDataCategories(@Path("id")id:String?):Call<List<String>>
@Headers("Authoriztion: Bearer ya29.$SERVER_KEY","Content-Type:$CONTENT_TYPE")
@POST("/v1/projects/myproject-b5ae1/messages:send")
    suspend fun postNotification(@Body notification: pushNotification): Response<ResponseBody>


}