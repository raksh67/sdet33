package my;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
import com.crm.grnericUtility.WebDriverUtility;
import com.crm.objectRepository.CreateContactPage;
import com.crm.objectRepository.HomePage;
import com.crm.objectRepository.LoginPage;
import com.crm.objectRepository.LogoutPage;
import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactTest {

	private static final String LastName = null;

	public static void main(String[] args) throws SQLException, Throwable {
    
    	//step1: fetch data from external file and store in variable
		
    	//FileInputStream fis = new FileInputStream("./src/test/resources/commonData.properties");
		String propertyfilepath = ConstantPath.PropertyFilePath;
		String databasepath = ConstantPath.DatabasePath;
    	//Properties properties=new Properties();
    	//properties.load(fis);
		FileUtility.intiallizePropertyFile(propertyfilepath);
    	//String url = properties.getProperty("url");
    	String url = FileUtility.fetchDataFromProperty("url");
    	//String userName = properties.getProperty("userName");
    	String userName = FileUtility.fetchDataFromProperty("userName");
    	//String password = properties.getProperty("password");
    	String password = FileUtility.fetchDataFromProperty("password");
    	//String browser = properties.getProperty("browser");
    	String browser = FileUtility.fetchDataFromProperty("browser");
    	//String timeouts = properties.getProperty("timeouts");
    	String timeout = FileUtility.fetchDataFromProperty("timeout");
    	//String excelpath = properties.getProperty("excelpath");
    	String excelpath = FileUtility.fetchDataFromProperty("excelpath");
		//String excelSheetName = properties.getProperty("excelSheetName");
		String excelSheetName = FileUtility.fetchDataFromProperty("excelSheetName");

    	long timeoutLong = Long.parseLong(timeout);
    	
    	//generate random number
    			Random ran = new Random();
    			int randomNumber = ran.nextInt(1000);
    			
    			//Fetch the data from excel
    			//FileInputStream fiss = new FileInputStream("./src/test/resources/testData2.xlsx");
    			//Workbook wb = WorkbookFactory.create(fiss);
    			ExcelUtility.openExcel(excelpath);
    			//Sheet sh = wb.getSheet(excelSheetName);
    			//Row row = sh.getRow(3);
    			//Cell cell = row.getCell(1);
    			//String LastName = cell.getStringCellValue();
    			ExcelUtility.writeDataInNewRow(excelpath, excelSheetName, 3, 1, LastName);
    			
    			//step 4: launch the browser
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
    			driver.get(url);
    			LoginPage login=new LoginPage(driver);
    			//step5:login to the application
    			//WebElement user_name = driver.findElement(By.name("user_name"));
    			login.loginAction(userName, password);
    			
    			//user_name.sendKeys(userName);
    			//WebDriverUtility.sendkeysThroughJS(driver, user_name, userName);
    			
    			 //WebElement user_password = driver.findElement(By.name("user_password"));
    			 //WebDriverUtility.sendkeysThroughJS(driver, user_password, password);
    			 //user_password.sendKeys(password);
    			 //WebElement submitButton = driver.findElement(By.id("submitButton"));
    			 //submitButton.click();
    			 //WebDriverUtility.clickActionThroughJS(driver, submitButton);
    			
    			//create contact
    			HomePage homepage=new HomePage(driver);
    			// WebElement Contacts = driver.findElement(By.linkText("Contacts"));
    			// Contacts.click();
    			homepage.contactClickAction();
    			//driver.findElement(By.cssSelector("img[alt='Create Contact...']")).click();
    			
    			//String expLastName = LastName+randomNumber;
    		
    			//driver.findElement(By.name("lastname")).sendKeys(expLastName);
    			CreateContactPage createcon=new CreateContactPage(driver);
    			createcon.enterlastname(LastName);
    			//driver.findElement(By.cssSelector("input[type='submit']")).click();
    			createcon.ContactSaveAction();
    			//String actualContactName = driver.findElement(By.id("dtlview_Last Name")).getText();
    			String actconame = createcon.verifyContactName();
    			//step9:verify the contact

    			if(LastName.equalsIgnoreCase(actconame))
    			{
    			System.out.println("Last Name succesfully created in Contact model");
    			}

    			LogoutPage logout=new LogoutPage(driver);
    			logout.logoutAction();
    			//driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
    			
    			//driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

    			//close the workbook
    			//wb.close();
    			//driver.quit();
    			ExcelUtility.closeExcel();
    }
	}
	
    			