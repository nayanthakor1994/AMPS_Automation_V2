package pages.agreementManager;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.base.Excel;
import com.util.CommonFunction;
import com.util.TestUtil;

public class AgreementObligationsPage extends BasePage {

	TestUtil util;
	CommonFunction commonFunction;

	public AgreementObligationsPage(WebDriver driver) {
		this.driver = driver;
		util = new TestUtil(driver);
		commonFunction = new CommonFunction(driver);
	}
	
	By agreementOrLeaseTab = By.xpath("//span[text()='Agreement Obligations' or text()='Lease Obligations']");
	By addNewAgreementObligationForm = By.xpath("//table[contains(@id,'LEASE_STIP')]//a[contains(@id,'lnkAddNewRecord')]");
	public void clickOnAddNewObligation() {
		if(!util.isElementVisible(addNewAgreementObligationForm)) {
			util.click(agreementOrLeaseTab);
		}
		util.click(addNewAgreementObligationForm);
		util.waitUntilLoaderDisappear();
		util.waitFor(5000);
	}
	
	By obligationType = By.xpath("//input[contains(@id,'EditFormControl_TYPE_ID_') and @type='text']");
	By obligationPriority = By.xpath("//input[contains(@id,'EditFormControl_PRIORITY_ID') and @type='text']");
	By obligationStatus = By.xpath("//input[contains(@id,'EditFormControl_STATUS_ID') and @type='text']");
	By obligationFrequency = By.xpath("//input[contains(@id,'EditFormControl_FREQUENCY_ID') and @type='text']");
	By dateApproved = By.xpath("//input[contains(@id,'EditFormControl_Date_Approved_dateInput') and @type='text']");
	By startDate = By.xpath("//input[contains(@id,'EditFormControl_Start_Date_dateInput') and @type='text']");
	By endDate = By.xpath("//input[contains(@id,'EditFormControl_End_Date_dateInput') and @type='text']");
	By assignedTo = By.xpath("//input[contains(@id,'EditFormControl_Assigned_To_ID') and @type='text']");
	By lastCompletedDate = By.xpath("//input[contains(@id,'EditFormControl_Last_Completed_Date_dateInput') and @type='text']");
	By nextDueDate = By.xpath("//input[contains(@id,'Next_Due_Date_dateInput') and @type='text']");
	By description = By.xpath("//textarea[contains(@id,'EditFormControl_DESCRIPTION')]");
	By associateAgreement = By.xpath("//input[contains(@id,'EditFormControl_Recording_ID') and @type='text']");
	By insertButton = By.xpath("//input[contains(@id,'EditFormControl_btnInsert')]");
	By successMessage = By.xpath("//span[contains(@id,'LEASE_STIP_usrMessage')]");
	
	public void enterObligationType(String value) {
		if(!commonFunction.checkNA(value)) {
			util.inputTextAndPressTab(obligationType, value);
		}
	}
	public void enterObligationPriority(String value) {
		if(!commonFunction.checkNA(value)) {
			util.inputTextAndPressTab(obligationPriority, value);
		}
	}
	public void enterObligationStatus(String value) {
		if(!commonFunction.checkNA(value)) {
			util.inputTextAndPressTab(obligationStatus, value);
		}
	}
	public void enterObligationFrequency(String value) {
		if(!commonFunction.checkNA(value)) {
			util.inputTextAndPressTab(obligationFrequency, value);
		}
	}
	public void enterDateApproved(String value) {
		if(!commonFunction.checkNA(value)) {
			util.inputTextAndPressTab(dateApproved, value);
		}
	}
	public void enterStartDate(String value) {
		if(!commonFunction.checkNA(value)) {
			util.inputTextAndPressTab(startDate, value);
		}
	}
	public void enterEndDate(String value) {
		if(!commonFunction.checkNA(value)) {
			util.inputTextAndPressTab(endDate, value);
		}
	}
	public void enterAssignedTo(String value) {
		if(!commonFunction.checkNA(value)) {
			util.inputTextAndPressTab(assignedTo, value);
		}
	}
	public void enterLastCompletedDate(String value) {
		if(!commonFunction.checkNA(value)) {
			util.inputTextAndPressTab(lastCompletedDate, value);
		}
	}
	public void enterNextDueDate(String value) {
		if(!commonFunction.checkNA(value)) {
			util.waitUntilLoaderDisappear();
			util.inputTextAndPressTab(nextDueDate, value);
		}
	}
	public void enterDescription(String value) {
		if(!commonFunction.checkNA(value)) {
			util.waitForWebElementToBeClickable(description, IMPLICIT_WAIT);
			util.waitFor(5000);
			try {
				util.inputText(description, value);
			} catch (Exception e) {
				util.waitFor(10000);
				util.inputText(description, value);
			}
		}
	}
	public void enterAssociateAgreement(String value) {
		if(!commonFunction.checkNA(value) && util.isElementPresent(associateAgreement)) {
			util.waitForWebElementToBeClickable(associateAgreement, IMPLICIT_WAIT);
			util.waitFor(2000);
			util.inputText(associateAgreement, value);
		}
	}
	
