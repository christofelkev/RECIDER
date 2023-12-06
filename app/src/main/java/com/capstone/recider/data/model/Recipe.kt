package com.capstone.recider.data.model

data class Recipe(
    val id: Long,
    val recipe: String,
    val image : String
)
data class SearchRecipeResponse(
    val status: String,
    val data: List<Recipe>
)
data class AllRecipesResponse(
    val status: String,
    val data: List<Recipe>
)



