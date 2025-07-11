package com.healthybear.giteekotlin.ui.login

import androidx.lifecycle.viewModelScope
import com.healthybear.giteekotlin.network.response.GiteeToken
import com.healthybear.library.base.viewModel.BaseViewModel
import com.healthybear.library.network.response.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {
    private val loginRespository by lazy { LoginRespository() }

    private val _loginResult = MutableStateFlow<ApiResponse<GiteeToken>>(ApiResponse.Loading)
    val loginResult: StateFlow<ApiResponse<GiteeToken>> = _loginResult

    fun login(email: String, password: String, clientId: String, clientSecret: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _loginResult.value = ApiResponse.Error("Email or password cannot be empty", null)
            return
        }
        viewModelScope.launch {
            loginRespository.login(email, password, clientId, clientSecret)
                .flowOn(Dispatchers.IO)
                .collect { state ->
                    _loginResult.value = state
                }
        }
    }


}