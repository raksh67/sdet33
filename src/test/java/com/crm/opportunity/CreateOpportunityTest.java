package com.crm.opportunity;
//89 opportunity excel sheet-->login to application using username and password-->click on opportunityTab-->click on create opportunity plusicon-->
//enter mandatory values and click on assigned to-->select organization lookupimg (plusicon)-->select the existing organization from orgpage-->
//enter mandatory values and click on assigned to and select grp as marketing grp-->click on save bz

import org.testng.annotations.Test;

import com.crm.genericUtility.BaseClass;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.FileUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.WebDriverUtility;
import com.crm.objectRepository.CreateOpportunityPage;
import com.crm.objectRepository.CreatingNewOpportunityPage;
import com.crm.objectRepository.OpportunityInformationPage;
import com.crm.objectRepository.OpportunityOrgPopupWindow;


public class CreateOpportunityTest extends BaseClass {
@Test
	public void createOpportunityTest() throws Throwable {
	//	create the object for pom class
	CreatingNewOpportunityPage oppage=new CreatingNewOpportunityPage(driver);
	CreateOpportunityPage cropp=new CreateOpportunityPage(driver);
	OpportunityInformationPage oppinfo=new OpportunityInformationPage(driver);
	OpportunityOrgPopupWindow opppop=new OpportunityOrgPopupWindow(driver);

	//store variable
	String oppname = ExcelUtility.fetchData(FileUtility.fetchDataFromProperty("excelSheetName"), 19, 1)+JavaUtility.generateRandomNumber(1000);
	
//click on opportunity tab
	homepage.opportunityTabAction();
	//click on create
	cropp.createOpportunityAction();
	//create opp
	oppage.enteroppname(null);
	oppage.userRadioClick();
	oppage.oppOrglookUpClick();

WebDriverUtility.switchToWindow(driver, "Accounts");
opppop.exisOrgNameAction();
//save
oppage.saveButtonClick();

//verify opportunity details
String actualoppname = oppinfo.verifyOppName();

if(actualoppname.equalsIgnoreCase(oppname)) {
	System.out.println("Opportunity created successfully");
}
else
	System.out.println("Opportunity not created");

	}
}
