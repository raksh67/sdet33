package com.crm.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchDataFromExcelTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
//STEP1: we should convert the physical file into java readable object
		FileInputStream fis = new FileInputStream("./src/test/resources/testData.xlsx");
		
		//STEP2:open the excel file using WorkbookFactory class and create(--)
		 Workbook wb = WorkbookFactory.create(fis);
		 
		 //STEP3:we should get the control of particular sheet by using getsheet(--)
		 Sheet sh = wb.getSheet("SDET33");

		 //STEP4:we should get the control of particular row by using getrow(--)
		 Row row = sh.getRow(1);
		 
		 //STEP5:we should get the control of particular cell by using getcell(--)
		Cell cell = row.getCell(0);
		
		//Step6:read /fetch the data by using getStringcellvalue(),toString()
		String data = cell.getStringCellValue();
		
		System.out.println(data);
		//STEP7:close the workbook
		wb.close();
		 
		
	}

}
						