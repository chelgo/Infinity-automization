package com.ilad.pages;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPageObject {
	protected final WebDriver driver;
	protected final WebDriverWait wait;
	protected final ElementOperations elmtOperations;
	private ScreenShotFactory screenShotFactory;

	public AbstractPageObject(WebDriver driver_) throws Exception {

		if (driver_ == null) {
			throw new Exception("Driver is not be initialized");
		}
		driver = driver_;
		wait = new WebDriverWait(driver_, 12);
		elmtOperations = new ElementOperations(driver, wait);
		screenShotFactory = ScreenShotFactory.getScreenShotFactoryInstance();
		screenShotFactory.takeScreenShotAndSendToDirectory();
	}

	/**
	 * Take an screen shot in middle of the test, with your own name.
	 * 
	 * @param screenName
	 * @throws IOException
	 * @author chelgo 
	 */
	public void takeScreenShotWithName( String screenName_) throws IOException {
		screenShotFactory.takeScreenShotAndSendToDirectoryWithName(screenName_);
	}
	
	/**
	 * Take an screen shot in middle of the test, with the current class name.
	 * @throws IOException
	 * @author chelgo
	 */
	public void takeScreenShotwithClassName() throws IOException  {
		screenShotFactory.takeScreenShotAndSendToDirectory();
	}
}