package com.crm.practice;

import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class PracticeTest2 {

@Test
public void test1() {
	Reporter.log("testyantra1");
}

@Test
public void demo2() {
	Reporter.log("testyantra2", true);
}

@BeforeSuite
public void test3() {
	Reporter.log("testyantra3", true);
}

@AfterSuite
public void demo4() {
	Reporter.log("testyantra4", true);
}
}
