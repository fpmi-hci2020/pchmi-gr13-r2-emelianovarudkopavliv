package com.hci.bookstore.models

class Order{
    var id: Int = 0
    lateinit var books: Array<Book>
    lateinit var orderDate: String
    lateinit var shipDate: String
    var totalPrice: Float = 0f
}