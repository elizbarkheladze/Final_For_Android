package com.example.recipie_app_for_final.interfaces

import com.example.recipie_app_for_final.entities.Category
import retrofit2.Call
import retrofit2.http.GET

interface Getdataservice {
    @GET("/categories.php")
    fun getCatList(): Call<Category>
}