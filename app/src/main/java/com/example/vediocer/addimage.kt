package com.example.vediocer

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import java.util.*

private lateinit var imageView: VideoView
private var num2:Int=0
class addimage : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_addimage)
    var addimage = findViewById<ImageButton>(R.id.imageButton)
    var upload = findViewById<ImageButton>(R.id.imageButton2)
   imageView = findViewById(R.id.videoView)
  
  
    var media = MediaController(this)
    media.setAnchorView(imageView)
    imageView.setMediaController(media)
  
    imageView.setOnClickListener {
      imageView.requestFocus()
      imageView.start()
    }
  
  
    var getdata = object : ValueEventListener {
      override fun onDataChange(snapshot: DataSnapshot) {
        for (i in snapshot.children){
          var num = i.child("num").getValue().toString()
          if(num=="null"||num=="0"){
          
          }
          else{
            num2=num.toInt()
            }
          }
        }
      override fun onCancelled(error: DatabaseError) {
        TODO("Not yet implemented")
      }
      }
    
      
    
  
    FirebaseDatabase.getInstance().getReference().addValueEventListener(getdata)
  
  
    addimage.setOnClickListener {
      var add = Intent(Intent.ACTION_PICK)
      add.type = "video/*"
      startActivityForResult(add,49)
    }
      upload.setOnClickListener {
          upload()
      }

  }

  var uri: Uri?=null

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if(requestCode==49&&resultCode==Activity.RESULT_OK&&data!=null){
     var data = data.data
     
      imageView.setVideoURI(uri)
      uri=data
      
      
    }
    super.onActivityResult(requestCode, resultCode, data)
  }
  private fun upload(){
    var pg = ProgressDialog(this)
    pg.setTitle("Video Uploading...")
    pg.setCancelable(false)
    num2++
    var filename = UUID.randomUUID().toString()
      var ref = FirebaseStorage.getInstance().getReference().child("images").child("$filename")
      var ref2 = FirebaseDatabase.getInstance().getReference().child("images").child("$num2")
      var ref3 = FirebaseDatabase.getInstance().getReference().child("images").child("num")
      Log.d("Main2","$filename")
      ref.putFile(uri!!).addOnProgressListener {
        pg.show()
      }.addOnSuccessListener {
        pg.hide()
          ref.downloadUrl.addOnCompleteListener {
            if(it.isSuccessful){
             Log.d("Main2","Done!!" )
              var result = it.result.toString()
              ref3.setValue(num2)
              Log.d("Main2","$result")
              ref2.setValue(result).addOnSuccessListener {
                Toast.makeText(applicationContext,"Video Uploaded!",Toast.LENGTH_SHORT).show()
              }
            }
          }
      }
  }
}