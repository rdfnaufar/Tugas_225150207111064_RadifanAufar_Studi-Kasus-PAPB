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
import androidx.lifecycle.ViewModel
import com.example.inventory.data.Item
import com.example.inventory.data.ItemsRepository
import java.text.NumberFormat

/**
 * ViewModel to validate and insert items in the Room database.
 */

/**
 * `ItemEntryViewModel` adalah ViewModel yang berfungsi untuk menangani logika bisnis terkait dengan item,
 * seperti memvalidasi data input pengguna dan menyimpan item ke dalam database Room melalui repository.
 * ViewModel ini memiliki state untuk menyimpan status data item yang sedang dimasukkan, termasuk validitas input.
 *
 * Fungsi utama dari ViewModel ini adalah untuk memperbarui state UI berdasarkan perubahan input pengguna
 * dan menyimpan data item ke dalam database jika validasi input berhasil.
 */
class ItemEntryViewModel(private val itemsRepository: ItemsRepository) : ViewModel() {

    /**
     * Holds current item ui state
     */
    /**
     * State UI yang menyimpan detail item dan status validitas entri.
     * Data ini akan digunakan oleh UI untuk menampilkan status input item dan validasi.
     */
    var itemUiState by mutableStateOf(ItemUiState())
        private set

    /**
     * Updates the [itemUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    /**
     * Mengupdate [itemUiState] dengan detail item yang diberikan. Fungsi ini juga memvalidasi input
     * dan memperbarui status validitas entri (seperti apakah semua field sudah diisi dengan benar).
     */
    fun updateUiState(itemDetails: ItemDetails) {
        itemUiState =
            ItemUiState(itemDetails = itemDetails, isEntryValid = validateInput(itemDetails))
    }

    /**
     * Fungsi ini menyimpan item ke dalam database Room dengan memanggil repository untuk menyisipkan
     * item yang telah divalidasi. Sebelum menyimpan, fungsi ini memvalidasi input terlebih dahulu.
     *
     * Fungsi ini bersifat suspend karena operasi penyimpanan dilakukan secara asinkron dengan menggunakan coroutine.
     */
    suspend fun saveItem() {
        if (validateInput()) {
            // Mengkonversi detail item menjadi objek Item yang sesuai dengan entity Room dan menyimpannya
            itemsRepository.insertItem(itemUiState.itemDetails.toItem())
        }
    }

    /**
     * Validasi untuk memastikan bahwa semua field item (nama, harga, kuantitas) tidak kosong.
     * Fungsi ini dipanggil setiap kali data item diperbarui untuk memastikan data yang dimasukkan
     * memenuhi persyaratan sebelum disimpan.
     */
    private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            // Mengecek apakah nama, harga, dan kuantitas tidak kosong
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }
}

/**
 * Represents Ui State for an Item.
 */
/**
 * `ItemUiState` adalah model data yang menyimpan status UI untuk item, termasuk detail item dan status validitas entri.
 * UI akan memperbarui tampilan berdasarkan status entri item yang disimpan dalam `itemUiState`.
 */
data class ItemUiState(
    val itemDetails: ItemDetails = ItemDetails(),
    val isEntryValid: Boolean = false
)

/**
 * `ItemDetails` adalah data model yang menyimpan informasi dasar item yang dimasukkan oleh pengguna,
 * seperti nama, harga, dan kuantitas. Data ini berfungsi untuk mengikat input pengguna dalam UI.
 */
data class ItemDetails(
    val id: Int = 0, // ID item (defaultnya adalah 0 untuk item baru)
    val name: String = "", // Nama item
    val price: String = "", // Harga item dalam bentuk String
    val quantity: String = "", // Jumlah item dalam bentuk String
)

/**
 * Extension function to convert [ItemDetails] to [Item]. If the value of [ItemDetails.price] is
 * not a valid [Double], then the price will be set to 0.0. Similarly if the value of
 * [ItemDetails.quantity] is not a valid [Int], then the quantity will be set to 0
 */
/**
 * Fungsi ekstensi untuk mengonversi objek `ItemDetails` menjadi objek `Item`,
 * yang merupakan entitas yang digunakan untuk menyimpan data di database Room.
 * Fungsi ini juga mengonversi harga menjadi tipe Double dan kuantitas menjadi tipe Integer.
 * Jika harga atau kuantitas tidak valid, nilai default (0.0 untuk harga dan 0 untuk kuantitas) akan digunakan.
 */
fun ItemDetails.toItem(): Item = Item(
    id = id, // Menyimpan ID item
    name = name, // Menyimpan nama item
    price = price.toDoubleOrNull() ?: 0.0, // Mengonversi harga ke Double, atau 0.0 jika tidak valid
    quantity = quantity.toIntOrNull() ?: 0 // Mengonversi jumlah ke Integer, atau 0 jika tidak valid
)

/**
 * Fungsi ekstensi untuk mengonversi objek `Item` menjadi format harga yang diformat sesuai dengan
 * mata uang yang berlaku, menggunakan `NumberFormat.getCurrencyInstance()`.
 * Fungsi ini berguna untuk menampilkan harga dalam format yang sesuai dengan konvensi mata uang.
 */
fun Item.formatedPrice(): String {
    return NumberFormat.getCurrencyInstance().format(price) // Memformat harga dengan simbol mata uang
}

/**
 * Extension function to convert [Item] to [ItemUiState]
 */
/**
 * Fungsi ekstensi untuk mengonversi objek `Item` menjadi `ItemUiState`,
 * yang digunakan untuk memperbarui UI dengan detail item yang sesuai.
 * Status validitas entri juga dapat disesuaikan (defaultnya adalah false).
 */
fun Item.toItemUiState(isEntryValid: Boolean = false): ItemUiState = ItemUiState(
    itemDetails = this.toItemDetails(),
    isEntryValid = isEntryValid
)

/**
 * Extension function to convert [Item] to [ItemDetails]
 */
/**
 * Fungsi ekstensi untuk mengonversi objek `Item` menjadi objek `ItemDetails`,
 * yang digunakan untuk menampilkan informasi item di UI.
 */
fun Item.toItemDetails(): ItemDetails = ItemDetails(
    id = id, // Menyimpan ID item
    name = name, // Menyimpan nama item
    price = price.toString(), // Mengonversi harga ke String
    quantity = quantity.toString() // Mengonversi jumlah ke String
)
