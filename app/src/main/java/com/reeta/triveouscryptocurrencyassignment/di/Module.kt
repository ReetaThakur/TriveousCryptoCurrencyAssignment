package com.reeta.triveouscryptocurrencyassignment.di

import android.content.Context
import androidx.room.Room
import com.reeta.triveouscryptocurrencyassignment.apiResponse.ApiService
import com.reeta.triveouscryptocurrencyassignment.database.CurrencyDao
import com.reeta.triveouscryptocurrencyassignment.database.RoomDatabaseCurrency
import com.reeta.triveouscryptocurrencyassignment.di.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    fun provideApiService(): ApiService {
        val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return builder.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRoomDb(@ApplicationContext context: Context): RoomDatabaseCurrency {
        val builder = Room.databaseBuilder(
            context, RoomDatabaseCurrency::class.java, "CurrencyDataBase"
        )
        builder.fallbackToDestructiveMigration()
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideDataToDao(db: RoomDatabaseCurrency): CurrencyDao {
        return db.getDao()
    }

}

object Constants {
    const  val BASE_URL="https://pro-api.coinmarketcap.com/"
    const val api_key="326ad57d-203b-4066-8a1b-7280c22ffecc"
}