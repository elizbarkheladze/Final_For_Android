package com.example.recipie_app_for_final

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipie_app_for_final.adapter.MainCategoryAdapter
import com.example.recipie_app_for_final.adapter.SubCategoryAdapter
import com.example.recipie_app_for_final.database.RecipieDatabase
import com.example.recipie_app_for_final.dto.MealsDTO
import com.example.recipie_app_for_final.entities.Category
import com.example.recipie_app_for_final.entities.Recipies
import com.example.recipie_app_for_final.interfaces.Getdataservice
import com.example.recipie_app_for_final.retrofit.retrofitclientinstance
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : BaseActivity() {
    var arrayMainCategory = ArrayList<Category>()
    var arraySubCategory = ArrayList<Recipies>()
    var mainCategoryAdapter = MainCategoryAdapter()
    var subCategoryAdapter = SubCategoryAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        getDatafromDb()
        rv_main_category.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_main_category.adapter = mainCategoryAdapter
        rv_sub_category.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_sub_category.adapter = subCategoryAdapter
    }

    private fun getDatafromDb() {

        val service = retrofitclientinstance.retrofitInstace?.create(Getdataservice::class.java)
        val call = service?.getMeals()

        call?.enqueue(object : Callback<MealsDTO> {
            override fun onResponse(call: Call<MealsDTO>, response: Response<MealsDTO>) {
                response.body()?.let {
                    subCategoryAdapter.setData(it.meals.map { meal ->
                        Recipies(meal.strMeal, meal.strMealThumb)
                    })
                }
            }

            override fun onFailure(call: Call<MealsDTO>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        launch {
            val cat = RecipieDatabase.getDatabase(this@HomeActivity).recipieDao().getAllCategory()
            arrayMainCategory = cat as ArrayList<Category>
            arrayMainCategory.reverse()
            mainCategoryAdapter.setData(arrayMainCategory)
            rv_main_category.layoutManager =
                LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            rv_main_category.adapter = mainCategoryAdapter
        }
    }


}