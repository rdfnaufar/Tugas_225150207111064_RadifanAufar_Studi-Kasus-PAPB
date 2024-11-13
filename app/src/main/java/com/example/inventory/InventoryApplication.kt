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

import android.app.Application
import com.example.inventory.data.AppContainer
import com.example.inventory.data.AppDataContainer

/**
 * Aplikasi utama untuk aplikasi Inventory yang menginisialisasi container untuk penyimpanan data.
 * Class ini adalah subclass dari `Application`, yang bertugas untuk menyediakan dan mengelola
 * dependensi yang dibutuhkan di seluruh aplikasi, termasuk akses ke komponen-komponen terkait Room.
 *
 * Fungsi `onCreate` akan dijalankan saat aplikasi dimulai dan digunakan untuk menginisialisasi
 * instance dari `AppContainer` yang menyediakan akses ke repository dan database. Komponen
 * ini memungkinkan aplikasi untuk menyimpan, mengambil, dan mengelola data secara konsisten
 * selama sesi aplikasi.
 */
class InventoryApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */

    /**
     * Instance dari `AppContainer` yang digunakan oleh seluruh kelas dalam aplikasi untuk
     * mendapatkan dependensi yang dibutuhkan, termasuk akses ke Room database dan repository.
     * `AppContainer` ini menyediakan berbagai layanan dan komponen seperti `ItemsRepository`
     * yang berinteraksi dengan database Room untuk melakukan operasi seperti menyimpan,
     * memperbarui, dan mengambil data item.
     */
    lateinit var container: AppContainer
    /**
     * Fungsi `onCreate` dipanggil saat aplikasi dimulai dan digunakan untuk menginisialisasi
     * `container`. Ini adalah tempat di mana aplikasi mengonfigurasi dependensi yang
     * digunakan oleh seluruh aplikasi. Dalam hal ini, `AppDataContainer` digunakan untuk
     * menyediakan implementasi `AppContainer`, yang kemungkinan besar mengelola
     * penyimpanan data dan akses ke database (seperti Room).
     *
     * Fungsi ini tidak berinteraksi langsung dengan Room, tetapi bertanggung jawab untuk
     * menginisialisasi container yang memuat repository dan data akses yang digunakan oleh
     * ViewModel dan komponen lainnya.
     */

    override fun onCreate() {
        super.onCreate()
        // Inisialisasi container dengan AppDataContainer yang akan mengelola akses ke data.
        container = AppDataContainer(this)
    }
}
