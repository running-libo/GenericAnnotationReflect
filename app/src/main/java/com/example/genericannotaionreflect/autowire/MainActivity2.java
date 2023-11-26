package com.example.genericannotaionreflect.autowire;

import android.os.Bundle;
import android.widget.Toast;
import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;
import com.example.genericannotaionreflect.R;

public class MainActivity2 extends ComponentActivity {

    @Autowired("name")
    String name;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InjectUtil.injectAutowired(this);

        setContentView(R.layout.activity_main2);

        Toast.makeText(getApplicationContext(), "我的名字: " + name, Toast.LENGTH_LONG).show();
    }
}
