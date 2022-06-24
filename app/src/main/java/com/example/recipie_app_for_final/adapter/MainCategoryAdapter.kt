package com.example.recipie_app_for_final.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipie_app_for_final.R
import com.example.recipie_app_for_final.entities.CategoryItems
import com.example.recipie_app_for_final.entities.Recipies
import kotlinx.android.synthetic.main.item_main_cat.view.*
import kotlinx.android.synthetic.main.item_sub_cat.view.*
import kotlinx.android.synthetic.main.item_sub_cat.view.tv_dish_name

class MainCategoryAdapter: RecyclerView.Adapter<MainCategoryAdapter.RecipieViewHolder>() {
    var ctx: Context? = null
    var arrayMainCat = ArrayList<CategoryItems>()
    class RecipieViewHolder(view: View): RecyclerView.ViewHolder(view){

    }

    fun setData(arrData : List<CategoryItems>){
        arrayMainCat = arrData as ArrayList<CategoryItems>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipieViewHolder {
        ctx = parent.context
        return RecipieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main_cat,parent,false))
    }

    override fun getItemCount(): Int {
        return arrayMainCat.size
    }
    override fun onBindViewHolder(holder: RecipieViewHolder, position: Int) {
        holder.itemView.tv_dish_name.text = arrayMainCat[position].strCategory

        Glide.with(ctx!!).load(arrayMainCat[position].strCategoryThumb).into(holder.itemView.img_dish)
    }
}