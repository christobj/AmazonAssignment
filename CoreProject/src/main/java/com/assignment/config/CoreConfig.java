package com.assignment.config;

import com.assignment.config.converter.StringToBrowserConverter;
import com.assignment.config.converter.StringToExecutionModeConverter;
import com.assignment.config.converter.StringToLongConverter;
import com.assignment.config.converter.StringToURLConverter;
import com.assignment.enums.BrowserEnum;
import com.assignment.enums.ExecutionMode;
import org.aeonbits.owner.Config;
import org.checkerframework.checker.units.qual.K;

import java.net.URL;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:${user.dir}/src/test/resources/coreConfig.properties",
})
public interface CoreConfig extends Config {

    @ConverterClass(StringToBrowserConverter.class)
    @DefaultValue("CHROME")
    @Key("BROWSER")
    BrowserEnum browser();

    @ConverterClass(StringToLongConverter.class)
    @Key("WEBDRIVERWAIT")
    Long webDriverWait();

    @ConverterClass(StringToLongConverter.class)
    @Key("IMPLICITWAIT")
    Long implicitWait();

    @DefaultValue("LOCAL")
    @Key("EXECUTIONMODE")
    @ConverterClass(StringToExecutionModeConverter.class)
    ExecutionMode executionMode();

    @Key("REMOTEURL")
    @ConverterClass(StringToURLConverter.class)
    URL remoteURL();
}
