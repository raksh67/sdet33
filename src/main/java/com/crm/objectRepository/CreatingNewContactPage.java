package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage {
	@FindBy(name = "lastname")
	private WebElement lastNameTextField;
	
	@FindBy(xpath = "(//img[@src='themes/softed/images/select.gif'])[2]")
	private WebElement reportLookUp;
	
	@FindBy(xpath  = "//input[@title='Save [Alt+S]']")
	private WebElement saveButton;
	
	//step3:Initialization --> we will create public constructors and initialize the elements/variables
	public CreatingNewContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	//step4:Utilization---> by developing public getters or/and Business library
	//way1:by creating public getters
	public WebElement getLastNameTextField() {
		return lastNameTextField;
	}

	public WebElement getReportLookUp() {
		return reportLookUp;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}
	//way2:by creating business library
	public void SavecontactAction() {
		saveButton.click();
	}

	public void reportLookUpClick() {
		reportLookUp.click();
	}

	public void enterlastname(String lastname) {
		lastNameTextField.sendKeys(lastname);
	}
}
