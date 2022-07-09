package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//step1:we should create public class and give the class name as webpage name
public class HomePage {
	//step2:declaration --->we will declare the locators as private using @findby
@FindBy(linkText = "Contacts")
private WebElement contactsTab;
	
@FindBy(xpath = "(//a[@href='index.php?module=Accounts&action=index'])[1]")
private WebElement organizationTab;

@FindBy(xpath = "//a[@href='index.php?module=Potentials&action=index']")
private WebElement opportunityTab;

@FindBy(xpath  = "//a[@href='index.php?module=Leads&action=index']")
private WebElement createLeadTab;

@FindBy(linkText = "More")
private WebElement MoreDropdown;

@FindBy(name = "Purchase Order")
private WebElement purchaseOrdeLink;

@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
private WebElement administratorBtn;

@FindBy(xpath = "//a[text()='Sign Out']")
private WebElement signoutLink;

//step3:Initialization --> we will create public constructors and initialize the elements/variables
public HomePage(WebDriver driver) {
	PageFactory.initElements(driver, this);
}

//step4:Utilization---> by developing public getters or/and Business library
//way1:by creating public getters
public WebElement getContactsTab() {
	return contactsTab;
}

public WebElement getOrganizationTab() {
	return organizationTab;
}

public WebElement getOpportunityTab() {
	return opportunityTab;
}

public WebElement getCreateLeadTab() {
	return createLeadTab;
}

public WebElement getMoreDropdown() {
	return MoreDropdown;
}
public WebElement getPurchaseOrdeLink() {
	return purchaseOrdeLink;
}

public WebElement getAdministratorBtn() {
	return administratorBtn;
}

public WebElement getSignoutLink() {
	return signoutLink;
}

//way2:by creating business library
public void contactTabAction() {
	contactsTab.click();
}
public void organizationTabAction() {
	organizationTab.click();
}
public void opportunityTabAction() {
	opportunityTab.click();
}
public void leadTabAction() {
	createLeadTab.click();
}
public void moreDropdwnAction() {
	MoreDropdown.click();
}
public void pruchaseOrderAction() {
	purchaseOrdeLink.click();
}
public void adminstatorAction() {
	administratorBtn.click();
}
public void signoutAction() {
	signoutLink.click();
}
}