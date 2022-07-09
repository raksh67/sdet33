package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOpportunityPage {
	@FindBy(xpath = "//img[@title='Create Opportunity...']")
	private WebElement createOpportunityicon;
	
	//step3:Initialization --> we will create public constructors and initialize the elements/variables
	
	public CreateOpportunityPage(WebDriver driver) {
	PageFactory.initElements(driver, this);
	}

	//step4:Utilization---> by developing public getters or/and Business library
	//way1:by creating public getters
	public WebElement getCreateOpportunityicon() {
		return createOpportunityicon;
	}
	
	//way2:by creating business library
	public void createOpportunityAction() {
		createOpportunityicon.click();
	}
	

}
