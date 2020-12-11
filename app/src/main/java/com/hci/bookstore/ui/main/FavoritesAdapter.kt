package com.hci.bookstore.ui.main

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.app.Activity
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import com.hci.bookstore.R
import com.hci.bookstore.models.Book
import com.hci.bookstore.services.BookStoreService

class FavoritesAdapter (context: Context, books: Array<Book>, private val service: BookStoreService): BaseAdapter() {

    private var favoritesContext = context
    private var booksInFavorites = books.toMutableList()

    private class FavoritesViewHolder {

        lateinit var coverView: ImageView
        lateinit var titleView: TextView
        lateinit var authorView: TextView
        lateinit var priceView: TextView
        lateinit var removeButton: Button
    }

    fun getBookOnPosition (position: Int) : Book{
        return booksInFavorites[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val holder: FavoritesViewHolder
        var view: View? = null

        if (convertView == null) {
            val bookInflater =
                favoritesContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = bookInflater.inflate(R.layout.book_in_favorites, null)

            holder = FavoritesViewHolder()
            holder.coverView = view.findViewById(R.id.favoritesBookCover) as ImageView
            holder.titleView = view.findViewById(R.id.favoritesBookTitle) as TextView
            holder.authorView = view.findViewById(R.id.favoritesBookAuthor) as TextView
            holder.priceView = view.findViewById(R.id.favoritesBookPrice) as TextView
            holder.removeButton  = view.findViewById(R.id.favoritesRemoveButton) as Button

            holder.removeButton.setOnClickListener{
                //service
                booksInFavorites.removeAt(position)
                notifyDataSetChanged()
            }

            view.tag = holder

        } else {
            holder = convertView.tag as FavoritesViewHolder
        }

        val book = booksInFavorites[position]

        service.getBookCover(book.id, holder.coverView)
        holder.titleView.text = book.title
        holder.authorView.text = book.author
        holder.priceView.text = book.price.toString()
        if(convertView != null) {
            return convertView
        }
        return view!!
    }

    override fun getItem(position: Int): Any {
        return booksInFavorites[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return booksInFavorites.size
    }
}