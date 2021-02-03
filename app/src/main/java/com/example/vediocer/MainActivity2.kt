package com.example.vediocer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var tab = findViewById<TabLayout>(R.id.tabLayout)
        var view = findViewById<ViewPager>(R.id.view)
        if(FirebaseAuth.getInstance().uid==null){
            var intent = Intent(this,MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        view.adapter = adapter(supportFragmentManager)
        tab.setupWithViewPager(view)

    }


}