package com.capstone.recider.data.model

data class RecommendationResponse(
    val recommendations: List<RecommendationRecipe>
)

data class RecommendationRecipe(
    val Ingredients: String,
    val Steps: String,
    val Title: String,
    val error : String
)
