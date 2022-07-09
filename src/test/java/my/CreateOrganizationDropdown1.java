package my;

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

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationDropdown1 {

	public static void main(String[] args) throws Throwable  {
		//step1: fetch data from external file and store in variable
		
		FileInputStream fis = new FileInputStream("./src/test/resources/commonData.properties");
		Properties properties=new Properties();
		properties.load(fis);
		String url = properties.getProperty("url");
		String userName = properties.getProperty("userName");
		String password = properties.getProperty("password");
		String browser = properties.getProperty("browser");

		String timeouts = properties.getProperty("timeouts");
		String excelpath = properties.getProperty("excelpath");
		String excelSheetName = properties.getProperty("excelSheetName");
		long timeoutLong = Long.parseLong(timeouts);

		//generate random number
		Random ran = new Random();
		int randomNumber = ran.nextInt(1000);
		
		//Fetch the data from excel
		
		FileInputStream fiss=new FileInputStream(excelpath);
		Workbook wb = WorkbookFactory.create(fiss);
		String orgName = wb.getSheet(excelSheetName).getRow(12).getCell(1).getStringCellValue();
		String industry = wb.getSheet(excelSheetName).getRow(12).getCell(2).getStringCellValue();
		String type = wb.getSheet(excelSheetName).getRow(12).getCell(3).getStringCellValue();
		
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
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(timeoutLong, TimeUnit.SECONDS);

		//step4:Open the url and navigate to application
		driver.get(url);

		//step5:login to the application
		driver.findElement(By.name("user_name")).sendKeys(userName);//username textfield
		driver.findElement(By.name("user_password")).sendKeys(password);//username textfield
		driver.findElement(By.id("submitButton")).click();//login button
		
		//create organization
		driver.findElement(By.xpath("//a[@href='index.php?module=Accounts&action=index']")).click();//organization tab
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();//createorganizationplusicon
		String expOrgName = orgName+randomNumber;
		driver.findElement(By.name("accountname")).sendKeys(expOrgName);//organizationname textfield
		
		WebElement industryDropDown = driver.findElement(By.name("industry"));//industry dropdown
		Select sIndustry = new Select(industryDropDown);
		sIndustry.selectByValue(industry);
		WebElement typeDropDown = driver.findElement(By.name("accounttype"));//type dropdown
		Select sType=new Select(typeDropDown);
		sType.selectByValue(type);
		//driver.findElement(By.cssSelector("//input[title='save [Alt+S]']")).click();
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();//save Button
		
		//Step 7:verify the contact
		String actOrgName = driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']")).getText();//organization name in organization informationpage
		String actIndustry = driver.findElement(By.xpath("//span[@id='dtlview_Industry']/font")).getText();// industr in organization informationpage
		String actType = driver.findElement(By.xpath("//span[@id='dtlview_Type']/font")).getText();//type in organization informationpage
		
		if(expOrgName.equalsIgnoreCase(actOrgName)&&actIndustry.equalsIgnoreCase(industry)&&actType.equalsIgnoreCase(type))
		{
			System.out.println("Organization created successfully with Industry and Type");
			wb.getSheet(excelSheetName).getRow(11).createCell(4).setCellValue("pass");
			FileOutputStream fos = new FileOutputStream(excelpath);
			wb.write(fos);
			
		}
		Thread.sleep(5000);
		
		//step8:SignOut/logout from application
	   driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();//administratorclick
	   driver.findElement(By.linkText("Sign Out")).click();//signout link
	   
	   //step 9 close the application
	   wb.close();
	   driver.quit();
		
		}
}