package com.example.lab_week_09

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun App(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Home { listData ->
                val encoded = java.net.URLEncoder.encode(listData, "utf-8")
                navController.navigate("resultContent/$encoded")
            }
        }
        composable("resultContent/{listData}") { backStackEntry ->
            val raw = backStackEntry.arguments?.getString("listData") ?: ""
            val decoded = try {
                java.net.URLDecoder.decode(raw, "utf-8")
            } catch (e: Exception) {
                raw
            }
            ResultContent(decoded)
        }
    }
}