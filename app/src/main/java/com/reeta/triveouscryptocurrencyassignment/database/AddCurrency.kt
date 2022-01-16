package com.reeta.triveouscryptocurrencyassignment.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currencyTable")
data class AddCurrency(@ColumnInfo(name = "currencyName")var currencyName:String,
                       @ColumnInfo(name = "currencySymbol")var currencySymbol:String,
                       @ColumnInfo(name = "currencyPrice")var currencyPrice:String){
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id:Int?=null
}
