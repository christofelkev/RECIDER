package com.capstone.recider.data.api

import com.capstone.recider.data.model.RecipeDetailResponse
import com.capstone.recider.data.model.SearchRecipeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/searchRecipe/{query}") // Update the search endpoint with the correct path
    fun searchRecipes(@Path("query") query: String): Call<SearchRecipeResponse>

    @GET("api/recipes/{recipeId}") // Update the recipe detail endpoint with the correct path
    fun getRecipeDetail(@Path("recipeId") recipeId: Long): Call<RecipeDetailResponse>
}