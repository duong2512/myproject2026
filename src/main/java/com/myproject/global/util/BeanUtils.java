package com.myproject.global.util;

import com.myproject.global.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class BeanUtils implements ApplicationContextAware {
    private static final Logger log4j = LoggerFactory.getLogger(BeanUtils.class);
    private static ApplicationContext context;

    public BeanUtils() {
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static void setContext(ApplicationContext context) {
        BeanUtils.context = context;
    }

    public void setApplicationContext(ApplicationContext context) {
        setContext(context);
    }

    public static <T> T getBean(Class<T> beanClass) {
        if (getContext() == null) {
            throw new ResourceNotFoundException(BeanUtils.class.getName(), ApplicationContext.class.getName(), beanClass.getName());
        } else {
            return getContext().getBean(beanClass);
        }
    }

    public static <T> T getBean(Class<T> beanClass, T clazz) {
        if (clazz != null) {
            return clazz;
        } else {
            log4j.info("getBean by class: [{}]", beanClass.getCanonicalName());
            return getBean(beanClass);
        }
    }

    public static <T> T getBean(String beanName) {
        if (getContext() == null) {
            throw new ResourceNotFoundException(BeanUtils.class.getName(), ApplicationContext.class.getName(), beanName);
        } else {
            return (T) getContext().getBean(beanName);
        }
    }

    public static <T> T getBean(String beanName, T clazz) {
        if (clazz != null) {
            return clazz;
        } else {
            log4j.info("getBean by name: [{}]", beanName);
            return getBean(beanName);
        }
    }
}
