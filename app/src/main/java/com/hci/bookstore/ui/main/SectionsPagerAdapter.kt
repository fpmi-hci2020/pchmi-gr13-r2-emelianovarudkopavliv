package com.hci.bookstore.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.hci.bookstore.ui.main.fragments.*

class SectionsPagerAdapter(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> HomeFragment()
            1 -> CartFragment()
            2 -> FavoritesFragment()
            3 -> OrdersFragment()
            else -> NewsFragment()
        }
    }
    override fun getCount(): Int {
        return 5
    }
}