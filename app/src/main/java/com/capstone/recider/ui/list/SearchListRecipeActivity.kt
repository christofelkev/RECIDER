package com.capstone.recider.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.capstone.recider.data.model.Recipe
import com.capstone.recider.databinding.ActivitySearchBinding
import com.capstone.recider.ui.detail.DetailRecipeActivity

class SearchListRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var viewModel: SearchListRecipeViewModel
    private lateinit var adapter: ListRecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ListRecipeAdapter()

        viewModel = ViewModelProvider(this)[SearchListRecipeViewModel::class.java]
        //adapter.notifyDataSetChanged()

        adapter.setOnItemClickCallback(object : ListRecipeAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Recipe) {
                Intent(this@SearchListRecipeActivity, DetailRecipeActivity::class.java).also {
                    it.putExtra(DetailRecipeActivity.EXTRA_RECIPE_ID, data.id)
                    startActivity(it)
                }
            }


        })
        //viewModel = ViewModelProvider(
        //this,
        //ViewModelProvider.NewInstanceFactory()
        //)[SearchListRecipeViewModel::class.java]

        //viewModel.listRecipe.observe(this, Observer { recipes ->
        // Update adapter with the new list of recipes
        // adapter.setList(recipes)
        // adapter.notifyDataSetChanged()
        //})

        binding.apply {
            rvRecipe.layoutManager = GridLayoutManager(this@SearchListRecipeActivity, 2)
            rvRecipe.setHasFixedSize(true)
            rvRecipe.adapter = adapter

            btnSearch.setOnClickListener {
                searchRecipe()
            }

            edQuery.setOnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    searchRecipe()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }

        /*viewModel.getSearchRecipes().observe(this) {
            if (it != null) {
                adapter.setList(it)
                showLoading(false)
            }
        }*/

        viewModel.getSearchRecipes().observe(this, Observer { recipes ->
            if (recipes != null) {
                adapter.setList(recipes)
                showLoading(false)
            }

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
}