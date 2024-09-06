package com.example.lifecycle

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val username1 = "user"
    private val password1 = "pass"
    private val phoneNumber1 = "123"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val username: EditText = findViewById(R.id.username)
        val password: EditText = findViewById(R.id.password)
        val phoneNumber: EditText = findViewById(R.id.phoneNumber)
        val buttonLogin: Button = findViewById(R.id.buttonLogin)

        buttonLogin.setOnClickListener {
            val usernameInput = username.text.toString()
            val passwordInput = password.text.toString()
            val phoneNumberInput = phoneNumber.text.toString()

            if (usernameInput == this.username1 && passwordInput == this.password1 &&
                phoneNumberInput == this.phoneNumber1
            ) {
                // opens secondactivity if credentials/username, password and phone number is correct
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
