package com.reeta.triveouscryptocurrencyassignment.ui

import com.reeta.triveouscryptocurrencyassignment.database.AddCurrency

/*
this interface will use for deleting crypto currency from our database
 */
interface RemoveFromFavorites {

    fun removeCurrency(currency: AddCurrency)
}