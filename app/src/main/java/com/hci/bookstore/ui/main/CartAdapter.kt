package com.hci.bookstore.ui.main

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.app.Activity
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageView
import com.hci.bookstore.R
import com.hci.bookstore.models.Book
import com.hci.bookstore.services.BookStoreService

class CartAdapter (context: Context, books: Array<Book>, private val service: BookStoreService): BaseAdapter() {

    private var cartContext = context
    private var booksInCart = books

    private class CartViewHolder {

        lateinit var coverView: ImageView
        lateinit var titleView: TextView
        lateinit var authorView: TextView
        lateinit var priceView: TextView
        lateinit var countView: EditText
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val holder: CartViewHolder
        var view: View? = null

        if (convertView == null) {
            val recordInflater =
                cartContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = recordInflater.inflate(R.layout.book_in_cart, null)

            holder = CartViewHolder()
            holder.coverView = view.findViewById(R.id.cartBookCover) as ImageView
            holder.titleView = view.findViewById(R.id.cartBookTitle) as TextView
            holder.authorView = view.findViewById(R.id.cartBookAuthor) as TextView
            holder.countView = view.findViewById(R.id.countView) as EditText
            holder.priceView = view.findViewById(R.id.cartBookPrice) as TextView
            view.tag = holder

        } else {
            holder = convertView.tag as CartViewHolder
        }

        val book = booksInCart[position]

        service.getBookCover(book.id, holder.coverView)
        holder.titleView.text = book.title
        holder.authorView.text = book.author
        holder.countView.setText("1")
        holder.priceView.text = book.price.toString()
        if(convertView != null) {
            return convertView
        }
        return view!!
    }

    override fun getItem(position: Int): Any {
        return booksInCart[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return booksInCart.size
    }
}