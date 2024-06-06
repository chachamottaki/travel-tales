package com.example.traveltales.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traveltales.network.LoginRequest
import com.example.traveltales.network.LoginResponse
import com.example.traveltales.network.ApiClient
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _loginResult = MutableLiveData<Result<String>>()
    val loginResult: LiveData<Result<String>> = _loginResult

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
