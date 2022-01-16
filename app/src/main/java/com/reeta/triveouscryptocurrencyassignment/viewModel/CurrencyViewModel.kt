package com.reeta.triveouscryptocurrencyassignment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.reeta.triveouscryptocurrencyassignment.apiResponse.Resource
import com.reeta.triveouscryptocurrencyassignment.apiResponse.ResponseDTO
import com.reeta.triveouscryptocurrencyassignment.database.AddCurrency
import com.reeta.triveouscryptocurrencyassignment.repository.Repository
import com.reeta.triveouscryptocurrencyassignment.ui.MainUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(val repository: Repository):ViewModel() {

    fun getDataFromAPI(): LiveData<Resource<ResponseDTO>> {
        return liveData(Dispatchers.IO) {
            val data = repository.getDataFromAPI()
            emit(data)
        }
    }

    fun insertCurrencyData(currency: AddCurrency) {
        repository.insertDataInDb(currency)
    }

    fun deleteCurrencyData(currency: AddCurrency) {
        repository.deleteCurrency(currency)
    }

    fun getCurrencyData():LiveData<List<AddCurrency>> {
       return repository.getCurrencyFromDb()
    }
}