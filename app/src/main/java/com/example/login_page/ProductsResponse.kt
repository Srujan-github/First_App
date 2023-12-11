package com.example.login_page

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductsResponse(

    val products: List<Product>,
    val limit: Int,
    val skip: Int,
    val total: Int
): Parcelable