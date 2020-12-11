package com.hci.bookstore.models

import com.google.gson.annotations.SerializedName

class News {
    lateinit var title: String
    lateinit var publisher: String

    @SerializedName("content")
    lateinit var text: String

    @SerializedName("date_issued")
    lateinit var date: String
}