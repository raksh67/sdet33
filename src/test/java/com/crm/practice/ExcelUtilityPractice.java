package com.crm.practice;

import com.crm.grnericUtility.ExcelUtility;

public class ExcelUtilityPractice {

	public static void main(String[] args) throws Throwable {
//excel1
		ExcelUtility.openExcel("./src/test/resources/testData2.xlsx");
		ExcelUtility.writeDataInNewRow("./src/test/resources/testData2.xlsx", "SDET33", 19, 1, "Status");
		String orgName = ExcelUtility.fetchData("SDET33", 8, 1);
		System.out.println(orgName);
		ExcelUtility.writeDataInNewRow("./src/test/resources/testData2.xlsx", "SDET33", 20, 1, "PASS");
	   String lastName = ExcelUtility.fetchData("SDET33", 8, 2);
	   System.out.println(lastName);
	   
	   //excel2
	   ExcelUtility.openExcel("./src/test/resources/testData2.xlsx");
	   ExcelUtility.writeDataInNewRow("./src/test/resources/testData2.xlsx", "SDET33", 19, 1, "Status");
	   String orgName1 = ExcelUtility.fetchData("SDET33", 8, 1);
	   System.out.println(orgName1);
	   ExcelUtility.writeDataInNewRow("./src/test/resources/testData2.xlsx", "SDET33", 20, 1, "pass");
	   String lastName1 = ExcelUtility.fetchData("SDET33", 8, 2);
	   System.out.println(lastName1);
	   ExcelUtility.closeExcel();
	   
	}

}
