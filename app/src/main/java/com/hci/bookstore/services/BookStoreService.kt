package com.hci.bookstore.services

import android.content.Intent
import android.graphics.Bitmap
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.*
import org.json.JSONException
import org.json.JSONObject
import com.google.gson.Gson
import com.hci.bookstore.MainActivity
import com.hci.bookstore.models.*
import com.hci.bookstore.ui.main.*
import com.hci.bookstore.ui.main.fragments.*
import org.json.JSONArray


class BookStoreService(var fragment: Fragment) {
    private var mRequestQueue = Volley.newRequestQueue(fragment.context)
    private val gson = Gson()
    private val url = "http://enigmatic-fjord-21043.herokuapp.com/api"

    fun registerUser(user: User) {
        val request = JsonObjectRequest(
            Request.Method.POST,
            "$url/accounts/",
            JSONObject(gson.toJson(user)),
            Response.Listener<JSONObject> { _ ->
                try {
                    val intent = Intent(fragment.activity, MainActivity::class.java)
                    intent.putExtra("email", user.email)
                    startActivity(fragment.context!!, intent, null)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { _ ->
                Toast.makeText(
                    fragment.context, "User with this title already exists!",
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
                    val user = gson.fromJson(response.toString(), User::class.java)
                    if(user.password != signIn.password.text.toString()){
                        Toast.makeText(fragment.context, "Password is incorrect!", Toast.LENGTH_LONG).show()
                    }
                    else{
                        val intent = Intent(fragment.activity, MainActivity::class.java)
                        intent.putExtra("email", email)
                        startActivity(fragment.context!!, intent, null)
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { _ ->
                Toast.makeText(
                    fragment.context, "User with this title doesn't exist!",
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
                    home.catalogGrid.adapter = CatalogAdapter(fragment.context!!, home.books, this)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { error ->
                error.printStackTrace()
            })

        mRequestQueue.add(request)
    }

    fun getBooksInCart(email: String) {
        val request = JsonArrayRequest(
            Request.Method.GET,
            "$url/store/cart/$email", null, Response.Listener<JSONArray> { response ->
                try {
                    val cart = fragment as CartFragment
                    val books = gson.fromJson(response.toString(), Array<BookInCart>::class.java)
                    cart.cartView.adapter = CartAdapter(fragment.context!!, books, email, this)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { error ->
                error.printStackTrace()
            })

        mRequestQueue.add(request)
    }

    fun addToCart(cartRequest: CartRequest) {
        val request = JsonObjectRequest(
            Request.Method.POST,
            "$url/store/cart/",
            JSONObject(gson.toJson(cartRequest)),
            Response.Listener<JSONObject> { _ ->
                try {
                    Toast.makeText(fragment.context, "Added to BookInCart", Toast.LENGTH_LONG).show()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener {  error ->
                error.printStackTrace()
            })
        mRequestQueue.add(request)
    }

    fun updateBookCountInCart(cartRequest: CartRequest) {
        val request = JsonObjectRequest(
            Request.Method.PUT,
            "$url/store/cart/",
            JSONObject(gson.toJson(cartRequest)),
            Response.Listener<JSONObject> {

            },
            Response.ErrorListener {  error ->
                error.printStackTrace()
            })
        mRequestQueue.add(request)
    }

    fun removeFromCart(email: String, bookId: Int) {
        val request = JsonObjectRequest(
            Request.Method.DELETE,
            "$url/store/cart/$email/$bookId", null, Response.Listener<JSONObject> {},
            Response.ErrorListener {  error ->
                error.printStackTrace()
            })
        mRequestQueue.add(request)
    }

    //Change url
    fun getOrders(email: String) {
        val request = JsonArrayRequest(
            Request.Method.GET,
            "$url/store/books", null, Response.Listener<JSONArray> { response ->
                try {
                    val ordersFragment = fragment as OrdersFragment
                    val orders = gson.fromJson(response.toString(), Array<Order>::class.java)
                    ordersFragment.ordersView.adapter = OrdersAdapter(fragment.context!!, orders)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { error ->
                error.printStackTrace()
            })

        mRequestQueue.add(request)
    }

    fun getBooksInFavorites(email: String) {
        val request = JsonArrayRequest(
            Request.Method.GET,
            "$url/accounts/favorites/$email", null, Response.Listener<JSONArray> { response ->
                try {
                    val favorites = fragment as FavoritesFragment
                    val books = gson.fromJson(response.toString(), Array<BookInCart>::class.java)
                    favorites.favoritesView.adapter = FavoritesAdapter(fragment.context!!, books, email,this)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { error ->
                error.printStackTrace()
            })

        mRequestQueue.add(request)
    }

    fun addToFavorites (cartRequest: CartRequest) {
        val request = JsonObjectRequest(
            Request.Method.POST,
            "$url/accounts/favorites",
            JSONObject(gson.toJson(cartRequest)),
            Response.Listener<JSONObject> { _ ->
                try {
                    Toast.makeText(fragment.context, "Added to Favorites", Toast.LENGTH_LONG).show()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener {  error ->
                error.printStackTrace()
            })
        mRequestQueue.add(request)
    }

    fun removeFromFavorites(email: String, bookId: Int) {
        val request = JsonObjectRequest(
            Request.Method.DELETE,
            "$url/accounts/$email/$bookId", null, Response.Listener<JSONObject> {},
            Response.ErrorListener {  error ->
                error.printStackTrace()
            })
        mRequestQueue.add(request)
    }

    fun getNews(email: String) {
        val request = JsonArrayRequest(
            Request.Method.GET,
            "$url/accounts/news/$email", null, Response.Listener<JSONArray> { response ->
                try {
                    val newsFragment = fragment as NewsFragment
                    val news = gson.fromJson(response.toString(), Array<News>::class.java)
                    newsFragment.newsView.adapter = NewsAdapter(fragment.context!!, news)
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