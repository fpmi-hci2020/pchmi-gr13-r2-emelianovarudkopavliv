package com.hci.bookstore

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.hci.bookstore.ui.main.CartAdapter
import com.hci.bookstore.ui.main.SectionsPagerAdapter

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var email: String

    override fun onClick(v: View?) {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        email = intent.getStringExtra("email");

        val sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter

        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val tabIcons = intArrayOf(R.drawable.home, R.drawable.cart, R.drawable.favorites,
            R.drawable.order, R.drawable.news )
        for (i in 0 until tabs.tabCount) {
            val view = layoutInflater.inflate(R.layout.menu_tab, null)
            val tab = tabs.getTabAt(i)
            view.findViewById<View>(R.id.icon).setBackgroundResource(tabIcons[i])
            if (tab != null) {
                tab!!.customView = view
            }
        }
    }

}