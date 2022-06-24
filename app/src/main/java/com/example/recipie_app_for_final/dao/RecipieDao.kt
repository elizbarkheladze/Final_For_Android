package com.example.recipie_app_for_final.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

import androidx.room.Query
import com.example.recipie_app_for_final.entities.Category
import com.example.recipie_app_for_final.entities.CategoryItems


@Dao
interface RecipieDao {

    @Query("SELECT * FROM category ORDER BY id DESC")
    suspend fun getAllCategory(): List<CategoryItems>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(categoryItems: CategoryItems?)

    @Query("DELETE FROM categoryitems")
    suspend fun clearDb()

}