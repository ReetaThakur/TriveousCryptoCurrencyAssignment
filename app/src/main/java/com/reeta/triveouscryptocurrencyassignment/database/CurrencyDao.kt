package com.reeta.triveouscryptocurrencyassignment.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CurrencyDao {

    @Query("select * from currencyTable")
    fun getAllCurrency():LiveData<List<AddCurrency>>

    @Insert
    fun insertCurrency(currency: AddCurrency)

    @Delete
    fun deleteCurrency(currency: AddCurrency)
}