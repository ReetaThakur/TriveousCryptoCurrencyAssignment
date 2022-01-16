package com.reeta.triveouscryptocurrencyassignment.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
This is our Entity class that will show how our table look like in to database, how many
column will present into our table. We will create object of this class and pass all
parameter that we want to store into database
 */
@Entity(tableName = "currencyTable")
data class AddCurrency(
    @ColumnInfo(name = "currencyName") var currencyName: String,
    @ColumnInfo(name = "currencySymbol") var currencySymbol: String,
    @ColumnInfo(name = "currencyPrice") var currencyPrice: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}
