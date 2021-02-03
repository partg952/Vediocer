package com.example.vediocer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    var sign_up = findViewById<Button>(R.id.button)
    var email = findViewById<EditText>(R.id.editTextTextEmailAddress)
    var password = findViewById<EditText>(R.id.editTextNumberPassword)
    var text = findViewById<TextView>(R.id.textView)

    sign_up.setOnClickListener {
      var email = email.text.toString()
      var pass = password.text.toString()
      FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,pass)
        .addOnSuccessListener {
          Toast.makeText(applicationContext,"Sign Up Success",Toast.LENGTH_SHORT).show()
          var gmail = FirebaseAuth.getInstance().currentUser?.email?.removeSuffix("@gmail.com").toString()
          FirebaseDatabase.getInstance().getReference().child("users").child("$gmail").child("email")
            .setValue(email)
          var intent = Intent(this,MainActivity2::class.java)
          intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
          startActivity(intent)
        }
    }
    text.setOnClickListener {
      var intent2 = Intent(this@MainActivity,SignIn::class.java)
      startActivity(intent2)
    }
  }
}