package com.example.mangaapp

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun View.Gone(){
    visibility = View.GONE
}
fun AppCompatActivity.showToast(message:String){
    Toast.makeText(this , message , Toast.LENGTH_SHORT).show()
}
fun View.Visiblie(){
    visibility = View.VISIBLE
}