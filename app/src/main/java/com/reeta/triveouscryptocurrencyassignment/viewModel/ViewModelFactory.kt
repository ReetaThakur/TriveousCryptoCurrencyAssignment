package com.reeta.triveouscryptocurrencyassignment.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reeta.triveouscryptocurrencyassignment.repository.Repository
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private var repository: Repository):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrencyViewModel(repository) as T
    }
}