package com.crm.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataIntoExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
FileInputStream fis = new FileInputStream("./src/test/resources/testData2.xlsx");
Workbook wb = WorkbookFactory.create(fis);
Sheet sh = wb.getSheet("sdet33");
Row row = sh.getRow(1);
Cell cell = row.createCell(1);
cell.setCellValue("pass");
 FileOutputStream fos = new FileOutputStream("./src/test/resources/testData2.xlsx");
 wb.write(fos);
 wb.close();
 System.out.println("Data is stored in excel");
	}

}
