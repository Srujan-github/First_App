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


class MyAdapter(private val context: Context, private val productList: List<MyDataItem>, private val clickListener: OnItemClickListener, private val longClickListener: MessagesFragment) : RecyclerView.Adapter<MyAdapter.ViewHolder>()
     {

    inner class ViewHolder(private val binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.title
        val description: TextView = binding.description
        val imageUrl:ImageView=binding.ImgView
        val productCard: CardView=binding.productCard
        val star1:ImageView=binding.star1
        val star2:ImageView=binding.star2
        val star3:ImageView=binding.star3
        val star4:ImageView=binding.star4
        val star5:ImageView=binding.star5

        var imageViewList :List<ImageView> =  listOf(star1,star2,star3,star4,star5)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowLayoutBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = productList[position]
        holder.title.text = currentItem.title
        holder.description.text = currentItem.description
        Glide.with(context)
            .load(currentItem.image) // Assuming currentItem.image is a URL or a resource identifier
            .into(holder.imageUrl)


        val starNum=currentItem.rating.rate.toInt()
        holder.productCard.setOnLongClickListener {
            longClickListener.onItemLongClick(currentItem)

            true
        }

        holder.productCard.setOnClickListener{
        clickListener.onItemClick(currentItem)

        }
        holder.imageViewList.forEachIndexed { index,imageView ->
           if(index < starNum){
            imageView.setImageResource(R.drawable.ic_star)
           } else {
               imageView.setImageResource(R.drawable.ic_star_blur)
           }
        }
    }
    interface OnItemClickListener {
        fun onItemClick(item: MyDataItem)
    }


    }

