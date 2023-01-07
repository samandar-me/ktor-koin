package com.sdk.koinktortest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sdk.koinktortest.ui.screen.Screen
import com.sdk.koinktortest.ui.screen.product_list.PostListScreen
import com.sdk.koinktortest.ui.screen.proudct.PostScreen
import com.sdk.koinktortest.ui.theme.KoinKtorTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinKtorTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navHostController = rememberNavController()
                    NavHost(
                        navController = navHostController,
                        startDestination = Screen.PostListScreen.route
                    ) {
                        composable(Screen.PostListScreen.route) {
                            PostListScreen(navHostController)
                        }
                        composable(
                            "${Screen.PostScreen.route}/{id}",
                            arguments = listOf(
                                navArgument(
                                    name = "id"
                                ) {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            val id = it.arguments?.getString("id") ?: "1"
                            PostScreen(navHostController, id)
                        }
                    }
                }
            }
        }
    }
}
