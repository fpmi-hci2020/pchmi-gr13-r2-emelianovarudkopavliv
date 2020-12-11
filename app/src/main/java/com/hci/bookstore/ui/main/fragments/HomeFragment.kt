package com.hci.bookstore.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.hci.bookstore.R
import com.hci.bookstore.models.Book
import com.hci.bookstore.services.BookStoreService
import com.hci.bookstore.ui.main.CatalogAdapter

class HomeFragment : Fragment() {
    lateinit var books: Array<Book>
    lateinit var catalogGrid: GridView
    lateinit var searchView: SearchView
    lateinit var filtersGroup: RadioGroup

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.home_fragment, container, false)

        val service = BookStoreService(this)
        service.getBooks()

        catalogGrid = root.findViewById(R.id.grid) as GridView

        catalogGrid.setOnItemClickListener { p, v, position, _ ->
            val bookFragment = BookFragment()

            val arguments = Bundle()
            arguments.putParcelable("book", (catalogGrid.adapter as CatalogAdapter).getBookOnPosition(position))
            bookFragment.arguments = arguments

            childFragmentManager.beginTransaction()
                .replace(R.id.home_fragment, bookFragment)
                .addToBackStack(null)
                .commit()

        }

        searchView = root.findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val selectedId = filtersGroup.checkedRadioButtonId
                val filter = root.findViewById<RadioButton>(selectedId)
                searchByFilter(filter.tag as String)
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        val closeButtonId = searchView.context.resources.getIdentifier("android:id/search_close_btn", null, null);
        val closeButton = searchView.findViewById<ImageView>(closeButtonId)
        closeButton.setOnClickListener {
            searchView.setQuery("", false)
            (catalogGrid.adapter as CatalogAdapter).updateItems(books)
        }

        filtersGroup = root.findViewById(R.id.filterGroup)

        return root
    }

    private fun searchByFilter(tag: String) {
        val filteredBooks = when (tag) {
            "title" -> books.filter { book -> containsQuery(book.title)}.toTypedArray()
            "author" -> books.filter { book -> containsQuery(book.author)}.toTypedArray()
            "genre" -> books.filter { book -> containsQuery(book.genre)}.toTypedArray()
            else -> books.filter { book -> containsQuery(book.publisher)}.toTypedArray()
        }
        (catalogGrid.adapter as CatalogAdapter).updateItems(filteredBooks)
    }

    private fun containsQuery(field: String) : Boolean{
        return field.contains(searchView.query.toString(), true)
    }
}