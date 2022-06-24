package com.example.recipie_app_for_final

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.recipie_app_for_final.database.RecipieDatabase
import com.example.recipie_app_for_final.dto.CategoryDTO
import com.example.recipie_app_for_final.entities.Category
import com.example.recipie_app_for_final.interfaces.Getdataservice
import com.example.recipie_app_for_final.retrofit.retrofitclientinstance
import kotlinx.android.synthetic.main.splash_screen.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        getCategories()
        btnStart.setOnClickListener {
            val intent = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getCategories() {
        val service = retrofitclientinstance.retrofitInstace?.create(Getdataservice::class.java)
        val call = service?.getCatList()
        call?.enqueue(object : Callback<CategoryDTO> {
            override fun onResponse(
                call: Call<CategoryDTO>,
                response: Response<CategoryDTO>
            ) {
                response.body()?.let {
                    insertDataIntoRoomDb(it.categories.map { item ->
                        Category(
                            item.idCategory.toInt(),
                            item.strCategory,
                            item.strCategoryThumb,
                            item.strCategoryDescription
                        )
                    })
                }
            }

            override fun onFailure(call: Call<CategoryDTO>, t: Throwable) {
                loader.visibility = View.INVISIBLE
                Toast.makeText(this@SplashActivity, "racxa ar wevida sworad", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    fun insertDataIntoRoomDb(category: List<Category>) {
        CoroutineScope(Dispatchers.IO).launch {
            RecipieDatabase.getDatabase(this@SplashActivity).recipieDao().clearDb()
            for (item in category) {
                RecipieDatabase.getDatabase(this@SplashActivity)
                    .recipieDao().insertCategory(item)
            }
            btnStart.visibility = View.VISIBLE
        }
    }
}