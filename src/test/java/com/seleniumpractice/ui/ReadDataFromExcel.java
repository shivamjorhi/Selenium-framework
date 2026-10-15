package com.seleniumpractice.ui;

import java.io.FileInputStream;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcel {
    public static Object[][] readExcelData(String filePath, String sheetName) {
        try (FileInputStream file = new FileInputStream(filePath);
             Workbook wb = WorkbookFactory.create(file)) {
            Sheet sheet = wb.getSheet(sheetName);
            int rowCount = sheet.getLastRowNum()+1;
            int colCount = sheet.getRow(0).getLastCellNum();

            Object[][] data = new Object[rowCount][colCount];
            DataFormatter formatter = new DataFormatter();
            for(int i=0; i<rowCount; i++) {
                for(int j=0; j<colCount; j++) {
                    data[i][j] = formatter.formatCellValue(sheet.getRow(i).getCell(j));
                }
            }
            return data;
        }
        catch (Exception e) {
           throw new RuntimeException("Failed to read Excel file", e);
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(ReadDataFromExcel.readExcelData("C:\\Users\\Shivam Johri\\Downloads\\test.xlsx", "Sheet1")));
    }
}
