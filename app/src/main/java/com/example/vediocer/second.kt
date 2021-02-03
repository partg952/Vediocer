package com.example.vediocer

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth

class second : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_second, container, false)
        var text = view.findViewById<TextView>(R.id.textView2)
        var add = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        var members = view.findViewById<FloatingActionButton>(R.id.floatingActionButton2)
        
        text.text = FirebaseAuth.getInstance().currentUser?.email.toString()
        
        add.setOnClickListener {
        var intent = Intent(requireContext(),addimage::class.java)
            startActivity(intent)
        }
        members.setOnClickListener {
            var inent = Intent(requireContext(),members::class.java)
            startActivity(inent)
        }
        
    return view
    }


}