package com.example.login_page
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterFace {

  @GET("products")
fun getData():Call<List<MyDataItem>>
  @GET("products/{id}")
fun getDataById(@Path("id")id:Int?):Call<List<MyDataItem>>
}