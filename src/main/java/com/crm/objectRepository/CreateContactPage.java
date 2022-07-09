package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactPage {
	
@FindBy(css = "img[alt='Create Contact...']")
private WebElement createContactIcon;
	
//step3:Initialization --> we will create public constructors and initialize the elements/variables
public CreateContactPage(WebDriver driver) {
	PageFactory.initElements(driver, this);
}

//step4:Utilization---> by developing public getters or/and Business library
//way1:by creating public getters
public WebElement getCreateContactIcon() {
	return createContactIcon;
}
//way2:by creating business library
public void createContactAction() {
	createContactIcon.click();
}
}