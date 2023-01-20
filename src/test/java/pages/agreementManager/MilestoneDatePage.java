package pages.agreementManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.util.CommonFunction;
import com.util.TestUtil;

public class MilestoneDatePage extends BasePage {

	TestUtil util;
	CommonFunction commonFunction;

	public MilestoneDatePage(WebDriver driver) {
		this.driver = driver;
		util = new TestUtil(driver);
		commonFunction = new CommonFunction(driver);
	}

	By editMilestoneButton = By.xpath("//input[contains(@id,'EditButton')]");
	By milestoneDatesTab = By.xpath("//div[contains(@id,'LSINFO_radPanels')]//span[text()='Milestone Dates']");

	private void openMilestoneDateSection() {
		if (!util.isElementVisible(editMilestoneButton, 5)) {
			util.click(milestoneDatesTab);
			util.waitFor(2000);
		}
	}

	private void clickOnEditMilestoneButton() {
		util.click(editMilestoneButton);
	}

	By expectedDateText = By.xpath("//input[contains(@id,'Expected_Date_dateInput') and @type='text']");
	By actualDateText = By.xpath("//input[contains(@id,'Actual_Date_dateInput') and @type='text']");

	private void setExpectedDates(String expectedDate) {
		util.inputText(expectedDateText, expectedDate);
	}

	private void setActualDates(String actualDate) {
		util.inputText(actualDateText, actualDate);
	}

	By updateMilestoneButton = By.xpath("//input[contains(@id,'EditFormControl_btnUpdate')]");

	private void clickOnUpdateMilestoneButton() {
		util.click(updateMilestoneButton);
	}

	By milestoneMessage = By.xpath("//span[contains(@id,'LeaseDates_usrMessage')]");

	private void verifySuccessMessageMilestoneDate() {
		util.waitForWebElementToBePresent(milestoneMessage, IMPLICIT_WAIT);
		if (!util.getText(milestoneMessage).equalsIgnoreCase("Changes saved successfully!")) {
			throw new RuntimeException();
		}
	}

	public void addMilestoneDate(String testcaseName) {
		try {
			// User is already on required page
			log("STEP 1:  Navigate to Menu -Agreement Information page", Status.PASS);
		} catch (Exception e) {
			log("STEP 1:  Navigate to Menu -Agreement Information page", Status.FAIL);
			throw new RuntimeException("Failed in step 1");
		}

		try {
			openMilestoneDateSection();
			log("STEP 2:  Click on milestone dates pannel", Status.PASS);
		} catch (Exception e) {
			log("STEP 2: Click on milestone dates pannel", Status.FAIL);
			throw new RuntimeException("Failed in step 2");
		}

		try {
			clickOnEditMilestoneButton();
			log("STEP 3:  Click on edit on the Date name under the Key Dates/Project Schedule", Status.PASS);
		} catch (Exception e) {
			log("STEP 3: Click on edit on the Date name under the Key Dates/Project Schedule", Status.FAIL);
			throw new RuntimeException("Failed in step 3");
		}

		try {
			setExpectedDates(TestUtil.getCurrentDateTime("MM/dd/yyyy"));
			log("STEP 4: Enter the Expected start Date and click update ", Status.PASS);
		} catch (Exception e) {
			log("STEP 4: Enter the Expected start Date and click update ", Status.FAIL);
			throw new RuntimeException("Failed in step 4");
		}

		try {
			setActualDates(TestUtil.getCurrentDateTime("MM/dd/yyyy"));
			clickOnUpdateMilestoneButton();
			verifySuccessMessageMilestoneDate();
			log("STEP 5: Enter the actual start Date and click update ", Status.PASS);
		} catch (Exception e) {
			log("STEP 5: Enter the actual start Date and click update ", Status.FAIL);
			throw new RuntimeException("Failed in step 5");
		}
	}

}
