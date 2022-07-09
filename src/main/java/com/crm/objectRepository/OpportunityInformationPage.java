package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpportunityInformationPage {
	@FindBy(id = "//span[@id='dtlview_Opportunity Name']")
	private WebElement actualOppName;
	
	//step3:Initialization --> we will create public constructors and initialize the elements/variables
	public OpportunityInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//step4:Utilization---> by developing public getters or/and Business library
		//way1:by creating public getters
	public WebElement getActualOppName() {
		return actualOppName;
	}
	
	//way2:by creating business library
	public String verifyOppName() {
		return actualOppName.getText();
	}
	}