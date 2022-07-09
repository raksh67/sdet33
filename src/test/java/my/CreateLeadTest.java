package my;
//707 leadexcelsheet-->open the browser and enter the url-->login with username and password-->click on leadtab-->click on create lead plusicon-->
//enter mandatory details-->click on save-->click on delete button-->click yes on popup alert-->logout
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.crm.grnericUtility.ConstantPath;
import com.crm.grnericUtility.FileUtility;
import com.crm.grnericUtility.JavaUtility;
import com.crm.grnericUtility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateLeadTest {
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
		//System.out.println(timeout);
		long timeoutLong = Long.parseLong(timeout);

		int randomnumber = JavaUtility.generateRandomNumber(1000);
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

		WebElement user_name = driver.findElement(By.name("user_name"));//username textfield
		WebDriverUtility.sendkeysThroughJS(driver, user_name, userName);
		WebElement user_password = driver.findElement(By.name("user_password"));//password textfield
		WebDriverUtility.sendkeysThroughJS(driver, user_password, password);
		WebElement submitButton = driver.findElement(By.id("submitButton"));//save button
		WebDriverUtility.clickActionThroughJS(driver, submitButton);
		
		driver.findElement(By.linkText("Leads")).click();//leads tab
		driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();//create lead plusicon
		
		driver.findElement(By.name("lastname")).sendKeys("Raksha");//lastnametextfield
		driver.findElement(By.name("company")).sendKeys("Wipro");//company textfield
		driver.findElement(By.xpath("(//input[@value='  Save  '])[1]")).click();//save button
		
		driver.findElement(By.xpath("(//input[@value='Delete'])[1]")).click();//delete button in lead information page
		driver.switchTo().alert().accept();
		
	    driver.findElement(By.xpath("//img[contains(@src,'/user.PNG')]")).click();//administrator icon
	    driver.findElement(By.linkText("Sign Out")).click();//signout
	       
	}
}
