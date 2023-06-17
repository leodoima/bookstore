package com.facade;

import java.lang.reflect.Field;

public class ReflectionFacade {
    public static Object mutableObjects(Object target, Object source) {

        for (Field field : target.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            try {
                if (field.get(source) != null) {
                    field.set(target, field.get(source));
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return target;
    }
}
