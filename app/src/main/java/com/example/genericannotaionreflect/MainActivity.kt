package com.example.genericannotaionreflect

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.example.genericannotaionreflect.annotate.Person
import com.example.genericannotaionreflect.annotate.TestAnnoReflect
import com.example.genericannotaionreflect.autowire.MainActivity2
import com.example.genericannotaionreflect.buterknife.InjectUtil
import com.example.genericannotaionreflect.hilt.MainActivity3
import com.example.genericannotaionreflect.reflactdemo.ClassReflact
import com.example.genericannotaionreflect.reflactdemo.FatherClass
import com.example.genericannotaionreflect.reflactdemo.SonClass
import com.example.genericannotaionreflect.retrofit.IHomeService
import com.example.genericannotaionreflect.retrofit.Retrofit
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    //    @InjectView(R.id.btn_click)
    private var button: Button? = null

    @RequiresApi(Build.VERSION_CODES.P)
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

        findViewById<Button>(R.id.btn_hilt).setOnClickListener {
            startActivity(Intent(this, MainActivity3::class.java))
        }

//        lifecycleScope.launch {
//            IHomeService.instance.getBanner()
//        }

        //反射测试用例
//        ClassReflact.printFileds(FatherClass::class.java)
//        ClassReflact.printMethods(SonClass::class.java)
//        ClassReflact.invokeMethod()
//        ClassReflact.modifyField()
//        ClassReflact.getConstructor()

        //注解测试用例
//        Person.getAnnoValue()
        TestAnnoReflect.testAnnotation()
    }

}
