package com.hci.bookstore.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.hci.bookstore.R
import android.widget.RadioButton
import com.hci.bookstore.Book
import com.hci.bookstore.BookService

class HomeFragment : Fragment() {

    lateinit var book: Book
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

        val service = BookService(this)
        service.getBook(1)

        grid = root.findViewById(R.id.grid) as GridView

        grid.setOnItemClickListener { p, v, position, id ->
            val nextFrag = BookFragment()
            val arguments = Bundle()
            arguments.putInt("book", position)
            nextFrag.arguments = arguments
            activity!!.supportFragmentManager.beginTransaction()
                .replace(((view as ViewGroup).parent as View).id, nextFrag, "findThisFragment")
                .addToBackStack(null)
                .commit()
        }

        return root
    }
}