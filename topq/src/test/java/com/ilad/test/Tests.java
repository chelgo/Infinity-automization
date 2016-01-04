package com.ilad.test;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ilad.pages.OverviewPage;
import com.ilad.pages.ScreenShotFactory;
import com.ilad.pages.TaskListsPage;
import com.ilad.pages.WebDriverFactory;
import com.ilad.pages.WebDriverFactory.BrowserType;
import com.ilad.pages.AbstractPageObject;
import com.ilad.pages.AddNewTaskModule;
import com.ilad.pages.ConfigurationReaderModule;
import com.ilad.pages.LoginPage;

public class Tests {

	private WebDriver driver;
	private ConfigurationReaderModule configModule;
	private String user;
	private String password;
	private ScreenShotFactory screenShotFactory;

	//@off
	
	@BeforeMethod(alwaysRun=true)
	public void setFramework() throws Exception {

		configModule = ConfigurationReaderModule.getStaticInstance();
		configModule.setProperties("fake12@fake.com", "fake", "https://topq.teamwork.com","../topq/test-output/TestsScreens/", 3000);

		configModule.loadInputStream();
		user = configModule.getUserName();
		password = configModule.getPassword();
		System.out.println("Got user password as:" + user + ", " + password);
		
		String url = configModule.getUrl();
		Long implicitwaitTime = configModule.getImplicitWaitTime();

		WebDriverFactory driverFactory = new WebDriverFactory();
		driver = driverFactory.setDriver(BrowserType.FIREFOX, url, implicitwaitTime);
		screenShotFactory = ScreenShotFactory.getScreenShotFactoryInstance();
		screenShotFactory.setDriver(driver);
		
		System.out.println("Got url, wait as:" + url + ", " + implicitwaitTime);
	}

	@DataProvider(name = "pvdUserNameAndPassword")
	public Object[][] providerLogin() throws IOException {
		
		String taskListName = "c" + System.currentTimeMillis() % 10000;
		String taskName = "end exercises";
		return new Object[][] { { taskListName, taskName } };
	}

	@Test(alwaysRun = true, dataProvider = "pvdUserNameAndPassword")
	public void testAddTask( String taskListName_, String taskName_)
			throws Exception {
		
		Reporter.log("Login the user name and password.", true);
		//Login page
		LoginPage loginPage = new LoginPage(driver)
				.typeInUserNameTextBox(user)
				.typeInUserPasswordTextBox(password);
		
		Reporter.log("Click the login button and go to the main page", true);
		//Overview page
		OverviewPage overViewPage = loginPage.clickOnLoginButtonToOverViewPage();
		Thread.sleep(4000);
		
		Reporter.log("Go to tasks page, click new list, type list name, click add new task button", true);
		//TaskList page
		TaskListsPage taskListPage = overViewPage
				.clickOnTasksTab()
				.clickOnAddTaskListBtn()
				.typeInNewTaskListNameTbx(taskListName_)
				.clickOnAddThisTaskListBtn();
				overViewPage.takeScreenShotWithName("Task list Added");
		Thread.sleep(4000);
		
		Reporter.log("Go back to tasklist page, move the screen to show the added tasklist", true);
		// go to added tasklist
		String taskListId = taskListPage
				.getAddedTaskListId(taskListName_);
		taskListPage.moveToAddedTaskList(taskListId);

		Reporter.log("Click on add new task for this specified list", true);
		// click new task button
		AddNewTaskModule newTaskModule = taskListPage
				.clickOnAddNewTaskBtnAndGoToNewTaskModule(taskListId);
		Thread.sleep(3000);

		Reporter.log("Type name, who should do, click save, click cancel link for second task ",true);
		// create new task 
		newTaskModule
		.typeInNewTaskNameTbx(taskName_)
		.selectWhoShouldDoThisDdwn(taskListId)
		.clickOnSaveMyChangesBtn()
		.clickOnCancelBtn();
		newTaskModule.takeScreenShotWithName("End Of Test");
		
		
		Reporter.log("TaskList and task are created successfully",true);
		Thread.sleep(2000);

	}

	//@on

	@AfterMethod
	public void CloseDriver() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

}
