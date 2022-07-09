package my;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
//open vtiger -->login using username and password,click on organization tab-->click on create organization-->enter organization name--->
//click on save button-->verify organization name in organization information page-->click on contacts tab--->click on create contact plus icon--->
//enter mandatoy field last name --->add organization by selecting plus icon in organization name-->search organization through search area field-->
//click on searchnow button --.click on organization name link -->click on save button--->verify actual organization and contact name --->
//mouse hover to administarator icon and click through actions class-->and click on signout
public class CreateContactWithOrganaizationTestp {

	public static void main(String[] args) throws IOException {
		//step1: fetch data from external file and store in variable
		
FileInputStream fis = new FileInputStream("./src/test/resources/commonData.properties");
Properties properties=new Properties();
properties.load(fis);
String url = properties.getProperty("url");
String userName = properties.getProperty("userName");
String password = properties.getProperty("password");
String browser = properties.getProperty("browser");

String timeout = properties.getProperty("timeout");
long timeoutLong = Long.parseLong(timeout);


//step 2: launch the browser
WebDriver driver=null;

if(browser.equalsIgnoreCase("Chrome"))
{
	WebDriverManager.chromiumdriver().setup();
	driver=new ChromeDriver();
}
else {
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

//step6:create organization
driver.findElement(By.xpath("//a[@href='index.php?module=Accounts&action=index']")).click();//organization tab
driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();//createorganizationplusicon
String expOrgName="Sdet33";
driver.findElement(By.name("accountname")).sendKeys(expOrgName);//organizationname textfield
driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();//saveButton

//step7:verify the organization name
String actOrgName=driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']")).getText();//organization name in organization informationpage

if(expOrgName.equalsIgnoreCase(actOrgName))
{
	System.out.println("Organization created successfully");
}

//step 8:create contact
driver.findElement(By.linkText("Contacts")).click();//contacts tab
driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();//createcontact plus icon
String expContactName="abc";
driver.findElement(By.name("lastname")).sendKeys(expContactName);//latname textfield

//step 9 add organization
//driver.findElement(By.xpath("//td[contains(.,'Organization Name') and @class='dvtCellLabel']/following-sibling::td[1]/img[@src='themes/softed/images.gif']")).click();
driver.findElement(By.xpath("(//img[@title='Select'])[1]")).click();//organization name selectplusicon
Set<String> winIDs = driver.getWindowHandles();

for(String id:winIDs) {
	driver.switchTo().window(id);
	if(driver.getTitle().contains("Accounts")) {
		break;
	}
}
driver.findElement(By.name("search_text")).sendKeys(actOrgName);//searcharea field
driver.findElement(By.xpath("//input[@name='search']")).click();//searchnow textfield
driver.findElement(By.linkText(expOrgName)).click();//organization name link
Set<String> wini1 = driver.getWindowHandles();
for(String id1:wini1)
{
	
	driver.switchTo().window(id1);
	if(driver.getTitle().contains("Contacts")) {
		break;
	}
		
	}
driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();//save button
if(expOrgName.equalsIgnoreCase(actOrgName)&&expContactName.equalsIgnoreCase(expContactName))
{
	System.out.println("Contact created successfully");
}
driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();//administratorclick
driver.findElement(By.linkText("Sign Out")).click();//signout link

	}
}

	