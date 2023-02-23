package com.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.base.BasePage;

public class CommonFunction extends BasePage {

	TestUtil util;

	public CommonFunction(WebDriver driver) {
		this.driver = driver;
		util = new TestUtil(driver);
	}

	By loaderIcon = By.xpath("//body/div[contains(@id,'Ajax')]");

	// Close Browser
	public void closeBrowser() {
		driver.quit();
	}
	
	public void refreshThePage() {
		util.reloadPage();
		util.dummyWait(5);
	}

	By navToolsMenu = By.xpath("//a//span[@class='rmText rmExpandDown' and contains(text(),'Tools')]");
	By navToolsMyDashboard = By.xpath("//a//span[contains(text(),'My Dashboard')]");
	By navProjectMenu = By.xpath("*//a//span[@class='rmText rmExpandDown' and contains(text(),'Project')] ");
	By navProjectInformation = By.xpath("*//a//span[contains(text(),'Project Information')] ");
	By navProjectDetails = By.xpath("*//a//span[contains(text(),'Project Details')] ");
	By btnLogOff = By.xpath("//input[@id='ctl00_ContentPlaceHolderHeader_YALHD_LogStatus']");
	By tabProjectDetails = By.xpath("//div[@id='ctl00_ConPHRightTop_RadTabStrip1']");

	// Add Button
	By topProjectMenu = By.xpath("//img[@id='ctl00_ConPHLeftTop_prjMenu']");
	By btnAdd = By.xpath("*//a//span[@class='rmText' and contains(text(),'Add')]");
	By btnArchieve = By.xpath("//input[contains(@id,'btnArchive')]");
	By archieveOk = By.xpath("//a[contains(@onClick,'confirm')][1]");
	By btnUnArchieve = By.xpath("//input[contains(@id,'btnUnarchive')]");
	By btnSave = By.xpath("//input[contains(@id,'btnSaveProject')]");

	public void navigateToProjectDeails() {
		util.waitUntilElementDisplay(navProjectMenu);
		util.click(navProjectMenu);
		util.waitUntilElementDisplay(navProjectInformation);
		util.click(navProjectInformation);
		util.waitUntilElementDisplay(navProjectDetails);
		util.click(navProjectDetails);
		String tabProjectDetails = driver.findElement(By.xpath("//div[@id='ctl00_ConPHRightTop_RadTabStrip1']"))
				.getText().trim();
		if (tabProjectDetails.contains("Project Details")) {
			System.out.println("User can navigate to the Project details !!!");
			log("User can navigate to the Project details !!!", Status.PASS);
		} else {
			System.out.println("User cannot see the option in Menu !!!");
			log("User cannot see the option in Menu !!!", Status.FAIL);
		}
		Assert.assertTrue(tabProjectDetails.contains("Project Details"), "User cannot see the option in Menu !!!");
	}

	public void navigateToMyDashboard() {
		util.waitUntilElementDisplay(navToolsMenu);
		util.click(navToolsMenu);
		util.waitUntilElementDisplay(navToolsMyDashboard);
		util.click(navToolsMyDashboard);
		log("navigating to create new Project");
	}

	public void clickOnAddButton() {
		// Click on Project Menu
		util.waitUntilElementDisplay(topProjectMenu);
		util.click(topProjectMenu);

		// Click on Add Button
		util.waitUntilElementDisplay(btnAdd);
		util.click(btnAdd);
	}

	public void clickOnArchieveButton() throws InterruptedException {
		util.waitUntilElementDisplay(btnArchieve);
		util.click(btnArchieve);
		Thread.sleep(1000);
		if (util.isElementPresent(archieveOk)) {
			util.waitUntilElementDisplay(archieveOk);
			util.click(archieveOk);
		}
		// util.waitUntilElementDisplay(btnUnArchieve);
		if (util.isElementPresent(btnUnArchieve)) {
			log("UnArchieve Present !!!", Status.PASS);
		} else {
			log("UnArchieve not Present !!!", Status.FAIL);
		}
	}

	public void clickOnSaveButton() {
		util.waitUntilElementDisplay(btnSave);
		util.click(btnSave);
	}

	public boolean checkNA(String value) {
		return value.equals(NA);
	}

	By successMessage = By.xpath("//span[@id='usrMessage']");

	public void waitForSuccessMessage() {
		util.waitForWebElementToBePresent(successMessage, 10);
	}

	public String getSuccessMessage() {
		return util.getText(successMessage);
	}

	By successAddMessage = By.xpath("//span[@id='usrMessageAdd']");

	public void waitForSuccessAddMessage() {
		util.waitForWebElementToBePresent(successAddMessage, 10);
	}

	public String getSuccessAddMessage() {
		return util.getText(successAddMessage);
	}

	public boolean iSArchieveButton() {
		return util.isElementPresent(btnArchieve, 10);
	}

	By buttonNewRecord = By.xpath("//a[contains(@id,'AddNewRecord')]");

	public void clickOnNewRecord() {
		util.waitUntilElementDisplay(buttonNewRecord);
		util.click(buttonNewRecord);
	}

}
