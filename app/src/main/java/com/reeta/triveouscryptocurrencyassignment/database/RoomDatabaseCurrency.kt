package com.reeta.triveouscryptocurrencyassignment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*
This class is like a medium to communicate with SQLite database, bcz room database also use
SQLite database intenally.
This is database class that will extends RoomDatabase() class,we can only create one object
and use anywhere.
 */
@Database(entities = [AddCurrency::class], version = 1)
abstract class RoomDatabaseCurrency : RoomDatabase() {

    abstract fun getDao(): CurrencyDao

    companion object {
        private var INSTANCE: RoomDatabaseCurrency? = null

        fun getDatabaseObject(context: Context): RoomDatabaseCurrency {

            if (INSTANCE == null) {
                val builder =
                    Room.databaseBuilder(
                        context.applicationContext,
                        RoomDatabaseCurrency::class.java,
                        "CurrencyDataBase"
                    )
                builder.fallbackToDestructiveMigration()
                INSTANCE = builder.build()
                return INSTANCE!!
            } else {
                return INSTANCE!!
            }
        }
    }
}