package com.hci.bookstore.models

import com.google.gson.annotations.SerializedName

class CartRequest(var account: String, @SerializedName("book") var bookId: Int,
                  @SerializedName("quantity")var count: Int) {

}