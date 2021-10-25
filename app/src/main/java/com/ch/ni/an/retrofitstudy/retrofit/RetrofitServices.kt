package com.ch.ni.an.retrofitstudy.retrofit

import com.ch.ni.an.retrofitstudy.model.AnimeChan
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {
    @GET("random")
  suspend  fun getAnimeChan(): AnimeChan
}

object Common{
    private val BASE_URL = "https://animechan.vercel.app/api/"

    val retrofitServices: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}