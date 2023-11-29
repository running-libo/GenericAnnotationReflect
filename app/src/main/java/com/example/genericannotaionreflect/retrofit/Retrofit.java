package com.example.genericannotaionreflect.retrofit;

import com.google.gson.Gson;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class Retrofit {
    private OkHttpClient mOkHttpClient;
    private static final MediaType MEDIA_TYPE = MediaType.get("application/json; charset=UTF-8");

    public Retrofit(OkHttpClient mOkHttpClient) {
        this.mOkHttpClient = mOkHttpClient;
    }

    public <T> T create(Class<T> service) {
        Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service}, new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                Annotation[] annotations = method.getAnnotations();
                for (Annotation annotation: annotations) {
                    if (annotation instanceof GET) {
                        return parseGet(((GET) annotation).value());
                    } else if (annotation instanceof POST){
                        return parsePost(((POST) annotation).value(), method, objects);
                    }
                }
                return null;
            }
        });
        return null;
    }

    /**
     * 处理GET请求信息获取并发起请求
     * @param url
     * @return
     */
    private Call parseGet(String url) {
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        return mOkHttpClient.newCall(request);
    }

    /**
     * 处理POST请求信息获取并发起请求
     * @param url
     * @return
     */
    private Call parsePost(String url, Method method, Object[] args) {
        Type[] genericParameterTypes = method.getGenericParameterTypes();//获取方法泛型
        if (genericParameterTypes.length > 0) {
            Object o = new Gson().fromJson((String) args[0], genericParameterTypes[0]);
            Request request = new Request.Builder()
                    .url(url)
                    .post(RequestBody.create(MEDIA_TYPE, o.toString()))
                    .build();
            return mOkHttpClient.newCall(request);
        }
        return null;
    }
}
