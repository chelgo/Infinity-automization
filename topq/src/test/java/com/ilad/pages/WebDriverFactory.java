package com.ilad.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverFactory {

	public enum BrowserType {

		FIREFOX(1), CHROME(2);

		private int m_value;

		private BrowserType(int value_) {
			m_value = value_;
		}
	}

	private WebDriver driver;

	/**	Set browser type, and properties.
	 * @author chelgo
	 * @since 23 12 15
	 * @param browserType_  Chrome, Firefox
	 * <strong> if chrome driver is selected, he must be in "../topq/DriversforSelenium/chromedriver"</strong>
	 * @param url_ url to begin
	 * @param implicitWaitTime_ time to wait until finding an element
	 * @return The created webdriver
	 */
	public WebDriver setDriver(BrowserType browserType_, String url_, Long implicitWaitTime_) {

		switch (browserType_) {
		case CHROME:
			
			System.setProperty("webdriver.chrome.driver", "../topq/DriversforSelenium/chromedriver");
			driver = new ChromeDriver();
			break;

		case FIREFOX:
		default:
			driver = new FirefoxDriver();
			break;
		}
		driver.get(url_);
		driver.manage().timeouts().implicitlyWait(implicitWaitTime_, TimeUnit.SECONDS);

		return driver;
	}
}
