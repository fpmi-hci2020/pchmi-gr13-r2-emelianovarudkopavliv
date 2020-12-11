package com.hci.bookstore.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.hci.bookstore.R
import com.hci.bookstore.models.News
import com.hci.bookstore.services.BookStoreService
import com.hci.bookstore.ui.main.NewsAdapter

class NewsFragment : Fragment() {

    lateinit var newsView: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.news_fragment, container, false)

        newsView = root.findViewById<View>(R.id.newsView) as ListView

        val service = BookStoreService(this)
        //service.getNews()

        var news = News()
        news.title = "New book you’re definitely interested in"
        news.text = "You’ll love it, we promise! Just click here to preorder and it would be delivered to your doorstep as soon as it comes out."
        news.publisher = "Aversav"

        val newsArray = arrayOf(news, news)
        newsView.adapter = NewsAdapter(context!!, newsArray)

        return root
    }
}