	public void clickOnInsertButton() {
		util.waitForWebElementToBeClickable(insertButton, IMPLICIT_WAIT);
		util.waitFor(2000);
		util.click(insertButton);
		util.waitUntilLoaderDisappear();	
	}
	public String getSuccessMessage() {
		util.waitUntilElementDisplay(successMessage);
		return util.getText(successMessage);
	}
	
	public void addAgreementObligations(Map<String, String> map, String testcaseName) {
		try {
			clickOnAddNewObligation();
			log("STEP 1: Click on Add new record button", Status.PASS);
		} catch (Exception e) {
			log("STEP 1: Click on Add new record button", Status.FAIL);
			throw new RuntimeException("Failed in step 1");
		}
		
		try {
			enterObligationType(map.get(Excel.ObligationType));
			log("STEP 2: Fill in Type DD", Status.PASS);
		} catch (Exception e) {
			log("STEP 2: Fill in Type DD", Status.FAIL);
			throw new RuntimeException("Failed in step 2");
		}
		
		try {
			enterObligationPriority(map.get(Excel.Priority));
			log("STEP 3: Fill in Priority DD", Status.PASS);
		} catch (Exception e) {
			log("STEP 3: Fill in Priority DD", Status.FAIL);
			throw new RuntimeException("Failed in step 3");
		}
		
		try {
			enterObligationStatus(map.get(Excel.Status));
			log("STEP 4: Fill in Status DD", Status.PASS);
		} catch (Exception e) {
			log("STEP 4: Fill in Status DD", Status.FAIL);
			throw new RuntimeException("Failed in step 4");
		}
		
		try {
			enterObligationFrequency(map.get(Excel.Frequency));
			log("STEP 5: Fill in Frequency DD", Status.PASS);
		} catch (Exception e) {
			log("STEP 5: Fill in Frequency DD", Status.FAIL);
			throw new RuntimeException("Failed in step 5");
		}
		
		try {
			enterDateApproved(TestUtil.addDaysInCurrentDate("MM/dd/yyyy", -10));
			log("STEP 6: Fill in Date approved date", Status.PASS);
		} catch (Exception e) {
			log("STEP 6: Fill in Date approved date", Status.FAIL);
			throw new RuntimeException("Failed in step 6");
		}
		
		try {
			enterStartDate(TestUtil.getCurrentDateTime("MM/dd/yyyy"));
			log("STEP 7: Fill in Start Date", Status.PASS);
		} catch (Exception e) {
			log("STEP 7: Fill in Start Date", Status.FAIL);
			throw new RuntimeException("Failed in step 7");
		}
		
		try {
			enterEndDate(TestUtil.addDaysInCurrentDate("MM/dd/yyyy", 60));
			log("STEP 8: Fill in End Date", Status.PASS);
		} catch (Exception e) {
			log("STEP 8: Fill in End Date", Status.FAIL);
			throw new RuntimeException("Failed in step 8");
		}
		
		try {
			enterAssignedTo(map.get(Excel.AssignedTo));
			log("STEP 9: Fill in Assigned To DD", Status.PASS);
		} catch (Exception e) {
			log("STEP 9: Fill in Assigned To DD", Status.FAIL);
			throw new RuntimeException("Failed in step 9");
		}
		
		try {
			enterLastCompletedDate(TestUtil.addDaysInCurrentDate("MM/dd/yyyy", -15));
			log("STEP 10: Fill in Last Completed Date", Status.PASS);
		} catch (Exception e) {
			log("STEP 10: Fill in Last Completed Date", Status.FAIL);
			throw new RuntimeException("Failed in step 10");
		}
		try {
			enterNextDueDate(TestUtil.addDaysInCurrentDate("MM/dd/yyyy", 60));
			log("STEP 11: Fill in Next Due Date", Status.PASS);
		} catch (Exception e) {
			log("STEP 11: Fill in Next Due Date", Status.FAIL);
			throw new RuntimeException("Failed in step 11");
		}
		try {
			enterDescription("Automation Description");
			enterAssociateAgreement(NA);
			log("STEP 12: Fill in Description", Status.PASS);
		} catch (Exception e) {
			log("STEP 12: Fill in Description", Status.FAIL);
			throw new RuntimeException("Failed in step 12");
		}
		try {
			clickOnInsertButton();
			String message = getSuccessMessage();
			Assert.assertEquals(message, "Changes saved successfully!", "Success message is mismatched");
			log("STEP 13: Click on Insert", Status.PASS);
		} catch (Exception e) {
			log("STEP 13: Click on Insert", Status.FAIL);
			throw new RuntimeException("Failed in step 13");
		}
	}
	
