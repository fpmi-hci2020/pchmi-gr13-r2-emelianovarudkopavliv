package com.hci.bookstore

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.hci.bookstore.ui.main.SectionsPagerAdapter2

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val sectionsPagerAdapter = SectionsPagerAdapter2(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.login_view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.login_tabs)
        tabs.setupWithViewPager(viewPager)
    }
}