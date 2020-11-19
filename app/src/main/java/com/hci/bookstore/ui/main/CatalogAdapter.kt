package com.hci.bookstore.ui.main

import android.content.Context
import android.view.View
import android.widget.TextView
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.view.LayoutInflater
import android.widget.ImageView
import com.hci.bookstore.Book
import com.hci.bookstore.R

class CatalogAdapter(context: Context, books: List<Book>): BaseAdapter() {

    private val books = books
    private val mContext: Context = context

    override fun getCount(): Int {
        return books.size
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val view: View?
        val book = books[position]

        val layoutInflater = LayoutInflater.from(mContext)
        view = convertView ?: layoutInflater.inflate(R.layout.book, null)

        val imageView = view?.findViewById<ImageView>(R.id.catalogBookCover)
        val titleView = view?.findViewById<TextView>(R.id.catalogBookTitle)
        val authorView = view?.findViewById<TextView>(R.id.catalogBookAuthor)
        val priceView = view?.findViewById<TextView>(R.id.catalogBookPrice)

        imageView?.setImageResource(R.drawable.forest)
        titleView?.text = book.title
        authorView?.text = book.author
        priceView?.text = book.price.toString()

        return view
    }
}