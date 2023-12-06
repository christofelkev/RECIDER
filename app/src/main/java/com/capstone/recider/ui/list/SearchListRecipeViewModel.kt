package com.capstone.recider.ui.list

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.recider.data.api.ApiConfig
import com.capstone.recider.data.api.ApiService
import com.capstone.recider.data.model.AllRecipesResponse
import com.capstone.recider.data.model.Category
import com.capstone.recider.data.model.Recipe
import com.capstone.recider.data.model.SearchRecipeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchListRecipeViewModel : ViewModel() {
    private val _listRecipe = MutableLiveData<List<com.capstone.recider.data.model.Recipe>>()
    val listRecipe: LiveData<List<com.capstone.recider.data.model.Recipe>> get() = _listRecipe

    private val _categoriesRecipe = MutableLiveData<List<Category>>()
    val categoriesRecipe: LiveData<List<Category>> get() = _categoriesRecipe

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> get() = _loadingState

    init {
        val categoryList = listOf(
            Category("Semua Resep"),
            Category("Ayam"),
            Category("Sapi")
        )
        _categoriesRecipe.value = categoryList

        fetchAllRecipes()
    }

    fun fetchAllRecipes() {
        val recipeApi = ApiConfig.getApiService().create(ApiService::class.java)
        val call: Call<AllRecipesResponse> = recipeApi.getAllRecipes()

        call.enqueue(object : Callback<AllRecipesResponse> {
            override fun onResponse(
                call: Call<AllRecipesResponse>,
                response: Response<AllRecipesResponse>
            ) {
                if (response.isSuccessful) {
                    val allRecipesResponse: AllRecipesResponse? = response.body()
                    val recipes: List<Recipe>? = allRecipesResponse?.data

                    if (recipes.isNullOrEmpty()) {
                        // Handle no result scenario
                        _listRecipe.value = emptyList()
                    } else {
                        _listRecipe.value = recipes
                    }

                } else {
                    // handle unsuccessful response
                    if (response.code() == 404) {
                        // Handle 404 status code (No results found)
                        _listRecipe.value = emptyList()
                    } else {
                        Log.d("unsuccessful", response.toString())
                    }
                }
            }

            override fun onFailure(call: Call<AllRecipesResponse>, t: Throwable) {
                // handle failure
            }
        })
    }


    fun setSearchRecipes(query: String) {
        val recipeApi = ApiConfig.getApiService().create(ApiService::class.java)
        val call: Call<SearchRecipeResponse> = recipeApi.searchRecipes(query)

        call.enqueue(object : Callback<SearchRecipeResponse> {
            override fun onResponse(
                call: Call<SearchRecipeResponse>,
                response: Response<SearchRecipeResponse>
            ) {
                if (response.isSuccessful) {
                    val searchRecipeResponse: SearchRecipeResponse? = response.body()
                    val recipes: List<com.capstone.recider.data.model.Recipe>? =
                        searchRecipeResponse?.data

                    if (recipes.isNullOrEmpty()) {
                        //Handle no result scenario
                        _listRecipe.value = emptyList()
                    } else {
                        _listRecipe.value = recipes
                    }

                } else {
                    // handle unsuccessful response
                    if (response.code() == 404) {
                        // Handle 404 status code (No results found)
                        _listRecipe.value = emptyList()
                    } else {
                        Log.d("unsuccessful", response.toString())
                    }

                }
            }

            override fun onFailure(call: Call<SearchRecipeResponse>, t: Throwable) {
                // handle failure
            }

        })
    }

    fun getSearchRecipes(): LiveData<List<com.capstone.recider.data.model.Recipe>> {
        return listRecipe
    }

    fun getCategories(): LiveData<List<Category>> {
        return categoriesRecipe
    }
}