package com.crm.genericUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.crm.objectRepository.HomePage;
import com.crm.objectRepository.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
      public WebDriver driver;
      public HomePage homepage;
      public static WebDriver sdriver;
      /**
       * This annotation is used to open the Database
     * @throws Throwable 
       */
	@BeforeSuite
	public void openDatabase() throws Throwable  {
		FileUtility.intiallizePropertyFile(ConstantPath.PropertyFilePath);
		DatabaseUtility.getMySqlDatabaseConnection(ConstantPath.DatabasePath, 
		FileUtility.fetchDataFromProperty("databaseUserName"),
		FileUtility.fetchDataFromProperty("databasePassword"));
		ExcelUtility.openExcel(ConstantPath.excelpath2);
	}
	
	/**
	 * This annotation is used to open the Browser
	 */
	@BeforeClass
	public void launchBrowser () {
		long timeout=JavaUtility.convertStringToLong(FileUtility.fetchDataFromProperty("timeout"));
		String browser=FileUtility.fetchDataFromProperty("browser");
		
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
			Reporter.log("Browser is not specified properly", true);
		}
		
		WebDriverUtility.launchApplicationWithMaximize(driver, FileUtility.fetchDataFromProperty("url"),
				timeout);
		sdriver=driver;
	}
	/**
	 * This method is used to Login to the Application
	 * @throws Throwable
	 */
	@BeforeMethod
	public void loginToApplication() throws Throwable {
		LoginPage loginpage=new LoginPage(driver);
		loginpage.loginAction(FileUtility.fetchDataFromProperty("userName"),
				FileUtility.fetchDataFromProperty("password"));
		 homepage=new HomePage(driver);
	}

	/**
	 * This method is used to Logout the Application
	 * @throws Throwable
	 */
	@AfterMethod
	public void logoutFromApplication() throws Throwable {
		homepage.adminstatorAction();
		homepage.signoutAction();
		
	}
	/**
	 * This method is used to close the Browser
	 */
	@AfterClass
	public void closeBrowser()
	{
		WebDriverUtility.closeBrowser(driver);
	}
	
	/**
	 * This method is used to close the Database
	 * @throws Throwable 
	 */
	@AfterSuite
	public void CloseDBConnection() throws Throwable {
		DatabaseUtility.closeDatabaseConnection();
		ExcelUtility.closeExcel();
	}
}
