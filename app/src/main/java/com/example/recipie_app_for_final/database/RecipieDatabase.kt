package com.example.recipie_app_for_final.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipie_app_for_final.dao.RecipieDao
import com.example.recipie_app_for_final.entities.Category
import com.example.recipie_app_for_final.entities.Recipies


@Database(
    entities = [Category::class],
    version = 1
)
abstract class RecipieDatabase : RoomDatabase() {

    companion object {

        var recipieDatabase: RecipieDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): RecipieDatabase {
            if (recipieDatabase == null) {
                recipieDatabase = Room.databaseBuilder(
                    context,
                    RecipieDatabase::class.java,
                    "recipie-db"
                ).build()
            }
            return recipieDatabase!!
        }
    }

    abstract fun recipieDao(): RecipieDao
}