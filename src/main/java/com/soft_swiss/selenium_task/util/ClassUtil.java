package com.soft_swiss.selenium_task.util;

public class ClassUtil {

    public static Class<?> getClazz(String clazz){
        try {
            return Class.forName(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
