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
import com.hci.bookstore.ui.main.FavoritesAdapter

class FavoritesFragment : Fragment() {

    lateinit var favoritesView: ListView
    lateinit var service: BookStoreService
    lateinit var email: String
    private var isViewShown = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.favorites_fragment, container, false)

        favoritesView = root.findViewById<View>(R.id.favoritesView) as ListView
        service = BookStoreService(this)
        email = (activity as MainActivity).email

        if(!isViewShown){
            service.getBooksInFavorites(email)
        }

        favoritesView.setOnItemClickListener { p, v, position, _ ->
           loadBookFragment(position)
        }

        return root
    }

    private fun loadBookFragment(position: Int){
        val bookFragment = BookFragment()

        val arguments = Bundle()
        arguments.putParcelable("book", (favoritesView.adapter as FavoritesAdapter).getBookOnPosition(position))
        bookFragment.arguments = arguments

        childFragmentManager.beginTransaction()
            .replace(R.id.favorites_fragment, bookFragment)
            .addToBackStack(null)
            .commit()

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(view != null){
            isViewShown = true
            if(isVisibleToUser){
                service.getBooksInFavorites(email)
            }
        }
        else{
            isViewShown = false
        }
    }
}