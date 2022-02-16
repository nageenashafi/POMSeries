package com.qa.opencart.pages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1. Always maintain the By locator first in private. to achieve encapsulation
	//These private by locators would be used to create web elements and to perform actions using sendKeys, .click() etc
	//These By locators are specific to this class or page so that is why they are defined private

	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	
	//2. page constructor	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//3. public page actions/methods
	//Just like getter and setter. 
	//A private variable of a class is being used by the public methods. This OOP feature is called encapsulation.
	
	public String getLoginPageTitle()
	{
		return eleUtil.doGetPageTitleIs(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	
	public String getLoginPageUrl()
	{
		return eleUtil.waitForUrlContains(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}
	
	public boolean isForgotPwdLinkExist()
	{
		return eleUtil.doisDisplayed(forgotPwdLink);
	}
	
	public void doLogin(String userName, String pwd)
	{
		eleUtil.doSendKeys(emailId,userName);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
	}
	

}
