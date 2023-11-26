package com.example.genericannotaionreflect

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.genericannotaionreflect.autowire.MainActivity2
import com.example.genericannotaionreflect.buterknife.InjectUtil

class MainActivity : ComponentActivity() {
//    @InjectView(R.id.btn_click)
    private var button: Button ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        InjectUtil.injectView(this)

        setContentView(R.layout.activity_main)

        button?.setOnClickListener {
            Toast.makeText(applicationContext, "获取button点击", Toast.LENGTH_LONG).show()
        }

        findViewById<Button>(R.id.btn_intent).setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java).apply {
                putExtra("name", "intent")
            })
        }
    }
}