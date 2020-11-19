package com.hci.bookstore.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

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