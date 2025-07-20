package com.healthybear.giteekotlin.ui.login

import android.se.omapi.Session
import androidx.compose.ui.platform.LocalView
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.google.gson.Gson
import com.healthybear.giteekotlin.constants.AppConstants
import com.healthybear.giteekotlin.dataSource.mmkv.SessionStorage
import com.healthybear.giteekotlin.network.response.GiteeToken
import com.healthybear.library.base.viewModel.BaseViewModel
import com.healthybear.library.network.response.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.util.Arrays

class LoginViewModel : BaseViewModel() {
    private val loginRespository by lazy { LoginRespository() }

    private val sessionStorage by lazy { SessionStorage() }


    private val _loginResult = MutableStateFlow<ApiResponse<GiteeToken>>(ApiResponse.Empty)
    val loginResult: StateFlow<ApiResponse<GiteeToken>> = _loginResult


    fun login(email: String, password: String, clientId: String, clientSecret: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _loginResult.value = ApiResponse.Error("Email or password cannot be empty", null)
            return
        }
        viewModelScope.launch {
            loginRespository.login(email, password, clientId, clientSecret)
                .flowOn(Dispatchers.IO)
                .onStart {
                    _loginResult.value = ApiResponse.Loading("登录中...")
                }
                .collect { state ->
                    _loginResult.value = state
                    if (state is ApiResponse.Success<GiteeToken>) {
                        LogUtils.d(GsonUtils.toJson(state.data))
                        sessionStorage.setData(AppConstants.STORAGE_KEYS.TAG_GITEE_TOKEN, state.data.accessToken)
                        sessionStorage.setData(AppConstants.STORAGE_KEYS.TAG_GITEE_REFRESH_TOKEN, state.data.refreshToken)
                        sessionStorage.setData(AppConstants.STORAGE_KEYS.TAG_GITEE_SCOPE, state.data.scope)
                    }
                }
        }
    }

    fun updateLoginResultState() {
        _loginResult.value = _loginResult.value.getMarkedAsUsedData()
    }


}