package com.healthybear.giteekotlin.ui.main.home

import com.healthybear.giteekotlin.network.api.ISeacherApi
import com.healthybear.giteekotlin.network.response.SearchRepositoriesResult
import com.healthybear.giteekotlin.com.healthybear.giteekotlin.network.manager.HttpManager
import com.healthybear.giteekotlin.dataSource.mmkv.SessionStorage
import com.healthybear.library.network.repository.BaseRepository
import com.healthybear.library.network.response.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @author: 郭健鸿
 * @Date: 2025-06-15 13:31
 * @Description:
 */
class HomeRepository: BaseRepository() {
    private val searcheApi by lazy { HttpManager.create(ISeacherApi::class.java) }

    fun searchRepositories_simple(work: String): Flow<ApiResponse<List<SearchRepositoriesResult>>> {
        return flow {
            emit(requestResponse {
                searcheApi.searchRepositories_simple(query = work)
            })
        }
    }

}