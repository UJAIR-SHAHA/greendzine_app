package com.example.greendzine_app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    // Predefined login credentials
    private val correctEmail = "user@gmail.com"
    private val correctPassword = "password123"

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEditText = findViewById(R.id.email_field)
        passwordEditText = findViewById(R.id.password_field)
        loginButton = findViewById(R.id.login_button)

        loginButton.setOnClickListener {
            loginUser()
            HomeFragment()
        }
    }

    private fun loginUser() {
        val enteredEmail = emailEditText.text.toString().trim()
        val enteredPassword = passwordEditText.text.toString().trim()

        // Check if the entered credentials match the predefined ones
        if (enteredEmail == correctEmail && enteredPassword == correctPassword) {
            // Successful login
            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()


            // You can navigate to another activity or perform other actions here
            // Navigate to the main activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            // Failed login
            Toast.makeText(this, "Invalid email or password. Please try again.", Toast.LENGTH_SHORT).show()
        }
    }
}


