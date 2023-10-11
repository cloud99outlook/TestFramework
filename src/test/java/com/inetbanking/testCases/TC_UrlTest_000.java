package com.inetbanking.testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;

public class TC_UrlTest_000 extends BaseClass {

	
	@Test 
	public void UrlTest() throws InterruptedException {

		
		LoginPage lp = new LoginPage(driver);
		System.out.println(driver.getTitle());
		//GTPL Bank Home Page
		if (driver.getTitle().equalsIgnoreCase("GTPL Bank Home Page")) {
			Assert.assertTrue(true);
			logger.info("title is checked and test case passed");

		} else {
			CaptureScreen(driver, "LoginTest");
			Assert.assertTrue(false);
			logger.info("title is checked and Testcase Failed");
		}

	}
}
