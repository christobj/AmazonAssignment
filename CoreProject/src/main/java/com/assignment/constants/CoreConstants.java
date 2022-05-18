package com.assignment.constants;

import lombok.Getter;

public final class CoreConstants {

    private CoreConstants() {}

    @Getter
    private static final String CONFIG_PROPERTY_PATH = "../CoreProject/src/test/resources/properties/coreConfig.properties";

    @Getter
    private static final String PATH_TO_EXTENT_REPORTS = "../CoreProject/testReports/";

}
