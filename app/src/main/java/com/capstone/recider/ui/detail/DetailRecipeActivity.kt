package com.capstone.recider.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.capstone.recider.R
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

        val recipeId = intent.getIntExtra(EXTRA_RECIPE_ID, -1)
        if (recipeId != -1) {
            viewModel.setRecipeDetail(recipeId)
        }

        // Amati LiveData untuk memperbarui tampilan saat data resep berubah
        viewModel.recipeDetail.observe(this, Observer { recipeDetail ->
            // Update tampilan detail menggunakan data resep
            binding.tvRecipeTitleDetail.text = recipeDetail.name
            binding.tvAboutRecipe.text = getString(recipeDetail.description)
            // Sesuaikan dengan komponen UI lainnya
            Glide.with(this@DetailRecipeActivity)
                .load(recipeDetail.photo)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(binding.ivRecipeDetail)
        })
    }
}