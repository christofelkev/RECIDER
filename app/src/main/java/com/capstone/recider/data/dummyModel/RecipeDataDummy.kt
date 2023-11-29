package com.capstone.recider.data.dummyModel

import com.capstone.recider.R

object RecipeDataDummy {
    val dummyRecipe = listOf(
        Recipe(
            id = 0,
            name = "Rendang",
            photo = R.drawable.rendang,
            description = R.string.rawon_recipe
        ),
        Recipe(
            id = 1,
            name = "Ayam Bakar",
            photo = R.drawable.rendang,
            description = R.string.rawon_recipe
        ),
        Recipe(
            id = 2,
            name = "Mie Ayam",
            photo = R.drawable.rendang,
            description = R.string.rawon_recipe
        ),
        Recipe(
            id = 3,
            name = "Sop Iga",
            photo = R.drawable.rendang,
            description = R.string.rawon_recipe
        ),
        Recipe(
            id = 4,
            name = "Gulai ",
            photo = R.drawable.rendang,
            description = R.string.rawon_recipe
        ),
        Recipe(
            id = 5,
            name = "Ketoprak",
            photo = R.drawable.rendang,
            description = R.string.rawon_recipe
        ),
        Recipe(
            id = 6,
            name = "Sate Ayam",
            photo = R.drawable.rendang,
            description = R.string.rawon_recipe
        ),
        Recipe(
            id = 7,
            name = "Bakso Sapi",
            photo = R.drawable.rendang,
            description = R.string.rawon_recipe
        ),
    )

    fun searchRecipes(query: String): List<Recipe> {
        return dummyRecipe.filter { recipe ->
            recipe.name.contains(query, ignoreCase = true)
        }
    }
}