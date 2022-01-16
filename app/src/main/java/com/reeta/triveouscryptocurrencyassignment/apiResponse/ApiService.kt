package com.reeta.triveouscryptocurrencyassignment.apiResponse

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

/* This interface use for api calling , we want to fetch only data from api thats why we
will use get request
 */

interface ApiService {

    @GET("v1/cryptocurrency/listings/latest")
   suspend fun getCurrency(@Header("X-CMC_PRO_API_KEY") api_key:String):ResponseDTO
}