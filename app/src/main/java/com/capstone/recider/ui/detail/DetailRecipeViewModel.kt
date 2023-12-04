package com.capstone.recider.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.recider.data.api.ApiConfig
import com.capstone.recider.data.api.ApiService
import com.capstone.recider.data.model.RecipeDetail
import com.capstone.recider.data.model.RecipeDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailRecipeViewModel: ViewModel() {
    private val _recipeDetail = MutableLiveData<RecipeDetail>()
    val recipeDetail: LiveData<RecipeDetail> get() = _recipeDetail


    fun setRecipeDetail(recipeId : Long) {
        val recipeApi = ApiConfig.getApiService().create(ApiService::class.java)
        val call : Call<RecipeDetailResponse> = recipeApi.getRecipeDetail(recipeId)

        call.enqueue(object : Callback<RecipeDetailResponse> {
            override fun onResponse(
                call: Call<RecipeDetailResponse>,
                response: Response<RecipeDetailResponse>
            ) {
                if (response.isSuccessful) {
                    val recipeDetailResponse: RecipeDetailResponse? = response.body()
                    val recipeDetail: RecipeDetail? = recipeDetailResponse?.data
                    _recipeDetail.value = recipeDetail
                    Log.d("success", "Success")
                } else {
                    // handle unsuccessful response
                    Log.d("unsuccessful", response.toString())
                }
            }

            override fun onFailure(call: Call<RecipeDetailResponse>, t: Throwable) {
                // handle failure
                Log.d("fail", "Failure")
            }

        })
    }
}