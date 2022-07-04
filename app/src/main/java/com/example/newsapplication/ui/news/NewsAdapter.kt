package com.example.newsapplication.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapplication.R
import com.example.newsapplication.databinding.ItemNewsBinding
import com.example.newsapplication.model.ArticlesItem

class NewsAdapter(var items: List<ArticlesItem?>?) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(val itemBinding: ItemNewsBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: ArticlesItem?) {
            itemBinding.item = item
            itemBinding.executePendingBindings()
        }

//        val title: TextView = itemView.findViewById(R.id.title)
//        val date: TextView = itemView.findViewById(R.id.date)
//        val author: TextView = itemView.findViewById(R.id.author)
//        val image: ImageView = itemView.findViewById(R.id.image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
//        return ViewHolder(view)

        val viewBinding: ItemNewsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_news, parent, false
        )
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val items = items?.get(position)

        holder.bind(items)

//        holder.title.setText(items?.title)
//        holder.author.setText(items?.author)
//        holder.date.setText(items?.publishedAt)

        //to show photo we use glide package
//        Glide.with(holder.itemView)//instance from request
//            .load(items?.urlToImage).into(holder.itemBinding.image);
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    fun changeData(data: List<ArticlesItem?>?) {
        items = data
        notifyDataSetChanged()
    }


}