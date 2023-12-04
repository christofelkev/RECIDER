package com.capstone.recider.ui.list

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
                    _listRecipe.value = recipes ?: emptyList()
                } else {
                    // handle unsuccessful response
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