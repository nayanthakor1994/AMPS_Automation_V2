package pages.agreementManager;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.base.Excel;
import com.util.CommonFunction;
import com.util.TestUtil;

public class AddNewInformationPage extends BasePage {

	TestUtil util;
	CommonFunction commonFunction;
	public static String AGREEMENT_NUMBER;

	public AddNewInformationPage(WebDriver driver) {
		this.driver = driver;
		util = new TestUtil(driver);
		commonFunction = new CommonFunction(driver);
	}

	By navProjectMenu = By.xpath("//a//span[@class='rmText rmExpandDown' and contains(text(),'Project')] ");
	By navAgreementManager = By.xpath("//a//span[contains(text(),'Agreement Manager')] ");
	By navLeaseManager = By.xpath("//a//span[contains(text(),'Lease Manager')] ");
	By navAgreementInformation = By.xpath("//a//span[contains(text(),'Agreement Information')] ");
	By pageTitle = By.xpath("//table[contains(@id,'LSLST_RadGridTracts')]//th//a[text()='Agreement Information']");
	By agreementNumber = By.xpath("//span[contains(@id,'_Lease_Number_wrapper')]/input[contains(@id,'_Lease_Number') and @type='text']");

	public void navigateToAgreementInformationALT() {
		util.waitUntilElementDisplay(navProjectMenu);
		util.click(navProjectMenu);
		util.waitUntilElementDisplay(navAgreementManager);
		util.click(navAgreementManager);
		util.waitFor(2000);
		if(!util.isElementPresent(navAgreementInformation, 5)) {
			util.click(navAgreementManager);
			util.waitFor(2000);
		}
		util.click(navAgreementInformation);
		util.waitForWebElementToBePresent(pageTitle, 20);
		if (!util.isElementPresent(pageTitle)) {
			throw new RuntimeException();
		}
	}
	
	public void navigateToAgreementInformationROW() {
		util.waitUntilElementDisplay(navProjectMenu);
		util.click(navProjectMenu);
		util.waitUntilElementDisplay(navLeaseManager);
		util.click(navLeaseManager);
		util.waitUntilElementDisplay(addButton);
	}

	By addButton = By.xpath("//table[contains(@id,'LSLST_RadGridTracts')]//a[contains(@id,'_AddJob')]");
	By assignOrModifyAgreementName = By.xpath("//a[contains(@id,'_changeEntName') and @href]");

	private void clickOnAddButton() {
		util.click(addButton);
		util.waitFor(2000);
		util.waitUntilElementDisappear(assignOrModifyAgreementName);
		util.waitForWebElementToBePresent(agreementNumber, 10);
	}

	private void verifyAutoPopulatedAgreementNumber() {
		AGREEMENT_NUMBER = util.getAttributeValue(agreementNumber, "value");
		if (AGREEMENT_NUMBER.length() == 0) {
			throw new RuntimeException();
		}
	}
	
	private void addLeaseNumber(String value) {
		if (!commonFunction.checkNA(value)) {
			util.inputText(agreementNumber, value);
		}
	}

	By txtAgreementType1 = By.xpath("//input[contains(@id,'Lease_Type_ID') and @type='text']");
	By txtAgreementType2 = By.xpath("//span/parent::div[contains(@id,'DOCUMENT_TYPE_ID')]");

	private void addAgreementType(String value) {
		if (!commonFunction.checkNA(value)) {
			if(util.isElementPresent(txtAgreementType1)) {
				util.inputTextAndPressTab(txtAgreementType1, value);
			} else if(util.isElementPresent(txtAgreementType2)) {
				util.selectValueFromDropdown(txtAgreementType2, value);
			}
		}
	}

	By txtAgreementStatus = By.xpath("//input[contains(@id,'Lease_Status_ID') and @type='text']");

	private void addAgreementStatus(String value) {
		if (!commonFunction.checkNA(value))
			util.inputTextAndPressTab(txtAgreementStatus, value);
	}

	By txtOperatingCompany = By.xpath("//input[contains(@id,'_YALComboBox') and @type='text']");

	private void addOperatingCompany(String value) {
		if (!commonFunction.checkNA(value))
			util.inputTextAndPressTab(txtOperatingCompany, value);
	}

	By saveButton = By.xpath("//input[contains(@id,'_btnSave') and not(@disabled)]");

	private void clickOnSaveButton(String testcaseName) {
		util.click(saveButton);
		if (testcaseName.toLowerCase().contains("row")) {
			util.waitFor(15000);
		} else {
			By savedAgreementInformation = By.xpath(
					"//div[contains(@id,'_RadDockTractSS')]//td[text()='Agreement Number']/following-sibling::td[contains(.,'"
							+ AGREEMENT_NUMBER + "')]");
			util.waitForWebElementToBePresent(savedAgreementInformation, 20);
		}
	}

