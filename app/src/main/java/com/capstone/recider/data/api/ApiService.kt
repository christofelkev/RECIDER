package com.capstone.recider.data.api

import com.capstone.recider.data.model.AllRecipesResponse
import com.capstone.recider.data.model.RecipeDetailResponse
import com.capstone.recider.data.model.RecommendationResponse
import com.capstone.recider.data.model.SearchRecipeResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("api/searchRecipe/{query}") // Update the search endpoint with the correct path
    fun searchRecipes(@Path("query") query: String): Call<SearchRecipeResponse>

    @GET("api/recipe/{recipeId}") // Update the recipe detail endpoint with the correct path
    fun getRecipeDetail(@Path("recipeId") recipeId: Long): Call<RecipeDetailResponse>

    @GET("api/allRecipes")
    fun getAllRecipes(): Call<AllRecipesResponse>

    @POST("/recommend")
    fun getRecommendations(@Body requestBody: Map<String, String>): Call<RecommendationResponse>

}