package com.example.recipie_app_for_final.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipie_app_for_final.dao.RecipieDao
import com.example.recipie_app_for_final.entities.Category
import com.example.recipie_app_for_final.entities.CategoryItems
import com.example.recipie_app_for_final.entities.Recipies
import com.example.recipie_app_for_final.entities.converter.CategoryListConverter


@Database(entities = [Recipies::class,CategoryItems::class,Category::class,CategoryListConverter::class], version = 1, exportSchema = false)
abstract class RecipieDatabase : RoomDatabase() {

    companion object{

        var recipieDatabase:RecipieDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): RecipieDatabase{
            if(recipieDatabase == null){
                recipieDatabase = Room.databaseBuilder(
                    context,
                    RecipieDatabase::class.java,
                    "recipie.db"
                ).build()
            }
            return recipieDatabase!!
        }
    }

    abstract fun recipieDao():RecipieDao
}