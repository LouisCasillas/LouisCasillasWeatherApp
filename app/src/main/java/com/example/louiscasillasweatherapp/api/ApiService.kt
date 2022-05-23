package com.example.louiscasillasweatherapp.api

import com.example.louiscasillasweatherapp.model.WeatherResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val WEATHER_API_KEY = "526d7da03414cb9cfe34b27bcef68e5c"

interface ApiService {
    @GET("forecast")
    suspend fun getForecast(
        @Query("q") name: String? = null,
        @Query("appid") apiKey: String = WEATHER_API_KEY
    ): Response<WeatherResponse>

    companion object {
        private var instance: ApiService? = null
        fun getApiService(): ApiService {
            if (instance ==  null) {
                instance = Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)
            }
            return instance!!
        }
    }
}