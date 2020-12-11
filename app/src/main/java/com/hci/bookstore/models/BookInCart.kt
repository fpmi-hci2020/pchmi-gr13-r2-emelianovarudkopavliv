package com.hci.bookstore.models

import com.google.gson.annotations.SerializedName

class BookInCart(var book: Book, @SerializedName("quantity") var count: Int) {

}