package com.example.recipie_app_for_final.interfaces

import com.example.recipie_app_for_final.dto.CategoryDTO
import com.example.recipie_app_for_final.dto.MealsDTO
import com.example.recipie_app_for_final.entities.Category
import retrofit2.Call
import retrofit2.http.GET

interface Getdataservice {
    @GET("/api/json/v1/1/categories.php")
    fun getCatList(): Call<CategoryDTO>

    @GET("/api/json/v1/1/search.php?f=a")
    fun getMeals(): Call<MealsDTO>
}