package com.reeta.triveouscryptocurrencyassignment.ui

import com.reeta.triveouscryptocurrencyassignment.apiResponse.ResponseDTO

sealed class MainUiModel{

    data class onSuccess(val responseDTO: ResponseDTO):MainUiModel()
    data class onFailure(val error:String):MainUiModel()
}
