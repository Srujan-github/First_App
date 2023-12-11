package com.example.login_page

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.login_page.databinding.RowLayoutBinding

class productAdapter(private val context: Context, private val productList: List<Product>, private val clickListener: categoriesFragment?) : RecyclerView.Adapter<productAdapter.ViewHolder>(){
    inner class ViewHolder(private val binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.title
        val description: TextView = binding.description
        val imageUrl: ImageView =binding.ImgView
        val productCard: CardView =binding.productCard
        val setPrice: TextView =binding.price
        val rating: TextView =binding.rating


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = productList[position]
        holder.title.text = currentItem.title
        holder.description.text = currentItem.description
        Glide.with(context)
            .load(currentItem.thumbnail) // Assuming currentItem.image is a URL or a resource identifier
            .into(holder.imageUrl)
        val starNum=currentItem.rating
        holder.rating.text= starNum.toString()
        holder.setPrice.text= "$${currentItem.price.toString()} USD"
        holder.productCard.setOnClickListener {
            clickListener?.OnProductClickListener(currentItem)
        }

    }
    interface OnProductClickListener {
        fun OnProductClickListener(item: Product)
    }

}