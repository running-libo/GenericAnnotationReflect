package com.example.genericannotaionreflect.reflactdemo;

import static java.lang.reflect.Modifier.PROTECTED;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.genericannotaionreflect.retrofit.GET;

import java.io.FileDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;

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

    /**
     * 获取类的方法的所有元素
     */
    @RequiresApi(api = Build.VERSION_CODES.P)
    public static void printMethods(Class cls) {
        System.out.println("类名：" + cls.getName());

        // 获取所有 public 访问权限的方法
        // 包括本类声明的和从父类继承的
        Method[] methods = cls.getMethods();

        // 获取所有本类声明的方法，包含各种访问权限
        Method[] declaredMethods = cls.getDeclaredMethods();

        for (Method method : declaredMethods) {
            method.setAccessible(true);

            System.out.println("方法名: " + method.getName());

            System.out.println("属性访问权限是否是 public： " + Modifier.isProtected(method.getModifiers()));

            //获取方法返回值类型
            Class<?> returnType = method.getReturnType();
            System.out.println( "返回类型: " + returnType.getName());

            //获取方法所有参数
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter: parameters) {
                System.out.println("拥有参数：" + parameter.getName() + "--" + parameter.getType().getName());
            }

            //获取方法抛出的异常
            Class<?>[] exceptionTypes = method.getExceptionTypes();

            //获取方法注解
            Annotation[] annotations = method.getAnnotations();

            //方法是否有某个注解
            if (method.isAnnotationPresent(GET.class)) {
                GET annotation = method.getAnnotation(GET.class);
                System.out.println(annotation.value());
            }

            //获取方法参数类型的泛型参数
            Type[] genericParameterTypes = method.getGenericParameterTypes();
            for (Type type: genericParameterTypes) {
                System.out.println(type.getTypeName());
            }

        }
    }

    public static void invokeMethod() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class cls = SonClass.class;
        SonClass sonClass = (SonClass) cls.newInstance();
        Method method = cls.getDeclaredMethod("setmSonName", String.class); //获取setmSonName方法
        method.setAccessible(true); //可以访问对象的私有方法
        method.invoke(sonClass, "lala"); //使用 invoke 反射调用私有方法，传需要操作的对象和方法的各个参数
        System.out.println(sonClass.getmSonName());
    }

    public static void modifyField() throws NoSuchFieldException, IllegalAccessException {
        Class cls = SonClass.class;

        Field field = cls.getDeclaredField("mSonAge");
        field.setAccessible(true);
        SonClass sonClass = new SonClass();
        field.set(sonClass, 18);
        System.out.println("age: " + sonClass.mSonAge);
    }

    public static void getConstructor() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {

        Class<?> cls = StringBuilder.class;
        StringBuilder sb = (StringBuilder) cls.newInstance();
        sb.append("hello");
        System.out.println(sb.toString());

        Class<?> cls2 = String.class;
        //获取String类带一个String参数的构造器
        Constructor constructor = cls2.getConstructor(String.class);
        //根据构造器constructor创建实例
        String str = (String) constructor.newInstance("hello");
        System.out.println(str);
    }
}
