package com.capstone.recider.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.capstone.recider.databinding.ActivityDetailRecipeBinding

class DetailRecipeActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_RECIPE_ID = "extra_recipe_id"
    }

    private lateinit var viewModel: DetailRecipeViewModel

    private lateinit var binding: ActivityDetailRecipeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[DetailRecipeViewModel::class.java]

        val recipeId = intent.getLongExtra(EXTRA_RECIPE_ID, -1)
        if (recipeId != -1L) {
            viewModel.setRecipeDetail(recipeId)
        }

        // Amati LiveData untuk memperbarui tampilan saat data resep berubah
        viewModel.recipeDetail.observe(this, Observer { recipeDetail ->
            // Update tampilan detail menggunakan data resep
            binding.tvRecipeTitleDetail.text = recipeDetail.recipe

            //binding.tvAboutRecipe.text = recipeDetail.ingredient.joinToString("\n")
            // Menggunakan bullet point untuk ingredient
            val ingredientText = recipeDetail.ingredient.joinToString("\n") { "- $it" }
            binding.tvAboutRecipe.text = ingredientText

            //binding.tvAboutRecipeHow.text = recipeDetail.step.joinToString("\n\n")
            // Menggunakan angka untuk langkah-langkah
            val stepsText = recipeDetail.step.withIndex()
                .joinToString("\n\n") { (index, step) -> "${index + 1}. $step" }
            binding.tvAboutRecipeHow.text = stepsText

            // Sesuaikan dengan komponen UI lainnya
            Glide.with(this@DetailRecipeActivity)
                .load(recipeDetail.image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(binding.ivRecipeDetail)
        })
    }
}