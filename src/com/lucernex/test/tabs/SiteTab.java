package com.lucernex.test.tabs;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import javax.swing.text.TabExpander;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


public class SiteTab extends com.lucernex.test.core.Test {

	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private HashMap<String, String> expectedMap = new HashMap();
	private HashMap<String, String> actualMap = new HashMap();
	private WebElement key1, value1, key2, value2;

	private void setUpExpectedData(){
		expectedMap.put("Name", "Shelbii Geo Code Test");
		expectedMap.put("Region", "1");
		expectedMap.put("Portfolio", "Global");
	}
	

	@Test
	public void test() throws Exception {

		//doLogin();
		setUpExpectedData();

		String pageTitle = driver.getTitle();
		assertEquals(pageTitle, "LxRetail");
		driver.switchTo().defaultContent();

		// Openeing Site tab
		driver.findElement(By.id("m1_PotentialProject")).click();
		Thread.sleep(1000);

		System.out.println("Site tab opened");

		// Openeing Site-->Details
		driver.findElement(By.id("m2_PotentialProject_Details")).click();
		System.out.println("Site-->Details openened");

		WebElement tabPanel = driver.findElement(By.className("x-tab-panel"));
		String tabPanelID = tabPanel.getAttribute("id");
		String currentURL = driver.getCurrentUrl();
		System.out.println("currentURL : " + currentURL);
		String PanelId = currentURL.substring(currentURL.lastIndexOf(":") + 1);
		System.out.println("PanelId : " + PanelId);

		WebElement summaryPanel = driver.findElement(By.id(PanelId));
		WebElement summaryPanelIframe = summaryPanel.findElement(By
				.tagName("iframe"));
		String summaryPanelIframesrc = summaryPanelIframe.getAttribute("src");

		driver.switchTo().frame(summaryPanel.findElement(By.tagName("iframe")));

		WebElement summarPanelBody = driver.findElement(By.tagName("body"));
		ArrayList trs = (ArrayList) summarPanelBody
				.findElements(By
						.xpath("//table[@class='tblBorder']/tbody/tr/td/table/tbody/tr[1]/td[1]/table/tbody/tr"));

		System.out.println("generalInfoSpacer = " + trs.size());
		Iterator i = trs.iterator();
		int rows = 0;
		while (i.hasNext()) {
			if (rows > 7)
				break;
			rows++;

			WebElement tr = (WebElement) i.next();
			System.out.println("tr class = " + tr.getAttribute("class"));

			try {
				if (tr.getAttribute("class").equals("tblSpacer"))
					continue;
				key1 = tr.findElement(By.cssSelector("td:nth-of-type(1)"));
				System.out.println("key1 getText = " + key1.getText());

			} catch (Exception e) {
			}
			try {
				value1 = tr.findElement(By.cssSelector("td:nth-of-type(2)"));
				System.out.println("value1 gettext = " + value1.getText());

			} catch (Exception e) {
			}
			actualMap.put(key1.getText(), value1.getText());
			try {

				key2 = tr.findElement(By.cssSelector("td:nth-of-type(3)"));
				System.out.println("key2 getText = " + key2.getText());

			} catch (Exception e) {
			}
			try {
				value2 = tr.findElement(By.cssSelector("td:nth-of-type(4)"));
				System.out.println("value2 gettext = " + value2.getText());
			} catch (Exception e) {
			}
			actualMap.put(key2.getText(), value2.getText());

		}
		Iterator itrExpected = expectedMap.entrySet().iterator();
		while (itrExpected.hasNext()) {
			Map.Entry pairs = (Map.Entry) itrExpected.next();
			String expectedKey = (String) pairs.getKey();
			String expectedValue = (String) pairs.getValue();
			System.out.println("expected key,value : (" + expectedKey + ","
					+ expectedValue + ")");

			Iterator itrActual = actualMap.entrySet().iterator();
			while (itrActual.hasNext()) {
				Map.Entry actualPairs = (Map.Entry) itrActual.next();
				String actualKey = (String) actualPairs.getKey();
				String actualValue = (String) actualPairs.getValue();
				if (actualKey.equals(expectedKey)) {
					System.out.println("actual key,value : ("
							+ actualPairs.getKey() + ","
							+ actualPairs.getValue() + ")");
					assertEquals( expectedValue,actualPairs.getValue());
				}
			}

		}

	}

}
