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

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.inventory.ui.theme.InventoryTheme

/**
 * MainActivity adalah aktivitas utama dalam aplikasi Inventory. Ini adalah titik masuk aplikasi
 * dan bertanggung jawab untuk mengatur antarmuka pengguna awal yang ditampilkan kepada pengguna.
 *
 * Pada fungsi `onCreate`, antarmuka pengguna disiapkan menggunakan Jetpack Compose. Di sini,
 * komponen `Surface` digunakan untuk menggambar layar pertama aplikasi dengan tema yang ditentukan
 * di `InventoryTheme`. Fungsi ini juga menyertakan pengaturan untuk memastikan aplikasi mendukung
 * Edge-to-Edge display.
 */
class MainActivity : ComponentActivity() {
    /**
     * Fungsi `onCreate` adalah metode yang dipanggil saat Activity pertama kali dibuat.
     * Pada metode ini, beberapa langkah pengaturan dilakukan:
     * - `enableEdgeToEdge()`: Fungsi ini digunakan untuk mengaktifkan desain edge-to-edge, yang
     *   memungkinkan tampilan aplikasi memanfaatkan seluruh layar perangkat, termasuk area
     *   status bar dan navigasi, memberikan pengalaman layar penuh.
     * - `setContent`: Digunakan untuk menentukan komposisi UI aplikasi yang akan ditampilkan.
     *   Dalam hal ini, `InventoryTheme` diterapkan untuk memberikan tema konsisten pada UI aplikasi.
     *   Setelah itu, `InventoryApp()` dipanggil untuk menampilkan navigasi dan tampilan aplikasi yang lebih kompleks.
     *
     * Fungsi `onCreate` tidak berinteraksi langsung dengan Room atau Data di sini, tetapi bertanggung jawab
     * untuk menyiapkan UI dan memastikan aplikasi berjalan di lingkungan yang benar (tema dan ukuran layar yang tepat).
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()  // Mengaktifkan tampilan edge-to-edge agar UI memanfaatkan seluruh layar perangkat.
        super.onCreate(savedInstanceState)
        setContent {
            // Menggunakan tema aplikasi untuk konsistensi desain UI
            // Surface digunakan untuk menggambar UI dengan warna latar belakang yang sesuai tema
            InventoryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    // Memanggil komposabel utama untuk aplikasi, yang bertanggung jawab untuk navigasi dan tampilan
                    InventoryApp()
                }
            }
        }
    }
}
