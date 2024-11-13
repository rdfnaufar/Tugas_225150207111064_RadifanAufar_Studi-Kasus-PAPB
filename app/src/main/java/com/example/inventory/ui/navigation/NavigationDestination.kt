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

/**
 * Interface to describe the navigation destinations for the app
 */
/**
 * Interface ini mendefinisikan destinasi navigasi dalam aplikasi.
 * Interface ini digunakan untuk menetapkan jalur rute yang dapat diakses oleh pengguna
 * dan menentukan judul yang akan ditampilkan pada tiap layar.
 * Interface ini tidak berhubungan langsung dengan Room atau database, namun berfungsi
 * dalam mengatur navigasi antar layar dalam aplikasi.
 */
interface NavigationDestination {
    /**
     * Unique name to define the path for a composable
     */
    /**
     * Properti ini memberikan jalur unik untuk setiap destinasi di aplikasi.
     * Setiap layar atau tampilan dalam aplikasi memiliki rute yang berbeda,
     * yang digunakan oleh sistem navigasi untuk berpindah antar layar.
     * Jalur ini diperlukan untuk membuat sistem navigasi menggunakan Jetpack Navigation.
     */
    val route: String

    /**
     * String resource id to that contains title to be displayed for the screen.
     */
    /**
     * Properti ini memberikan ID sumber daya string untuk judul layar yang ditampilkan
     * saat pengguna berada di layar tertentu. Ini memberikan label yang sesuai untuk setiap layar.
     * ID string ini dapat digunakan untuk mengambil judul layar dari berkas sumber daya (resources),
     * yang nantinya akan ditampilkan di antarmuka pengguna.
     */
    val titleRes: Int
}
