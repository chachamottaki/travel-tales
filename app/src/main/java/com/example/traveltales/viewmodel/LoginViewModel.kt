package com.example.traveltales.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traveltales.network.ApiClient
import com.example.traveltales.network.LoginRequest
import com.example.traveltales.network.LoginResponse
import com.example.traveltales.network.setToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.util.Log

class LoginViewModel : ViewModel() {
    private val _loginResult = MutableStateFlow<Result<String>?>(null)
    val loginResult: StateFlow<Result<String>?> = _loginResult

    private val _userToken = MutableStateFlow<String?>(null) // Define _userToken
    val userToken: StateFlow<String?> = _userToken // Define userToken

    private val _userData = MutableStateFlow<String>("")
    val userData: StateFlow<String> = _userData

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response: LoginResponse = ApiClient.retrofitService.login(LoginRequest(email, password))
                _loginResult.value = Result.success("Login successful. Token: ${response.token}")
                _userToken.value = response.token // Store user token on successful login
                setToken(response.token) // Set token for future requests
                fetchUserData(response.token) // Fetch user-specific data
            } catch (e: Exception) {
                _loginResult.value = Result.failure(e)
            }
        }
    }

    private fun fetchUserData(token: String) {
        viewModelScope.launch {
            try {
                val userId = extractUserIdFromToken(token)
                val response = ApiClient.retrofitService.getUserData("Bearer $token", userId)
                _userData.value = response.data
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Error fetching user data", e)
                _userData.value = "Failed to fetch user data"
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
