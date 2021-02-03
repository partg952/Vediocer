package com.example.vediocer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        var sign_up = findViewById<Button>(R.id.button2)
        var email = findViewById<EditText>(R.id.editTextTextEmailAddress2)
        var password = findViewById<EditText>(R.id.editTextNumberPassword2)

        sign_up.setOnClickListener {
            var email = email.text.toString()
            var pass = password.text.toString()
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass)
                .addOnSuccessListener {
                    Toast.makeText(applicationContext,"Sign In Success",Toast.LENGTH_SHORT).show()
                    var intent = Intent(this,MainActivity2::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
        }
    }
}