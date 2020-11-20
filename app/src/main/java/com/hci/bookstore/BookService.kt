package com.hci.bookstore

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.*
import org.json.JSONException
import org.json.JSONObject
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.hci.bookstore.ui.main.CatalogAdapter
import com.hci.bookstore.ui.main.HomeFragment
import org.json.JSONArray


class BookService(var fragment: Fragment) {
    private var mRequestQueue: RequestQueue
    private val gson: Gson
    private val url = "http://enigmatic-fjord-21043.herokuapp.com/api"

    init {
        mRequestQueue = Volley.newRequestQueue(fragment.context)
        gson = Gson()
    }

    fun getBooks() {
        val request = JsonArrayRequest(
            Request.Method.GET,
            "$url/store/books", null, Response.Listener<JSONArray> { response ->
                try {
                    val home = fragment as HomeFragment
                    home.books = gson.fromJson(response.toString(), Array<Book>::class.java)
                    home.grid.adapter = CatalogAdapter(fragment.context!!, home.books, this)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { error ->
                error.printStackTrace()
            })

        mRequestQueue.add(request)
    }

    fun getBookCover(id: Int, imageView: ImageView) {
        val request = ImageRequest(
            "$url/store/books/cover/$id", Response.Listener<Bitmap> { response ->
                imageView.setImageBitmap(response)
            }, 0, 0, null,null,
            Response.ErrorListener { error ->
                error.printStackTrace()
            })

        mRequestQueue.add(request)
    }
}