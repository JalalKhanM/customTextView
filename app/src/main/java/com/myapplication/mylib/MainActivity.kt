package com.myapplication.mylib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.custom.view.textview.JalalView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.logging.Handler

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