	By btnAddterm = By.xpath("//input[contains(@id,'_btnAddProjectPhase') or contains(@id,'btnAddLeaseTerm')]");
	By leaseDocumentIframe = By.xpath("//iframe[@name='LeaseDocument']");
	By drpAgreementTerm = By.xpath("//input[contains(@id,'Period_Type_ID_') and @type='text']");

	private void clickOnAddTerm() {
		util.click(btnAddterm);
		util.waitForWebElementToBePresent(leaseDocumentIframe, 20);
	}

	private void switchToTermIframe() {
		util.switchToIframe(leaseDocumentIframe);
	}

	private void addAgreementTerm(String value) {
		if (!commonFunction.checkNA(value) && util.isElementPresent(drpAgreementTerm)) {
			util.inputTextAndPressTab(drpAgreementTerm, value);
		}
	}

	By chkExtensionProvision = By.xpath("//input[contains(@id,'chkExtensionProvision')]");

	private void clickExtensionProvision() {
		if (util.isElementPresent(chkExtensionProvision)) {
			util.click(chkExtensionProvision);
		}
	}

	By chkActive = By.xpath("//input[contains(@id,'active_ind')]");

	private void clickActiveCheckbox() {
		util.click(chkActive);
	}

	By effectiveDate = By.xpath("//input[contains(@id,'Effective_Date_dateInput')]");

	private void addEffectiveDate(String value) {
		if (!commonFunction.checkNA(value)) {
			util.inputTextAndPressTab(effectiveDate, value);
		}
	}

	By termYear = By.xpath("//input[contains(@id,'Term_Years') and @type='text']");

	private void addTermYear(String value) {
		if (!commonFunction.checkNA(value)) {
			util.inputTextAndPressTab(termYear, value);
		}
	}

	By termMonths = By.xpath("//input[contains(@id,'Term_Months') and @type='text']");

	private void addTermMonths(String value) {
		if (!commonFunction.checkNA(value)) {
			util.inputTextAndPressTab(termMonths, value);
		}
	}

	By termDays = By.xpath("//input[contains(@id,'Term_Days') and @type='text']");

	private void addTermDays(String value) {
		if (!commonFunction.checkNA(value)) {
			util.inputTextAndPressTab(termDays, value);
		}
	}

	By expirationDate = By.xpath("//input[contains(@id,'Expiration_Date_dateInput') and @type='text']");

	private void addExpirationDate(String value) {
		if (!commonFunction.checkNA(value)) {
			util.inputTextAndPressTab(expirationDate, value);
		}
	}

	By flatPaymentAmount = By.xpath("//input[contains(@id,'Flat_Payment_Amount') and @type='text']");

	private void addFlatPaymentAmount(String value) {
		if (!commonFunction.checkNA(value)) {
			util.inputTextAndPressTab(flatPaymentAmount, value);
		}
	}

	By txtareaComments = By.xpath("//textarea[contains(@id,'Comments')]");

	private void addComments(String value) {
		if (!commonFunction.checkNA(value)) {
			util.inputTextAndPressTab(txtareaComments, value);
		}
	}

	By iframeSaveButton = By.xpath("//input[@id='btnSave']");

	private void clickOnSaveButtonIframe() {
		util.click(iframeSaveButton);
		commonFunction.waitForSuccessMessage();
		if (!commonFunction.getSuccessMessage().equals("Changes saved successfully!")) {
			throw new RuntimeException();
		}
	}

	By closeLeaseDocumentIframe = By.xpath("//div[contains(@id,'_LeaseDocument')]//a[@title='Close']");

	private void closeTermIframe() {
		util.switchToDefaultContent();
		util.click(closeLeaseDocumentIframe);
		util.waitFor(5000);
	}
	
