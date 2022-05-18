package com.assignment.enums;

public enum BrowserEnum {

    CHROME("chrome"),
    EDGE("edge");

    private String browser;

    public String getBrowser() {
        return this.browser;
    }

    BrowserEnum(String browser) {
        this.browser = browser;
    }
}
