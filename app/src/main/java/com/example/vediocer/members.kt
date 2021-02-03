package com.example.vediocer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class members : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_members)
    var list = findViewById<ListView>(R.id.list)
    var ref = FirebaseDatabase.getInstance().getReference().child("users")
    var array = arrayListOf<String>()
    var adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array)
    list.adapter = adapter
      var getdata = object : ValueEventListener {
      override fun onDataChange(snapshot: DataSnapshot) {
        for(i in snapshot.children){
          var email = i.child("email").getValue().toString()
          if(email=="null"){

          }
          else{
            array.add(email)
            adapter.notifyDataSetChanged()
          }
        }
      }

      override fun onCancelled(error: DatabaseError) {
        TODO("Not yet implemented")
      }
    }
    ref.addValueEventListener(getdata)
  }
}