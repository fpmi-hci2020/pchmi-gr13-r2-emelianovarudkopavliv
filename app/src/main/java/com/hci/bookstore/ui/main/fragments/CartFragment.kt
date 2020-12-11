package com.hci.bookstore.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.hci.bookstore.R
import com.hci.bookstore.services.BookStoreService
import com.hci.bookstore.ui.main.CartAdapter

class CartFragment : Fragment() {

    lateinit var cartView: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.cart_fragment, container, false)

        cartView = root.findViewById<View>(R.id.cartView) as ListView

        val service = BookStoreService(this)
        service.getBooksInCart()

        return root
    }
}