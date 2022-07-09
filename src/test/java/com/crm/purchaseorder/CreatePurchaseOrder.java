package com.crm.purchaseorder;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.crm.grnericUtility.ConstantPath;
import com.crm.grnericUtility.ExcelUtility;
import com.crm.grnericUtility.FileUtility;
import com.crm.grnericUtility.JavaUtility;
import com.crm.grnericUtility.WebDriverUtility;
import com.crm.objectRepository.HomePage;
import com.crm.objectRepository.LoginPage;
import com.crm.objectRepository.CreatePurchaseOrderPage;
import com.crm.objectRepository.CreatingNewPurchaseOrderPage;

import io.github.bonigarcia.wdm.WebDriverManager;
//5
public class CreatePurchaseOrder {

	public static void main(String[] args) throws Throwable {
		String propertyfilepath = ConstantPath.PropertyFilePath;
		System.out.println(propertyfilepath);

		FileUtility.intiallizePropertyFile(propertyfilepath);
		String url = FileUtility.fetchDataFromProperty("url");
		String userName = FileUtility.fetchDataFromProperty("userName");
		String password = FileUtility.fetchDataFromProperty("password");
		String browser = FileUtility.fetchDataFromProperty("browser");

		String excelSheetName = FileUtility.fetchDataFromProperty("excelSheetName");
		String excelpath = ConstantPath.excelpath2;
		String timeout = FileUtility.fetchDataFromProperty("timeout");
		System.out.println(timeout);
		long timeoutLong = Long.parseLong(timeout);

		//int randomnumber = JavaUtility.generateRandomNumber(1000);
		
		//Fetch the data from excel
		//FileInputStream fiss=new FileInputStream(excelpath);
		//Workbook wb = WorkbookFactory.create(fiss);
		ExcelUtility.openExcel(excelpath);
		//String subjectname = wb.getSheet(excelSheetName).getRow(22).getCell(1).getStringCellValue();
		String subjectname = ExcelUtility.fetchData(excelSheetName, 22, 1);
		
		//step 2: launch the browser
		WebDriver driver=null;

		if(browser.equalsIgnoreCase("Chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else
		{
			System.out.println("Browser is not Specified Properly");

		}

		WebDriverUtility.maximizeBrowser(driver);
		WebDriverUtility.waitforPageLoad(driver, timeoutLong);
		WebDriverUtility.launchApplicationWithMaximize(driver, url, timeoutLong);

		//WebElement user_name = driver.findElement(By.name("user_name"));//username textfield
		//WebDriverUtility.sendkeysThroughJS(driver, user_name, userName);
		//WebElement user_password = driver.findElement(By.name("user_password"));//password textfield
		//WebDriverUtility.sendkeysThroughJS(driver, user_password, password);
		//WebElement submitButton = driver.findElement(By.id("submitButton"));//save button
		//WebDriverUtility.clickActionThroughJS(driver, submitButton);
		
		LoginPage loginpage=new LoginPage(driver);
		loginpage.loginAction(userName, password);
		
		//driver.findElement(By.linkText("More")).click();
		HomePage homepage=new HomePage(driver);
		homepage.moreDropdwnAction();
		//WebElement purchaseodr = driver.findElement(By.name("Purchase Order"));
		//purchaseodr.click();
		homepage.pruchaseOrderAction();
	    //driver.findElement(By.xpath("//img[@title='Create Purchase Order...']")).click();
		CreatePurchaseOrderPage prorder=new CreatePurchaseOrderPage(driver);
		prorder.createpurchaseAction();
		//driver.findElement(By.name("subject")).sendKeys(subjectname);
		CreatingNewPurchaseOrderPage newpurchase=new CreatingNewPurchaseOrderPage(driver);
		newpurchase.entersubjectname(subjectname);
		//driver.findElement(By.xpath("(//input[@value='  Save  '])[1]")).click();
		newpurchase.savepurchaseAction();
		
		homepage.logoutAction();
		ExcelUtility.closeExcel();
	}

}
