package com.hci.bookstore.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.hci.bookstore.R
import com.hci.bookstore.ui.main.fragments.SignInFragment
import com.hci.bookstore.ui.main.fragments.SignUpFragment

private val TAB_TITLES = arrayOf(
    R.string.sign_in,
    R.string.sign_up
)

class SectionsPagerAdapter2(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        if(position == 1){
            return SignUpFragment()
        }
        return SignInFragment()
    }
    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

}