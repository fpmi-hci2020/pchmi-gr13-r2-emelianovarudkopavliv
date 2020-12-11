package com.hci.bookstore.ui.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.hci.bookstore.R
import android.widget.TextView
import android.widget.Toast
import com.hci.bookstore.services.BookStoreService
import com.hci.bookstore.models.User


class SignUpFragment : Fragment(), View.OnClickListener {

    lateinit var email: TextView
    lateinit var password: TextView
    lateinit var repeatPassword: TextView

    override fun onClick(v: View?) {
        registerUser()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.sign_up_fragment, container, false)
        val button: Button = root.findViewById(R.id.signUpButton)
        email = root.findViewById(R.id.up_email)
        password = root.findViewById(R.id.up_password)
        repeatPassword = root.findViewById(R.id.up_password2)
        button.setOnClickListener(this)
        return root
    }
    private fun registerUser() {
        if (email.text.isNullOrEmpty() || password.text.isNullOrEmpty()|| repeatPassword.text.isNullOrEmpty()) {
            Toast.makeText(context, "Fill all fields!", Toast.LENGTH_LONG).show()
        }
        if( android.util.Patterns.EMAIL_ADDRESS.matcher(email.text).matches()) {
            if(password.text.toString() == repeatPassword.text.toString()){
                BookStoreService(this).registerUser(
                    User(
                        email.text.toString(),
                        password.text.toString()
                    )
                )
            }
            else{
                Toast.makeText(context, "Passwords do not match!", Toast.LENGTH_LONG).show()
            }
        }
        else{
            Toast.makeText(context, "Incorrect title!", Toast.LENGTH_LONG).show()
        }
    }
}
