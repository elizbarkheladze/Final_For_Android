package com.example.recipie_app_for_final.entities

import androidx.room.*

@Entity(tableName = "Category")
data class Category(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "strCategory")
    val strCategory: String,

    @ColumnInfo(name = "strCategoryThumb")
    val strCategoryThumb: String,

    @ColumnInfo(name = "strCategoryDescription")
    val strCategoryDescription: String
)

