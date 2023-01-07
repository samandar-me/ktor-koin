package com.sdk.koinktortest.ui.screen.proudct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.koinktortest.network.ApiService
import com.sdk.koinktortest.network.Response
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel(
    private val apiService: ApiService
) : ViewModel() {
    private val _state: MutableStateFlow<ProductState> = MutableStateFlow(ProductState())
    val state: StateFlow<ProductState> get() = _state

    fun getProductById(id: Int) {
        viewModelScope.launch {
            apiService.getProductById(id).collect { response ->
                _state.update {
                    ProductState(isLoading = true)
                }
                delay(1000L)
                when (response) {
                    is Response.Error -> {
                        _state.update {
                            ProductState(isLoading = false, error = response.message)
                        }
                    }
                    is Response.Success -> {
                        _state.update {
                            ProductState(isLoading = false, product = response.data)
                        }
                    }
                }
            }
        }
    }
}