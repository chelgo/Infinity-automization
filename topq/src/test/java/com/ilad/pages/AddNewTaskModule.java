package com.ilad.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class AddNewTaskModule extends AbstractPageObject {

	private By saveMyChangeBtnBy = By.xpath("//form[@id='newTaskForm']//input[@type='submit']");
	private By newTaskNameTbxBy = By.xpath("//form[@id='newTaskForm']//input[@class='tt-query']");
	private By cancelBtnBy = By.xpath("//form[@id='newTaskForm']//div[@id='editTaskFormFooter0']//a");

	public AddNewTaskModule(WebDriver driver_) throws Exception {
		super(driver_);
	}

	public AddNewTaskModule typeInNewTaskNameTbx(String taskName_) {
		elmtOperations.sendKeys(newTaskNameTbxBy, taskName_);
		return this;
	}

	public AddNewTaskModule selectWhoShouldDoThisDdwn(String taskListID_) {
		WebElement element = driver.findElement(
				By.xpath("//form[@id='newTaskForm']//select[@id='taskAssignedToUserIdForList" + taskListID_ + "']"));
		Select select = new Select(element);
		select.selectByVisibleText("fake12 fake12 (me)");
		return this;
	}

	public AddNewTaskModule clickOnSaveMyChangesBtn() {
		elmtOperations.clickOnElement(saveMyChangeBtnBy);
		return this;
	}

	public TaskListsPage clickOnCancelBtn() throws Exception {
		elmtOperations.clickOnElement(cancelBtnBy);
		takeScreenShotWithName("TaskAdded");
		return new TaskListsPage(driver);
	}
}
