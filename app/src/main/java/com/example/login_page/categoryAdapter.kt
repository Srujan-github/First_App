package com.example.login_page

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.login_page.databinding.CategoriesLayoutBinding
import com.example.login_page.databinding.RowLayoutBinding

class categoryAdapter(private val context: Context, private val categoriesList: List<String>, private val clickListener:OnItemClickListener) : RecyclerView.Adapter<categoryAdapter.ViewHolder>()  {

    inner class ViewHolder(private val binding: CategoriesLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.categoryName
        val img: ImageView =binding.categoryImg
        val categoryCard =binding.categoryCard

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CategoriesLayoutBinding.inflate(inflater, parent, false)
//        val shake: Animation = android.view.animation.AnimationUtils.loadAnimation(parent.context, R.anim.)
//        YOUR_TEXT_VIEW.startAnimation(shake)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         val currentItem=categoriesList[position]
        holder.name.text=currentItem
        Glide.with(context)
            .load(categoriesImgMap().image.get(currentItem)) // Assuming currentItem.image is a URL or a resource identifier
            .into(holder.img)
        holder.categoryCard.setOnClickListener {
            clickListener.onItemClick(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    interface OnItemClickListener {
        fun onItemClick(item: String)
    }
}