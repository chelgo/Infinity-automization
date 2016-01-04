package com.ilad.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementOperations {

	WebDriver driver;
	WebDriverWait wait;

	public ElementOperations(WebDriver driver_, WebDriverWait wait_) {
		driver = driver_;
		wait = wait_;
	}

	public void clickOnElement(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public void sendKeys(By locator, String keyToSend) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator)).sendKeys(keyToSend);
	}

	public WebElement findWebElement(By locator) {
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * Find element in parent element by locators
	 * 
	 * @param parentLocator
	 *            element that the child locator works on.
	 * @param childLocator
	 *            child locator, with implicit wait
	 * @return the child element
	 * @author chelgo
	 */
	public WebElement findWebElementInParentElement(By parentLocator, By childLocator) {
		return findWebElement(parentLocator).findElement(childLocator);
	}
}
