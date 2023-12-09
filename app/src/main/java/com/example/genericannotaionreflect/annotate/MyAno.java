package com.example.genericannotaionreflect.annotate;

import androidx.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface MyAno {

    String name() default "angela";
    int age() default 18;
}

@IntDef(value = {1, 2, 3, 4, 5})
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
@interface Type {

}

@MyAno(name = "范冰冰", age = 15)
class UserAno {

}
