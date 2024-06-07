package com.example.traveltales.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

private const val BASE_URL = "http://10.0.2.2:8000"

private var token: String? = null

fun setToken(newToken: String) {
    token = newToken
}

private val client = OkHttpClient.Builder().addInterceptor { chain ->
    val requestBuilder = chain.request().newBuilder()
    token?.let {
        requestBuilder.addHeader("Authorization", "Bearer $it")
    }
    chain.proceed(requestBuilder.build())
}.build()

private val json = Json {
    ignoreUnknownKeys = true // Add this line to ignore unknown keys
}

private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .client(client)
    .build()

object ApiClient {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
