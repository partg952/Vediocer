package com.example.vediocer

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.ArrayList

class Manager(private var context: Context,private var array: ArrayList<String>) : RecyclerView.Adapter<Manager.ViewHolder>() {
  
  inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView)
  {
  var video = itemView.findViewById<VideoView>(R.id.videoView2)
    var share = itemView.findViewById<FloatingActionButton>(R.id.floatingActionButton3)
    init {
      video.setOnClickListener {
        video.start()
	 }
      share.setOnClickListener {
        var intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"${array[adapterPosition]}")
        var chooser = Intent.createChooser(intent,"Complete Action Using....")
        context.startActivity(chooser)
      }
    }
  }
  
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    var view = LayoutInflater.from(context).inflate(R.layout.card,parent,false)
    return ViewHolder(view)
  }
  
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    var currentpos = array[position]
    var uri = Uri.parse(currentpos)
    holder.video.setVideoURI(uri)
    var media = MediaController(context)
    media.setAnchorView(holder.video)
    holder.video.requestFocus()
    holder.video.setMediaController(media)
    
    
  }
  
  override fun getItemCount() = array.size
  
  
}
