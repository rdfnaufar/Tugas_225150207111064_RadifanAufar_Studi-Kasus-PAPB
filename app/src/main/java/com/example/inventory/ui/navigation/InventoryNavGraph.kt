/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.inventory.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.inventory.ui.home.HomeDestination
import com.example.inventory.ui.home.HomeScreen
import com.example.inventory.ui.item.ItemDetailsDestination
import com.example.inventory.ui.item.ItemDetailsScreen
import com.example.inventory.ui.item.ItemEditDestination
import com.example.inventory.ui.item.ItemEditScreen
import com.example.inventory.ui.item.ItemEntryDestination
import com.example.inventory.ui.item.ItemEntryScreen

/**
 * Provides Navigation graph for the application.
 */
/**
 * Fungsi ini menyediakan graf navigasi untuk aplikasi, yang mengatur bagaimana pengguna dapat bernavigasi
 * antar berbagai layar dalam aplikasi menggunakan [NavHostController]. Ini merupakan struktur inti
 * dari navigasi di aplikasi yang menggunakan Jetpack Compose dan Navigation Compose.
 */
@Composable
fun InventoryNavHost(
    navController: NavHostController,  // Controller yang mengelola navigasi antar layar
    modifier: Modifier = Modifier, // Modifier untuk mengatur tampilan NavHost
) {
    // NavHost digunakan untuk mendefinisikan berbagai layar dan rute navigasi dalam aplikasi
    NavHost(
        navController = navController, // Menetapkan NavController untuk menangani navigasi
        startDestination = HomeDestination.route, // Rute awal ketika aplikasi dimulai (HomeScreen)
        modifier = modifier
    ) {
        // Rute pertama, menampilkan layar HomeScreen
        composable(route = HomeDestination.route) {
            // HomeScreen memungkinkan navigasi ke layar entri item dan detail item untuk pembaruan
            HomeScreen(navigateToItemEntry = { navController.navigate(ItemEntryDestination.route) }, // Navigasi ke layar entri item
                navigateToItemUpdate = {
                    navController.navigate("${ItemDetailsDestination.route}/${it}") // Navigasi ke layar detail item berdasarkan ID
                })
        }
        // Rute untuk layar entri item (ItemEntryScreen)
        composable(route = ItemEntryDestination.route) {
            // ItemEntryScreen memungkinkan pengguna untuk menambah item baru
            ItemEntryScreen(navigateBack = { navController.popBackStack() }, // Navigasi kembali ke layar sebelumnya
                onNavigateUp = { navController.navigateUp() }) // Navigasi ke layar sebelumnya
        }
        // Rute untuk layar detail item, dengan argument ID item
        composable(
            route = ItemDetailsDestination.routeWithArgs, // Rute dengan ID item sebagai argumen
            arguments = listOf(navArgument(ItemDetailsDestination.itemIdArg) {
                type = NavType.IntType // Menyatakan bahwa argumen ID adalah tipe Integer
            })
        ) {
            // ItemDetailsScreen menampilkan detail item berdasarkan ID dan memungkinkan navigasi ke layar edit item
            ItemDetailsScreen(
                navigateToEditItem =
                {
                    // Navigasi ke layar edit item berdasarkan ID item
                    navController.navigate("${ItemEditDestination.route}/$it")
                },
                navigateBack = { navController.navigateUp() }) // Navigasi kembali ke layar sebelumnya
        }
        // Rute untuk layar edit item, dengan argument ID item
        composable(
            route = ItemEditDestination.routeWithArgs,  // Rute dengan ID item sebagai argumen
            arguments = listOf(navArgument(ItemEditDestination.itemIdArg) {
                type = NavType.IntType // Menyatakan bahwa argumen ID adalah tipe Integer
            })
        ) {
            // ItemEditScreen memungkinkan pengguna untuk mengedit item yang sudah ada
            ItemEditScreen(navigateBack = { navController.popBackStack() },  // Navigasi kembali ke layar sebelumnya
                onNavigateUp = { navController.navigateUp() }) // Navigasi ke layar sebelumnya
        }
    }
}
