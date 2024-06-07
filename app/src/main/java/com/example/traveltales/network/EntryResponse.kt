package com.example.traveltales.network
import kotlinx.serialization.Serializable

@Serializable
data class EntryResponse(
    val entry_id: Int,
    val date: String,
    val location: String,
    val entry: String,
    val createdAt: String,
    val updatedAt: String
)