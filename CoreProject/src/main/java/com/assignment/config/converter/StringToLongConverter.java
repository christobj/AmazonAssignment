package com.assignment.config.converter;

import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;

public class StringToLongConverter implements Converter<Long> {

    @Override
    public Long convert(Method method, String timeout) {
        return Long.valueOf(timeout);
    }
}
