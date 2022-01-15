package com.reeta.triveouscryptocurrencyassignment.apiResponse

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {

    val BASE_URL="https://pro-api.coinmarketcap.com/"
    val api_key="326ad57d-203b-4066-8a1b-7280c22ffecc"


    fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()
}