package com.assignment.pojo;

import io.github.sskorol.data.FieldName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AboutItem {

//    TC_No,Main_Menu,Sub_Menu,Group,Value,SortBy,SelectOrder

    @FieldName(value = "TC_No")
    private String TC_No;
    @FieldName(value = "Main_Menu")
    private String Main_Menu;
    @FieldName(value = "Sub_Menu")
    private String Sub_Menu;
    @FieldName(value = "Group")
    private String Group;
    @FieldName(value = "Value")
    private String Value;
    @FieldName(value = "SortBy")
    private String SortBy;
    @FieldName(value = "SelectOrder")
    private String SelectOrder;

}
