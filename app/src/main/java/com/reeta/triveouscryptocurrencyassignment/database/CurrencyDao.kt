package com.reeta.triveouscryptocurrencyassignment.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Update




/*
This is an interface that will map our sql queries to particular functions. so we can directly
insert our data into database.
Dao- data access object.
 */
@Dao
interface CurrencyDao {

    @Query("select * from currencyTable")
    fun getAllCurrency(): LiveData<List<AddCurrency>>?

    @Insert
    fun insertCurrency(currency: AddCurrency)

    @Delete
    fun deleteCurrency(currency: AddCurrency)

}