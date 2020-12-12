package com.hci.bookstore.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.hci.bookstore.MainActivity
import com.hci.bookstore.R
import com.hci.bookstore.models.BookInCart
import com.hci.bookstore.models.Order
import com.hci.bookstore.services.BookStoreService

class CartFragment : Fragment() {

    lateinit var cartView: ListView
    lateinit var makeOrderButton: Button
    lateinit var paymentGroup: RadioGroup
    lateinit var deliveryGroup: RadioGroup

    lateinit var service: BookStoreService
    lateinit var books: Array<BookInCart>
    lateinit var email: String
    private var isViewShown = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.cart_fragment, container, false)

        cartView = root.findViewById(R.id.cartView)
        makeOrderButton =  root.findViewById(R.id.makeOrderButton)
        paymentGroup = root.findViewById(R.id.paymentGroup)
        deliveryGroup = root.findViewById(R.id.deliveryGroup)

        service = BookStoreService(this)
        email = (activity as MainActivity).email

        if(!isViewShown){
            service.getBooksInCart(email)
        }

        makeOrderButton.setOnClickListener{
            var selectedId = paymentGroup.checkedRadioButtonId
            val paymentMethod =  root.findViewById<RadioButton>(selectedId).tag.toString()
            selectedId = deliveryGroup.checkedRadioButtonId
            val deliveryMethod =  root.findViewById<RadioButton>(selectedId).tag.toString()
            service.makeOrder(Order(paymentMethod, deliveryMethod, books, email))
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