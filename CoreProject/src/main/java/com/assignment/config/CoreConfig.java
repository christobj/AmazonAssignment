package com.assignment.config;

import com.assignment.config.converter.StringToBrowserConverter;
import com.assignment.enums.BrowserEnum;
import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:${user.dir}/src/test/resources/coreConfig.properties",
})
public interface CoreConfig extends Config {

    @ConverterClass(StringToBrowserConverter.class)
    @DefaultValue("CHROME")
    BrowserEnum browser();
}
