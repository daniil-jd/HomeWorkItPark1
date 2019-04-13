package ru.itpark;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import ru.itpark.util.Cached;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CachedAnnotationBPP implements BeanPostProcessor {
    private final Map<Object, Object> cache = new HashMap<>();
    private final Map<String, Class> classCache = new HashMap<>();
    private boolean flag = false;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //если класс содержит аннотацию, сохранить его

        for (Method method : bean.getClass().getMethods()) {
            if (method.isAnnotationPresent(Cached.class)) {
                classCache.put(beanName, bean.getClass());
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //"улучшатель" класса, реализация от спринга (есть еще JDKProxy)
        var enhancer = new Enhancer();
        //устанавливаем классу улучшателю родителя
        enhancer.setSuperclass(classCache.get(beanName));
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                if (method.isAnnotationPresent(Cached.class)) {
                    if (cache.containsKey(Arrays.hashCode(objects))) {
                        System.out.println("--Cached annotated method intercepted.");
                        return cache.get(Arrays.hashCode(objects));
                    }

                    var result = methodProxy.invoke(bean, objects);
                    cache.put(Arrays.hashCode(objects), result);

                    return result;
                }
                flag = true;
                return null;
            }
        });

        //если среди классов с аннотацией нет данного, то не подменяем вызов метода
        if (!classCache.containsKey(beanName) && flag) {
            return bean;
        }

        return enhancer.create();
    }
}
