package com.example.traveltales.network
import kotlinx.serialization.Serializable

@Serializable
data class EditJournalRequest(
    val name: String
)