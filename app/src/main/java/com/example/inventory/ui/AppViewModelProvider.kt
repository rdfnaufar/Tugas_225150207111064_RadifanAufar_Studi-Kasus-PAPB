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

package com.example.inventory.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.inventory.InventoryApplication
import com.example.inventory.ui.home.HomeViewModel
import com.example.inventory.ui.item.ItemDetailsViewModel
import com.example.inventory.ui.item.ItemEditViewModel
import com.example.inventory.ui.item.ItemEntryViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
/**
 * Objekt ini menyediakan `Factory` untuk membuat instance ViewModel untuk seluruh aplikasi Inventory.
 * Factory ini berfungsi untuk menyediakan cara untuk membuat ViewModel yang terkait dengan data aplikasi,
 * yang diinisialisasi dengan dependency yang diperlukan, seperti repository dan data yang disimpan.
 */
object AppViewModelProvider {
    /**
     * Factory yang digunakan untuk membuat berbagai ViewModel untuk aplikasi ini.
     * Di dalam factory ini, kita mendefinisikan initializers untuk setiap ViewModel yang dibutuhkan.
     */
    val Factory = viewModelFactory {
        // Initializer for ItemEditViewModel
        // Initializer untuk ItemEditViewModel
        initializer {
            /**
             * Fungsi ini membuat instance `ItemEditViewModel`. ViewModel ini digunakan untuk mengedit item
             * dan membutuhkan state yang disimpan (SavedStateHandle) untuk menangani navigasi dan data terkait.
             * ViewModel ini bertanggung jawab untuk memvalidasi dan memperbarui data item di dalam aplikasi.
             */
            ItemEditViewModel(
                this.createSavedStateHandle() // Membuat SavedStateHandle untuk menyimpan state UI.
            )
        }
        // Initializer for ItemEntryViewModel
        // Initializer untuk ItemEntryViewModel
        initializer {
            /**
             * Fungsi ini membuat instance `ItemEntryViewModel`. ViewModel ini bertanggung jawab untuk
             * menambahkan item baru ke dalam aplikasi dan memvalidasi input dari pengguna.
             * ViewModel ini memerlukan `ItemsRepository` untuk melakukan operasi data, seperti menyimpan
             * item baru ke dalam database (Room).
             * `inventoryApplication().container.itemsRepository` memberikan akses ke repository untuk
             * memanipulasi data.
             */
            ItemEntryViewModel(inventoryApplication().container.itemsRepository)
        }

        // Initializer for ItemDetailsViewModel
        // Initializer untuk ItemDetailsViewModel
        initializer {
            /**
             * Fungsi ini membuat instance `ItemDetailsViewModel`. ViewModel ini digunakan untuk menampilkan
             * detail item dan memperbarui item sesuai dengan perubahan yang dilakukan oleh pengguna.
             * ViewModel ini juga menggunakan `SavedStateHandle` untuk menangani data yang perlu disimpan
             * dan dipertahankan saat navigasi terjadi.
             */
            ItemDetailsViewModel(
                this.createSavedStateHandle() // Menyimpan state terkait item di dalam handle
            )
        }

        // Initializer for HomeViewModel
        // Initializer untuk HomeViewModel
        initializer {
            /**
             * Fungsi ini membuat instance `HomeViewModel`. ViewModel ini digunakan untuk menampilkan
             * daftar item di layar utama aplikasi. Tidak membutuhkan dependensi data langsung karena hanya
             * berfokus pada UI dan data yang ada.
             */
            HomeViewModel()
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */
/**
 * Fungsi ekstensi ini digunakan untuk mendapatkan instance dari objek `Application` yang merupakan
 * turunan dari `InventoryApplication`. Fungsi ini memberikan akses ke `InventoryApplication` untuk
 * memudahkan penyuntikan dependensi (seperti repository) ke dalam ViewModel.
 */
fun CreationExtras.inventoryApplication(): InventoryApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as InventoryApplication)
