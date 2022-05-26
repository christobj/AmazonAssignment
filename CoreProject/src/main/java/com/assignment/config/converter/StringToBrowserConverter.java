package com.assignment.config.converter;

import com.assignment.enums.BrowserEnum;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;
import java.util.Map;

public class StringToBrowserConverter implements Converter<BrowserEnum> {

    @Override
    public BrowserEnum convert(Method method, String browser) {
        Map<String, BrowserEnum> browserMap = Map.of("CHROME", BrowserEnum.CHROME,
                "EDGE", BrowserEnum.EDGE);
        return browserMap.getOrDefault(browser.toUpperCase(), BrowserEnum.CHROME);
    }
}
