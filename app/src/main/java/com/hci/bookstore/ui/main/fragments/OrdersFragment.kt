package com.hci.bookstore.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.hci.bookstore.R
import com.hci.bookstore.models.Order
import com.hci.bookstore.services.BookStoreService
import com.hci.bookstore.ui.main.OrdersAdapter

class OrdersFragment : Fragment() {

    lateinit var ordersView: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.orders_fragment, container, false)
        ordersView = root.findViewById<View>(R.id.ordersView) as ListView

        val service = BookStoreService(this)
        //service.getOrders()

        var order = Order()
        order.orderDate = "12.03.2020"
        order.shipDate = "15.03.2020"
        order.totalPrice = 20.56f

        val orders = arrayOf(order, order)
        ordersView.adapter = OrdersAdapter(context!!, orders)

        return root
    }
}