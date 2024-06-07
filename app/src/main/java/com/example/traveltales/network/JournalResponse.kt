package com.example.traveltales.network

import kotlinx.serialization.Serializable

@Serializable
data class JournalResponse(
    val journal_id: Int,
    val name: String,
    val theme_id: Int?,
    val user_id: Int,
    val createdAt: String,
    val updatedAt: String
)