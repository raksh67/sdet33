package com.crm.practice;

import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.grnericUtility.ExcelUtility;

public class PracticeTest3 {

	@Test(dataProvider="dataProvider_excel")
	public void test1(String username,String password)
	{
		System.out.println(username+"======"+password);
	}
	
	@DataProvider
	public Object[][] dataProvider_excel() throws Throwable{
		ExcelUtility.openExcel("./src/test/resources/testData2.xlsx");
		Object[][] arr = ExcelUtility.fetchMultipleData("sheet1");
		return arr;
	}
	
	@Test(priority = 3)
	public void script1()
	{
		System.out.println("script1");
	}
	
	@Test
	public void test2()
	{
		System.out.println("test2");
	}
	
	@Test(enabled=false)
	public void xscript()
	{
		System.out.println("xscript");
	}
	
	@Test(priority = 2)
	public void zscript()
	{
		System.out.println("zscript");
		Assert.fail();
		
	}
}
