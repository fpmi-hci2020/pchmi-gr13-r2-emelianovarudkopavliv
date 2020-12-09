package com.hci.bookstore.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.hci.bookstore.R
//import com.hci.bookstore.ui.main.SectionsPagerAdapter.FirstPageFragmentListener
import kotlinx.android.synthetic.main.activity_main.view.*

class SectionsPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    //private val fm = fm
    //private var mFragmentAtPos0: Fragment? = null

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> HomeFragment()
            /*{
                if (mFragmentAtPos0 == null) {
                    mFragmentAtPos0 =
                         HomeFragment( object : FirstPageFragmentListener {
                            override fun onSwitchToNextFragment() {
                                val f = BookFragment()
                                fm.beginTransaction()
                                    .remove(mFragmentAtPos0!!)
                                    .commit()
                                mFragmentAtPos0 = f
                                notifyDataSetChanged()
                            }
                        })
                }
                return mFragmentAtPos0!!
            }*/
            1 -> CartFragment()
            2 -> FavoritesFragment()
            3 -> OrdersFragment()
            else -> NewsFragment()
        }
    }
    override fun getCount(): Int {
        return 5
    }


    /*interface FirstPageFragmentListener {
        fun onSwitchToNextFragment()
    }*/

}