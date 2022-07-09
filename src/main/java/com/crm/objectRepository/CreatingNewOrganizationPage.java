package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewOrganizationPage {
	@FindBy(name = "accountname")
	private WebElement organizationNameTxtFld;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveButton;
	
	//step3:Initialization --> we will create public constructors and initialize the elements/variables
	public CreatingNewOrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	//step4:Utilization---> by developing public getters or/and Business library
	//way1:by creating public getters
	
	public WebElement getOrganizationNmaeTxtFld() {
		return organizationNameTxtFld;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}
	//way2:by creating business library
	public void enterOrgname(String orgname) {
		organizationNameTxtFld.sendKeys(orgname);
	}
	public void saveAction() {
		saveButton.click();
}
}
