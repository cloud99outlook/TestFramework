package com.inetbanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	//make the constructor public
	WebDriver ldriver;
	public LoginPage(WebDriver rdriver){
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
		
	}
	
	
	@FindBy(name="uid")
	@CacheLookup
	WebElement txtUserName;
	
	@FindBy(name="password")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@value='LOGIN']")
	@CacheLookup
	WebElement txtLogin;
	
	
	
	public void setUserName(String uname)
	{
		txtUserName.sendKeys(uname);
	}
	
	
	public void setPassword(String upassword)
	{
		txtPassword.sendKeys(upassword);
	}
	
	public void clickSubmit() throws InterruptedException
	{
		
		Thread.sleep(5000);
		txtLogin.click();
		Thread.sleep(5000);
	}
	
	
	
	
}
