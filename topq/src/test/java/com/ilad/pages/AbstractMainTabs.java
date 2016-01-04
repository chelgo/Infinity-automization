package com.ilad.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class AbstractMainTabs extends AbstractPageObject {
	
	public AbstractMainTabs(WebDriver driver_) throws Exception {
		super(driver_);
	}

	public  TaskListsPage clickOnTasksTab() throws Exception {
		elmtOperations.findWebElementInParentElement(By.id("tab_tasks"), By.className("ql")).click();
		return new TaskListsPage(driver);
	}

	public MilestonesPage clickOnMilestoneTab() throws Exception {
		return new MilestonesPage(driver);
	}
	
}
