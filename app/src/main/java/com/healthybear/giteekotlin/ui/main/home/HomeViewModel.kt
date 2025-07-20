package com.healthybear.giteekotlin.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.healthybear.giteekotlin.network.response.SearchRepositoriesResult
import com.healthybear.library.base.viewModel.BaseViewModel
import com.healthybear.library.network.response.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {
    private val dataRepository by lazy { HomeRepository() }

    private val _responses = MutableStateFlow<ApiResponse<List<SearchRepositoriesResult>>>(ApiResponse.Empty)
    val mResponses: StateFlow<ApiResponse<List<SearchRepositoriesResult>>> = _responses


    fun searchResponse(work: String) {
        viewModelScope.launch(Dispatchers.Main) {
            dataRepository.searchRepositories_simple(work)
                .flowOn(Dispatchers.IO)
                .collect { state ->
                    if (state is ApiResponse.Success) {
                        LogUtils.d("test   "+state.data)
                    }
                    _responses.value = state
                }

        }
    }
}