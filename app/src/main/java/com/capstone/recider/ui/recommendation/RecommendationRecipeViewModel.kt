package com.capstone.recider.ui.recommendation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.recider.data.api.ApiConfig
import com.capstone.recider.data.api.ApiService
import com.capstone.recider.data.api.RecommendationRequest
import com.capstone.recider.data.model.RecommendationRecipe
import com.capstone.recider.data.model.RecommendationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendationRecipeViewModel : ViewModel() {

    private val _recommendationsRecipe = MutableLiveData<List<RecommendationRecipe>>()
    val recommendationsRecipe: LiveData<List<RecommendationRecipe>> get() = _recommendationsRecipe

    fun setRecommendations(requestBody: String) {
        val recommendationRequest = RecommendationRequest(requestBody)
        val recipeApi = ApiConfig.getApiService().create(ApiService::class.java)
        val call: Call<RecommendationResponse> = recipeApi.getRecommendations(recommendationRequest)


        call.enqueue(object : Callback<RecommendationResponse> {
            override fun onResponse(
                call: Call<RecommendationResponse>,
                response: Response<RecommendationResponse>
            ) {
                if (response.isSuccessful) {
                    val recommendationResponse: RecommendationResponse? = response.body()
                    val recommendations: List<RecommendationRecipe> =
                        recommendationResponse?.recommendations.orEmpty()
                    _recommendationsRecipe.value = recommendations

                } else {
                    // Handle unsuccessful response
                    Log.d("unsuccessful", "Response Code: ${response.code()}")
                    Log.d("unsuccessful", "Error Body: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<RecommendationResponse>, t: Throwable) {
                // Handle failure
                Log.d("unsuccessful", "Error fetching recommendations: ${t.message}")
                Log.d("unsuccessful", "Error Body: ${call.execute().errorBody()?.string()}")
                _recommendationsRecipe.value = emptyList()
            }

        })

    }

    fun getRecommendationsRecipeData(): LiveData<List<RecommendationRecipe>> {
        return recommendationsRecipe
    }
}