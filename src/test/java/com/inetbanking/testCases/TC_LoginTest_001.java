package com.inetbanking.testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {

	//Checked in 
	
	@Test 
	public void LoginTest() throws InterruptedException {

		LoginPage lp = new LoginPage(driver);
		
		Thread.sleep(2000);
		lp.setUserName(username);
		logger.info("Entered username");
		lp.setPassword(password);
		logger.info("Entered password");
		Thread.sleep(5000);
		lp.clickSubmit();
		
		logger.info("submit Clicked");

		System.out.println(driver.getTitle());

		if (driver.getTitle().equals("GTPL Bank Manager HomePage")) {
			Assert.assertTrue(true);
			logger.info("title is checked and test case passed");

		} else {
			CaptureScreen(driver, "LoginTest");
			Assert.assertTrue(false);
			logger.info("title is checked and Testcase Failed");
		}

	}
}
