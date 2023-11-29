package com.example.genericannotaionreflect.hilt;

import android.os.Bundle;
import android.widget.Toast;
import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;
import com.example.genericannotaionreflect.R;

public class MainActivity3 extends ComponentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        Module singleInstance = Hilt.getSingleInstance(Module.class);

        singleInstance.person.setUserName("haha");
        singleInstance.person.setPassword("123456");

        Toast.makeText(getApplicationContext(), "我的名字: " + singleInstance.person.getUserName() + " 我的密码:" + singleInstance.person.getPassword(), Toast.LENGTH_LONG).show();
    }
}
