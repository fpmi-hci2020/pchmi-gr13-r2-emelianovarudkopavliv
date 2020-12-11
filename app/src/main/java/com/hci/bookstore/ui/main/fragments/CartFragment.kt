package com.hci.bookstore.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.hci.bookstore.MainActivity
import com.hci.bookstore.R
import com.hci.bookstore.services.BookStoreService

class CartFragment : Fragment() {

    lateinit var cartView: ListView
    lateinit var service: BookStoreService
    lateinit var email: String
    private var isViewShown = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.cart_fragment, container, false)

        cartView = root.findViewById<View>(R.id.cartView) as ListView
        service = BookStoreService(this)
        email = (activity as MainActivity).email

        if(!isViewShown){
            service.getBooksInCart(email)
        }

        return root
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(view != null){
            isViewShown = true
            if(isVisibleToUser){
                service.getBooksInCart(email)
            }
        }
        else{
            isViewShown = false
        }
    }
}