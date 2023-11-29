package com.capstone.recider.data.model

data class RecipeResponse(

    //items harus sesuai response yg ada di api
    // atau bisa memakai @SerializedName("nama_variabel)
    val items : ArrayList<Recipe>
)
