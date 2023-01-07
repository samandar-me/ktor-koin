package com.sdk.koinktortest.ui.screen.product_list

import com.sdk.koinktortest.model.StoreDTO

data class ProductListState(
    val isLoading: Boolean = false,
    val error: String = "",
    val postList: List<StoreDTO> = emptyList()
)