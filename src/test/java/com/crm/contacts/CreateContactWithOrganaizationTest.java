package com.crm.contacts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.grnericUtility.BaseClass;
import com.crm.grnericUtility.ConstantPath;
import com.crm.grnericUtility.ExcelUtility;
import com.crm.grnericUtility.FileUtility;
import com.crm.grnericUtility.JavaUtility;
import com.crm.grnericUtility.WebDriverUtility;
import com.crm.objectRepository.CreateContactPage;
import com.crm.objectRepository.CreateOrganizationPage;
import com.crm.objectRepository.CreatingNewContactPage;
import com.crm.objectRepository.CreatingNewOrganizationPage;
import com.crm.objectRepository.HomePage;
import com.crm.objectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithOrganaizationTest  extends BaseClass {

	public void createContactWithOrganaizationTest() {
		//create the object for pom class
		CreateOrganizationPage orgpage=new CreateOrganizationPage(driver);
		CreatingNewOrganizationPage neworg=new CreatingNewOrganizationPage(driver);
		CreateContactPage conpage=new CreateContactPage(driver);
		CreatingNewContactPage newcon=new CreatingNewContactPage(driver);
		
		//store variable
		String orgname = ExcelUtility.fetchData(FileUtility.fetchDataFromProperty("excelSheetName"), 6, 1)+JavaUtility.generateRandomNumber(1000);
		String lastname = ExcelUtility.fetchData(FileUtility.fetchDataFromProperty("ecxelSheetName"), 6, 2)+JavaUtility.generateRandomNumber(1000);
		//create organization
		homepage.organizationTabAction();
		
		//click ON create organization
		orgpage.createOrganizationAction();
		// create organization
		neworg.enterOrgname(orgname);
		neworg.saveAction();
		//click on contact tab
		homepage.contactTabAction();
		
		//create contact
		conpage.createContactAction();
		//create contact
		newcon.enterlastname(orgname);
		newcon.SavecontactAction();


		//step7:verify the contact

//String actOrgName=driver.findElement(By.xpath("//span[@id='dtlview_organization Name']")).getText();
//String actOrgName=driver.findElement(By.id("dtlview_Organization Name")).getText();
//String actname = corg.verifyContactName();
//if(actname.equalsIgnoreCase(lastname))
//{
//	System.out.println("Organization created successfully");
//}

//step 8:create contact
//driver.findElement(By.xpath("//a[@href='index.php?module=contacts&action=index']")).click();
//driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
//homepage.contactClickAction();
//String expContactName=lastname;
//driver.findElement(By.name("lastname")).sendKeys(expContactName);

//step 9 add organization
//driver.findElement(By.xpath("//td[contains(.,'Organization Name') and @class='dvtCellLabel']/following-sibling::td[1]/img[@src='themes/softed/images.gif']")).click();

//WebDriverUtility.switchToWindow(driver, "Accounts");
//Set<String> winIDs = driver.getWindowHandles();

//for(String id:winIDs) {
//	driver.switchTo().window(id);
//	if(driver.getTitle().contains("Accounts")) {
//		break;
//	}
//}

driver.findElement(By.name("search_text")).sendKeys(actOrgName);
//driver.findElement(By.id("search_txt")).sendKeys(OrgName);


driver.findElement(By.xpath("//inpu[@name='search']")).click();
//driver.findElement(By.name("search")).click();

driver.findElement(By.linkText(actOrgName)).click();
WebDriverUtility.switchToWindow(driver, "Contacts");
//Set<String> winIDs1 = driver.getWindowHandles();
//for(String id1:winIDs1)
//{
	
//	driver.switchTo().window(id1);
	//if(driver.getTitle().contains("Contacts")) {
//		break;
	//}
		
	//}
driver.findElement(By.xpath("//input[@title='Save [Alt+S]'])")).click();
String Act_1 = driver.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();
//String act_add_name=driver.findElement(By.xpath("//td[contains(.,'Organization Name')and @class='dvtCellLabel']/following-sibling")click();


if(lastname.equalsIgnoreCase(Act_1)&&OrgName.equalsIgnoreCase(Act_1))
{
	System.out.println("Contact created successfully with organization");
}
driver.findElement(By.xpath("//img[@src='themes/softed/images/users/user.PNG']")).click();
driver.findElement(By.linkText("Sign Out")).click();

	}
}

	