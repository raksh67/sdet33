package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewPurchaseOrderPage {
	@FindBy(name = "subject")
	private WebElement subjectTextField;
	
	@FindBy(xpath = "(//input[@value='  Save  '])[1]")
	private WebElement saveButton;
	
	//step3:Initialization --> we will create public constructors and initialize the elements/variables
		public CreatingNewPurchaseOrderPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
		
		//step4:Utilization---> by developing public getters or/and Business library
		//way1:by creating public getters
		public WebElement getSubjectTextField() {
			return subjectTextField;
		}
		
		public WebElement getSaveButton() {
			return saveButton;
		}
		
		//way2:by creating business library

		public void entersubjectname(String subjectname) {
			subjectTextField.sendKeys(subjectname);
		}
		
		public void savepurchaseAction() {
			saveButton.click();
		}
}
