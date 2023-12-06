package com.capstone.recider.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstone.recider.R
import com.capstone.recider.data.model.Category


class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var categories: List<Category> = emptyList()
    private var categoryItemClickListener: OnCategoryItemClickListener? = null

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryName: TextView = itemView.findViewById(R.id.tv_categories)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    categoryItemClickListener?.onCategoryItemClick(categories[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.categoryName.text = category.name
        // Set an OnClickListener to handle category item clicks
        holder.itemView.setOnClickListener {
            onCategoryItemClick(category)
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    fun setCategories(categories: List<Category>) {
        this.categories = categories
        notifyDataSetChanged()
    }

    // Define a callback interface for category item clicks
    interface OnCategoryItemClickListener {
        fun onCategoryItemClick(category: Category)
    }


    fun setOnCategoryItemClickListener(listener: OnCategoryItemClickListener) {
        this.categoryItemClickListener = listener
    }

    private fun onCategoryItemClick(category: Category) {
        categoryItemClickListener?.onCategoryItemClick(category)
    }
}
