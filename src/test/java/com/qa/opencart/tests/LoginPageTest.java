package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class LoginPageTest extends BaseTest{
	
	
	
	@Test
	public void loginPageTitleTest()
	{
		String title = loginPage.getLoginPageTitle();
		System.out.println("Title is: "+title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test
	public void loginPageUrlTest()
	{
		String Url = loginPage.getLoginPageUrl();
		System.out.println("The Url is: "+Url);
		Assert.assertTrue(Url.contains(Constants.LOGIN_PAGE_URL_FRACTION));
	}
	
	@Test
	public void isForgotPwdLinkExist()
	{
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
		
	}
	
	@Test
	public void loginTest()
	{
      loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());

    }
	
}
