package com.capstone.recider.ui.recommendation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstone.recider.data.model.RecommendationRecipe
import com.capstone.recider.databinding.ItemRecommendationBinding

class RecommendationRecipeAdapter :
    RecyclerView.Adapter<RecommendationRecipeAdapter.RecommendationViewHolder>() {

    private val list = ArrayList<RecommendationRecipe>()

    fun setList(recommendations: List<RecommendationRecipe>) {
        list.clear()
        list.addAll(recommendations)
        notifyDataSetChanged()
    }

    inner class RecommendationViewHolder(val binding: ItemRecommendationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recommendation: RecommendationRecipe) {
            binding.apply {
                tvRecommendationTitle.text = recommendation.Title

                // Mengganti "--" dengan "\n-" dan menghilangkan tanda minus ("-") di akhir
                val ingredientsText = recommendation.Ingredients
                    .replace("--", "\n-")
                    .replace(Regex("""-\s*$"""), "")
                tvIngredient.text = ingredientsText

                val stepsText = recommendation.Steps
                    .replace("--", "\n\n-")
                    .replace(Regex("""-\s*$"""), "")
                tvSteps.text = stepsText
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendationRecipeAdapter.RecommendationViewHolder {
        val view =
            ItemRecommendationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecommendationViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RecommendationRecipeAdapter.RecommendationViewHolder,
        position: Int
    ) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}