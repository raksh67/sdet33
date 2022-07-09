package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatePurchaseOrderPage {
	
	
	@FindBy(xpath = "//img[@title='Create Purchase Order...']")
	private WebElement createPurchaseicon;

	//step3:Initialization --> we will create public constructors and initialize the elements/variables
	public CreatePurchaseOrderPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//step4:Utilization---> by developing public getters or/and Business library
	//way1:by creating public getters

	public WebElement getCreatePurchaseicon() {
		return createPurchaseicon;
	}

	//way2:by creating business library
	public void createpurchaseAction() {
		createPurchaseicon.click();
	}
}

