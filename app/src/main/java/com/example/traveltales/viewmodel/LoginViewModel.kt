package com.example.traveltales.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traveltales.network.ApiClient
import com.example.traveltales.network.CreateJournalRequest
import com.example.traveltales.network.EntryResponse
import com.example.traveltales.network.JournalResponse
import com.example.traveltales.network.LoginRequest
import com.example.traveltales.network.LoginResponse
import com.example.traveltales.network.setToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _loginResult = MutableStateFlow<Result<String>?>(null)
    val loginResult: StateFlow<Result<String>?> = _loginResult

    private val _userToken = MutableStateFlow<String?>(null)
    val userToken: StateFlow<String?> = _userToken

    private val _journals = MutableStateFlow<List<JournalResponse>>(emptyList())
    val journals: StateFlow<List<JournalResponse>> = _journals

    private val _entries = MutableStateFlow<List<EntryResponse>>(emptyList())
    val entries: StateFlow<List<EntryResponse>> = _entries

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response: LoginResponse = ApiClient.retrofitService.login(LoginRequest(email, password))
                _loginResult.value = Result.success("Login successful. Token: ${response.token}")
                _userToken.value = response.token
                setToken(response.token)
                fetchUserJournals(response.token)
            } catch (e: Exception) {
                _loginResult.value = Result.failure(e)
            }
        }
    }

    fun createJournal(journalName: String) {
        viewModelScope.launch {
            try {
                _userToken.value?.let { token ->
                    val userId = extractUserIdFromToken(token)
                    val response = ApiClient.retrofitService.createJournal(
                        "Bearer $token", userId, CreateJournalRequest(journalName)
                    )
                    fetchUserJournals(token)
                }
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Error creating journal", e)
            }
        }
    }

    fun fetchEntries(journalId: Int) {
        viewModelScope.launch {
            try {
                val token = _userToken.value ?: return@launch
                Log.d("LoginViewModel", "Fetching entries for journalId: $journalId with token: $token")
                val entries = ApiClient.retrofitService.getJournalEntries("Bearer $token", journalId)
                Log.d("LoginViewModel", "Entries fetched: $entries")
                _entries.value = entries
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Error fetching entries", e)
            }
        }
    }

    fun fetchUserJournals(token: String) {
        viewModelScope.launch {
            try {
                val userId = extractUserIdFromToken(token)
                Log.d("LoginViewModel", "Fetching journals for user ID: $userId")
                val journals = ApiClient.retrofitService.getUserJournals("Bearer $token", userId)
                Log.d("LoginViewModel", "Journals fetched: $journals")
                _journals.value = journals
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Error fetching journals", e)
                _journals.value = emptyList()
            }
        }
    }

    private fun extractUserIdFromToken(token: String): String {
        val parts = token.split(".")
        if (parts.size == 3) {
            val payload = String(android.util.Base64.decode(parts[1], android.util.Base64.DEFAULT))
            val userId = Regex("\"id\":(\\d+)").find(payload)?.groups?.get(1)?.value
            return userId ?: ""
        }
        return ""
    }
}
