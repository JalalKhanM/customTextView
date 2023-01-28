package com.myapplication.mylib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.text.stylish.JalalView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var myView = findViewById<JalalView>(R.id.empty)



//        GlobalScope.launch(Dispatchers.Main){
//            delay(2000)
//            myView.setSpeed(100)
//        }
    }
}