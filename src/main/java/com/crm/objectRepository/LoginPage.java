package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//step1:we should create public class and give the class name as webpage name
public class LoginPage {
	//step2:declaration --->we will declare the locators as private using @findby

@FindBy(name ="user_name")
private WebElement userNameTextField;

@FindBy(name = "user_password")
private WebElement passwordTextField;

@FindBy(id = "submitButton")
private WebElement loginButton;
	
//step3:Initialization --> we will create public constructors and initialize the elements/variables
public LoginPage(WebDriver driver) {
	PageFactory.initElements(driver, this);
}

//step4:Utilization---> by developing public getters or/and Business library
//way1:by creating public getters
public WebElement getUserNameTextField() {
	return userNameTextField;
}

public WebElement getPasswordTextField() {
	return passwordTextField;
}

public WebElement getLoginButton() {
	return loginButton;
}

//way2:by creating business library
public void loginAction(String username,String password) {
	enterUN_Pwd(username,password);
	loginButton.click();
}

public void enterUN_Pwd(String username,String password) {
	
	userNameTextField.sendKeys(username);
	passwordTextField.sendKeys(password);
}
}
