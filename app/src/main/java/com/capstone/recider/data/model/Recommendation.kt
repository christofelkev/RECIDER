package com.capstone.recider.data.model

data class RecommendationResponse(
    val recommendations: List<Recommendation>
)

data class Recommendation(
    val ingredients: String,
    val steps: String,
    val title: String
)