	By editButton = By.xpath("//table[contains(@id,'LEASE_STIP')]//input[contains(@id,'_EditButton')]");
	By updateButton = By.xpath("//input[contains(@id,'EditFormControl_btnUpdate')]");
	
	public void clickOnEditButton() {
		util.click(editButton);
		util.waitUntilLoaderDisappear();
		util.dummyWait(2);
	}
	public void clickOnUpdateButton() {
		util.click(updateButton);
	}
	
	public void editAgreementObligations(Map<String, String> map, String testcaseName) {
		try {
			clickOnEditButton();
			log("STEP 1: Cick on the edit button on the Agreement Obligations grid", Status.PASS);
		} catch (Exception e) {
			log("STEP 1: Cick on the edit button on the Agreement Obligations grid", Status.FAIL);
			throw new RuntimeException("Failed in step 1");
		}
		
		try {
			enterObligationType(map.get(Excel.ObligationType));
			enterObligationPriority(map.get(Excel.Priority));
			enterObligationStatus(map.get(Excel.Status));
			enterObligationFrequency(map.get(Excel.Frequency));
			log("STEP 2: Update the value for some of the Fields", Status.PASS);
		} catch (Exception e) {
			log("STEP 2: Update the value for some of the Fields", Status.FAIL);
			throw new RuntimeException("Failed in step 2");
		}
		
		try {
			clickOnUpdateButton();
			String message = getSuccessMessage();
			Assert.assertEquals(message, "Changes saved successfully!", "Success message is mismatched");
			log("STEP 3: Click on Update", Status.PASS);
		} catch (Exception e) {
			log("STEP 3: Click on Update", Status.FAIL);
			throw new RuntimeException("Failed in step 3");
		}
	}

	By deleteButton = By.xpath("//table[contains(@id,'LEASE_STIP')]//input[contains(@id,'DeleteAlignment')]");
	By deleteOkButton = By.xpath("//div[contains(@class,'radconfirm')]//span[text()='OK']");
	By deleteCancelButton = By.xpath("//div[contains(@class,'radconfirm')]//span[text()='Cancel']");
	
	public void clickOnDeleteButton() {
		util.click(deleteButton);
		util.waitUntilLoaderDisappear();
	}
	public void clickOnDeleteOkButton() {
		util.click(deleteOkButton);
	}
	public void clickOnDeleteCancelButton() {
		util.click(deleteCancelButton);
	}

	public void deleteAgreementObligations(String testcaseName) {
		try {
			clickOnDeleteButton();
			log("STEP 1: Click on the Delete button for the record in the Agreement Obligations panel ", Status.PASS);
		} catch (Exception e) {
			log("STEP 1: Click on the Delete button for the record in the Agreement Obligations panel ", Status.FAIL);
			throw new RuntimeException("Failed in step 1");
		}
		
		try {
			clickOnDeleteCancelButton();
			log("STEP 2: click on the Cancel button in the Delete popup", Status.PASS);
		} catch (Exception e) {
			log("STEP 2: click on the Cancel button in the Delete popup", Status.FAIL);
			throw new RuntimeException("Failed in step 2");
		}
		
		try {
			clickOnDeleteButton();
			clickOnDeleteOkButton();
			String message = getSuccessMessage();
			Assert.assertEquals(message, "Changes saved successfully!", "Success message is mismatched");
			log("STEP 3: click OK button in the Delet popup", Status.PASS);
		} catch (Exception e) {
			log("STEP 3: click OK button in the Delet popup", Status.FAIL);
			throw new RuntimeException("Failed in step 3");
		}
	}
}
