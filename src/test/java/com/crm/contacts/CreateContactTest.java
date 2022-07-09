package com.crm.contacts;

import org.testng.annotations.Test;

import com.crm.genericUtility.BaseClass;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.FileUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.objectRepository.ContactInformationPage;
import com.crm.objectRepository.CreateContactPage;
import com.crm.objectRepository.CreatingNewContactPage;

public class CreateContactTest extends BaseClass {

	@Test
	public void createContactTest() throws Throwable  {
		//create the object for POM class
		CreateContactPage conpage=new CreateContactPage(driver);
		CreatingNewContactPage newcon=new CreatingNewContactPage(driver);
		ContactInformationPage coninfo=new ContactInformationPage(driver);
		
		//store variable
		String lastname = ExcelUtility.fetchData(FileUtility.fetchDataFromProperty("excelSheetName"), 3, 1)+JavaUtility.generateRandomNumber(1000);
				
    	//click on contact tab
		homepage.contactTabAction();
		
    	//click on create contact
		conpage.createContactAction();
    			
    	//create contact
		newcon.enterlastname(lastname);
		newcon.SavecontactAction();
    	//step9:verify the contact
    	String actlastname = coninfo.verifyContactName();
    			if(actlastname.equalsIgnoreCase(lastname))
    			{
    			System.out.println("Last Name succesfully created in Contact model");
    			}
    			else
    				System.out.println("Last Name not created in contact model");
    }
	}
	
    			