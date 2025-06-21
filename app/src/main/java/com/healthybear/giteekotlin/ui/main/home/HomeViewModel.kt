package com.healthybear.giteekotlin.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.healthybear.giteekotlin.network.response.SearchRepositoriesResult
import com.healthybear.library.network.response.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val dataRepository by lazy { HomeRepository() }

    private val _responses = MutableStateFlow<ApiResponse<List<SearchRepositoriesResult>>>(ApiResponse.Loading)
    val mResponses: StateFlow<ApiResponse<List<SearchRepositoriesResult>>> = _responses


    fun searchResponse(work: String) {
        viewModelScope.launch(Dispatchers.Main) {
            dataRepository.searchRepositories_simple(work)
                .flowOn(Dispatchers.IO)
                .collect { state ->
                    LogUtils.d("test   "+state.toString())
                    _responses.value = state
//                    when(state) {
//                        ApiResponse.Loading -> _responses.value = state
//                        is ApiResponse.Error -> _responses.value = state
//                        is ApiResponse.Success<*> -> _responses.value = state
//                    }
                }

        }
    }
    // TODO: Implement the ViewModel
}