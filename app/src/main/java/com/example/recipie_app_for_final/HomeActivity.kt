package com.example.recipie_app_for_final

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipie_app_for_final.adapter.MainCategoryAdapter
import com.example.recipie_app_for_final.adapter.SubCategoryAdapter
import com.example.recipie_app_for_final.database.RecipieDatabase
import com.example.recipie_app_for_final.entities.CategoryItems
import com.example.recipie_app_for_final.entities.Recipies
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.launch

class HomeActivity : BaseActivity() {
    var arrayMainCategory = ArrayList<CategoryItems>()
    var arraySubCategory = ArrayList<Recipies>()
    var mainCategoryAdapter = MainCategoryAdapter()
    var subCategoryAdapter = SubCategoryAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //Test


        getDatafromDb()
//        arraySubCategory.add(Recipies(1,"mwvadi2"))
//        arraySubCategory.add(Recipies(2,"qababi3"))
//        arraySubCategory.add(Recipies(3,"khinkali4"))
//        arraySubCategory.add(Recipies(4,"lobio5"))
//        subCategoryAdapter.setData(arraySubCategory)


        rv_main_category.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rv_main_category.adapter = mainCategoryAdapter

        rv_sub_category.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rv_sub_category.adapter = subCategoryAdapter


    }

    private fun getDatafromDb(){
        launch {
            this.let {
                var cat = RecipieDatabase.getDatabase(this@HomeActivity).recipieDao().getAllCategory()
                arrayMainCategory = cat as ArrayList<CategoryItems>
                arrayMainCategory.reverse()
                mainCategoryAdapter.setData(arrayMainCategory)
                rv_main_category.layoutManager = LinearLayoutManager(this@HomeActivity,LinearLayoutManager.HORIZONTAL,false)
                rv_main_category.adapter = mainCategoryAdapter
            }
        }
    }


}