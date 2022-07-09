package com.crm.practice;

import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class PracticeTest {

@Test
public void rakshaTest() {
	Reporter.log("Hi", true);
}

@Test
public void deekshaTest() {
	Reporter.log("Bye", true);
}

@BeforeSuite
public void rakshitaTest() {
	Reporter.log("testng", true);
}

@AfterSuite
public void sushmitaTest() {
	Reporter.log("testyantra", true);
}
}
