package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformationPage {
	@FindBy(id = "dtlview_Last Name")
	private WebElement actualcontactName;

	//step3:Initialization --> we will create public constructors and initialize the elements/variables
	public ContactInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	//step4:Utilization---> by developing public getters or/and Business library
	//way1:by creating public getters
	public WebElement getActualcontactName() {
		return actualcontactName;
	}
	
	//way2:by creating business library
	public String verifyContactName() {
		return actualcontactName.getText();
	}
}
