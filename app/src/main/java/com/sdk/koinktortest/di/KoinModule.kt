package com.sdk.koinktortest.di

import com.sdk.koinktortest.network.ApiService
import com.sdk.koinktortest.network.ApiServiceImpl
import com.sdk.koinktortest.ui.screen.product_list.ProductListViewModel
import com.sdk.koinktortest.ui.screen.proudct.ProductViewModel
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModule = module {
    single {
        HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    kotlinx.serialization.json.Json {
                        isLenient = true
                        encodeDefaults = false
                        ignoreUnknownKeys = true //
                    }
                )
            }
        }
    }
    factory<ApiService> {
        ApiServiceImpl(get())
    }
    viewModel {
        ProductListViewModel(get())
    }
    viewModel {
        ProductViewModel(get())
    }
}