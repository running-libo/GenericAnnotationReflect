package com.example.genericannotaionreflect.generic;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class ReflectionGeneric {

    @RequiresApi(api = Build.VERSION_CODES.P)
    public static void inject() throws NoSuchMethodException, ClassNotFoundException {
        Method method = Person.class.getMethod("method", List.class);
        Type[] genericParameterTypes = method.getGenericParameterTypes();

        //获取泛型类型
        for (Type genericParameterType : genericParameterTypes) {
            //获取泛型里面的实际参数类型
            Type[] actualTypeArguments = ((ParameterizedType) genericParameterType).getActualTypeArguments();
            for (Type actualTypeArgument : actualTypeArguments) {
                //获取非String类型的类
                if (actualTypeArgument.getTypeName().equals("java.lang.String")) {
                    String classNamePath = actualTypeArgument.getTypeName();
                    Class<?> aClass = Class.forName(classNamePath);
                    //获得所有属性
                    Arrays.stream(aClass.getDeclaredFields()).forEach(System.out::println);
                }
            }
        }

        //获取返回类型
        Type genericReturnType = method.getGenericReturnType();
        Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
        Type actualTypeArgument = actualTypeArguments[0];
        if (actualTypeArgument.getTypeName().equals("java.lang.String")) {
            //获取参数全类名
            String classNamePath = actualTypeArgument.getTypeName();
            //根据类路径获取class文件
            Class<?> returnClass = Class.forName(classNamePath);
            //获得所有属性
            Arrays.stream(returnClass.getDeclaredFields()).forEach(System.out::println);
        }

    }

}
