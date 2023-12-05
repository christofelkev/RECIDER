package com.capstone.recider.ui.list

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.recider.data.api.ApiConfig
import com.capstone.recider.data.api.ApiService
import com.capstone.recider.data.model.SearchRecipeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchListRecipeViewModel : ViewModel() {
    private val _listRecipe = MutableLiveData<List<com.capstone.recider.data.model.Recipe>>()
    val listRecipe: LiveData<List<com.capstone.recider.data.model.Recipe>> get() = _listRecipe

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
                        Log.d("unsuccessful",response.toString())
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
}