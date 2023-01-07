package com.sdk.koinktortest.ui.screen.proudct

import com.sdk.koinktortest.model.StoreDTO

data class ProductState(
    val isLoading: Boolean = false,
    val error: String = "",
    val product: StoreDTO? = null
)