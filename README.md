# Crypto Currency Readme file

This is documentation for crypto Currency app. what I used all things are in this file.


## Screenshots

![App Screenshot](https://raw.githubusercontent.com/ReetaThakur/TriveousCryptoCurrencyAssignment/master/Screenshot_20220116_184711.png)

![App Screenshot](https://raw.githubusercontent.com/ReetaThakur/TriveousCryptoCurrencyAssignment/master/Screenshot_20220116_184720.png)

![App Screenshot](https://raw.githubusercontent.com/ReetaThakur/TriveousCryptoCurrencyAssignment/master/Screenshot_20220116_184731.png)

## API Reference

#### Get all items

  BASE_URL="https://pro-api.coinmarketcap.com/"
  api_key="326ad57d-203b-4066-8a1b-7280c22ffecc"

@GET("v1/cryptocurrency/listings/latest")
suspend fun getCurrency(@Header("X-CMC_PRO_API_KEY") api_key:String):ResponseDTO





## Dependencies

    //Retrofit
    def retrofit2_version = "2.9.0"
    def okhttp3_version = "4.9.0"
    implementation 'com.squareup.retrofit2:adapter-rxjava2:2.5.0'
    implementation "com.squareup.retrofit2:retrofit:$retrofit2_version"
    implementation "com.squareup.retrofit2:converter-gson:$retrofit2_version"
    implementation "com.squareup.okhttp3:okhttp:$okhttp3_version"

    //hilt
    implementation("com.google.dagger:hilt-android:2.38.1")
    kapt("com.google.dagger:hilt-android-compiler:2.38.1")
    implementation 'androidx.activity:activity-ktx:1.4.0'


    // ViewModel and LiveData
    def arch_version = '2.2.0-alpha01'
    implementation "androidx.lifecycle:lifecycle-extensions:$arch_version"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$arch_version"
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:$arch_version"
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:$arch_version"

    // Coroutines
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2'


    //RoomDatabase
    implementation 'androidx.room:room-ktx:2.4.1'
    kapt 'androidx.room:room-compiler:2.4.1'

## Description
This is the crypto currency app, where you can find different types of crypto
currency and if you want to search any crypto currency by its name you can also
search those crypto currency. If you want to add any currency in your favorites
list you also can add currency in your favorites list and if in future you want
to remove currency from your favorites list you can remove also.

