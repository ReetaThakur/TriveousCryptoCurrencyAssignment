package com.reeta.triveouscryptocurrencyassignment.apiResponse

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ApiService {

  //  @Headers("X-CMC_PRO_API_KEY:326ad57d-203b-4066-8a1b-7280c22ffecc")
    @GET("v1/cryptocurrency/listings/latest")
    fun getCurrency(@Header("X-CMC_PRO_API_KEY") api_key:String):Call<ResponseDTO>
}