package com.example.login_page

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductsResponseWrapper(
    val products: ProductsResponse,
    val total: Int,
    val skip: Int,
    val limit: Int
): Parcelable