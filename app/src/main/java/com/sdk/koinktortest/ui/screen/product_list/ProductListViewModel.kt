package com.sdk.koinktortest.ui.screen.product_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.koinktortest.network.ApiService
import com.sdk.koinktortest.network.Response
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductListViewModel(
    private val apiService: ApiService
) : ViewModel() {
    private val _state: MutableStateFlow<ProductListState> = MutableStateFlow(ProductListState())
    val state: StateFlow<ProductListState> get() = _state

    init {
        getAllProductList()
    }

    private fun getAllProductList() {
        viewModelScope.launch {
            apiService.getProducts().collect { response ->
                _state.update {
                    ProductListState(isLoading = true)
                }
                delay(500L)
                when (response) {
                    is Response.Error -> {
                        _state.update {
                            ProductListState(isLoading = false, error = response.message)
                        }
                    }
                    is Response.Success -> {
                        _state.update {
                            ProductListState(isLoading = false, postList = response.data)
                        }
                    }
                }
            }
        }
    }
}