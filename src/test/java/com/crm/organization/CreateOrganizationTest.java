package com.crm.organization;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.genericUtility.BaseClass;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.FileUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.objectRepository.CreateOrganizationPage;
import com.crm.objectRepository.CreatingNewOrganizationPage;
import com.crm.objectRepository.OrganizationInfoPage;

@Listeners(com.crm.grnericUtility.ListnerImplementation.class)
public class CreateOrganizationTest extends BaseClass {
@Test
	public void createOrganizationTest() throws Throwable {
		//create the object for POM class
		CreateOrganizationPage orgpage=new CreateOrganizationPage(driver);
		CreatingNewOrganizationPage neworg=new CreatingNewOrganizationPage(driver);
		OrganizationInfoPage orginfo=new OrganizationInfoPage(driver);
		
		//store variable
		String orgname = ExcelUtility.fetchData(FileUtility.fetchDataFromProperty("excelSheetName"), 9, 1)+JavaUtility.generateRandomNumber(1000);
		
		//click on organization tab
		homepage.organizationTabAction();
		
		//click on create organization
		orgpage.createOrganizationAction();
		//create organization and save
		neworg.enterOrgname(orgname);
		neworg.saveAction();
		
		//verify the contact
	
		String actorg = orginfo.verifyContactName();
		Assert.assertEquals(orgname, actorg);
		//Assert.fail();
		Reporter.log("Organization created Successfully", true);
		//if(orgname.equalsIgnoreCase(actorg)) {
		//	System.out.println("Organization created Successfully");
	    //  }
		//else
			//System.out.println("Organization not created ");

}
}