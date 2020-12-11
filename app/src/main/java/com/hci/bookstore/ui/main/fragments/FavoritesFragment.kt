package com.hci.bookstore.ui.main.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.hci.bookstore.R
import com.hci.bookstore.services.BookStoreService
import com.hci.bookstore.ui.main.FavoritesAdapter

class FavoritesFragment : Fragment() {

    lateinit var favoritesView: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.favorites_fragment, container, false)

        favoritesView = root.findViewById<View>(R.id.favoritesView) as ListView

        val service = BookStoreService(this)
        service.getBooksInFavorites()

        favoritesView.setOnItemClickListener { p, v, position, _ ->
            Log.println(Log.INFO, null, "Here")
            val bookFragment = BookFragment()

            val arguments = Bundle()
            arguments.putParcelable("book", (favoritesView.adapter as FavoritesAdapter).getBookOnPosition(position))
            bookFragment.arguments = arguments

            childFragmentManager.beginTransaction()
                .replace(R.id.favorites_fragment, bookFragment)
                .addToBackStack(null)
                .commit()

        }

        return root
    }
}