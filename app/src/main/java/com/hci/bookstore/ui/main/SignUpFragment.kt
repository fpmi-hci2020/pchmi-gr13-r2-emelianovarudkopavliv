package com.hci.bookstore.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.hci.bookstore.R
import android.content.Intent
import android.widget.TextView
import android.widget.Toast
import com.hci.bookstore.MainActivity


class SignUpFragment : Fragment(), View.OnClickListener {

    lateinit var email: TextView
    lateinit var password: TextView
    lateinit var repeatPassword: TextView

    override fun onClick(v: View?) {
        if(validateUserData()) {
            startActivity(Intent(activity, MainActivity::class.java))
        }
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
    private fun validateUserData() : Boolean{
        if (email.text.isNullOrEmpty() || password.text.isNullOrEmpty()|| repeatPassword.text.isNullOrEmpty()) {
            Toast.makeText(context, "Fill all fields!", Toast.LENGTH_LONG).show()
            return false
        }
        if( android.util.Patterns.EMAIL_ADDRESS.matcher(email.text).matches()) {
            if(password.text.toString() == repeatPassword.text.toString()){
                return true
            }
            else{
                Toast.makeText(context, "Passwords do not match!", Toast.LENGTH_LONG).show()
            }
        }
        else{
            Toast.makeText(context, "Incorrect email!", Toast.LENGTH_LONG).show()
        }
        return false

    }
}
