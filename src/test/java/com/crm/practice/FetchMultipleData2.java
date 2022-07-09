package com.crm.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchMultipleData2 {

	public static void main(String[] args) throws Throwable {
		FileInputStream fis = new FileInputStream("./src/test/resources/testData3.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Login");
		for(int i=1;i<=sh.getLastRowNum();i++) {
			Row rowCount = sh.getRow(i);
			System.out.println(rowCount);
			for(int j=0;j<rowCount.getLastCellNum();j++)
			{
				System.out.println(rowCount.getLastCellNum());
			//String usernamepassword = sh.getRow(i).getCell(j).toString();
			//String usernamepassword = df.formatCellsh.getRow(i).getCell(j).toString();
            //System.out.println(usernamepassword);
	}

}
	}
}
