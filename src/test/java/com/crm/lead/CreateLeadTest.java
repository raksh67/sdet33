package com.crm.lead;

import org.testng.annotations.Test;

import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.FileUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.WebDriverUtility;
import com.crm.objectRepository.CreateLeadPage;
import com.crm.objectRepository.CreatingNewLeadPage;
import com.crm.objectRepository.LeadInformationPage;

public class CreateLeadTest extends com.crm.genericUtility.BaseClass {
	@Test
	public void createLeadTest()  throws Throwable {
		//create the object for POM class
		CreateLeadPage createlead=new CreateLeadPage(driver);
		CreatingNewLeadPage newlead=new CreatingNewLeadPage(driver);
		LeadInformationPage leadinfo=new LeadInformationPage(driver);
		
		//store variable
		String lastname = ExcelUtility.fetchData(FileUtility.fetchDataFromProperty("excelSheetName"), 16, 1)+JavaUtility.generateRandomNumber(1000);
		String compname = ExcelUtility.fetchData(FileUtility.fetchDataFromProperty("excelSheetName"), 16, 2)+JavaUtility.generateRandomNumber(1000);


		//click on lead tab
		homepage.leadTabAction();
		
		//click on create organization
		createlead.createLeadAction();
		
		//creating new lead
		newlead.enterlastncompanyname(lastname, compname);
		newlead.saveAction();
		
	   //delete lead
		leadinfo.deleteLeadAction();
		
		//driver.switchTo().alert().accept();
		WebDriverUtility.acceptAlertPopup(driver);
	}
	
}


