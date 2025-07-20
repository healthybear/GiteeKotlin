package com.healthybear.giteekotlin.network.api

import androidx.annotation.NonNull
import com.healthybear.giteekotlin.dataSource.mmkv.SessionStorage
import com.healthybear.giteekotlin.network.response.GiteeToken
import com.healthybear.giteekotlin.network.response.SearchRepositoriesResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author: 郭健鸿
 * @Date: 2025-06-15 12:56
 * @Description:
 */
interface ISeacherApi {

    /**
     * 搜索仓库 简易参数
     */
    @GET("api/v5/search/repositories")
    suspend fun searchRepositories_simple(
        @NonNull @Query("q") query: String, // 搜索关键词
        @Query("page") page: Int = 1, // 页码（默认 1）
        @Query("per_page") perPage: Int = 30, //每页数量 默认 30，最大 100
    ): Response<List<SearchRepositoriesResult>>
}