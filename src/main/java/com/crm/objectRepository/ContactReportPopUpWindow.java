package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactReportPopUpWindow {
	//step2:declaration --->we will declare the locators as private using @findby

		@FindBy(linkText = "sr")
		private WebElement exContact;
		
		@FindBy(xpath = "(//input[@alt='Clear'])[2]")
		private WebElement clearLink;
			
		//step3:Initialization --> we will create public constructors and initialize the elements/variables
		public ContactReportPopUpWindow(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
		
		//step4:Utilization---> by developing public getters or/and Business library
		//way1:by creating public getters

		public WebElement getExContact() {
			return exContact;
		}

		public WebElement getClearLink() {
			return clearLink;
		}

		//way2:by creating business library
		public void exContactAction() {
			exContact.click();
		}
		
		public void clearreportAction() {
			clearLink.click();
		}
		}
