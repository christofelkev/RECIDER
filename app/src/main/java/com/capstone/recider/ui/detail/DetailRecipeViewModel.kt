package com.capstone.recider.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.recider.data.dummyModel.DetailRecipe
import com.capstone.recider.data.dummyModel.Recipe
import com.capstone.recider.data.dummyModel.RecipeDataDummy

class DetailRecipeViewModel: ViewModel() {
    private val _recipeDetail = MutableLiveData<Recipe>()
    val recipeDetail: LiveData<Recipe> get() = _recipeDetail


    fun setRecipeDetail(recipeId : Int) {
        val recipe = RecipeDataDummy.dummyRecipe.find { it.id == recipeId }
        _recipeDetail.value = recipe
    }
}