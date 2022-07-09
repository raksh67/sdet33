package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateLeadPage {
	@FindBy(xpath = "//img[@title='Create Lead...']")
	private WebElement createLeadicon;
	
	//step3:Initialization --> we will create public constructors and initialize the elements/variables
	public CreateLeadPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//step4:Utilization---> by developing public getters or/and Business library
	//way1:by creating public getters
	public WebElement getCreateLeadicon() {
		return createLeadicon;
	}
	
	//way2:by creating business library
	public void createLeadAction() {
		createLeadicon.click();
	}
	
}
