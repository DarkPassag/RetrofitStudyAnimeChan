package com.ch.ni.an.retrofitstudy.retrofit

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {
    private var retrofit: Retrofit? = null

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun getClient(baseUrl: String): Retrofit {
        if(retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }
        return retrofit!!
    }
}