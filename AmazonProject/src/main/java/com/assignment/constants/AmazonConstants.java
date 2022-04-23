package com.assignment.constants;

import lombok.Getter;

public final class AmazonConstants {

    private AmazonConstants() {}

    @Getter
    private static final String PATHTOAMAZONPROPERTY = "../AmazonProject/src/test/resources/properties/amazon.properties";

    @Getter
    private static final String PATHTOTESTDATA = "../AmazonProject/src/test/java/com/assignment/testData/CheckAboutTheProduct.csv";
}
