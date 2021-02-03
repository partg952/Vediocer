package com.example.vediocer

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class first : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var string=""
        var view = inflater.inflate(R.layout.fragment_first, container, false)
        var array = arrayListOf<String>()
        var recycle = view.findViewById<RecyclerView>(R.id.recyclerView)
        var adapter = Manager(requireContext(),array)
        recycle.adapter = adapter
        recycle.layoutManager = LinearLayoutManager(requireContext())
        
        var getdata = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children){
                  var num = i.child("num").getValue().toString()
                    if(num=="null"||num=="0"){
                    
                    }
                    else{
                        var num = num.toInt()
                        array.clear()
                        while (num>0){
                            var url = i.child("$num").getValue().toString()
                            array.add(url)
                            adapter.notifyDataSetChanged()
                            num--
                        }
                    }
                }
            }
    
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        
        FirebaseDatabase.getInstance().getReference().addValueEventListener(getdata)
        
    return view
    }


}