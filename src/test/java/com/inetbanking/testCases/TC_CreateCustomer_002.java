package com.inetbanking.testCases;

import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.pageObjects.editCustomer;

public class TC_CreateCustomer_002 extends BaseClass{

	//edit Customer
	
	@Test
	public void createCustomer() throws InterruptedException {
		
		
		LoginPage lp = new LoginPage(driver);		
		Thread.sleep(2000);
		lp.setUserName(username);
		logger.info("Entered username");
		lp.setPassword(password);
		logger.info("Entered password");
		Thread.sleep(5000);
		lp.clickSubmit();
		Thread.sleep(5000);
		
		//Alert popup
		isAlertPresent();				
		editCustomer edit_Customer = new editCustomer(driver);
		edit_Customer.clickNewCustomer();
		logger.info("New customer option Clicked");
		
	}

}
