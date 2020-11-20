package com.hci.bookstore

import android.content.Intent
import android.graphics.Bitmap
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.*
import org.json.JSONException
import org.json.JSONObject
import com.google.gson.Gson
import com.hci.bookstore.ui.main.CatalogAdapter
import com.hci.bookstore.ui.main.HomeFragment
import com.hci.bookstore.ui.main.SignInFragment
import com.hci.bookstore.ui.main.SignUpFragment
import org.json.JSONArray


class BookService(var fragment: Fragment) {
    private var mRequestQueue: RequestQueue
    private val gson: Gson
    private val url = "http://enigmatic-fjord-21043.herokuapp.com/api"

    init {
        mRequestQueue = Volley.newRequestQueue(fragment.context)
        gson = Gson()
    }

    fun registerUser(user: User) {
        val request = JsonObjectRequest(
            Request.Method.POST,
            "$url/accounts/",
            JSONObject(gson.toJson(user)),
            Response.Listener<JSONObject> { response ->
                try {
                    startActivity(fragment.context!!, Intent(fragment.activity, MainActivity::class.java), null)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { error ->
                Toast.makeText(
                    fragment.context, "User with this email already exists!",
                    Toast.LENGTH_LONG
                ).show()
            })
            mRequestQueue.add(request)
    }

    fun getUser(email: String) {
        val request = JsonObjectRequest(
            Request.Method.GET,
            "$url/accounts/$email", null, Response.Listener<JSONObject> { response ->
                try {
                    val signIn = fragment as SignInFragment
                    var user = gson.fromJson(response.toString(), User::class.java)
                    if(user.password != signIn.password.text.toString()){
                        Toast.makeText(fragment.context, "Password is incorrect!", Toast.LENGTH_LONG).show()
                    }
                    else{
                        startActivity(fragment.context!!, Intent(fragment.activity, MainActivity::class.java), null)
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { error ->
                Toast.makeText(
                    fragment.context, "User with this email doesn't exist!",
                    Toast.LENGTH_LONG
                ).show()
            })

        mRequestQueue.add(request)
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