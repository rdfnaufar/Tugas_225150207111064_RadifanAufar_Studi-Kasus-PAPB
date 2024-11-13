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

package com.example.inventory.ui.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.inventory.data.ItemsRepository

/**
 * ViewModel to retrieve and update an item from the [ItemsRepository]'s data source.
 */

/**
 * `ItemEditViewModel` adalah kelas ViewModel yang bertanggung jawab untuk mengambil dan memperbarui data item yang ada
 * melalui `ItemsRepository`. Kelas ini mengelola state UI item yang sedang diedit, serta melakukan validasi input
 * sebelum melakukan perubahan data pada Room database.
 *
 * ViewModel ini menggunakan `SavedStateHandle` untuk mendapatkan `itemId` yang diperlukan untuk mengambil item tertentu
 * dari data source. `itemId` ini biasanya digunakan untuk mendapatkan item dari Room database melalui repository.
 * Data item ini akan dikelola dalam bentuk `itemUiState`, yang berisi informasi item yang akan ditampilkan di UI.
 *
 * Fungsi `validateInput` digunakan untuk memvalidasi data item yang sedang diedit sebelum disimpan kembali ke Room database.
 * Fungsi ini memastikan bahwa semua field (nama, harga, kuantitas) memiliki nilai yang valid (tidak kosong).
 */
class ItemEditViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    /**
     * Holds current item ui state
     */

    /**
     * Menyimpan status terkini dari item yang sedang diedit.
     * `itemUiState` adalah state yang digunakan oleh UI untuk menampilkan data item dan mengelola perubahan.
     * Variabel ini dipantau oleh Compose untuk merender ulang UI ketika terjadi perubahan.
     */
    var itemUiState by mutableStateOf(ItemUiState())
        private set

    /**
     * Mendapatkan `itemId` yang diteruskan melalui argumen saat tampilan ini dibuka.
     * `itemId` digunakan untuk mengambil data item dari sumber data (seperti Room) yang sesuai.
     * Nilai `itemId` diambil dari `savedStateHandle`, yang memungkinkan data untuk bertahan meskipun terjadi perubahan konfigurasi (seperti rotasi layar).
     */
    private val itemId: Int = checkNotNull(savedStateHandle[ItemEditDestination.itemIdArg])

    /**
     * Fungsi untuk memvalidasi input dari pengguna untuk memastikan data yang dimasukkan tidak kosong.
     * Validasi ini memeriksa tiga field yang penting: nama, harga, dan kuantitas.
     * Jika ada salah satu yang kosong, fungsi ini akan mengembalikan `false`, dan data tidak akan disimpan.
     */
    private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }
}
