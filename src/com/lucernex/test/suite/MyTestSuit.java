package com.lucernex.test.suite;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.lucernex.test.tabs.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   CapitalProjectTab.class,
   SiteTab.class
})
public class MyTestSuit {

	public static WebDriver driver;

	
	public static WebDriver getDriver(){
		return driver;
	}
	
	
	
    @BeforeClass
    public static void setUp() {
    	System.out.println("before class");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(22, TimeUnit.SECONDS);

    }

    
    @AfterClass
    public static void quit() {
		driver.quit();

    }

	
}
