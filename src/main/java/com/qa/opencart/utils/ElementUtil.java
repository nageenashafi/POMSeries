package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.netty.handler.timeout.TimeoutException;

public class ElementUtil {
	
	private WebDriver driver;
	
	public ElementUtil(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public By getBy(String LocatorType, String LocatorValue)
	{
		By Locator = null;
		switch(LocatorType.toLowerCase())
		{
		case "id":
		Locator = By.id(LocatorValue);
			break;
		case "name":
			Locator = By.name(LocatorValue);
				break;
		case "classname":
			Locator = By.className(LocatorValue);
				break;
		case "xpath":
			Locator = By.xpath(LocatorValue);
				break;
		case "css":
			Locator = By.cssSelector(LocatorValue);
				break;
		case "linktext":
			Locator = By.linkText(LocatorValue);
				break;
		case "partiallinktext":
			Locator = By.partialLinkText(LocatorValue);
				break;
		case "tagname":
			Locator = By.tagName(LocatorValue);
				break;
			
			default:
			break;
		}
		return Locator;
		}
		
	public WebElement getElement(By Locator)
	{
		return driver.findElement(Locator);
	}
	
	public void doSendKeys(By Locator, String value)
	{
		getElement(Locator).sendKeys(value);
	}
	
	//Method Overload
	
		public void doSendKeys(String LocatorType, String LocatorValue, String value)
		{
			getElement(getBy(LocatorType, LocatorValue)).sendKeys(value);		
		}
		
		public void doClick(By Locator)
		{
			getElement(Locator).click();
		}
		//Overload
		public void doClick(String LocatorType, String LocatorValue)
		{
			getElement(getBy(LocatorType,LocatorValue)).click();
		}
		
		public String dogetText(By Locator)
		{
		return getElement(Locator).getText();
		}
		
		public String dogetAttribute(By Locator, String attrName)
		{
			return getElement(Locator).getAttribute(attrName);
		}
		
		public boolean doisDisplayed(By Locator)
		{
			return getElement(Locator).isDisplayed();
		}
	
		//1. Get Element
		public List<WebElement> getElements(By Locator)
		{
			return driver.findElements(Locator);
		}
		//2. Total number of links
	    public  int getElementCount(By Locator)
	    {
	    	return getElements(Locator).size();
	    }
	    //3. Print the link texts
	    
	    public void printElementsText(By Locator)
	    {
	    	List<WebElement> eleList = getElements(Locator);
	    	for(WebElement e: eleList)
	    	{
	    		String text = e.getText();
	    		if(!text.isEmpty())
	    		{
	    			System.out.println(text);
	    		}
	    	}
	    }
	    
	    //4. This method will return the list of element's text
	    public List<String> getElementsTextList(By Locator)
	    {
	    	List<WebElement> eleList = getElements(Locator);
	    	List<String> eleTextList = new ArrayList<String>();
	    	for(WebElement e: eleList)
	    	{
	    		String text = e.getText();
	    	//	if(!text.isEmpty())
	    		//	{
	    			eleTextList.add(text);
	   // 	}
	        	}
			return eleTextList;
	    }
	    
	    //5. This method will return the Element's attribute value
	    public List<String> getElementsAttributeList(By Locator, String value)
	    {
	    	List<WebElement> eleList = getElements(Locator); //Capture the complete list first i.e, list of webElement
	    	List<String> eleAttrList = new ArrayList<String>(); //Maintain a blank array list of type string
	    	for(WebElement e: eleList)
	    	{
	    		String AttrVal = e.getAttribute(value);
	    		eleAttrList.add(AttrVal);
	    	}
	    	return eleAttrList;
	    	}
	
	    /*************************************************Dropdown Utils*************************************************************/
	    
	    public void doSelectDropdownByIndex(By Locator, int index)
		{
			Select select = new Select(getElement(Locator));
					select.selectByIndex(index);
		}
		
		public void doSelectDropdownByVisibleText(By Locator, String visibleText)
		{
			Select select = new Select(getElement(Locator));
			select.selectByVisibleText(visibleText);
		}

		public void doSelectDropdownByValue(By Locator, String value)
		{
			Select select = new Select(getElement(Locator));
			select.selectByValue(value);
		}
		
		public int getDropdownOptionsCount(By Locator)
		{
			Select select = new Select(getElement(Locator));
			return select.getOptions().size();
		}
	    
		public List<String> getDropdownOptionsList(By Locator)
		{
			Select select = new Select(getElement(Locator));
					List<WebElement> optionsList = select.getOptions();
				List<String> optionsTextList = new ArrayList<String>();	
				System.out.println(optionsList.size());
			for(WebElement e:optionsList)
			{
				String text = e.getText();
				optionsTextList.add(text);
			}
			return optionsTextList;
						
		}
		
		public void selectValueFromDropdown(By Locator,String Value)
		{
			Select select = new Select(getElement(Locator));
			List<WebElement> optionsList = select.getOptions();
			for(WebElement e:optionsList)
				{
				String text = e.getText();
				System.out.println(text);
				if(text.equals(Value))
				{
					e.click();
					break;
				}
				}
		}
	/**************************************Action Methods*****************************************/
		
		public void actiondoSendKeys(By Locator, String Value)
		{
			Actions act = new Actions(driver);
			act.sendKeys(getElement(Locator),Value).perform();
		}
		
		public void actiondoClick(By Locator)
		{
			Actions act = new Actions(driver);
			act.click(getElement(Locator)).perform();
		}
		
		/************************* Wait Utils **************************/

		/**
		 * An expectation for checking an element is visible and enabled such that you can click it.
		 * @param locator
		 * @param timeOut
		 */
		public void clickWhenReady(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		}

		
		
		/**
		 * An expectation for checking that an element is present on the DOM of a page.
		 * This does not necessarily mean that the element is visible.
		 * 
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public WebElement waitForElementPresence(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}

		/**
		 * An expectation for checking that there is at least one element present on a
		 * web page.
		 * 
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public List<WebElement> waitForElementsPresence(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		}

		/**
		 * An expectation for checking that an element is present on the DOM of a page
		 * and visible. Visibility means that the element is not only displayed but also
		 * has a height and width that is greater than 0.
		 * default polling time = 500 ms
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public WebElement waitForElementVisible(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}
		
		/**
		 * An expectation for checking that an element is present on the DOM of a page
		 * and visible. Visibility means that the element is not only displayed but also
		 * has a height and width that is greater than 0.
		 * default polling time = customized time
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public WebElement waitForElementVisible(By locator, int timeOut, int pollingTime) {
			WebDriverWait wait = new WebDriverWait(driver, 
						Duration.ofSeconds(timeOut), Duration.ofSeconds(pollingTime));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}

		/**
		 * An expectation for checking that all elements present on the web page that
		 * match the locator are visible. Visibility means that the elements are not
		 * only displayed but also have a height and width that is greater than 0.
		 * 
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public List<WebElement> waitForElementsVisible(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

		}

		// non web elements: title, url, alert

		public boolean waitForPageTitle(String titleVal, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.titleContains(titleVal));
		}

		public boolean waitForPageActTitle(String actTitle, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.titleIs(actTitle));
		}

		public String doGetPageTitleContains(String titleVal, int timeOut) {
			if (waitForPageTitle(titleVal, timeOut)) {
				return driver.getTitle();
			}
			return null;
		}

		public String doGetPageTitleIs(String titleVal, int timeOut) {
			if (waitForPageActTitle(titleVal, timeOut)) {
				return driver.getTitle();
			}
			return null;
		}

		public String waitForUrlContains(String urlFraction, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

			try {
				if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
					return driver.getCurrentUrl();
				}
			} catch (TimeoutException e) {
				return null;

			}
			return null;
		}

		public String waitForUrlToBe(String url, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

			try {
				if (wait.until(ExpectedConditions.urlToBe(url))) {
					return driver.getCurrentUrl();
				}
			} catch (TimeoutException e) {
				return null;

			}
			return null;
		}

		public Alert waitForAlert(int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.alertIsPresent());
		}

		public String getAlertText(int timeOut) {
			return waitForAlert(timeOut).getText();
		}

		public void acceptAlert(int timeOut) {
			waitForAlert(timeOut).accept();
		}

		public void dismissAlert(int timeOut) {
			waitForAlert(timeOut).dismiss();
		}

		public WebDriver waitForFrameByIndex(int timeOut, int frameIndex) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
		}

		public WebDriver waitForFrameByLocator(int timeOut, By frameLocator) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
		}

		public WebDriver waitForFrameByElement(int timeOut, WebElement frameElement) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
		}
		
		public WebElement waitForElementPresenceWithFluentWait(By locator, int timeOut, int pollingTime) {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(timeOut))
					.pollingEvery(Duration.ofSeconds(pollingTime))
					.ignoring(NoSuchElementException.class)
					.ignoring(StaleElementReferenceException.class)
					.withMessage(locator + " is not found within the given time......");
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));						
		}
		
		public WebElement waitForElementPresenceWithWait(By locator, int timeOut, int pollingTime) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.pollingEvery(Duration.ofSeconds(pollingTime))
					.ignoring(NoSuchElementException.class)
					.ignoring(StaleElementReferenceException.class)
					.withMessage(locator + " is not found within the given time......");

			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));						
			
			
		}

		

			
		}
	

