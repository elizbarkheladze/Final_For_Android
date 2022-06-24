package com.example.recipie_app_for_final.entities

import androidx.room.*
import com.example.recipie_app_for_final.entities.converter.CategoryListConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Category")
data class Category(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    @ColumnInfo(name = "categorieitems")
    @Expose
    @SerializedName("categories")
    @TypeConverters(CategoryListConverter::class)
    var categoryItems: List<CategoryItems>? = null
)

