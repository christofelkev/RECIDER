package com.capstone.recider.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.recider.R
import com.capstone.recider.databinding.ActivityDetailRecipeBinding

class DetailRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailRecipeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}