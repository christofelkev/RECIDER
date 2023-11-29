package com.capstone.recider.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.recider.data.dummyModel.Recipe
import com.capstone.recider.data.dummyModel.RecipeDataDummy

class SearchListRecipeViewModel : ViewModel() {
    val listRecipe = MutableLiveData<List<Recipe>>()

    fun setSearchRecipes(query: String) {
        val searchResult = RecipeDataDummy.searchRecipes(query)
        listRecipe.value = searchResult
    }

    fun getSearchRecipes(): LiveData<List<Recipe>> {
        return listRecipe
    }
}