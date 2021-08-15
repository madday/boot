package com.appz9001.boot.util;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public final class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
    }

    public static <T> T getBean(Class<T> clazz) {
        return SpringUtil.applicationContext.getBean(clazz);
    }

    public static Object getBean(String name) {
        return SpringUtil.applicationContext.getBean(name);
    }

    public static String getProperty(String key) {
        return SpringUtil.applicationContext.getEnvironment().getProperty(key);
    }
}
