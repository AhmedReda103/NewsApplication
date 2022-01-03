package com.example.newsapplication.ui.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.R
import com.google.android.material.card.MaterialCardView

class CategoriesAdapter(val categories: List<Category>) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val img: ImageView = itemView.findViewById(R.id.image)
        val cardView: MaterialCardView = itemView.findViewById(R.id.material_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            if (viewType == LEFT_SIDED_VIEW_TYPE) R.layout.left_sided_category
            else R.layout.right_sided_category, parent, false
        )
        return ViewHolder(view)

    }

    val LEFT_SIDED_VIEW_TYPE = 10;
    val RIGHT_SIDED_VIEW_TYPE = 20;
    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) LEFT_SIDED_VIEW_TYPE else RIGHT_SIDED_VIEW_TYPE

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = categories[position]
        holder.title.setText(item.titleId)
        holder.img.setImageResource(item.imgResId)
        holder.cardView.setCardBackgroundColor(
            ContextCompat.getColor(
                holder.itemView.context,
                item.backgroundColorId
            )
        )

        onItemClickListener?.let {
            holder.itemView.setOnClickListener {
                onItemClickListener?.onItemClick(position, item)
            }
        }

    }

    var onItemClickListener: OnItemClickListner? = null

    interface OnItemClickListner {
        fun onItemClick(pos: Int, category: Category)
    }

    override fun getItemCount(): Int = categories.size
}
