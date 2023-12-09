//@FullAnnoTest("package")
package com.example.genericannotaionreflect.annotate;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 定义target是注解的注解
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
@interface AnnoTest {
    String value() default "anno";
}

/**
 * 定义一个几乎全量信息的注解
 */
@AnnoTest("annotest")
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE, ElementType.FIELD, ElementType.TYPE, ElementType.METHOD,
        ElementType.PACKAGE, ElementType.PARAMETER, ElementType.CONSTRUCTOR, ElementType.LOCAL_VARIABLE})
@Documented
@interface FullAnnoTest {
    String value() default "FullAnnoTest";
}

class ParentObj {
}

@FullAnnoTest("class")
public class TestAnnoReflect<@FullAnnoTest("parameter") T> extends @FullAnnoTest("parent") ParentObj {

    //注解字段域
    private @FullAnnoTest("name") String name;

    //注解泛型字段域
    private @FullAnnoTest("value") T value;

    //注解通配符
    private @FullAnnoTest("list") List<@FullAnnoTest("generic") ?> list;

    @FullAnnoTest("constructor")
    public TestAnnoReflect() {
    }

    //注解方法
    @FullAnnoTest("method")             //注解方法参数
    public String hello(@FullAnnoTest("methodParameter") String name) throws @FullAnnoTest("Exception") Exception {  //注解异常抛出
        //注解局部变量
        @FullAnnoTest("result") String result;
        result = "result";
        System.out.println(result);
        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void testAnnotation() throws NoSuchMethodException {
        TestAnnoReflect<String> testAnnoReflect = new TestAnnoReflect<>();
        Class<TestAnnoReflect<Object>> clazz = (Class<TestAnnoReflect<Object>>) testAnnoReflect.getClass();

        FullAnnoTest annotation;
        Field[] fields = clazz.getDeclaredFields();
        Annotation[] annotations = clazz.getAnnotations();

        //获取class的注解
        annotation = (FullAnnoTest)annotations[0];
        System.out.println("修饰TestAnnoReflect类的注解value: " + annotation.value());

        //获取构造器的 注解
        Constructor<TestAnnoReflect<Object>> constructor = (Constructor<TestAnnoReflect<Object>>) clazz.getDeclaredConstructors()[0];
        annotation = constructor.getAnnotation(FullAnnoTest.class);
        System.out.println("构造器的注解value: " + annotation.value());

        //获取注解的 注解
        Class<? extends Annotation> annotationType = annotation.annotationType();
        AnnoTest annoTest = annotationType.getAnnotation(AnnoTest.class);
        System.out.println("修饰注解的注解AnnoTest-value: " + annoTest.value());

        //获取方法的 注解
        Method method = clazz.getDeclaredMethod("hello", String.class);
        annotation = method.getAnnotation(FullAnnoTest.class);
        System.out.println("修饰方法的注解value: " + annotation.value());

        //获取方法参数的 注解
        Parameter parameter = method.getParameters()[0];
        annotation = parameter.getAnnotation(FullAnnoTest.class);
        System.out.println("方法参数的注解value: " + annotation.value());

        //获取方法抛出的异常上的 注解
        Class<?>[] exceptionTypes = method.getExceptionTypes();
        for (Class<?> exceptionType: exceptionTypes) {
            annotation = exceptionType.getAnnotation(FullAnnoTest.class);
            if (annotation != null) {
                System.out.println("方法抛出的异常上的注解value: " + annotation.value());
            }
        }

        //获取包的 注解
        Package p = Package.getPackage("com.example.genericannotaionreflect.annotate");
        annotation = p.getAnnotation(FullAnnoTest.class);
        if (annotation != null) {
            System.out.println("修饰package的注解value: " + annotation.value());
        }

        //获取各个属性的注解 和 属性类型的通配符注解，即 List<@FullAnnoTest("generic") ?>的注解
        for (Field field : fields) {
            FullAnnoTest fieldAnnotation = field.getAnnotation(FullAnnoTest.class);
            if (fieldAnnotation != null) {
                System.out.println("Field " + field.getName() + " Annotation Value: " + fieldAnnotation.value());

                // 获取字段的泛型类型
                Type genericType = field.getGenericType();
                if (genericType instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) genericType;

                    // 获取泛型参数的实际类型
                    Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                    for (Type typeArgument : actualTypeArguments) {
                        if (typeArgument instanceof Class) {
                            Class<?> genericClass = (Class<?>) typeArgument;

                            // 获取泛型参数上的注解
                            FullAnnoTest genericAnnotation = genericClass.getAnnotation(FullAnnoTest.class);
                            if (genericAnnotation != null) {
                                System.out.println("Generic Type Annotation Value: " + genericAnnotation.value());
                            }
                        }
                    }
                }
            }
        }
    }

}
