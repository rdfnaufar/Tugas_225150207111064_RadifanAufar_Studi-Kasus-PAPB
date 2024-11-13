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

package com.example.inventory

import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.inventory.R.string
import com.example.inventory.ui.navigation.InventoryNavHost

/**
 * Top level composable that represents screens for the application.
 */
/**
 * Fungsi utama untuk aplikasi Inventory yang menyatukan dan menampilkan seluruh navigasi aplikasi.
 * Fungsi ini menghubungkan berbagai layar aplikasi dengan menggunakan `NavController` dan menavigasi
 * antar destinasi layar sesuai dengan interaksi pengguna.
 *
 * Fungsi ini tidak langsung berinteraksi dengan Room, tetapi berfungsi sebagai penghubung antarscreen
 * yang dapat memanggil ViewModel dan operasi database melalui Repository yang tersedia pada ViewModel.
 */
@Composable
fun InventoryApp(navController: NavHostController = rememberNavController()) {
    // Menampilkan NavHost yang mengelola navigasi antar destinasi dalam aplikasi.
    InventoryNavHost(navController = navController)
}

/**
 * App bar to display title and conditionally display the back navigation.
 */
/**
 * Fungsi untuk menampilkan AppBar yang menunjukkan judul layar dan menyediakan tombol navigasi
 * untuk kembali jika diperlukan. Tombol navigasi muncul berdasarkan argumen `canNavigateBack`.
 * Fungsi ini digunakan di layar detail atau edit item, di mana pengguna dapat menavigasi kembali ke layar sebelumnya.
 *
 * Fungsi ini tidak langsung berhubungan dengan Room, tetapi terkait dengan pengaturan UI untuk
 * memastikan bahwa tampilan antarmuka aplikasi sesuai dengan navigasi, misalnya, saat kembali ke
 * layar sebelumnya setelah mengedit atau menambahkan item.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryTopAppBar(
    title: String, // Judul layar
    canNavigateBack: Boolean, // Apakah tombol kembali harus ditampilkan
    modifier: Modifier = Modifier, // Modifier untuk penataan
    scrollBehavior: TopAppBarScrollBehavior? = null, // Mengatur perilaku scroll app bar
    navigateUp: () -> Unit = {}
) {
    // Menampilkan Top AppBar dengan pengaturan navigasi dan judul
    CenterAlignedTopAppBar(title = { Text(title) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            // Menampilkan ikon kembali jika dapat menavigasi ke atas (kembali ke layar sebelumnya)
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Filled.ArrowBack,
                        contentDescription = stringResource(string.back_button)
                    )
                }
            }
        })
}
