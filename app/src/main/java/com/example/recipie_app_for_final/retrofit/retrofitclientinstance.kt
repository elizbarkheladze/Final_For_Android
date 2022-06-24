package com.example.recipie_app_for_final.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class retrofitclientinstance {
    companion object{
        private val BASE_URL = "https://www.themealdb.com/"
        private var retrofit: Retrofit? = null
        val retrofitInstace:Retrofit?
            get() {
                if (retrofit == null){
                    retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
                return retrofit
            }
    }

}