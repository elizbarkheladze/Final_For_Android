package com.example.recipie_app_for_final.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

import androidx.room.Query
import com.example.recipie_app_for_final.entities.Category

@Dao
interface RecipieDao {

    @Query("SELECT * FROM category ORDER BY id DESC")
    suspend fun getAllCategory(): List<Category>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category: Category?)

    @Query("DELETE FROM category")
    fun clearDb()

}