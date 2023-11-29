package com.example.genericannotaionreflect.buterknife;

import android.app.Activity;
import android.view.View;
import java.lang.reflect.Field;

public class InjectUtil {

    public static void injectView(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field: declaredFields) {
            if (field.isAnnotationPresent(InjectView.class)) { //如果该field含有InjectView类注解
                InjectView annotation = field.getAnnotation(InjectView.class); //获取该注解类型对象
                View view = activity.findViewById(annotation.value());
                field.setAccessible(true);
                try {
                    field.set(activity, view);  //将该view设置给该属性对象
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
