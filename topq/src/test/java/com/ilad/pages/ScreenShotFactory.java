package com.ilad.pages;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotFactory {

	private WebDriver driver;
	private File directoryFile;
	private static ScreenShotFactory SSFINSTANCE;
	private ConfigurationReaderModule configModule;
	private String directoryPath;

	private ScreenShotFactory() throws IOException {
		configModule = ConfigurationReaderModule.getStaticInstance();
		directoryPath = configModule.getScreenShotDirectoryPath();
		directoryFile = createScreenShotDirectoryName(directoryPath);

	}

	/**
	 * This method gives you the static instance of the screenshot factory.
	 * 
	 * @param driver_
	 *            the application driver
	 * @param directoryPath_
	 *            the directory you wish the all test screenshots been saved.
	 * @return the static screenshot instance
	 * @throws IOException
	 */
	public static ScreenShotFactory getScreenShotFactoryInstance() throws IOException {
		if (SSFINSTANCE == null) {
			SSFINSTANCE = new ScreenShotFactory();
		}
		return SSFINSTANCE;
	}

	/**
	 * Takes an screen shot by each new pageObject in the current test. Copy
	 * screen shot to the created directory, the screen name is the same as the.
	 * page object name.
	 * 
	 * @throws IOException
	 */
	public void takeScreenShotAndSendToDirectory() throws IOException {

		File screenFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenFile, new File(directoryFile, this.getClass() + ".png"));
	}

	/**
	 * Takes an screen shot by each new pageObject in the current test. Copy
	 * screen shot to the created directory, the screen name is the same as the.
	 * page object name.
	 * 
	 * @throws IOException
	 */
	public void takeScreenShotAndSendToDirectoryWithName(String screenName_) throws IOException {
		File screenFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenFile, new File(directoryFile, screenName_ + ".png"));
	}

	/**
	 * Create Directory for this test to add all screenShots. Directory name is
	 * "TestedOnDate_" + current date and time for the Directory name of this
	 * test.
	 * 
	 * @return Directory file
	 * @author Chaim Gottlieb
	 */
	private File createScreenShotDirectoryName(String directoryPath_) {
		String directoryName = "TestedOnDate_"
				+ new SimpleDateFormat("MM_dd_HH_mm").format(Calendar.getInstance().getTime());
		return new File(directoryPath_ + directoryName);
	}

	public void setDriver(WebDriver driver_) {
		driver = driver_;
	}
}
