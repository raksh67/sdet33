package com.crm.genericUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * This c
 * @author r pc
 *
 */
public class ExcelUtility {
static Workbook wb;
/**
 * This Method is used to fetch the single data from excel
 * @param sheetName
 * @param rowNumber
 * @param cellNumber
 * @return
 */
	 public static String fetchData(String sheetName,int rowNumber,int cellNumber) throws  Throwable{
	Sheet sh=wb.getSheet(sheetName);
	String data = sh.getRow(rowNumber) .getCell(cellNumber).getStringCellValue();
    return data;
	} 
	 /**
	  * This Method is used to create/write the data to new row into specified excel sheet
	  * @param path
	  * @param sheetName
	  * @param rowNumber
	  * @param cellNumber
	  */
	 public static void writeDataInNewRow(String path,String sheetName,int rowNumber,int cellNumber,String
	 data)throws Throwable{
		 Sheet sh=wb.getSheet(sheetName);
		sh.createRow(rowNumber).createCell(cellNumber).setCellValue(data);
		FileOutputStream fosExcel = new FileOutputStream(path);
		wb.write(fosExcel);
		System.out.println("Data is written successfully");
		 
	 }
/**
 * This is used to create/write the data to existing row into specified excel sheet
 * @param path
 * @param sheetName
 * @param rowNumber
 * @param cellNumber
 * @param data
 * @throws Throwable
 */
	 public static void writeDataInExistingRow(String path,String sheetName,int rowNumber,int cellNumber,
			 String data)throws Throwable{
		 
		 Sheet sh=wb.getSheet(sheetName);
		 sh.getRow(rowNumber).createCell(cellNumber).setCellValue(data);
		 FileOutputStream fosExcel = new FileOutputStream(path);
		 wb.write(fosExcel);
		 System.out.println("Data is written successfully");
	 }
	 /**
	  * This method is used to open the excel File 
	  * @param path
	  * @throws Throwable
	  */
	 public static void openExcel(String path)throws Throwable{
		 FileInputStream fisExcel = new FileInputStream(path);
		 wb=WorkbookFactory.create(fisExcel);
		 System.out.println("Excel Open successfull");
		 
	 }
	 /**
	  * This method is used to close the Excel File
	  * @throws Throwable
	  */
	 public static void closeExcel() throws Throwable{
		 wb.close();
		 System.out.println("Excel closed Successfully");
	 }
	 
	 public static Object[][] fetchMultipleData(String sheetName) throws Throwable{
	 Sheet sh=wb.getSheet(sheetName);
	 Object[][] arr = new Object[sh.getLastRowNum()][sh.getRow(0).getLastCellNum()];
	 for(int i=0;i<sh.getLastRowNum();i++)
	 {for(int j=0;j<sh.getRow(0).getLastCellNum();j++)
	 {
		 arr[i][j]=sh.getRow(i+1).getCell(j).toString();
	 }
	 }
	 return arr;
	 }
}