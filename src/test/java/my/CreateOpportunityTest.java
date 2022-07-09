package my;
//89 opportunity excel sheet-->login to application using username and password-->click on opportunityTab-->click on create opportunity plusicon-->
//enter mandatory values and click on assigned to-->select organization lookupimg (plusicon)-->select the existing organization from orgpage-->
//enter mandatory values and click on assigned to and select grp as marketing grp-->click on save bz
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
import com.crm.objectRepository.CreateOpportunityPage;
import com.crm.objectRepository.CreateOrganizationPage;
import com.crm.objectRepository.CreatingNewOpportunityPage;
import com.crm.objectRepository.HomePage;
import com.crm.objectRepository.LoginPage;
import com.crm.objectRepository.OpportunityInformationPage;
import com.crm.objectRepository.OpportunityOrgPopupWindow;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOpportunityTest {

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

//int randomnumber = JavaUtility.generateRandomNumber(1000);

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
//WebElement submitButton = driver.findElement(By.id("submitButton"));//savebutton
//WebDriverUtility.clickActionThroughJS(driver, submitButton);
LoginPage loginpage=new LoginPage(driver);
loginpage.loginAction(userName, password);

//WebElement opportunities = driver.findElement(By.xpath("//a[@href='index.php?module=Potentials&action=index']"));//opportunities tab
//WebDriverUtility.clickActionThroughJS(driver, opportunities);
//WebElement plusicon = driver.findElement(By.xpath("//img[@title='Create Opportunity...']"));//create opp plusicon
//WebDriverUtility.clickActionThroughJS(driver, plusicon);
HomePage homepage=new HomePage(driver);
homepage.opportunityTabAction();

//String expOpp = "TestYantra";
//WebElement opportunityname = driver.findElement(By.xpath("//input[@name='potentialname']"));//opportunity nametextfield
//WebDriverUtility.sendkeysThroughJS(driver, opportunityname, expOpp);

CreateOpportunityPage createopppage=new CreateOpportunityPage(driver);
createopppage.createOpportunityAction();

CreatingNewOpportunityPage opppage=new CreatingNewOpportunityPage(driver);
opppage.userRadioClick();
//WebElement grpradio = driver.findElement(By.xpath("(//input[@name='assigntype'])[2]"));//radio button
//WebDriverUtility.clickActionThroughJS(driver, grpradio);
//WebElement lookupimage = driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[1]"));//plus lookup image
//WebDriverUtility.clickActionThroughJS(driver, lookupimage);
opppage.oppOrglookUpClick();

//Set<String> winIDs = driver.getWindowHandles();

//for(String id:winIDs) {
//	driver.switchTo().window(id);
//	if(driver.getTitle().contains("Accounts")) {
//	break;
//}
//}
WebDriverUtility.switchToWindow(driver, "Accounts");

//WebElement existingorganization = driver.findElement(By.linkText("pragathi"));//organization name in list
//WebDriverUtility.clickActionThroughJS(driver, organization);
OpportunityOrgPopupWindow oppexisorgname=new OpportunityOrgPopupWindow(driver);
oppexisorgname.exisOrgNameAction();

// driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
opppage.saveButtonClick();

//verify opportunity details
//String actualoppname = driver.findElement(By.xpath("//span[@id='dtlview_Opportunity Name']")).getText();

OpportunityInformationPage oppinfopage=new OpportunityInformationPage(driver);
String actopp = oppinfopage.verifyOppName();

if(opportunityname.equalsIgnoreCase(actopp)) {
	System.out.println("Opportunity created successfully");
}

ExcelUtility.closeExcel();
	}
}
