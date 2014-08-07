package com.lucernex.test.core;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.lucernex.test.suite.MyTestSuit;

public class Test {

	public  String baseUrl;
	public  WebDriver driver;	
	public WebDriver getDriver() {
        WebDriver driver = MyTestSuit.getDriver();
        if(driver != null) {
            return driver;
        }

        return new FirefoxDriver();
    }	
	
	@Before
	public void setUp() throws Exception {
		driver = getDriver();
		baseUrl = "http://train.lucernex.com/";
		driver.manage().timeouts().implicitlyWait(22, TimeUnit.SECONDS);

	}	
	
	protected void doLogin(){
		driver.get(baseUrl + "en/login.jsp");
		System.out
				.println("Login page openend, now entering username and firm");

		driver.findElement(By.name("authLoginName")).sendKeys("rootDev");
		driver.findElement(By.name("authLoginFirmName")).clear();
		driver.findElement(By.name("authLoginFirmName")).sendKeys("QA");
		driver.findElement(By.cssSelector("input.loginbutton")).click();
		System.out.println("Login page openend, now entering password");

		driver.findElement(By.name("authPassword")).clear();
		driver.findElement(By.name("authPassword")).sendKeys("lbjFr33way");
		driver.findElement(By.cssSelector("input.loginbutton")).click();
		System.out.println("now testing dashboard");		
	}
}
