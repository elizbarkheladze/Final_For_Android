package com.example.recipie_app_for_final.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipie_app_for_final.R
import com.example.recipie_app_for_final.entities.Recipies
import kotlinx.android.synthetic.main.item_main_cat.view.*
import kotlinx.android.synthetic.main.item_sub_cat.view.*
import kotlinx.android.synthetic.main.item_sub_cat.view.tv_dish_name

class SubCategoryAdapter: RecyclerView.Adapter<SubCategoryAdapter.RecipieViewHolder>() {

    var arraySubCat = ArrayList<Recipies>()
    class RecipieViewHolder(view: View): RecyclerView.ViewHolder(view){

    }

    fun setData(arrData : List<Recipies>){
        arraySubCat = arrData as ArrayList<Recipies>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipieViewHolder {
        return RecipieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_sub_cat,parent,false))
    }

    override fun getItemCount(): Int {
        return arraySubCat.size
    }
    override fun onBindViewHolder(holder: RecipieViewHolder, position: Int) {
        holder.itemView.tv_dish_name.text = arraySubCat[position].dishName
    }
}