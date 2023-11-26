package com.example.genericannotaionreflect.autowire;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import java.lang.reflect.Field;
import java.util.Arrays;

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
                    //获取单个数组元素
                    Class<?> componentType = field.getType().getComponentType();
                    // 当前属性是数组并且是 Parcelable（子类）数组
                    if (field.getType().isArray() && Parcelable.class.isAssignableFrom(componentType)) {
                        Object[] objs = (Object[]) obj;
                       //创建对应类型的数组，objs 拷贝
                        Object[] objects = Arrays.copyOf(objs, objs.length, (Class<? extends Object[]>) field.getType());
                        obj = objects;
                    }

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
