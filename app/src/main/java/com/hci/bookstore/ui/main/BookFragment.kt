package com.hci.bookstore.ui.main

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.hci.bookstore.Book
import com.hci.bookstore.BookStoreService
import com.hci.bookstore.R

class BookFragment : Fragment() {
    lateinit var coverView: ImageView
    lateinit var titleView: TextView
    lateinit var authorView: TextView
    lateinit var priceView: TextView
    lateinit var descriptionView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var root = inflater.inflate(R.layout.book_fragment, container, false)
        root.isFocusableInTouchMode = true
        root.requestFocus()

        root.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                fragmentManager!!.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                return@setOnKeyListener true
            } else return@setOnKeyListener false
        }

        coverView = root!!.findViewById(R.id.bookCover)
        titleView = root!!.findViewById(R.id.bookTitle)
        authorView = root!!.findViewById(R.id.bookAuthor)
        priceView = root!!.findViewById(R.id.bookPrice)
        descriptionView = root!!.findViewById(R.id.bookDescription)

        val book = arguments!!.getParcelable<Book>("book")!!
        initBookInfo(book)
        val service = BookStoreService(this)
        service.getBookCover(book.id, coverView)
        return root
    }

    private fun initBookInfo(book: Book){
        titleView.text = book.title
        authorView.text = book.author
        priceView.text = book.price.toString()
        descriptionView.text = book.description
    }
}