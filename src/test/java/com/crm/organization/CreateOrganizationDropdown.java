package com.crm.organization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.crm.grnericUtility.ConstantPath;
import com.crm.grnericUtility.ExcelUtility;
import com.crm.grnericUtility.FileUtility;
import com.crm.grnericUtility.WebDriverUtility;
import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationDropdown {

	public static void main(String[] args) throws Throwable  {
		//step1: fetch data from external file and store in variable
		
		//FileInputStream fis = new FileInputStream("./src/test/resources/commonData.properties");
		String propertyfile = ConstantPath.PropertyFilePath;
		//Properties properties=new Properties();
		//properties.load(fis);
		FileUtility.intiallizePropertyFile(propertyfile);
		//String url = properties.getProperty("url");
		String url = FileUtility.fetchDataFromProperty("url");
		//String userName = properties.getProperty("userName");
		String userName = FileUtility.fetchDataFromProperty("userName");
		//String password = properties.getProperty("password");
		String password = FileUtility.fetchDataFromProperty("password");
		//String browser = properties.getProperty("browser");
		String browser = FileUtility.fetchDataFromProperty("browser");
		//String timeouts = properties.getProperty("timeouts");
		String timeouts = FileUtility.fetchDataFromProperty("timeouts");
		//String excelpath = properties.getProperty("excelpath");
		String excelpath = FileUtility.fetchDataFromProperty("excelpath");
		//String excelSheetName = properties.getProperty("excelSheetName");
		String excelSheetName = FileUtility.fetchDataFromProperty("excelSheetName");
		long timeoutLong = Long.parseLong(timeouts);

		//generate random number
		Random ran = new Random();
		int randomNumber = ran.nextInt(1000);
		
		//Fetch the data from excel
		
		ExcelUtility.openExcel(excelpath);
		String orgName = ExcelUtility.fetchData(excelSheetName, 12, 1);
		String industry = ExcelUtility.fetchData(excelSheetName, 12, 2);
		String type = ExcelUtility.fetchData(excelSheetName, 13, 3);
		
		//step 2: launch the browser
		WebDriver driver=null;

		if(browser.equalsIgnoreCase("Chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else
		{
			System.out.println("Browser is not Specified Properly");
		}

		//step 3:Do basic config for browser
		//driver.manage().window().maximize();
		WebDriverUtility.maximizeBrowser(driver);
		//driver.manage().timeouts().implicitlyWait(timeoutLong, TimeUnit.SECONDS);
		WebDriverUtility.waitforPageLoad(driver, timeoutLong);

		//step4:Open the url and navigate to application
		//driver.get(url);
		WebDriverUtility.openApplicationThroughJS(driver, url);

		//step5:login to the application
		WebElement user_name = driver.findElement(By.name("user_name"));
		user_name.sendKeys(userName);
		WebDriverUtility.sendkeysThroughJS(driver, null, type);
		WebElement user_password = driver.findElement(By.name("user_password"));
		user_password.sendKeys(password);
		WebElement submitButton = driver.findElement(By.id("submitButton"));
		submitButton.click();
		
		//create organization
		driver.findElement(By.xpath("//a[@href='index.php?module=Accouts&action=index']")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		String expOrgName = orgName+randomNumber;
		driver.findElement(By.name("accountname")).sendKeys(expOrgName);
		
		WebElement industryDropDown = driver.findElement(By.name("industry"));
		//Select sIndustry = new Select(industryDropDown);
		//sIndustry.selectByValue(industry);
		WebDriverUtility.select(industryDropDown, expOrgName);
		WebElement typeDropDown = driver.findElement(By.name("accounttype"));
		//Select sType=new Select(typeDropDown);
		//sType.selectByValue(type);
		WebDriverUtility.select(expOrgName, typeDropDown);
		driver.findElement(By.cssSelector("//input[title='save [Alt+S]']")).click();
	
		//Step 7:verify the contact
		String actOrgName = driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']")).getText();
		String actIndustry = driver.findElement(By.xpath("//span[@id='dtlview_Industry']/font")).getText();
		String actType = driver.findElement(By.xpath("//span[@id='dtlview_Type']/font")).getText();
		
		if(expOrgName.equalsIgnoreCase(actOrgName)&&actIndustry.equalsIgnoreCase(industry)&&actType.equalsIgnoreCase(type))
		{
			System.out.println("Organization created successfully with Industry and Type");
			//wb.getSheet(excelSheetName).getRow(11).createCell(4).setCellValue("pass");
			//FileOutputStream fos = new FileOutputStream(excelpath);
			//wb.write(fos);
			
			ExcelUtility.writeDataInExistingRow(excelpath, excelSheetName, 11, 4, "pass");
			
		}
		Thread.sleep(5000);
		
		//step8:SignOut/logout from application
	   driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
	   driver.findElement(By.linkText("Sign Out")).click();
	   
	   //step 9 close the application
	   ExcelUtility.closeExcel();
	   driver.quit();
		
		}
}