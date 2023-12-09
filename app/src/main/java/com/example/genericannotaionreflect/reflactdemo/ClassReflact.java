package com.example.genericannotaionreflect.reflactdemo;

import static java.lang.reflect.Modifier.PROTECTED;

import java.io.FileDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ClassReflact {

    public static void getInfo(Class cls) throws NoSuchMethodException, NoSuchFieldException {

        //构造方法
        cls.getConstructor();

        //获取某个方法
        cls.getMethod("");

        //包含的方法
        cls.getMethods();

        //获取某个属性
        cls.getField("");

        //包含的属性
        cls.getFields();

        //实现的接口
        cls.getInterfaces();

        //包含的Annotation
        cls.getAnnotations();

        //内部类
        cls.getDeclaredClasses();

        //外部类
        cls.getDeclaringClass();

        //获取类名
        cls.getName();

        //获取包名
        cls.getPackage();

        //获取修饰符
        cls.getModifiers();

        cls.getConstructor();

    }

    /**
     * 通过反射获取类的三种方式
     * @throws ClassNotFoundException
     */
    private void getClassWay() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class cls = null;
        cls = FatherClass.class;
        cls = new FatherClass().getClass();
        cls = Class.forName("com.example.genericannotaionreflect.reflactdemo.FatherClass");

        FatherClass fatherClass = (FatherClass)cls.newInstance();  //利用newInstance创建对象
    }

    /**
     * 通过反射获取类的所有变量
     */
    public static void printFileds(Class cls) {
        System.out.println("类名：" + cls.getName());

        // 获取所有 public 访问权限的变量
        // 包括本类声明的和从父类继承的
        Field[] fields = cls.getFields();

        // 获取所有本类声明的变量，包含各种访问权限
        Field[] declaredFields = cls.getDeclaredFields();

        for (Field field: declaredFields) {
            //获取访问权限并输出
            field.setAccessible(true);
            int modifier = field.getModifiers();
            System.out.println("属性访问权限是否是 PROTECTED： " + Modifier.isProtected(modifier));
            //输出变量的类型及变量名
            System.out.println("属性类型：" + field.getType().getName() + "属性名：" + field.getName());
        }
    }
}
