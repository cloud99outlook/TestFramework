package com.inetbanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class editCustomer {

	WebDriver ldriver;
	public editCustomer(WebDriver rdriver){
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
		
	@FindBy(xpath="/html/body/div[3]/div/ul/li[2]/a")
	@CacheLookup
	WebElement btnNewCustomer;
	
	public void clickNewCustomer() throws InterruptedException
	{
		
		Thread.sleep(3000);
		btnNewCustomer.click();
		Thread.sleep(2000);
	}

}
