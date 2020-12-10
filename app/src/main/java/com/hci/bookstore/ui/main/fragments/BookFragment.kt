package com.hci.bookstore.ui.main.fragments

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.hci.bookstore.models.Book
import com.hci.bookstore.services.BookStoreService
import com.hci.bookstore.R

class BookFragment : Fragment() {
    lateinit var coverView: ImageView
    lateinit var titleView: TextView
    lateinit var authorView: TextView
    lateinit var priceView: TextView
    private lateinit var descriptionView: TextView
    private lateinit var preOrderButton: Button
    private lateinit var addToCartButton: Button
    private lateinit var addToFavoritesButton: Button
    private lateinit var subscribeButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.book_fragment, container, false)
        root.isFocusableInTouchMode = true
        root.requestFocus()

        root.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                fragmentManager!!.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                return@setOnKeyListener true
            } else return@setOnKeyListener false
        }

        val service = BookStoreService(this)

        coverView = root.findViewById(R.id.bookCover)
        titleView = root.findViewById(R.id.bookTitle)
        authorView = root.findViewById(R.id.bookAuthor)
        priceView = root.findViewById(R.id.bookPrice)
        descriptionView = root.findViewById(R.id.bookDescription)
        preOrderButton = root.findViewById(R.id.preOrderButton)
        addToCartButton = root.findViewById(R.id.addToCartButton)
        addToFavoritesButton = root.findViewById(R.id.addToFavoritesButton)
        subscribeButton = root.findViewById(R.id.subscribeButton)

        preOrderButton.setOnClickListener{
            //TODO
            Toast.makeText(context, "Added to pre-order list", Toast.LENGTH_LONG).show()
        }

        addToCartButton.setOnClickListener{
            //TODO
            Toast.makeText(context, "Added to Cart", Toast.LENGTH_LONG).show()
        }

        addToFavoritesButton.setOnClickListener{
            //TODO
            Toast.makeText(context, "Added to Favorites", Toast.LENGTH_LONG).show()
        }

        subscribeButton.setOnClickListener{
            //TODO
            Toast.makeText(context, "Subscribed to news from publisher", Toast.LENGTH_LONG).show()
        }

        val book = arguments!!.getParcelable<Book>("book")!!
        initBookInfo(book)
        service.getBookCover(book.id, coverView)

        return root
    }

    private fun initBookInfo(book: Book){
        titleView.text = book.title
        authorView.text = book.author
        priceView.text = book.price.toString()
        descriptionView.text = book.description
        toggleButtons(book.isAvailable)
    }

    private fun toggleButtons(isAvailable: Boolean){
        preOrderButton.isEnabled = !isAvailable
        addToCartButton.isEnabled = isAvailable
    }
}