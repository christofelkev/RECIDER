package com.capstone.recider.ui.recommendation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.recider.R
import com.capstone.recider.databinding.ActivityRecommendationRecipeBinding
import com.capstone.recider.ui.list.ListRecipeAdapter

class RecommendationRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecommendationRecipeBinding
    private lateinit var viewModel: RecommendationRecipeViewModel
    private lateinit var adapter: RecommendationRecipeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecommendationRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[RecommendationRecipeViewModel::class.java]
        adapter = RecommendationRecipeAdapter()


        binding.apply {
            rvRecommendation.layoutManager = LinearLayoutManager(
                this@RecommendationRecipeActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            rvRecommendation.adapter = adapter

            btnSearch.setOnClickListener {
                searchRecommendation()
            }
            edQuery.setOnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    searchRecommendation()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }

        viewModel.getRecommendationsRecipeData().observe(this, Observer { recommendations ->
            if (recommendations != null) {
                if (recommendations.isEmpty()) {
                    showNoResult()
                } else {
                    adapter.setList(recommendations)
                    showLoading(false)
                }
            }
        })


    }

    private fun showNoResult() {
        Toast.makeText(this, "Resep Tidak Ditemukan", Toast.LENGTH_SHORT).show()
        showLoading(false)
    }

    private fun searchRecommendation() {
        binding.apply {
            val query = edQuery.text.toString()
            if (query.isNotEmpty()) {
                showLoading(true)
                viewModel.setRecommendations(query)
                Log.d("query","Query : {$query}")
            }

        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}