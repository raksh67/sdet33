package com.crm.opportunity;
//361
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.crm.genericUtility.ConstantPath;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.FileUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.WebDriverUtility;
import com.crm.objectRepository.CreateOpportunityPage;
import com.crm.objectRepository.HomePage;
import com.crm.objectRepository.LoginPage;
import com.crm.objectRepository.OpportunityOrgPopupWindow;

import io.github.bonigarcia.wdm.WebDriverManager;
//344
public class OpportunityWithDropdown {

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
			//String opportunityname = wb.getSheet(excelSheetName).getRow(19).getCell(1).getStringCellValue();
			String opportunityname = ExcelUtility.fetchData(excelSheetName, 19, 1);
		    
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
			
			//WebElement opportunities = driver.findElement(By.xpath("//a[@href='index.php?module=Potentials&action=index']"));//opportunities tab
			//WebDriverUtility.clickActionThroughJS(driver, opportunities);
			//WebElement plusicon = driver.findElement(By.xpath("//img[@title='Create Opportunity...']"));//create opp plusicon
			//WebDriverUtility.clickActionThroughJS(driver, plusicon);
			HomePage homepge=new HomePage(driver);
			homepge.opportunityClickAction();
		
			//WebElement opportunityname = driver.findElement(By.xpath("//input[@name='potentialname']"));//opportunity nametextfield
			//WebDriverUtility.sendkeysThroughJS(driver, opportunityname, expOpp);
			CreateOpportunityPage creatopp=new CreateOpportunityPage(driver);
			creatopp.enteroppname(opportunityname);
			//WebElement grpradio = driver.findElement(By.xpath("//input[@name='assigntype'])[2]"));//userradio button
			//WebDriverUtility.clickActionThroughJS(driver, grpradio);
			creatopp.userRadioClick();
			//WebElement lookupimage = driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[1]"));//plus lookup image
			//WebDriverUtility.clickActionThroughJS(driver, lookupimage);
			creatopp.oppOrglookUpClick();
			
			//Set<String> winIDs = driver.getWindowHandles();

			//for(String id:winIDs) {
			//	driver.switchTo().window(id);
			//	if(driver.getTitle().contains("Accounts")) {
			//	break;
			//}
			//}
			WebDriverUtility.switchToWindow(driver, "Accounts");


			//WebElement organization = driver.findElement(By.linkText("pragathi"));//organization name in list
			//WebDriverUtility.clickActionThroughJS(driver, organization);
			OpportunityOrgPopupWindow opppopup=new OpportunityOrgPopupWindow(driver);
			opppopup.exisOrgNameAction();
			
			driver.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();//assigned to group
			WebElement assigneddropdwn = driver.findElement(By.name("assigned_group_id"));
			//creatopp.grpRadioClick();
			Select select=new Select(assigneddropdwn);
			select.deselectByVisibleText("Team Selling");
			
		   WebElement expclosedate = driver.findElement(By.id("jscal_trigger_closingdate"));//calander
		   Select select1=new Select(expclosedate);
		   select1.
			
			WebElement salesstage = driver.findElement(By.name("sales_stage"));
			Select select2=new Select(salesstage);
			select2.selectByValue("Closed Lost");
			driver.findElement(By.xpath("(//input[@value='  Save  '])[1]")).click();//save button
			//verify 
			String actualoppname = driver.findElement(By.xpath("//span[@id='dtlview_Opportunity Name']")).getText();
			//WebElement actassignedto = driver.findElement(By.xpath("//a[@href='index.php?module=Settings&action=GroupDetailView&groupId=3']"));

			//if(expOpp.equalsIgnoreCase(actualoppname))	
			//{
//				System.out.println("opportunity created successfully");
			//}
			

	}

}
