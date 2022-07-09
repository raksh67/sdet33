package com.crm.practice;

import org.testng.annotations.Test;

public class PracticeTest5 {
@Test
public void A1()
{
	System.out.println("abcd");
}

@Test (groups={"smoke","regression"})
public void B1()
{
	System.out.println("EFGH");
}

@Test(groups="smoke")
public void C1()
{
	System.out.println("HIJK");
}

@Test
public void D1()
{
	System.out.println("LMNO");
}
}
