package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpportunityOrgPopupWindow {
	//step2:declaration --->we will declare the locators as private using @findby

	@FindBy(linkText ="pragathi")
	private WebElement existingOrgName;
		
	//step3:Initialization --> we will create public constructors and initialize the elements/variables
	public OpportunityOrgPopupWindow(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//step4:Utilization---> by developing public getters or/and Business library
	//way1:by creating public getters

	public WebElement getExistingOrgName() {
		return existingOrgName;
	}

	//way2:by creating business library
	public void exisOrgNameAction() {
		existingOrgName.click();
	}
	}