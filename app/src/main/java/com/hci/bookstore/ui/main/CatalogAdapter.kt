package com.hci.bookstore.ui.main

import android.content.Context
import android.view.View
import android.widget.TextView
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.view.LayoutInflater
import android.widget.ImageView
import com.hci.bookstore.models.Book
import com.hci.bookstore.services.BookStoreService
import com.hci.bookstore.R

class CatalogAdapter(context: Context, private var books: Array<Book>,
                     private val service: BookStoreService
): BaseAdapter() {

    private val mContext: Context = context

    fun updateItems(newBooks: Array<Book>){
        books = newBooks
        notifyDataSetChanged()
    }

    fun getBookOnPosition (position: Int) : Book{
        return books[position]
    }

    override fun getCount(): Int {
        return books.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(position: Int): Any? {
        return books[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val view: View?
        val book = books[position]

        val layoutInflater = LayoutInflater.from(mContext)
        view = convertView ?: layoutInflater.inflate(R.layout.book, null)

        val coverView = view?.findViewById<ImageView>(R.id.catalogBookCover)
        val titleView = view?.findViewById<TextView>(R.id.catalogBookTitle)
        val authorView = view?.findViewById<TextView>(R.id.catalogBookAuthor)
        val priceView = view?.findViewById<TextView>(R.id.catalogBookPrice)

        service.getBookCover(book.id, coverView!!)
        titleView?.text = book.title
        authorView?.text = book.author
        priceView?.text = book.price.toString()

        return view
    }
}