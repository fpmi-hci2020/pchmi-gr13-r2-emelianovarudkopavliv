package com.hci.bookstore.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import com.hci.bookstore.R
import android.widget.RadioButton
import com.hci.bookstore.Book
import com.hci.bookstore.BookStoreService

class HomeFragment : Fragment() {
    lateinit var books: Array<Book>
    lateinit var grid: GridView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.home_fragment, container, false)
        val filters : Array<RadioButton>  = arrayOf(
            root!!.findViewById(R.id.titleButton),
            root!!.findViewById(R.id.authorButton),
            root!!.findViewById(R.id.genreButton),
            root!!.findViewById(R.id.publisherButton)
            )

        val service = BookStoreService(this)
        service.getBooks()

        grid = root.findViewById(R.id.grid) as GridView

        grid.setOnItemClickListener { p, v, position, _ ->
            val nextFrag = BookFragment()

            val arguments = Bundle()
            arguments.putParcelable("book", (grid.adapter as CatalogAdapter).books[position])
            nextFrag.arguments = arguments
            childFragmentManager.beginTransaction()
                .replace(R.id.home_fragment, nextFrag)
                .addToBackStack(null)
                .commit()
        }
        return root
    }
}