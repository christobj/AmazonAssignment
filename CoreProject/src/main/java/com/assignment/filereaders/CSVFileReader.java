package com.assignment.filereaders;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.testng.Assert;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public final class CSVFileReader {

    private CSVFileReader() {}

    public static List<String> getColumnData(String file, String columnName) {
        List<String> columnData = new ArrayList<>();
        int columnNum = 0;
        boolean columnFound = false;
        int headerRow = 0;
        try {
            FileReader fileReader = new FileReader(file);
            CSVReader csvReader = new CSVReaderBuilder(fileReader).build();
            List<String[]> allData = csvReader.readAll();
            String[] attributes = allData.get(headerRow);
            int noOfColumns = attributes.length;
            for (int i = 0 ; i < noOfColumns ; i++) {
                if (attributes[i].contentEquals(columnName)) {
                    columnNum = i;
                    columnFound = true;
                    break;
                }
            }
            Assert.assertTrue(columnFound, "The Column name " +columnName + " is not found");
            int noOfRows = allData.size();
            for (int i = 1; i < noOfRows ; i++) {
                String[] rowData = allData.get(i);
                columnData.add(rowData[columnNum]);
            }
        } catch (Exception e) {
            Assert.assertTrue(false, "Exception happened reading the csv file");
        }
        return columnData;
    }
}
