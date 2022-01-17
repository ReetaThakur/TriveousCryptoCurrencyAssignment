package com.reeta.triveouscryptocurrencyassignment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.reeta.triveouscryptocurrencyassignment.apiResponse.Resource
import com.reeta.triveouscryptocurrencyassignment.apiResponse.ResponseDTO
import com.reeta.triveouscryptocurrencyassignment.database.AddCurrency
import com.reeta.triveouscryptocurrencyassignment.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/*
This is viewModel, our main data logic will here, so if any case our activity re-create
in this case our data will not loss, viewModel will communicate with repository for taking
the data and give that data to our activity.

All these things like viewModel, Repository provides one type of structure for our app.
 */
@HiltViewModel
class CurrencyViewModel @Inject constructor(val repository: Repository) : ViewModel() {

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

    fun getCurrencyData(): LiveData<List<AddCurrency>>? {
        return repository.getCurrencyFromDb()
    }


}