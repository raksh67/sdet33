package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewOpportunityPage {
	@FindBy(xpath  = "//input[@name='potentialname']")
	private WebElement oppNameTextField;
	
	@FindBy(xpath  = "(//input[@name='assigntype'])[2]")
	private WebElement grpRadioButton;
	
	@FindBy(xpath = "(//input[@name='assigntype'])[2]")
	private WebElement userRadioButton;
	
	@FindBy(xpath = "(//img[@src='themes/softed/images/select.gif'])[1]")
	private WebElement lookUpPlusicon;

	@FindBy(xpath  = "(//input[@value='  Save  '])[2]")
	private WebElement saveButton;
	
	//step3:Initialization --> we will create public constructors and initialize the elements/variables
	public CreatingNewOpportunityPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		}
	//step4:Utilization---> by developing public getters or/and Business library
	//way1:by creating public getters
	public WebElement getOppNameTextField() {
		return oppNameTextField;
	}

	public WebElement getGrpRadioButton() {
		return grpRadioButton;
	}

	public WebElement getUseRadioButton() {
		return userRadioButton;
	}

	public WebElement getLookUpPlusicon() {
		return lookUpPlusicon;
	}
	public WebElement getSaveButton() {
		return saveButton;
	}
	
	//way2:by creating business library

	public void enteroppname(String oppname) {
		oppNameTextField.sendKeys(oppname);
	}
	public void userRadioClick() {
		userRadioButton.click();
	}
	public void grpRadioClick() {

		grpRadioButton.click();
	}
	public void saveButtonClick() {

		saveButton.click();
	}
	public void oppOrglookUpClick() {

		lookUpPlusicon.click();
	}
}
