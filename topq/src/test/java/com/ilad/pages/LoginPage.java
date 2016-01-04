package com.ilad.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractMainTabs {

	@FindBy(id = "userLogin")
	private WebElement userName;

	@FindBy(id = "password")
	private WebElement userPassword;

	@FindBy(id = "ordLoginSubmitBtn")
	private WebElement loginButton;

	public LoginPage(WebDriver driver_) throws Exception {

		super(driver_);

		if (!driver_.getCurrentUrl().equals("https://topq.teamwork.com/")) {
			System.out.println("The page is incorrect");
		}

		PageFactory.initElements(driver, this);
	}

	public LoginPage typeInUserNameTextBox(String userName_) {
		userName.clear();
		userName.sendKeys(userName_);
		return this;
	}

	public LoginPage typeInUserPasswordTextBox(String userPassword_) {
		userPassword.clear();
		userPassword.sendKeys(userPassword_);
		return this;
	}

	public LoginPage clickOnLoginButtonToLogin() {
		loginButton.click();
		return this;
	}

	public OverviewPage clickOnLoginButtonToOverViewPage() throws Exception {
		loginButton.click();
		takeScreenShotWithName("ClickedOnLoginBtn");
		return new OverviewPage(driver);
	}

}
