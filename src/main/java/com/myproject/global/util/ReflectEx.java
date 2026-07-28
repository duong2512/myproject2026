package com.myproject.global.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myproject.global.annotations.DbColumnMapper;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;

public class ReflectEx {

    private ReflectEx() {
    }

    public static <T extends Annotation> T getClassAnnotationEx(Class<?> clazz, Class<T> annotationClass) {
        return clazz.getAnnotation(annotationClass);
    }

    public static <T> T getClassNewInstanceEx(String className) {
        Class<?> clazz = getClassByNameEx(className);
        return getClassNewInstanceEx(clazz);
    }

    public static <T> T getClassNewInstanceEx(Class<?> clazz) {
        try {
            return (T) clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static Class<?> getClassByNameEx(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Field> getClassFieldsEx(List<Field> fields, Class<?> clazz) {
        if (!clazz.equals(Object.class)) {
            Field[] arrField = getClassDeclaredFieldsEx(clazz);
            if (!CheckEx.getInstance().checkArrayIsEmpty(arrField)) {
                Collections.addAll(fields, arrField);
            }

            getClassFieldsEx(fields, clazz.getSuperclass());
        }
        return fields;
    }

    public static Field[] getClassDeclaredFieldsEx(Object obj) {
        return getClassDeclaredFieldsEx(obj.getClass());
    }

    public static Field[] getClassDeclaredFieldsEx(Class<?> clazz) {
        return clazz.getDeclaredFields();
    }

    public static Field getClassgetDeclaredFieldByName(Class<?> clazz, String fieldName) {
        try {
            try {
                return clazz != null && !clazz.getCanonicalName().equals(Object.class.getCanonicalName()) ? clazz.getDeclaredField(fieldName) : null;
            } catch (NoSuchFieldException var3) {
                return getClassgetDeclaredFieldByName(clazz.getSuperclass(), fieldName);
            }
        } catch (Throwable var4) {
            throw var4;
        }
    }

    public static Field[] getClassFieldsEx(Object obj) {
        return getClassFieldsEx(obj.getClass());
    }

    public static Field[] getClassFieldsEx(Class<?> clazz) {
        return clazz.getFields();
    }

    public static Method[] getClassDeclaredMethodsEx(Class<?> clazz) {
        return clazz.getDeclaredMethods();
    }

    public static Method[] getClassMethodsEx(Class<?> clazz) {
        return clazz.getMethods();
    }

    public static Method getClassMethodEx(Object obj, String name, Class<?>... parameterTypes) {
        return getClassMethodEx(obj.getClass(), name, parameterTypes);
    }

    public static Method getClassMethodEx(Class<?> clazz, String name, Class<?>... parameterTypes) {
        try {
            return clazz.getMethod(name, parameterTypes);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getClassNameEx(Class<?> clazz) {
        return clazz.getName();
    }

    public static boolean isClassAbstractEx(Class<?> clazz) {
        return Modifier.isAbstract(clazz.getModifiers());
    }

    public static boolean isClassFinalEx(Class<?> clazz) {
        return Modifier.isFinal(clazz.getModifiers());
    }

    public static <T> T getProperty(Object obj, String property, T defaultValue) {
        T returnValue = (T) getProperty(obj, property);
        if (returnValue == null) {
            returnValue = defaultValue;
        }

        return returnValue;
    }

    public static Object getProperty(Object obj, String property) {
        Object returnValue = null;

        try {
            returnValue = getFieldValueEx(obj, property);
            return returnValue;
        } catch (Exception var4) {
            throw new RuntimeException(var4);
        }
    }

    public static String getFieldValueToStringEx(Object obj, String fieldName) {
        try {
            return getFieldValueToStringEx(obj, obj.getClass().getDeclaredField(fieldName));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getFieldValueToStringEx(Object obj, Field field) {
        Object objRet = getFieldValueEx(obj, field);
        return objRet == null ? "" : objRet.toString();
    }

    public static <T> T getFieldValueEx(Object obj, String fieldName) {
        try {
            return getFieldValueEx(obj, obj.getClass().getDeclaredField(fieldName));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T getFieldValueEx(Object obj, Field field) {
        try {
            setFieldAccessible(field);
            return (T) field.get(obj);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getFieldNameEx(Field field) {
        return field.getName();
    }

    public static Class<?> getFieldTypeEx(Field field) {
        return field.getType();
    }

    public static String getFieldTypeCanonicalNameEx(Field field) {
        return getFieldTypeEx(field).getCanonicalName();
    }

    public static <T extends Annotation> T getFieldAnnotationEx(Field field, Class<T> annotationClass) {
        setFieldAccessible(field);
        return field.getAnnotation(annotationClass);
    }

    public static void setFieldValueEx(Object obj, Field field, Object fieldValue) {
        try {
            setFieldAccessible(field);
            field.set(obj, fieldValue);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setFieldAccessible(Field field) {
        field.setAccessible(true);
    }

    public static Class<?> getMethodDeclaringClassEx(Method method) {
        return method.getDeclaringClass();
    }

    public static int getMethodParameterCountEx(Method method) {
        return method.getParameterCount();
    }

    public static Class<?>[] getMethodParameterTypesEx(Method method) {
        return method.getParameterTypes();
    }

    public static String getMethodNameEx(Method method) {
        return method.getName();
    }

    public static Object invokeMethodEx(Object obj, String methodName, Object... args) {
        return invokeMethodEx(obj, getClassMethodEx(obj, methodName), args);
    }

    public static Object invokeMethodEx(Object obj, Method method, Object... args) {
        try {
            return method == null ? null : method.invoke(obj, args);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getDbColumnMapper(Field field) {
        String columnName = getFieldAnnotationEx(field, DbColumnMapper.class) != null ? ((DbColumnMapper) getFieldAnnotationEx(field, DbColumnMapper.class)).value() : "";
        columnName = StringUtils.isEmpty(columnName) && getFieldAnnotationEx(field, Column.class) != null ? ((Column) getFieldAnnotationEx(field, Column.class)).name() : columnName;
        columnName = StringUtils.isEmpty(columnName) && getFieldAnnotationEx(field, JsonProperty.class) != null ? ((JsonProperty) getFieldAnnotationEx(field, JsonProperty.class)).value() : columnName;
        columnName = StringUtils.isEmpty(columnName) ? field.getName() : columnName;
        return columnName;
    }
}
