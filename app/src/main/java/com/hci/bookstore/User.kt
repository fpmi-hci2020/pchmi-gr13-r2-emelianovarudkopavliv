package com.hci.bookstore

class User {
    lateinit var email: String
    lateinit var password: String

    constructor(email:String, password: String) {
        this.email = email
        this.password = password
    }
    constructor(){

    }
}