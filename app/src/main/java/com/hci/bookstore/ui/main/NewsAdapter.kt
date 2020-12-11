package com.hci.bookstore.ui.main

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.app.Activity
import android.view.LayoutInflater
import com.hci.bookstore.R
import com.hci.bookstore.models.News

class NewsAdapter (context: Context, news: Array<News>): BaseAdapter() {

    private var newsContext = context
    private var news = news

    private class NewsViewHolder {

        lateinit var titleView: TextView
        lateinit var textView: TextView
        lateinit var publisherView: TextView
        lateinit var dateView: TextView
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val holder: NewsViewHolder
        var view: View? = null

        if (convertView == null) {
            val bookInflater =
                newsContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = bookInflater.inflate(R.layout.news, null)

            holder = NewsViewHolder()
            holder.titleView = view.findViewById(R.id.newsTitle) as TextView
            holder.textView = view.findViewById(R.id.newsText) as TextView
            holder.publisherView = view.findViewById(R.id.newsPublisher) as TextView
            holder.dateView = view.findViewById(R.id.newsDate) as TextView

            view.tag = holder

        } else {
            holder = convertView.tag as NewsViewHolder
        }

        val news = news[position]

        holder.titleView.text = news.title
        holder.textView.text = news.text
        holder.publisherView.text = news.publisher
        holder.dateView.text = news.date
        if(convertView != null) {
            return convertView
        }
        return view!!
    }

    override fun getItem(position: Int): Any {
        return news[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return news.size
    }
}