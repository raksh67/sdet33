package com.crm.practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchMultipleData3 {

	public static void main(String[] args) throws Throwable, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/testData3.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Login");
		for(int i=1;i<sh.getLastRowNum();i++) {
			String username = sh.getRow(i).getCell(0).toString();
			
			if(username.equalsIgnoreCase("username4"))
			{
			String password = sh.getRow(i).getCell(1).toString();
			
System.out.println(password);
	}

}
	}

}
