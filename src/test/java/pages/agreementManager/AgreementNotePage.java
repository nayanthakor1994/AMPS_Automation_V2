package pages.agreementManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.util.CommonFunction;
import com.util.TestUtil;

public class AgreementNotePage extends BasePage {
	
	TestUtil util;
	CommonFunction commonFunction;
	Actions action;
	public AgreementNotePage(WebDriver driver) {
		this.driver = driver;
		action = new Actions(driver);
		util = new TestUtil(driver);
		commonFunction = new CommonFunction(driver);
	}
	By navProjectMenu = By.xpath("//a//span[@class='rmText rmExpandDown' and contains(text(),'Project')] ");
	By navAgreementManager = By.xpath("//a//span[contains(text(),'Agreement Manager')] ");
	By navLeaseManager = By.xpath("//a//span[contains(text(),'Lease Manager')] ");
	By navAgreementNotes = By.xpath("//a//span[contains(text(),'Agreement Notes')] ");
	By addNoteButton = By.xpath("//input[contains(@id,'LSINFO_btnAddnotes')]");
	
	public void navigateToAgreementNoteALT() {
		util.waitUntilElementDisplay(navProjectMenu);
		util.click(navProjectMenu);
		util.waitUntilElementDisplay(navAgreementManager);
		util.click(navAgreementManager);
		util.waitUntilElementDisplay(navAgreementNotes);
		util.click(navAgreementNotes);
		if (!util.isElementPresent(addNoteButton, 30)) {
			throw new RuntimeException();
		}
	}
	
	By addLeaseButton = By.xpath("//table[contains(@id,'LSLST_RadGridTracts')]//a[contains(@id,'_AddJob')]");
	By leaseNoteTab = By.xpath("//span[text()='Lease Notes']/../../parent::a");
	public void navigateToLeaseNoteROW() {
		util.waitUntilElementDisplay(navProjectMenu);
		util.click(navProjectMenu);
		util.waitUntilElementDisplay(navLeaseManager);
		util.click(navLeaseManager);
		util.waitUntilElementDisplay(addLeaseButton);
		util.click(leaseNoteTab);
		if (!util.isElementPresent(addNoteButton, 30)) {
			throw new RuntimeException();
		}
	}
	
	private void clickOnNoteButton() {
		util.click(addNoteButton);
	}
	
	By noteIframe = By.xpath("//iframe[@name='AddNotesLease']");
	private void switchToNoteIframe() {
		util.waitForWebElementToBePresent(noteIframe);
		util.switchToIframe(noteIframe);
	}
	By activityDate = By.xpath("//input[contains(@id,'AgntNotes_AD_dateInput') and @type='text']");
	private void setActivityDate(String value) {
		if (!commonFunction.checkNA(value)) {
			util.inputTextAndPressTab(activityDate, value);
		}
	}
	
	By agentInput = By.xpath("//input[contains(@id,'AgntNotes_UID') and @type='text']");
	private void setAgent(String value) {
		if (!commonFunction.checkNA(value)) {
			util.inputTextAndPressTab(agentInput, value);
		}
	}
	By categoryInput = By.xpath("//input[contains(@id,'AgntNotes_CID') and @type='text']");
	private void setCategory(String value) {
		if (!commonFunction.checkNA(value)) {
			util.inputTextAndPressTab(categoryInput, value);
		}
	}
	By priorityInput = By.xpath("//input[contains(@id,'AgntNotes_PID') and @type='text']");
	private void setPriority(String value) {
		if (!commonFunction.checkNA(value)) {
			util.inputTextAndPressTab(priorityInput, value);
		}
	}
	
	By descriptionIframe = By.xpath("//iframe[contains(@id,'AgntNotes_RE1')]");
	private void setDescription(String value) {
		util.waitForWebElementToBePresent(descriptionIframe);
		util.switchToIframe(descriptionIframe);
		driver.findElement(By.xpath("//body")).sendKeys(value);
		util.switchToDefaultContent();
	}
	
	By saveNoteButton = By.xpath("//input[contains(@id,'AgntNotes_SN')]");
	private void clickOnSaveNote() { //need to switch to iframe
		util.click(saveNoteButton);
	}
	
