package com.example.traveltales.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traveltales.network.ApiClient
import com.example.traveltales.network.LoginRequest
import com.example.traveltales.network.LoginResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _loginResult = MutableStateFlow<Result<String>?>(null)
    val loginResult: StateFlow<Result<String>?> = _loginResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response: LoginResponse = ApiClient.retrofitService.login(LoginRequest(email, password))
                _loginResult.value = Result.success("Login successful. Token: ${response.token}")
            } catch (e: Exception) {
                _loginResult.value = Result.failure(e)
            }
        }
    }
}
