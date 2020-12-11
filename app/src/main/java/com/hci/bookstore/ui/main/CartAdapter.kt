package com.hci.bookstore.ui.main

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.app.Activity
import android.view.KeyEvent
import android.view.LayoutInflater
import android.widget.*
import com.hci.bookstore.R
import com.hci.bookstore.models.BookInCart
import com.hci.bookstore.models.CartRequest
import com.hci.bookstore.services.BookStoreService

class CartAdapter (context: Context, books: Array<BookInCart>, private val email: String,
                   private val service: BookStoreService): BaseAdapter() {

    private var cartContext = context
    private var booksInCart = books.toMutableList()

    private class CartViewHolder {

        lateinit var coverView: ImageView
        lateinit var titleView: TextView
        lateinit var authorView: TextView
        lateinit var priceView: TextView
        lateinit var countView: EditText
        lateinit var removeButton: Button
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val holder: CartViewHolder
        var view: View? = null

        if (convertView == null) {
            val bookInflater =
                cartContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = bookInflater.inflate(R.layout.book_in_cart, null)

            holder = CartViewHolder()
            holder.coverView = view.findViewById(R.id.cartBookCover) as ImageView
            holder.titleView = view.findViewById(R.id.cartBookTitle) as TextView
            holder.authorView = view.findViewById(R.id.cartBookAuthor) as TextView
            holder.countView = view.findViewById(R.id.countView) as EditText
            holder.priceView = view.findViewById(R.id.cartBookPrice) as TextView
            holder.removeButton  = view.findViewById(R.id.cartRemoveButton) as Button

            holder.countView.setOnKeyListener{ _: View, i: Int, keyEvent: KeyEvent ->
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    service.updateBookCountInCart(CartRequest(email,booksInCart[position].book.id,
                        holder.countView.text.toString().toInt()))
                }
                false
            }

            holder.removeButton.setOnClickListener{
                service.removeFromCart(email, booksInCart[position].book.id)
                booksInCart.removeAt(position)
                notifyDataSetChanged()
            }

            view.tag = holder

        } else {
            holder = convertView.tag as CartViewHolder
        }

        val bookInCart = booksInCart[position]

        service.getBookCover(bookInCart.book.id, holder.coverView)
        holder.titleView.text = bookInCart.book.title
        holder.authorView.text = bookInCart.book.author
        holder.countView.setText(bookInCart.count.toString())
        holder.priceView.text = bookInCart.book.price.toString()

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