	public void addNotes(String testCaseName) {
		
		try {
			if(testCaseName.toLowerCase().contains("row")) {
				navigateToLeaseNoteROW();
			} else {
				navigateToAgreementNoteALT();
			}
			log("STEP 1: Navigate to Menu -Agreement Information page.Click on Agreement Notes tab", Status.PASS);
		} catch (Exception e) {
			log("STEP 1: Navigate to Menu -Agreement Information page.Click on Agreement Notes tab", Status.FAIL);
			throw new RuntimeException("Failed in step 1");
		}
		
		try {
			clickOnNoteButton();
			log("STEP 2: Click Add Notes button", Status.PASS);
		} catch (Exception e) {
			log("STEP 2: Click Add Notes button", Status.FAIL);
			throw new RuntimeException("Failed in step 2");
		}
		try {
			switchToNoteIframe();
			setActivityDate(testCaseName);
			log("STEP 3: Fill in Activity Date", Status.PASS);
		} catch (Exception e) {
			log("STEP 3: Fill in Activity Date", Status.FAIL);
			throw new RuntimeException("Failed in step 3");
		}
		try {
			setAgent(testCaseName);
			log("STEP 4: Fill in Agent DD", Status.PASS);
		} catch (Exception e) {
			log("STEP 4: Fill in Agent DD", Status.FAIL);
			throw new RuntimeException("Failed in step 4");
		}
		try {
			setCategory(testCaseName);
			log("STEP 5: Fill in Category DD", Status.PASS);
		} catch (Exception e) {
			log("STEP 5: Fill in Category DD", Status.FAIL);
			throw new RuntimeException("Failed in step 5");
		}
		try {
			setPriority(testCaseName);
			log("STEP 6: Fill in Priority DD", Status.PASS);
		} catch (Exception e) {
			log("STEP 6: Fill in Priority DD", Status.FAIL);
			throw new RuntimeException("Failed in step 6");
		}
		try {
			setDescription(testCaseName);
			log("STEP 7: Fill in Note Description", Status.PASS);
		} catch (Exception e) {
			log("STEP 7: Fill in Note Description", Status.FAIL);
			throw new RuntimeException("Failed in step 7");
		}
		try {
			switchToNoteIframe();
			clickOnSaveNote();
			log("STEP 8: Click on Add Notes button", Status.PASS);
		} catch (Exception e) {
			log("STEP 8: Click on Add Notes button", Status.FAIL);
			throw new RuntimeException("Failed in step 8");
		}
		
	}
	
	public void editNotes(String testCaseName) {
		try {
			if(testCaseName.toLowerCase().contains("row")) {
				navigateToLeaseNoteROW();
			} else {
				navigateToAgreementNoteALT();
			}
			log("STEP 1: Navigate to Menu -Agreement Information page.Click on Agreement Notes tab", Status.PASS);
		} catch (Exception e) {
			log("STEP 1: Navigate to Menu -Agreement Information page.Click on Agreement Notes tab", Status.FAIL);
			throw new RuntimeException("Failed in step 1");
		}
		
		try {
			log("STEP 2: Click edit Notes button", Status.PASS);
		} catch (Exception e) {
			log("STEP 2: Click edit Notes button", Status.FAIL);
			throw new RuntimeException("Failed in step 2");
		}
		try {
			switchToNoteIframe();
			setActivityDate(testCaseName);
			log("STEP 3: Fill in Activity Date", Status.PASS);
		} catch (Exception e) {
			log("STEP 3: Fill in Activity Date", Status.FAIL);
			throw new RuntimeException("Failed in step 3");
		}
		try {
			setAgent(testCaseName);
			log("STEP 4: Fill in Agent DD", Status.PASS);
		} catch (Exception e) {
			log("STEP 4: Fill in Agent DD", Status.FAIL);
			throw new RuntimeException("Failed in step 4");
		}
		try {
			setCategory(testCaseName);
			log("STEP 5: Fill in Category DD", Status.PASS);
		} catch (Exception e) {
			log("STEP 5: Fill in Category DD", Status.FAIL);
			throw new RuntimeException("Failed in step 5");
		}
		try {
			setPriority(testCaseName);
			log("STEP 6: Fill in Priority DD", Status.PASS);
		} catch (Exception e) {
			log("STEP 6: Fill in Priority DD", Status.FAIL);
			throw new RuntimeException("Failed in step 6");
		}
		try {
			setDescription(testCaseName);
			log("STEP 7: Fill in Note Description", Status.PASS);
		} catch (Exception e) {
			log("STEP 7: Fill in Note Description", Status.FAIL);
			throw new RuntimeException("Failed in step 7");
		}
		try {
			switchToNoteIframe();
			clickOnSaveNote();
			log("STEP 8: Click on Add Notes button", Status.PASS);
		} catch (Exception e) {
			log("STEP 8: Click on Add Notes button", Status.FAIL);
			throw new RuntimeException("Failed in step 8");
		}
	}
	

}