	By refreshButton = By.xpath("//table[contains(@id,'LEASE_PAYMENTS_ygccontainTable')]//img/parent::a[contains(@id,'Button')]");
	By termTableRecord = By.xpath("//table[contains(@id,'LEASE_PAYMENTS_ygccontainTable')]/tbody//tr[contains(@id,'LEASE_PAYMENTS_radYALGridControl')]");
	private void refreshTermTableAndCheckRecord() {
		if(util.isElementPresent(refreshButton)) {
			util.click(refreshButton);
			try {
				util.waitForWebElementToBePresent(termTableRecord, IMPLICIT_WAIT);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	By duplicateButton = By.xpath("//input[contains(@id,'_btnDuplicate')]");
	By leaseCopyIframe = By.xpath("//iframe[@name='LeaseCopy']");
	By duplicateSaveButton = By.xpath("//input[@id='btnLinkLogs']");

	private void clickOnDuplicateButton() {
		try {
			util.click(duplicateButton);
			util.waitForWebElementToBePresent(leaseCopyIframe, 20);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void switchToLeaseCopyIframe() {
		util.switchToIframe(leaseCopyIframe);
	}

	private void clickOnDuplicateSaveButton() {
		util.click(duplicateSaveButton);
		commonFunction.waitForSuccessAddMessage();
		if (!commonFunction.getSuccessAddMessage()
				.equals("New lease has been created. Please close window to see new lease record.")) {
			throw new RuntimeException();
		}
	}

	By closeLeaseCopyIframe = By.xpath("//div[contains(@id,'_LeaseCopy')]//a[@title='Close']");

	private void closeLeaseCopyIframe() {
		util.switchToDefaultContent();
		util.click(closeLeaseCopyIframe);
		util.waitFor(5000);
	}

	public String addAgreementInformation(Map<String, String> map, String testcaseName) {
		try {
			if(testcaseName.toLowerCase().contains("row")) {
				navigateToAgreementInformationROW();
			} else {
				navigateToAgreementInformationALT();
			}
			log("STEP 1:  Navigate to Menu - Agreement Information page", Status.PASS);
		} catch (Exception e) {
			log("STEP 1:  Navigate to Menu - Agreement Information page", Status.FAIL);
			throw new RuntimeException("Failed in step 1");
		}

		try {
			clickOnAddButton();
			log("STEP 2:  Click Add button", Status.PASS);
		} catch (Exception e) {
			log("STEP 2:  Click Add button", Status.FAIL);
			throw new RuntimeException("Failed in step 2");
		}

		try {
			if(testcaseName.toLowerCase().contains("row")) {
//				AGREEMENT_NUMBER = util.randomNumber();
//				addLeaseNumber(AGREEMENT_NUMBER);
			} else {
				verifyAutoPopulatedAgreementNumber();
			}
			log("STEP 3:  Value auto populate in Agreement Number Field", Status.PASS);
		} catch (Exception e) {
			log("STEP 3:  Value auto populate in Agreement Number Field", Status.FAIL);
			throw new RuntimeException("Failed in step 3");
		}

		try {
			addAgreementType(map.get(Excel.AgreementType));
			log("STEP 4:  Fill in Agreement Type", Status.PASS);
		} catch (Exception e) {
			log("STEP 4:  Fill in Agreement Type", Status.FAIL);
			throw new RuntimeException("Failed in step 4");
		}

		try {
			addAgreementStatus(map.get(Excel.AgreementStatus));
			log("STEP 5:  Fill in Agreement Status", Status.PASS);
		} catch (Exception e) {
			log("STEP 5:  Fill in Agreement Status", Status.FAIL);
			throw new RuntimeException("Failed in step 5");
		}

		try {
			addOperatingCompany(map.get(Excel.OperatingCompany));
			log("STEP 6:  Fill in Operating Company", Status.PASS);
		} catch (Exception e) {
			log("STEP 6:  Fill in Operating Company", Status.FAIL);
			throw new RuntimeException("Failed in step 6");
		}
		try {
			clickOnSaveButton(testcaseName);
			log("STEP 7:  Click on save button", Status.PASS);
		} catch (Exception e) {
			log("STEP 7:  Click on save button", Status.FAIL);
			throw new RuntimeException("Failed in step 7");
		}
		try {
			clickOnAddTerm();
			log("STEP 8:  Click on Add Term button", Status.PASS);
		} catch (Exception e) {
			log("STEP 8:  Click on Add Term button", Status.FAIL);
			throw new RuntimeException("Failed in step 8");
		}

		try {
			switchToTermIframe();
			addAgreementTerm(map.get(Excel.AgreementTerm));
			log("STEP 9:  Fill in Agreement Term", Status.PASS);
		} catch (Exception e) {
			log("STEP 9:  Fill in Agreement Term", Status.FAIL);
			throw new RuntimeException("Failed in step 9");
		}
		try {
			clickExtensionProvision();
			log("STEP 10:  Click on Extension Provision Check box", Status.PASS);
		} catch (Exception e) {
			log("STEP 10:  Click on Extension Provision Check box", Status.FAIL);
			throw new RuntimeException("Failed in step 10");
		}
		try {
			clickActiveCheckbox();
			log("STEP 11:  Click on Active Check box", Status.PASS);
		} catch (Exception e) {
			log("STEP 11:  Click on Active Check box", Status.FAIL);
			throw new RuntimeException("Failed in step 11");
		}
		try {
			addEffectiveDate(map.get(Excel.EffectiveDate));
			log("STEP 12:  Select Effective Date field", Status.PASS);
		} catch (Exception e) {
			log("STEP 12:  Select Effective Date field", Status.FAIL);
			throw new RuntimeException("Failed in step 12");
		}

		try {
			addTermYear(map.get(Excel.TermYMD).split("-")[0]);
			log("STEP 13:  Enter the value in Term Year Text field", Status.PASS);
		} catch (Exception e) {
			log("STEP 13:  Enter the value in Term Year Text field", Status.FAIL);
			throw new RuntimeException("Failed in step 13");
		}
		try {
			addTermMonths(map.get(Excel.TermYMD).split("-")[1]);
			log("STEP 14:  Enter the value in Term Month Text field", Status.PASS);
		} catch (Exception e) {
			log("STEP 14:  Enter the value in Term Month Text field", Status.FAIL);
			throw new RuntimeException("Failed in step 14");
		}
		try {
			addTermDays(map.get(Excel.TermYMD).split("-")[2]);
			log("STEP 15:  Enter the value in Term Days Text field", Status.PASS);
		} catch (Exception e) {
			log("STEP 15:  Enter the value in Term Days Text field", Status.FAIL);
			throw new RuntimeException("Failed in step 15");
		}

		try {
			addExpirationDate(map.get(Excel.ExpirationDate));
			log("STEP 16:  Select Expiration Date field", Status.PASS);
		} catch (Exception e) {
			log("STEP 16:  Select Expiration Date field", Status.FAIL);
			throw new RuntimeException("Failed in step 16");
		}
		try {
			addFlatPaymentAmount(map.get(Excel.Amount));
			log("STEP 17:  Enter the value in Flat Payment Amount Text field", Status.PASS);
		} catch (Exception e) {
			log("STEP 17:  Enter the value in Flat Payment Amount Text field", Status.FAIL);
			throw new RuntimeException("Failed in step 17");
		}
		try {
			addComments(map.get(Excel.Comments));
			log("STEP 18:  Enter the value in Comments Text field", Status.PASS);
		} catch (Exception e) {
			log("STEP 18:  Enter the value in Comments Text field", Status.FAIL);
			throw new RuntimeException("Failed in step 18");
		}
		try {
			clickOnSaveButtonIframe();
			closeTermIframe();
			refreshTermTableAndCheckRecord();
			log("STEP 19:  Click on save button", Status.PASS);
		} catch (Exception e) {
			log("STEP 19:  Click on save button", Status.FAIL);
			throw new RuntimeException("Failed in step 19");
		}
		if(!testcaseName.toLowerCase().contains("row")) {
			try {
				clickOnDuplicateButton();
				log("STEP 20: Click on Duplicate button", Status.PASS);
			} catch (Exception e) {
				log("STEP 20: Click on Duplicate button", Status.FAIL);
				throw new RuntimeException("Failed in step 20");
			}
			try {
				switchToLeaseCopyIframe();
				clickOnDuplicateSaveButton();
				closeLeaseCopyIframe();
				log("STEP 21: Click on Save button", Status.PASS);
			} catch (Exception e) {
				log("STEP 21: Click on Save button", Status.FAIL);
				throw new RuntimeException("Failed in step 21");
			}
		}
		return AGREEMENT_NUMBER;
	}

	By legalDescriptionIframe = By.xpath("//iframe[contains(@id,'Lease_Description')]");
	By agreementInfoTab = By.xpath("//div[contains(@id,'LSINFO_radPanels')]//span[text()='Agreement Information']");
	private void switchToLegalDescriptionIframe() {
		if(!util.isElementVisible(legalDescriptionIframe,5)) {
			util.click(agreementInfoTab);
			util.waitFor(2000);
		} 
		util.switchToIframe(legalDescriptionIframe);
	}
	
	By legalDescription = By.xpath("//body");
	private void addLegalDescription(String value) {
		util.inputText(legalDescription, value);
	}
	
	By saveButtonAgreementInfo = By.xpath("//input[contains(@id,'btnSave2')]");
	private void clickSaveButtonAgreementInfo() {
		util.switchToDefaultContent();
		util.click(saveButtonAgreementInfo);
	}
	public void addAgreementInformation(String testcaseName) {
		try {
			switchToLegalDescriptionIframe();
			addLegalDescription("Automation comments");
			log("STEP 1: Fill in Legal Description text box", Status.PASS);
		} catch (Exception e) {
			log("STEP 1: Fill in Legal Description text box", Status.FAIL);
			throw new RuntimeException("Failed in step 1");
		}
		
		try {
			clickSaveButtonAgreementInfo();
			log("STEP 2: Click on Save", Status.PASS);
		} catch (Exception e) {
			log("STEP 2: Click on Save", Status.FAIL);
			throw new RuntimeException("Failed in step 2");
		}
	}
	
	
}
