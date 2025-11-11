package com.example.lab_week_09

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@Composable
fun App(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Home { listData ->
                // Convert list ke JSON pakai Moshi
                val moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
                val adapter = moshi.adapter(List::class.java)
                val json = adapter.toJson(listData)

                // Encode biar bisa dikirim lewat navController
                val encoded = java.net.URLEncoder.encode(json, "utf-8")
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