package com.example.traveltales.network

import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}
