package com.hci.bookstore.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hci.bookstore.BookStoreService
import com.hci.bookstore.R

class SignInFragment : Fragment(), View.OnClickListener {

    lateinit var email: TextView
    lateinit var password: TextView

    override fun onClick(v: View?) {
        signInUser()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.sign_in_fragment, container, false)
        val button: Button = root.findViewById(R.id.signInButton)
        button.setOnClickListener(this)
        email = root.findViewById(R.id.email)
        password = root.findViewById(R.id.password)
        return root
    }

    private fun signInUser() {

        if (email.text.isNullOrEmpty() || password.text.isNullOrEmpty()) {
            Toast.makeText(context, "Fill all fields!", Toast.LENGTH_LONG).show()
        }
        if( android.util.Patterns.EMAIL_ADDRESS.matcher(email.text).matches()) {
            BookStoreService(this).getUser(email.text.toString())
        }
        else{
            Toast.makeText(context, "Incorrect email!", Toast.LENGTH_LONG).show()
        }

    }
}