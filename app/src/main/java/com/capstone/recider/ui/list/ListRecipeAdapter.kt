package com.capstone.recider.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.capstone.recider.data.dummyModel.Recipe
import com.capstone.recider.databinding.ItemRecipeBinding

class ListRecipeAdapter : RecyclerView.Adapter<ListRecipeAdapter.RecipeViewHolder>() {

    private val list = ArrayList<Recipe>()

    fun setList(recipes: List<Recipe>) {
        list.clear()
        list.addAll(recipes)
        notifyDataSetChanged()
    }

    inner class RecipeViewHolder(val binding: ItemRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: Recipe) {
            binding.apply {
                Glide.with(itemView)
                    .load(recipe.photo)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(ivRecipe)
                tvRecipeTitle.text = recipe.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(list[position])
    }
}