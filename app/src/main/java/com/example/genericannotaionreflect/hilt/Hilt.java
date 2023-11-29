package com.example.genericannotaionreflect.hilt;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自动注入创建对象
 */
public class Hilt {
    public static Map<Class<?>, Object> instances = new ConcurrentHashMap<>();
    public static <T> T getSingleInstance(Class<T> cls) {
        try {
            //判断是否走单例
            if (!cls.isAnnotationPresent(SimpleSingleInject.class)){
                return getInstance(cls);
            }
            Object obj = instances.get(cls);
            if (null != obj) {
                return (T)obj;
            }
            //使用类锁锁代码块
            synchronized (cls) {
                if (null == instances.get(cls)) {
                    obj = getInstance(cls);
                    instances.put(cls, obj);
                }

            }
            return (T)obj;
        }
        catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static<T> T getInstance (Class<T> cls){
        //如果该class中含有SimpleInject注解的filed，则反射创建该filed对象设置给该class
        try {
            T obj = cls.newInstance();
            //返回本类申明的字段包括非public,不包括父类
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field f : declaredFields) {
                //判断字段是否包含指定注解类型
                if (f.isAnnotationPresent(SimpleInject.class)) {
                    //判断字段是否为私有
                    if (!f.isAccessible()) {
                        f.setAccessible(true);
                    }
                    //再次递归调用赋值
                    f.set(obj, getInstance(f.getType()));
                }

            }
            return obj;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
