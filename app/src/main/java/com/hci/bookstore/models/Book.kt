package com.hci.bookstore.models

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Book :  Parcelable {
    var id: Int = 0
    lateinit var title: String
    lateinit var author: String
    lateinit var genre: String
    lateinit var description: String
    lateinit var publisher: String
    var price: Float = 0f

    @SerializedName("availability")
    var isAvailable: Boolean = true

    @SuppressLint("NewApi")
    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        title = parcel.readString()!!
        author = parcel.readString()!!
        genre = parcel.readString()!!
        description = parcel.readString()!!
        publisher = parcel.readString()!!
        price = parcel.readFloat()
        isAvailable = parcel.readBoolean()
    }

    constructor(id:Int, title: String, author: String, genre: String, description: String, price: Float) {
        this.id = id
        this.title = title
        this.author = author
        this.genre = genre
        this.description = description
        this.price = price
    }

    constructor(){}

    @SuppressLint("NewApi")
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(author)
        parcel.writeString(genre)
        parcel.writeString(description)
        parcel.writeString(publisher)
        parcel.writeFloat(price)
        parcel.writeBoolean(isAvailable)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }
}