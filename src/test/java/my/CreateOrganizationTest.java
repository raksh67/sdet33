package my;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
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
import org.openqa.selenium.interactions.Actions;

import com.crm.grnericUtility.ConstantPath;
import com.crm.grnericUtility.ExcelUtility;
import com.crm.grnericUtility.FileUtility;
import com.crm.grnericUtility.WebDriverUtility;
import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.xdevapi.Statement;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationTest {

	public static void main(String[] args) throws Throwable {
		Connection connection=null;
		WebDriver driver=null;
	
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
		//String timeout = properties.getProperty("timeout");
		String timeout = FileUtility.fetchDataFromProperty("timeout");
		//String excelpath = properties.getProperty("excelpath");
		String excelpath = FileUtility.fetchDataFromProperty("excelpath");
		//String excelSheetName = properties.getProperty("excelSheetName");
		String excelSheetName = FileUtility.fetchDataFromProperty("excelSheetName");
		long timeoutLong = Long.parseLong(timeout);
		
		//generate the Random number
		Random ran = new Random();
		int randomNumber = ran.nextInt(1000);
		
		//fetch the data from excel
		//FileInputStream fiss = new FileInputStream("./src/test/resources/testData2.xlsx");
		//Workbook wb = WorkbookFactory.create(fiss);
		//ExcelUtility.openExcel(excelpath);
		//Sheet sh = wb.getSheet(excelSheetName);
		//Row row = sh.getRow(9);
		//Cell cell = row.getCell(1);
		//ExcelUtility.writeDataInNewRow(excelpath, excelSheetName, 9, 1, organizationName);
		
		//String organizationName = cell.getStringCellValue();
		

		//step 2: launch the browser
		WebDriver driver1=null;

		if(browser.equalsIgnoreCase("Chrome"))
		{
			WebDriverManager.chromiumdriver().setup();
			driver1=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")) 
		{
			WebDriverManager.chromiumdriver().setup();
			driver1=new ChromeDriver();
		}
		else {
			System.out.println("Browser is not Specified Properly");
		}
		
		//step 3:Do basic config for browser
	    WebDriverUtility.maximizeBrowser(driver1);
		//driver1.manage().window().maximize();
	    WebDriverUtility.waitforPageLoad(driver1, timeoutLong);
		//driver1.manage().timeouts().implicitlyWait(timeoutLong, TimeUnit.SECONDS);

		//step4:Open the url and navigate to application
		driver1.get(url);

		//step5:login to the application
		driver1.findElement(By.name("user_name")).sendKeys(userName);
		driver1.findElement(By.name("user_password")).sendKeys(password);
		driver1.findElement(By.id("submitButton")).click();

		//step6:create organization
		
		driver1.findElement(By.xpath("//a[@href='index.php?module=Accounts&action=index']")).click();
		driver1.findElement(By.name("//img[@alt='Create Organization...']")).click();
		String organizationName="Test yantra";
		String expOrganizationName = organizationName+randomNumber;
		driver1.findElement(By.name("accountname")).sendKeys(expOrganizationName);
		driver1.findElement(By.xpath("//input[contains(@value,'Save')]")).click();
		
		//verify the organization
		String actualOrganizationName = driver1.findElement(By.id("dtlview_Organization Name")).getText();
		if(actualOrganizationName.equalsIgnoreCase(expOrganizationName)) {
			System.out.println("Organization Name is successfully created");
		}
  
		WebElement logout = driver1.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions actions=new Actions(driver1);
		actions.moveToElement(logout).click().perform();
		
		//click on signout
		driver1.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
		//wb.close();
		//driver.quit();
		ExcelUtility.closeExcel();

}
}