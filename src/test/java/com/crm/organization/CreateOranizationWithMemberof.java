package com.crm.organization;
//77
import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.grnericUtility.ConstantPath;
import com.crm.grnericUtility.ExcelUtility;
import com.crm.grnericUtility.FileUtility;
import com.crm.grnericUtility.JavaUtility;
import com.crm.grnericUtility.WebDriverUtility;
import com.crm.objectRepository.CreateOrganizationPage;
import com.crm.objectRepository.CreatingNewOrganizationPage;
import com.crm.objectRepository.HomePage;
import com.crm.objectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOranizationWithMemberof {

	public static void main(String[] args) throws Throwable {
		String propertyfilepath = ConstantPath.PropertyFilePath;
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

		int randomnumber = JavaUtility.generateRandomNumber(1000);
		
		//Fetch the data from excel
		//FileInputStream fiss=new FileInputStream(excelpath);
		//Workbook wb = WorkbookFactory.create(fiss);
		ExcelUtility.openExcel(excelpath);
		//String lastname = wb.getSheet(excelSheetName).getRow(16).getCell(1).getStringCellValue();
		//String companyname = wb.getSheet(excelSheetName).getRow(16).getCell(2).getStringCellValue();
		String lastname = ExcelUtility.fetchData(excelSheetName, 16, 1);
		String companyname = ExcelUtility.fetchData(excelSheetName, 16, 2);
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
		//WebElement submitButton = driver.findElement(By.id("submitButton"));//savebutton
		//WebDriverUtility.clickActionThroughJS(driver, submitButton);
		LoginPage login=new LoginPage(driver);
		login.loginAction(userName, password);
		//create organization
		//driver.findElement(By.xpath("//a[@href='index.php?module=Accouts&action=index']")).click();//organizationtab
		HomePage homepage=new HomePage(driver);
		homepage.organizationTabAction();
		//driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();//orgplus icon
		CreateOrganizationPage orgpage=new CreateOrganizationPage(driver);
		orgpage.createOrganizationAction();
		
		CreatingNewOrganizationPage createorg=new CreatingNewOrganizationPage(driver);
		
		driver.findElement(By.name("accountname")).sendKeys("GV Organization");//orgname textfield
		
		driver.findElement(By.xpath("//img[@title='Select']")).click();//memberof plusicon
		Set<String> winIDs = driver.getWindowHandles();

		for(String id:winIDs) {
			driver.switchTo().window(id);
			if(driver.getTitle().contains("Accounts")) {
			break;
		}
		}
		WebElement organization = driver.findElement(By.linkText("pragathi"));//organization name in list
		WebDriverUtility.clickActionThroughJS(driver, organization);
		
		driver.switchTo().alert().accept();

		driver.findElement(By.xpath("//input[@title='Clear']")).click();
		
		driver.findElement(By.xpath("//img[contains(@src,'/user.PNG')]")).click();//administrator icon
	    driver.findElement(By.linkText("Sign Out")).click();//signout
	       
	}

}
