package com.healthybear.giteekotlin.ui.login

import com.healthybear.giteekotlin.network.api.IAccessApi
import com.healthybear.giteekotlin.network.api.ISeacherApi
import com.healthybear.giteekotlin.network.response.GiteeToken
import com.healthybear.giteekotlin.network.response.SearchRepositoriesResult
import com.healthybear.library.network.manager.HttpManager
import com.healthybear.library.network.repository.BaseRepository
import com.healthybear.library.network.response.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginRespository : BaseRepository() {
    private val accessApi by lazy { HttpManager.create(IAccessApi::class.java) }

    fun login(email: String, password: String, clientId: String, clientSecret: String) : Flow<ApiResponse<GiteeToken>> {
        return flow {
            emit(requestResponse {
                accessApi.getAccessTokenByPwd(
                    username = email,
                    password = password,
                    clientId = clientId,
                    clientSecret = clientSecret)
            })
        }
    }

}