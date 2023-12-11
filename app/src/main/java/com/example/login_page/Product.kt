package com.example.login_page

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val brand: String,
    val category: String,
    val thumbnail: String,
    val images: List<String>
) : Parcelable

fun createProduct(
    id: Int,
    title: String,
    description: String,
    price: Int,
    discountPercentage: Double,
    rating: Double,
    stock: Int,
    brand: String,
    category: String,
    thumbnail: String,
    images: List<String>
): Product {
    return Product(
        id,
        title,
        description,
        price,
        discountPercentage,
        rating,
        stock,
        brand,
        category,
        thumbnail,
        images
    )
}