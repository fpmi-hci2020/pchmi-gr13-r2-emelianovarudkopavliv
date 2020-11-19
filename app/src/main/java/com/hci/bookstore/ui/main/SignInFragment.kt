package com.hci.bookstore.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.hci.bookstore.MainActivity
import com.hci.bookstore.R

class SignInFragment : Fragment(), View.OnClickListener {

    lateinit var email: TextView
    lateinit var password: TextView

    override fun onClick(v: View?) {
        if(validateUserData()) {
            startActivity(Intent(activity, MainActivity::class.java))
        }
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

    private fun validateUserData() : Boolean{
        if (email.text.isNullOrEmpty() || password.text.isNullOrEmpty()) {
            Toast.makeText(context, "Fill all fields!", Toast.LENGTH_LONG).show()
            return false
        }
        if( android.util.Patterns.EMAIL_ADDRESS.matcher(email.text).matches()) {
            if(password.text.toString() == "123"){
                return true
            }
            else{
                Toast.makeText(context, "Email or password is incorrect!", Toast.LENGTH_LONG).show()
            }
        }
        else{
            Toast.makeText(context, "Incorrect email!", Toast.LENGTH_LONG).show()
        }
        return false

    }
}