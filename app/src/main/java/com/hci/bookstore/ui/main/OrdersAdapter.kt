package com.hci.bookstore.ui.main

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.app.Activity
import android.view.LayoutInflater
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.hci.bookstore.R
import com.hci.bookstore.models.Order
import androidx.recyclerview.widget.LinearLayoutManager
import com.hci.bookstore.models.Book
import com.hci.bookstore.services.BookStoreService

class OrdersAdapter (context: Context, orders: Array<Order>,
                     private val service: BookStoreService): BaseAdapter() {

    private var ordersContext = context
    private var orders = orders.toMutableList()

    private class OrdersViewHolder {

        lateinit var orderNumView: TextView
        lateinit var ordersView: RecyclerView
        lateinit var orderDateView: TextView
        lateinit var shipDateView: TextView
        lateinit var priceView: TextView
        lateinit var cancelButton: Button
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val holder: OrdersViewHolder
        var view: View? = null

        if (convertView == null) {
            val bookInflater =
                ordersContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = bookInflater.inflate(R.layout.order, null)

            holder = OrdersViewHolder()

            holder.orderNumView = view.findViewById(R.id.orderNumView) as TextView

            holder.ordersView = view.findViewById(R.id.booksInOrderView) as RecyclerView
            holder.ordersView.layoutManager = LinearLayoutManager(view.context)

            holder.orderDateView = view.findViewById(R.id.orderDateView) as TextView
            holder.shipDateView = view.findViewById(R.id.shipDateView) as TextView
            holder.priceView = view.findViewById(R.id.orderPrice) as TextView
            holder.cancelButton  = view.findViewById(R.id.orderCancelButton) as Button

            holder.cancelButton.setOnClickListener{
                service.cancelOrder(orders[position].id)
                orders.removeAt(position)
                notifyDataSetChanged()
            }

            view.tag = holder

        } else {
            holder = convertView.tag as OrdersViewHolder
        }

        val order = orders[position]

        holder.orderNumView.text = "Order № ${position + 1}"
        holder.orderDateView.text = "Order date: ${order.orderDate}"
        holder.shipDateView.text = "Ship date: ${order.shipDate}"
        holder.priceView.text = order.totalPrice.toString()

        val adapter = BooksInOrderAdapter(ordersContext, orders[position].books)
        holder.ordersView.adapter = adapter

        if(convertView != null) {
            return convertView
        }
        return view!!
    }

    override fun getItem(position: Int): Any {
        return orders[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return orders.size
    }
}