package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.stream.FileImageInputStream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public WebDriver driver;
	public Properties prop;
	
	/**
	 * This method is used to initialize the driver
	 * @param browserName
	 * @return this returns the WebDriver
	 */
	
	public WebDriver init_driver(Properties prop)
	{
		String browserName = prop.getProperty("browser").trim();
		System.out.println("My browser is: "+browserName);
		
		if (browserName.equalsIgnoreCase("chrome"))
		{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://demo.opencart.com/index.php?route=account/login");
		}
		
		else if(browserName.equalsIgnoreCase("firefox"))
		{
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		}
		
		else if(browserName.equalsIgnoreCase("safari"))
		{
			driver = new SafariDriver();
		}
		else 
		{
			System.out.println("Please pass the right browser name: :" + browserName);
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url").trim());
		return driver;
	}
	
	//Create initialize properties method
	
	/**
	 * This method is used to initialize the properties
	 * 
	 * @return this returns properties class reference
	 */
	
	public Properties init_prop()
	{
		//Create object of properties class to read the properties from properties file
		
		prop = new Properties();
		//Create a stream with java code and with config.properties which will help to connect with config.properties
		try {
			
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			//All properties with key and value pair will be stored inside properties object ip. This object is referred by prop. Load method will load all properties from this stream
			prop.load(ip);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//These are pre-defined classes. both Properties and FileInputStream.
		//Load might give you IO exception so try block helps
         catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return the complete object from here
		return prop;
	}

}
