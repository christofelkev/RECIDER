package com.capstone.recider.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.recider.data.model.Category
import com.capstone.recider.data.model.Recipe
import com.capstone.recider.databinding.ActivitySearchBinding
import com.capstone.recider.ui.detail.DetailRecipeActivity

class SearchListRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var viewModel: SearchListRecipeViewModel
    private lateinit var adapter: ListRecipeAdapter
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ListRecipeAdapter()
        categoryAdapter = CategoryAdapter()

        viewModel = ViewModelProvider(this)[SearchListRecipeViewModel::class.java]

        adapter.setOnItemClickCallback(object : ListRecipeAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Recipe) {
                Intent(this@SearchListRecipeActivity, DetailRecipeActivity::class.java).also {
                    it.putExtra(DetailRecipeActivity.EXTRA_RECIPE_ID, data.id)
                    startActivity(it)
                }
            }


        })

        binding.apply {
            rvRecipe.layoutManager = GridLayoutManager(this@SearchListRecipeActivity, 2)
            rvRecipe.setHasFixedSize(true)
            rvRecipe.adapter = adapter

            // Setup Recyclerview for categories
            rvCategories.layoutManager = LinearLayoutManager(
                this@SearchListRecipeActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            rvCategories.adapter = categoryAdapter

            btnSearch.setOnClickListener {
                searchRecipe()
            }

            categoryAdapter.setOnCategoryItemClickListener(object :
                CategoryAdapter.OnCategoryItemClickListener {
                override fun onCategoryItemClick(category: Category) {
                    handleCategoryItemClick(category)
                }

            })


            edQuery.setOnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    searchRecipe()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }

        // Observe categories and update the categoryAdapter
        viewModel.getCategories().observe(this, Observer { categories ->
            categoryAdapter.setCategories(categories)
        })


        viewModel.getSearchRecipes().observe(this, Observer { recipes ->
            if (recipes != null) {
                if (recipes.isEmpty()) {
                    showNoResult()
                } else {
                    adapter.setList(recipes)
                    showLoading(false)
                }
            }
        })
        viewModel.loadingState.observe(this, Observer { isLoading ->
            showLoading(isLoading)
        })
    }


    private fun searchRecipe() {
        binding.apply {
            val query = edQuery.text.toString()
            if (query.isEmpty()) return
            showLoading(true)
            viewModel.setSearchRecipes(query)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }


    private fun showNoResult() {
        Toast.makeText(this, "Resep Tidak Ditemukan", Toast.LENGTH_SHORT).show()
        showLoading(false)
    }

    private fun handleCategoryItemClick(category: Category) {
        Toast.makeText(this, "Category: ${category.name}", Toast.LENGTH_SHORT).show()
        when (category.name) {
            "Semua Resep" -> {
                // Handle click for "All Recipe"
                // For example, show all recipes
                // You may call a method or perform an action to display all recipes
                showAllRecipes()
                showLoading(false)
            }

            "Ayam" -> {
                // Handle click for "Ayam"
                // For example, perform a search for recipes related to "Ayam"
                viewModel.setSearchRecipes("Ayam")
                showLoading(false)
            }

            "Sapi" -> {
                // Handle click for "Ayam"
                // For example, perform a search for recipes related to "Ayam"
                viewModel.setSearchRecipes("Sapi")
            }
            // Add cases for other categories as needed
            else -> {
                // Handle other categories
            }
        }
        showLoading(true)
    }

    private fun showAllRecipes() {
        viewModel.fetchAllRecipes()
        showLoading(true)
    }
}