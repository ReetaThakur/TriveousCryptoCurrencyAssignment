package com.reeta.triveouscryptocurrencyassignment.repository

import androidx.lifecycle.LiveData
import com.reeta.triveouscryptocurrencyassignment.apiResponse.*
import com.reeta.triveouscryptocurrencyassignment.database.AddCurrency
import com.reeta.triveouscryptocurrencyassignment.database.CurrencyDao
import com.reeta.triveouscryptocurrencyassignment.di.Constants
import com.reeta.triveouscryptocurrencyassignment.di.Module
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class Repository @Inject constructor(private val currencyDao: CurrencyDao) {

    private val responseHandler: ResponseHandler = ResponseHandler()

    suspend fun getDataFromAPI(): Resource<ResponseDTO> {
        return try {
            val responseDTO: ResponseDTO =
                Module.provideApiService().getCurrency(Constants.api_key)
            responseHandler.handleSuccess(responseDTO)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    fun insertDataInDb(currecy:AddCurrency) {
        CoroutineScope(Dispatchers.IO).launch {
            currencyDao.insertCurrency(currecy)
        }
    }

    fun getCurrencyFromDb(): LiveData<List<AddCurrency>> {
        return currencyDao.getAllCurrency()
    }

    fun deleteCurrency(currecy: AddCurrency){
        currencyDao.deleteCurrency(currecy)
    }



}