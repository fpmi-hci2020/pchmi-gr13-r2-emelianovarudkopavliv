package com.hci.bookstore.models

import com.google.gson.annotations.SerializedName

class Order(
    @SerializedName("payment_method") var paymentMethod: String,
    @SerializedName("shipping_method") var shippingMethod: String,
    var books: Array<BookInCart>,
    @SerializedName("account") var email: String) {

    var id: Int = 0

    @SerializedName("price")
    var totalPrice: Float = 0f

    @SerializedName("date_placed")
    var orderDate: String = ""

    @SerializedName("date_delivered")
    var shipDate: String = ""

}