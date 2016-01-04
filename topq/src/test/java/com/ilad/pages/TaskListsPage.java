package com.ilad.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TaskListsPage extends AbstractMainTabs {

	public TaskListsPage(WebDriver driver_) throws Exception {
		super(driver_);
	}

	public TaskListsPage clickOnAddTaskListBtn() throws IOException {
		elmtOperations.clickOnElement(By.id("liBFOATL"));
		takeScreenShotWithName("ClickedOnAddTaskListBtn");
		return this;
	}

	public AddNewTaskModule clickOnAddNewTaskBtnAndGoToNewTaskModule(String taskListID_) throws Exception {
		WebElement element = elmtOperations.findWebElementInParentElement(By.id("options" + taskListID_),
				(By.tagName("button")));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().perform();
		takeScreenShotWithName("clickedOnNewTaskBtn");
		return new AddNewTaskModule(driver);
	}

	public TaskListsPage moveToAddedTaskList(String taskListID_) {
		WebElement element = elmtOperations.findWebElementInParentElement(By.id("options" + taskListID_),
				By.tagName("button"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
		return this;
	}

	public TaskListsPage clickOnAddThisTaskListBtn() {
		elmtOperations.clickOnElement(By.id("btnCreateTaskList"));
		return this;
	}

	public String getAddedTaskListId(String listName_) {

		WebElement element = driver.findElement(By.linkText(listName_));
		String addedTaskListId = element.getAttribute("href");
		addedTaskListId = addedTaskListId.split("/")[4];

		return addedTaskListId;
	}

	public TaskListsPage typeInNewTaskListNameTbx(String taskListName_) throws IOException {
		elmtOperations.sendKeys(By.id("newTaskListName"), taskListName_);
		takeScreenShotWithName("NewTaskList");
		return this;
	}
}
