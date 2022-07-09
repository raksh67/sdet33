package com.crm.contacts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.genericUtility.ConstantPath;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.FileUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.WebDriverUtility;
import com.crm.objectRepository.ContactReportPopUpWindow;
import com.crm.objectRepository.CreateContactPage;
import com.crm.objectRepository.CreatingNewContactPage;
import com.crm.objectRepository.HomePage;
import com.crm.objectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;
//91 contact
public class ContactWithReportTest {

	public static void main(String[] args) throws Throwable {
		String propertyfilepath = ConstantPath.PropertyFilePath;
		System.out.println(propertyfilepath);
		FileUtility.intiallizePropertyFile(propertyfilepath);//FileUtility.java
		String url = FileUtility.fetchDataFromProperty("url");
		String userName = FileUtility.fetchDataFromProperty("userName");
		String password = FileUtility.fetchDataFromProperty("password");
		String browser = FileUtility.fetchDataFromProperty("browser");

		String excelSheetName = FileUtility.fetchDataFromProperty("excelSheetName");
		String excelpath = ConstantPath.excelpath2;
		String timeout = FileUtility.fetchDataFromProperty("timeout");
		//System.out.println(timeout);
		long timeoutLong = Long.parseLong(timeout);

		int randomnumber = JavaUtility.generateRandomNumber(1000);//JavaUtility.java
		
		//Fetch the data from excel
		//FileInputStream fiss=new FileInputStream(excelpath);
		//Workbook wb = WorkbookFactory.create(fiss);
		ExcelUtility.openExcel(excelpath);
		//String lastname = wb.getSheet(excelSheetName).getRow(25).getCell(1).getStringCellValue();
		String lastname = ExcelUtility.fetchData(excelSheetName, 25, 1);
		String explastname=lastname+randomnumber;
		
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
				HomePage homepage= new HomePage(driver);
				homepage.contactTabAction();
				
				CreateContactPage conpage=new CreateContactPage(driver);
				conpage.createContactAction();
				CreatingNewContactPage newconpage=new CreatingNewContactPage(driver);
				newconpage.enterlastname(explastname);
				newconpage.reportLookUpClick();
				
				
				//Set<String> winIDs = driver.getWindowHandles();

				//for(String id:winIDs) {
//					driver.switchTo().window(id);
//					if(driver.getTitle().contains("Accounts")) {
//					break;
				//}
				//}
				WebDriverUtility.switchToWindow(driver, "Accounts");
				
				ContactReportPopUpWindow conrep=new ContactReportPopUpWindow(driver);
				conrep.exContactAction();
				conrep.clearreportAction();
				
				//newconpage.SavecontactAction();
				//ExcelUtility.closeExcel();
				
		
	}

}
