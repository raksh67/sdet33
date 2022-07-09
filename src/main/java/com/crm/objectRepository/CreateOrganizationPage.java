package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOrganizationPage {
@FindBy(xpath = "//img[@alt='Create Organization...']")
private WebElement createorganizationicon;


//step3:Initialization --> we will create public constructors and initialize the elements/variables
public CreateOrganizationPage(WebDriver driver) {
	PageFactory.initElements(driver, this);
}

//step4:Utilization---> by developing public getters or/and Business library
//way1:by creating public getters
public WebElement getCreateorganizationicon() {
	return createorganizationicon;
}

//way2:by creating business library
public void createOrganizationAction() {
	createorganizationicon.click();
}
}
