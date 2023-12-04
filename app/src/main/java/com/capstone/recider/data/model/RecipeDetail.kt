package com.capstone.recider.data.model

data class RecipeDetail(
    val id: Long,
    val recipe: String,
    val recipeLower: String,
    val image: String,
    val ingredient: List<String>,
    val keywords: List<String>,
    val step: List<String>
)

data class RecipeDetailResponse(
    val status: String,
    val data: RecipeDetail
)