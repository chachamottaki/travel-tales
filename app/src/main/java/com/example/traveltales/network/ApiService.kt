package com.example.traveltales.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @GET("/user/{user_id}")
    suspend fun getUserData(
        @Header("Authorization") token: String,
        @Path("user_id") userId: String
    ): UserDataResponse
}
