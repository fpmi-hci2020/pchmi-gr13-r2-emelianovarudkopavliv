package com.hci.bookstore

import android.util.Log
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import com.android.volley.toolbox.JsonObjectRequest
import com.google.gson.Gson
import com.hci.bookstore.ui.main.CatalogAdapter
import com.hci.bookstore.ui.main.HomeFragment


class BookService(var fragment: Fragment) {
    private var mRequestQueue: RequestQueue
    private val gson: Gson
    private val url = "http://enigmatic-fjord-21043.herokuapp.com/api/"

    init {
        mRequestQueue = Volley.newRequestQueue(fragment.context)
        gson = Gson()
    }

    fun getBook(id: Int) {
        val request = JsonObjectRequest(
            Request.Method.GET,
            "$url/store/books/$id", null, Response.Listener<JSONObject> { response ->
                try {
                    Log.println(Log.INFO,null, "here")
                    val home = fragment as HomeFragment
                    home.book = gson.fromJson(response.toString(), Book::class.java)
                    home.grid.adapter = CatalogAdapter(fragment.context!!, listOf(home.book))
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { error ->
                error.printStackTrace()
            })

        mRequestQueue.add(request)
    }
}