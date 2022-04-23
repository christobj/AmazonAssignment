package com.assignment.constants;

import lombok.Getter;

public final class CoreConstants {

    private CoreConstants() {}

    @Getter
    private static final String CONFIGPROPERTYPATH = "../CoreProject/src/test/resources/properties/coreConfig.properties";

    @Getter
    private static final String PATHTOEXTENTREPORTS = "../CoreProject/testReports/";

}
