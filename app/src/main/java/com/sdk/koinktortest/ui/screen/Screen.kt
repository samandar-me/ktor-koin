package com.sdk.koinktortest.ui.screen

sealed class Screen(val route: String) {
    object PostListScreen: Screen(route = "PostListScreen")
    object PostScreen: Screen(route = "PostScreen")
}