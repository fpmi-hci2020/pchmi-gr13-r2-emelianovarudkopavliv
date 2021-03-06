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
import com.hci.bookstore.MainActivity
import com.hci.bookstore.models.Book
import com.hci.bookstore.services.BookStoreService
import com.hci.bookstore.R
import com.hci.bookstore.models.CartRequest

class BookFragment : Fragment() {
    lateinit var coverView: ImageView
    lateinit var titleView: TextView
    lateinit var authorView: TextView
    lateinit var priceView: TextView
    lateinit var genreView: TextView
    lateinit var publisherView: TextView
    private lateinit var descriptionView: TextView
    private lateinit var preOrderButton: Button
    private lateinit var addToCartButton: Button
    private lateinit var addToFavoritesButton: Button
    private lateinit var subscribeButton: Button
    private lateinit var email: String

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
        genreView = root.findViewById(R.id.bookGenre)
        publisherView = root.findViewById(R.id.bookPublisher)
        descriptionView = root.findViewById(R.id.bookDescription)
        preOrderButton = root.findViewById(R.id.preOrderButton)
        addToCartButton = root.findViewById(R.id.addToCartButton)
        addToFavoritesButton = root.findViewById(R.id.addToFavoritesButton)
        subscribeButton = root.findViewById(R.id.subscribeButton)

        email = (activity as MainActivity).email

        val book = arguments!!.getParcelable<Book>("book")!!
        initBookInfo(book)
        service.getBookCover(book.id, coverView)


        preOrderButton.setOnClickListener{
            service.makePreOrder(CartRequest(email, book.id, 1))
        }

        addToCartButton.setOnClickListener{
            service.addToCart(CartRequest(email, book.id, 1))
        }

        addToFavoritesButton.setOnClickListener{
            service.addToFavorites(CartRequest(email, book.id, 0))
        }

        subscribeButton.setOnClickListener{
            service.subscribeToNews(email, book.publisher)
        }

        return root
    }

    private fun initBookInfo(book: Book){
        titleView.text = book.title
        authorView.text = book.author
        priceView.text = book.price.toString()
        descriptionView.text = book.description
        genreView.text = book.genre
        publisherView.text = book.publisher
        toggleButtons(book.isAvailable)
    }

    private fun toggleButtons(isAvailable: Boolean){
        preOrderButton.isEnabled = !isAvailable
        addToCartButton.isEnabled = isAvailable
    }
}