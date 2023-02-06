package pages.projectGroup;

import java.util.Map;

import javax.lang.model.element.ExecutableElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.base.Excel;
import com.util.CommonFunction;
import com.util.TestUtil;

public class AddProjectGroupPage extends BasePage {

	TestUtil util;
	CommonFunction commonFunction;

	public AddProjectGroupPage(WebDriver driver) {
		this.driver = driver;
		util = new TestUtil(driver);
		commonFunction = new CommonFunction(driver);
	}

	By navProjectMenu = By.xpath("*//a//span[@class='rmText rmExpandDown' and contains(text(),'Project')] ");
	By navProjectGroup = By.xpath("*//a//span[contains(text(),'Project Group') or contains(text(),'Group Information')] ");

	public void navigateToProjectGroup() {
		util.waitUntilElementDisplay(navProjectMenu);
		util.click(navProjectMenu);
		util.waitUntilElementDisplay(navProjectGroup);
		util.click(navProjectGroup);
	}

	By txtGroupName = By.xpath("//input[contains(@id,'GROUPNAME')]");

	public void setGroupName(String value) {
		if (!commonFunction.checkNA(value))
		util.waitUntilElementDisplay(txtGroupName);
		util.inputTextAndPressTab(txtGroupName, util.randomNumber());
	}

	By txtGroupDescription = By.xpath("//textarea[contains(@id,'GROUPDESC')]");

	public void setGroupDescription(String value) {
		if (!commonFunction.checkNA(value))
		util.waitUntilElementDisplay(txtGroupDescription);
		util.inputTextAndPressTab(txtGroupDescription, value);
	}

	By btnAddNew = By.xpath("//input[contains(@id,'btnAddGroup')]");

	public void clickOnAddNew() {
		util.click(btnAddNew);
	}

	By btnSave = By.xpath("//input[contains(@id,'btnSaveGroup')]");

	public void clickOnSave() {
		util.click(btnSave);
	}

	public void selectProject(String value) {
		
		By selectProjectProject = By.xpath("//*[contains(text(),'" + value + "')]/../input");
		util.inputTextAndPressTab(selectProjectProject, value);
	}

	By btnAddGroup = By.xpath("//input[contains(@id,'btnLinkLogs')]");

	public void clickOnAddGroup() {
		util.click(btnAddGroup);
	}

	public void isProjectGroupPresent(String value) {
		By selectProjectgroup = By.xpath(
				"//div[contains(@id,'LKNPROJECTS_radYALDropDownList')]//following-sibling::li//*[contains(text(),'"
						+ value + "')]");
		util.isElementPresent(selectProjectgroup);

	}

	public void addProjectGroup(Map<String, String> map, String testcaseName) {
		try {
			navigateToProjectGroup();
			log("STEP 1:  Navigate to Menu - Project Group", Status.PASS);
		} catch (Exception e) {
			log("STEP 1:  Navigate to Menu - Project Group", Status.FAIL);
			throw new RuntimeException("Failed in step 1: Popup up does not appear");
		}

		try {
			setGroupName(map.get(Excel.ProjectGroupName));
			log("STEP 2:  User can enter Project Group", Status.PASS);
		} catch (Exception e) {
			log("STEP 2:  User can not enter Project Group", Status.FAIL);
			throw new RuntimeException("STEP 2:  User can not enter Project Group");
		}

		try {
			setGroupDescription(map.get(Excel.GroupDescription));
			log("STEP 3:  User can enter Group Description", Status.PASS);
		} catch (Exception e) {
			log("STEP 3:  User can not enter Group Description", Status.FAIL);
			throw new RuntimeException("STEP 3:  User can not enter Group Description");
		}
		
//		try {
//			clickOnAddNew();
//			log("STEP 4:  User can Click on Add New ", Status.PASS);
//		} catch (Exception e) {
//			log("STEP 4:  User can not Click on Add New", Status.FAIL);
//			throw new RuntimeException("STEP 4:  User can not Click on Add New");
//		}

		try {
			clickOnSave();
			log("STEP 5:  User can Click on Add Save", Status.PASS);
		} catch (Exception e) {
			log("STEP 5:  User can not Click on Add Save", Status.FAIL);
			throw new RuntimeException("STEP 5:  User can not Click on Add Save");
		}
		try {
			selectProject(map.get(Excel.AvailableProjects));
			log("STEP 6:  User can Select available Project", Status.PASS);
		} catch (Exception e) {
			log("STEP 6:  User can not Select available Project", Status.FAIL);
			throw new RuntimeException("STEP 6:  User can not Select available Project");
		}
		try {
			clickOnAddGroup();
			isProjectGroupPresent(map.get(Excel.AvailableProjects));
			log("STEP 7:  User can Click on Add Group", Status.PASS);
			log("STEP 7:  Selected project dispalyed in Project Group field", Status.PASS);
		} catch (Exception e) {
			log("STEP 7:  User can not Click on Add Group", Status.FAIL);
			log("STEP 7:  Selected project dispalyed in Project Group field", Status.FAIL);
			throw new RuntimeException("STEP 7:  User can not Click on Add Group");
		}
	}
}
