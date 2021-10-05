package com.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class TestUtil {
	public static long PAGE_LODE_TIMEOUT=60;
	public static long IMPLICIT_WAIT_TIMEOUT=60;

	static String EXCEL_DATA_PATH="D:\\IRFAN---\\java program\\Xtreme-EcommerceAutomation_FrameWork\\src\\main\\java\\com\\qa\\testdata\\Xtreme-TestData.xlsx";
	static Workbook book;
	static Sheet sheet;
	
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file= null;
		try {
			file= new FileInputStream(EXCEL_DATA_PATH);
		} catch (FileNotFoundException e) {
			System.out.println("Excel sheet not present,......pls check Location");
		}try {
			book=WorkbookFactory.create(file);
		} catch (Exception e) {
			// TODO: handle exception
		}
		sheet= book.getSheet(sheetName);
		Object[][] data= new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(int i=0; i<sheet.getLastRowNum();i++) {
			for(int k=0; k<sheet.getRow(0).getLastCellNum();k++) {
				data[i][k]=sheet.getRow(1+i).getCell(k).toString();
				
			}
		}
		return data;
		
		
	}
}
