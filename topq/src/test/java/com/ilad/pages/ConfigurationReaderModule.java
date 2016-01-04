package com.ilad.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.velocity.runtime.directive.Parse;
import org.testng.internal.annotations.Converter;

public class ConfigurationReaderModule {

	private Properties properties = new Properties();

	private static ConfigurationReaderModule CONFIGMODULE;

	private FileInputStream inputStream;

	public static ConfigurationReaderModule getStaticInstance() throws IOException {
		if (CONFIGMODULE == null) {

			CONFIGMODULE = new ConfigurationReaderModule();
		}
		return CONFIGMODULE;
	}

	private ConfigurationReaderModule() {

	}

	/**
	 * With this method you can edit user name, password, url to test, and the
	 * implicit wait time.
	 * 
	 * @param user_
	 *            name to test login
	 * @param password_
	 *            for login
	 * @param url_
	 *            url for the beginning test
	 * @param implicitWait_
	 *            time to wait until found an element [in milliseconds]
	 * @throws IOException
	 */
	public void setProperties(String user_, String password_, String url_, String screenShotDirectoryPath_,
			long implicitWait_) throws IOException {

		File file = new File("../topq/config.properties");
		FileOutputStream os = new FileOutputStream(file);

		// To write property file:
		properties.setProperty("user", user_);
		properties.setProperty("password", password_);
		properties.setProperty("urlToBegin", url_);
		properties.setProperty("implicitWait", Long.toString(implicitWait_));
		properties.setProperty("screenShotDirectoryPath", screenShotDirectoryPath_);
		properties.store(os, "NOTE: In this file you can edit your user name, password, url to test, and path for screenshots.");
	}

	public void loadInputStream() throws IOException {
		inputStream = new FileInputStream("../topq/config.properties");
		properties.load(inputStream);
	}

	public String getUserName() {
		return properties.getProperty("user");
	}

	public String getPassword() {
		return properties.getProperty("password");
	}

	public String getUrl() {
		return properties.getProperty("urlToBegin");
	}

	public Long getImplicitWaitTime() {
		return Long.parseLong(properties.getProperty("implicitWait"));
	}

	public String getScreenShotDirectoryPath() {
		return properties.getProperty("screenShotDirectoryPath");
	}
}
