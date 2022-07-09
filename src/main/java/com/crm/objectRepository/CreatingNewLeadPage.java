package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewLeadPage {
	@FindBy(name = "lastname")
	private WebElement lastNameTextField;
	
	@FindBy(name = "company")
	private WebElement companyNameTextField;

	@FindBy(css = "input[type='submit']")
	private WebElement saveButton;

	@FindBy(xpath = "(//input[@value='Delete'])[1]")
	private WebElement deleteButton;
	
	//step3:Initialization --> we will create public constructors and initialize the elements/variables
		public CreatingNewLeadPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
		//step4:Utilization---> by developing public getters or/and Business library
		//way1:by creating public getters
		public WebElement getLastNameTextField() {
			return lastNameTextField;
		}

		public WebElement getCompanyNameTextField() {
			return companyNameTextField;
		}

		public WebElement getSaveButton() {
			return saveButton;
		}

		public WebElement getDeleteButton() {
			return deleteButton;
		}
		//way2:by creating business library
		public void saveAction() {
			saveButton.click();
		}
		public void deleteLeadAction() {
			deleteButton.click();
		}

		public void enterlastncompanyname(String lastname,String companyname) {
			lastNameTextField.sendKeys(lastname);
			companyNameTextField.sendKeys(companyname);
		}
}
