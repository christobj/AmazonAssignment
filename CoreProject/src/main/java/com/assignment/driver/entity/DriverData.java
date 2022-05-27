package com.assignment.driver.entity;

import com.assignment.enums.BrowserEnum;
import com.assignment.enums.ExecutionMode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DriverData {

    private BrowserEnum browserEnum;
    private ExecutionMode executionMode;
}
