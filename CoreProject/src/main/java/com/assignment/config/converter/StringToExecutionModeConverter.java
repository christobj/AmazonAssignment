package com.assignment.config.converter;

import com.assignment.enums.ExecutionMode;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;
import java.util.Map;

public class StringToExecutionModeConverter implements Converter<ExecutionMode> {

    @Override
    public ExecutionMode convert(Method method, String executionMode) {
        Map<String, ExecutionMode> executionModeMap = Map.of("LOCAL", ExecutionMode.LOCAL,
                "REMOTE", ExecutionMode.REMOTE);
        return executionModeMap.get(executionMode.toUpperCase());
    }
}
