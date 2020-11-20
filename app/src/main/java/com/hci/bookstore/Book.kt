package com.hci.bookstore

class Book {
    var id: Int = 0
    lateinit var title: String
    lateinit var author: String
    lateinit var genre: String
    lateinit var description: String
    var price: Float = 0f
    var imageResource: Int = 0

    constructor(id:Int, title: String, author: String, genre: String, description: String, price: Float, resource: Int) {
        this.id = id
        this.title = title
        this.author = author
        this.genre = genre
        this.description = description
        this.price = price
        imageResource = resource
    }
    constructor(){

    }
}