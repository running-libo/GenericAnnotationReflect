package com.example.genericannotaionreflect.autowire;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import java.lang.reflect.Field;

public class InjectUtil {

    public static void injectAutowired(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        Intent intent = activity.getIntent();
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return;
        }

        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field: declaredFields) {
            if (field.isAnnotationPresent(Autowired.class)) {
                Autowired annotation = field.getAnnotation(Autowired.class);
                String key = TextUtils.isEmpty(annotation.value()) ? field.getName() : annotation.value(); //如果设置了value，key就是value，否则key就用name
                if (!key.isEmpty()) {
                    Object obj = extras.get(key);
                    field.setAccessible(true);
                    try {
                        field.set(activity, obj);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
