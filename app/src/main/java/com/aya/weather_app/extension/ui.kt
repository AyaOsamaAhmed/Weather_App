package com.aya.weather_app.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun View.show(){
    this.visibility = View.VISIBLE
}

fun View.hide(){
    this.visibility = View.GONE
}

fun View.showMessage(message:String){
    // Toast.makeText(this.context,message,Toast.LENGTH_SHORT).show()
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}

val ViewGroup.layoutInflater: LayoutInflater get() = LayoutInflater.from(this.context)

