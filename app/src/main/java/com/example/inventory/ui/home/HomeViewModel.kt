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

package com.example.inventory.ui.home

import androidx.lifecycle.ViewModel
import com.example.inventory.data.Item

/**
 * ViewModel to retrieve all items in the Room database.
 */

/**
 * ViewModel untuk mengambil semua item dari database Room.
 * ViewModel ini bertanggung jawab untuk memuat data item dari sumber data
 * (biasanya repositori atau DAO yang mengakses Room), sehingga data dapat ditampilkan
 * pada `HomeScreen` dan diperbarui sesuai kebutuhan.
 *
 * `TIMEOUT_MILLIS` mendefinisikan batas waktu maksimum untuk operasi pemuatan data,
 * untuk mencegah ViewModel menunggu terlalu lama jika terjadi masalah saat pengambilan data.
 */
class HomeViewModel() : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * Ui State for HomeScreen
 */

/**
 * Kelas data `HomeUiState` yang merepresentasikan status UI untuk `HomeScreen`.
 * `itemList` adalah daftar item yang diambil dari database Room.
 * `HomeUiState` menyimpan data ini agar UI dapat menampilkannya atau menunjukkan pesan jika tidak ada item.
 * Class ini juga dapat diperluas untuk menambah informasi status seperti loading, error, atau filter item.
 */
data class HomeUiState(val itemList: List<Item> = listOf())
