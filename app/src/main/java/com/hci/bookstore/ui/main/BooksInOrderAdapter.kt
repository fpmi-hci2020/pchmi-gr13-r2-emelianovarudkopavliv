package com.hci.bookstore.ui.main

import android.content.Context
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.hci.bookstore.R
import com.hci.bookstore.models.BookInCart

class BooksInOrderAdapter (context: Context, var books: Array<BookInCart>)  :
    RecyclerView.Adapter<BooksInOrderAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val bookInfoView: TextView = itemView.findViewById(R.id.bookInOrderView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.book_in_order, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bookInCart = books[position]
        holder.bookInfoView.text = "\"${bookInCart.book.title}\", ${bookInCart.book.author}" +
                " - ${bookInCart.count}"
    }

    override fun getItemCount(): Int {
        return books.size
    }
}