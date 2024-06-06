package com.example.traveltales.network

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(val token: String, val maxAge: Long)
