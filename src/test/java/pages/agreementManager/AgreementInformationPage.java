package pages.agreementManager;

import java.io.File;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.base.Excel;
import com.util.CommonFunction;
import com.util.TestUtil;

public class AgreementInformationPage extends BasePage {

	TestUtil util;
	CommonFunction commonFunction;

	public AgreementInformationPage(WebDriver driver) {
		this.driver = driver;
		util = new TestUtil(driver);
		commonFunction = new CommonFunction(driver);
	}
	
	By tabDocument = By.xpath(
			"//div[@id='idManageApproval_tabStrip']//a[normalize-space()='Documents' and not(contains(@class,'rtsDisabled'))]");
	By iframeDocument = By.xpath("//iframe[@name='AddDocuments']");
	public void clickOnDocumentTab() {
		util.waitForWebElementToBePresent(tabDocument, 60);
		util.waitFor(2000);
		util.click(tabDocument);
	}
	
	By btnAddDocument = By.xpath("//input[@id='idManageApproval_btnAddDocuments']");
	public void clickOnAddDocument() {
		util.click(btnAddDocument);
	}

	By drpCategory = By.xpath("//select[contains(@id,'RadUpload1category')]");
	public void selectCategory(String value) {
		if(util.isElementPresent(drpCategory)) {
			util.selectValueFromDropdown2(value, drpCategory);
		}
	}
	By description = By.xpath("//input[contains(@id,'RadUpload1Desc')]");
	public void setDescription(String value) {
		if(util.isElementPresent(description)) {
			util.inputText(description, value);
		}
	}
	
	By fileUpload = By.xpath("//input[contains(@id,'FileUploadfil')]");
	public void uploadFile() {
		String filepath = System.getProperty("user.dir") + File.separator + "test.txt";
		driver.findElement(fileUpload).sendKeys(filepath);
	}
	By iframeApproval = By.xpath("//iframe[@name='ManageApprovalDialog']");
	By addedApprovedNote = By
			.xpath("//table[contains(@id,'idManageApproval_NOTES_radYALGridControl')]//td[contains(.,'Approved')]");
	By loadDocumentFile = By.xpath("//input[contains(@id,'buttonSubmit_input')]");
	By documentSuccessMessage = By.xpath("//span[@id='lblResults']");
	By btnCloseDocument = By.xpath("//div[contains(@id,'_AddDocuments')]//a[@title='Close']");
	By refreshDocuments = By.xpath("//div[contains(@id,'Approval_DOCUMENT_radYALGridControlPanel')]//a[contains(@id,'_lnkRefresh')]");
	By tabNotes = By.xpath(
			"//div[@id='idManageApproval_tabStrip']//a[normalize-space()='Notes' and not(contains(@class,'rtsDisabled'))]");
	By tabLinkedApprovals = By.xpath(
			"//div[@id='idManageApproval_tabStrip']//a[normalize-space()='Linked Approvals' and not(contains(@class,'rtsDisabled'))]");
	By tabAssociatedRecords = By.xpath(
			"//div[@id='idManageApproval_tabStrip']//a[normalize-space()='Associated Records' and not(contains(@class,'rtsDisabled'))]");
	
	public void clickOnNoteTab() {
		util.click(tabNotes);
	}
	By btnAddNotes = By.xpath("//input[@id='idManageApproval_btnAddNotes']");
	By iframeNotes = By.xpath("//iframe[@name='radWinAgentNotes']");
	public void clickOnAddNote() {
		util.click(btnAddNotes);
	}
	By btnSaveNotes = By.xpath("//input[@id='AgntNotes_SN']");
	public void clickOnSaveNote() {
		util.click(btnSaveNotes);
	}
	By btnCloseNotes = By.xpath("//div[contains(@id,'_radWinAgentNotes')]//a[@title='Close']");
	public void clickOnCloseNote() {
		util.click(btnCloseNotes);
	}
	By btnRefreshNotes = By.xpath("//table[contains(@id,'idManageApproval_NOTES')]//a[contains(@id,'_lnkRefresh')]");
	public void clickOnRefreshNote() {
		util.click(btnRefreshNotes);
	}
	By notesMessages = By.xpath("//span[@id='AgntNotes_usrMessage']");
	public String getNotesMessage() {
		return util.getText(notesMessages);
	}

	public void clickOnAssociatedRecordsTab() {
		util.click(tabAssociatedRecords);
	}
	
	By tabLinkApproval = By.xpath("//div[@id='idManageApproval_tabStrip']//a[normalize-space()='Linked Approvals' and not(contains(@class,'rtsDisabled'))]");
	public void ClickonLinkApprovaltab() {
		util.click(tabLinkApproval);
	}
	By excelIcon = By.xpath("//a[contains(@id,'DownloadEXCELNew')]");
	
	By agreementOrLeaseTab = By.xpath("//span[text()='Agreement Forms' or text()='Lease Forms']");
	By addNewAgreementForm = By.xpath("//table[contains(@id,'LeaseInfoApproval')]//a[contains(@id,'lnkAddNewRecord')]");
	public void navigateToAgreementForm() {
		if(!util.isElementVisible(addNewAgreementForm)) {
			util.click(agreementOrLeaseTab);
		}
		util.click(addNewAgreementForm);
	}
	
	By projectApprovalIFrame = By.xpath("//iframe[@name='ManageApprovalDialog']");
	public void switchToProjectApprovalIframe() {
		util.waitForWebElementToBePresent(projectApprovalIFrame, IMPLICIT_WAIT);
		util.switchToIframe(projectApprovalIFrame);
	}
	
	By approvalTypeDRP = By.xpath("//input[contains(@id,'ParcelApprovalType') and @type='text']");
	public void selectApprovalType(String value) {
		util.inputText(approvalTypeDRP, value);
	}
	
	By typeOfPaymentTXT = By.xpath("//span[text()='Type of Payment:']/..//input[@type='text']");
	By paymentAmountTXT = By.xpath("//span[text()='Payment Amount:']/..//input[@type='text']");
	By agreementPhaseTXT = By.xpath("//span[text()='Agreement Phase:']/..//input[@type='text']");
	By dueDateTXT = By.xpath("//span[text()='Due Date:']/..//input[@type='text' and not(@style)]");
	By distributionTXT = By.xpath("//span[text()='Distribution:']/..//input[@type='text']");
	By additionalPaymentTXT = By.xpath("//span[text()='# of Additional Payments']/..//input[@type='text']");
	By frequencyIncreaseTXT = By.xpath("//span[text()='Frequency Increment:']/..//input[@type='text']");
	By paymentFrquencyTXT = By.xpath("//span[text()='Payment Frequency:']/..//input[@type='text']");
	By remarksTXT = By.xpath("//span[text()='Remarks:']/..//textarea");
	public void fillAlldetailsInProjectApproval(String typeOFPaymentValue, String agreementPhaseValue, String distributionValue) {
		util.inputText(typeOfPaymentTXT, typeOFPaymentValue);
		util.inputText(paymentAmountTXT, "1");
		util.inputText(agreementPhaseTXT, agreementPhaseValue);
		util.inputText(dueDateTXT, TestUtil.getCurrentDateTime("dd/MM/yyyy"));
		util.inputText(distributionTXT, distributionValue);
		util.inputText(additionalPaymentTXT, "1");
		util.inputText(frequencyIncreaseTXT, "1");
		util.inputText(paymentFrquencyTXT, "Days");
		util.inputText(remarksTXT, "Automation Remarks");
	}
	By saveProjectApproval = By.xpath("//input[@id='idManageApproval_btnSaveHeader']");
	By submitForReviewButton = By.xpath("//input[contains(@id,'btnSubmit')]");
	public void clickSaveProjectApproval() {
		util.click(saveProjectApproval);
		util.waitForWebElementToBeClickable(submitForReviewButton, IMPLICIT_WAIT);
	}
	
	By headerMessage = By.xpath("//span[contains(@id,'usrHeaderMessage')]");
	public void verifyHeaderMessage() {
		util.waitUntilElementDisplay(headerMessage);
		if(!util.getText(headerMessage).equals("Changes saved successfully.")) {
			throw new RuntimeException();
		}
	}

	// Associated records
	By drpAddProjects = By.xpath(
			"//*[normalize-space()='Projects:']/../following-sibling::tr//input[@id='idManageApproval_Projects_Input']");
	By drpAddProjectsClose = By.xpath(
			"//*[normalize-space()='Projects:']/../following-sibling::tr//a[contains(@id,'Approval_Projects_Arrow')]");
	By drpListOfTrack = By.xpath(
			"//*[normalize-space()='List of Tract:']/../following-sibling::tr//input[@id='idManageApproval_Tract_Agreement_Input']");
	By drpListOfTrackClose = By.xpath(
			"//*[normalize-space()='List of Tract:']/../following-sibling::tr//a[contains(@id,'Tract_Agreement_Arrow')]");

	By drpListofLeases = By.xpath(
			"//*[normalize-space()='List of Leases:']/../following::div[@id='idManageApproval_Lease_Association']");
	By drpListofLeasesClose = By.xpath(
			"//*[normalize-space()='List of Leases:']/../following-sibling::tr//a[@id='idManageApproval_Lease_Association_Arrow']");
	By btnGoProject = By.xpath("//input[@id='idManageApproval_btnGo']");
	By btnAddListOfTract = By.xpath("//input[@id='idManageApproval_btnAddApprovalAssociation']");
	By btnRefreshListTract = By.xpath(
			"//table[contains(@id,'idManageApproval_ASSOCIATED_APPROVAL_TRACTS')]//a[contains(@id,'_lnkRefresh')]");
	By listTractMessage = By.xpath("//span[@id='idManageApproval_msgAgreement']");
	By btnDeleteListTract = By.xpath("//table[contains(@id,'ASSOCIATED_APPROVAL_TRACTS')]//input[@alt='Delete']");
	By btnDeleteOk = By.xpath("//div[contains(@class,'rwDialogPopup')]//span[text()='OK']");
	By listTractUsrMessage = By.xpath("//span[@id='idManageApproval_ASSOCIATED_APPROVAL_TRACTS_usrMessage']");
	public void selectProject(String value) {
		util.selectValueFromDropdown(drpAddProjects, value);
		util.click(drpAddProjectsClose);
	}
	
	public void clickOnGoProject() {
		util.click(btnGoProject);
	}
	
	public void selectListOfTrack(String value) {
		util.selectValueFromDropdown(drpListOfTrack, value);
		util.click(drpListOfTrackClose);
	}
	public void selectListOfLeases(String value) {
		util.selectValueFromDropdown(drpListofLeases, value);
		util.click(drpListofLeasesClose);
	}
	
	public void clickOnListOfTract() {
		util.click(btnAddListOfTract);
	}

	public void refreshListOfTract() {
		util.click(btnRefreshListTract);
	}

	public String getListOfTractMessage() {
		return util.getText(listTractMessage);
	}

	public void deleteListOfTract() {
		util.waitForWebElementToBePresent(btnDeleteListTract);
		util.click(btnDeleteListTract);
		util.waitFor(2000);
		util.click(btnDeleteOk);

	}
	public String getListTractUsrMessage() {
		return util.getText(listTractUsrMessage);
	}

	By tabApproval = By.xpath(
			"//div[@id='idManageApproval_tabStrip']//a[normalize-space()='Approvals' and not(contains(@class,'rtsDisabled'))]");
	By btnSubmitForReview = By.xpath("//input[contains(@id,'WorkflowApprove_btnSubmit')]");
	By autoApproved = By.xpath("//a[text()='Approved']");
	public void submitTheFormForReview() {
		try {
			util.click(tabApproval);
			util.click(btnSubmitForReview);
			util.waitForWebElementToBePresent(autoApproved, 30);
			Assert.assertTrue(util.isElementPresent(autoApproved), "Form is not auto approved");
			log("STEP 22: Click on submit for review on the form", Status.PASS);
			log("Form is auto approved");
			util.switchToDefaultContent();
		} catch (Exception e) {
			log("STEP 22: Not Click on submit for review on the form", Status.FAIL);
		}
	}
	
	public void addAgreementFormDetails(Map<String, String> map, String testcaseName, boolean isAllSteps) {
		
		try {
			navigateToAgreementForm();
			switchToProjectApprovalIframe();
			log("STEP 1: Click on Add button under the  Project Approvals panels", Status.PASS);
		} catch (Exception e) {
			log("STEP 1: Click on Add button under the  Project Approvals panels", Status.FAIL);
			throw new RuntimeException("Failed in step 1");
		}
		try {
			selectApprovalType(map.get(Excel.AddProjectALT));
			log("STEP 2: Select Any Work Flow from Approval Type DD", Status.PASS);
		} catch (Exception e) {
			log("STEP 2: Select Any Work Flow from Approval Type DD", Status.FAIL);
			throw new RuntimeException("Failed in step 2");
		}
		try {
//			fillAlldetailsInProjectApproval();
			log("STEP 3: Select all mandatory field", Status.PASS);
		} catch (Exception e) {
			log("STEP 3: Select all mandatory field", Status.FAIL);
			throw new RuntimeException("Failed in step 3");
		}
		try {
			log("STEP 4: Verify any validations if any", Status.PASS);
		} catch (Exception e) {
			log("STEP 4: Verify any validations if any", Status.FAIL);
			throw new RuntimeException("Failed in step 4");
		}
		
		try {
			clickSaveProjectApproval();
			verifyHeaderMessage();
			log("STEP 5: click on save button", Status.PASS);
		} catch (Exception e) {
			log("STEP 5: click on save button", Status.FAIL);
			throw new RuntimeException("Failed in step 5");
		}
		
		try {
			clickOnDocumentTab();
			clickOnAddDocument();
			log("STEP 6: Selected the Douments tab and clicked on add button", Status.PASS);
		} catch (Exception e) {
			log("STEP 6: Select the Douments tab and click on add button", Status.FAIL);
			throw new RuntimeException("Failed in step 6");
		}

		try {
			util.switchToIframe(iframeDocument);
			selectCategory(map.get(Excel.DocCategory));
			log("STEP 7: Select the document category", Status.PASS);
		} catch (Exception e) {
			log("STEP 7: Select the document category", Status.FAIL);
			throw new RuntimeException("Failed in step 7");
		}

		try {
			setDescription(map.get(Excel.DocComments));
			log("STEP 8: Entered a Title of document", Status.PASS);
		} catch (Exception e) {
			log("STEP 8: Entered a Title of document", Status.FAIL);
			throw new RuntimeException("Failed in step 8");
		}

		try {
			uploadFile();
			util.waitFor(10000);
			log("STEP 9: Document is uploaded", Status.PASS);
		} catch (Exception e) {
			log("STEP 9: Document is not uploaded", Status.FAIL);
			throw new RuntimeException("Failed in step 9");
		}

		try {
			util.click(loadDocumentFile);
			util.waitUntilElementDisplay(documentSuccessMessage);
			String msg = util.getText(documentSuccessMessage);
			Assert.assertEquals(msg, "Loaded: test.txt", "Success message is mismatch");
			util.switchToDefaultContent();
			util.switchToIframe(iframeApproval);
			util.click(btnCloseDocument);
			util.waitFor(2000);
			util.click(refreshDocuments);
			log("STEP 10: Clicked on Load documents button", Status.PASS);
		} catch (Exception e) {
			log("STEP 10: Clicked on Load documents button", Status.FAIL);
			throw new RuntimeException("Failed in step 10");
		}

		// Notes Tab
		try {
			clickOnNoteTab();
			clickOnAddNote();
			log("STEP 11: Selected the Notes  tab and click on add button", Status.PASS);
		} catch (Exception e) {
			log("STEP 11: Selected the Notes  tab and click on add button", Status.FAIL);
			throw new RuntimeException("Failed in step 11");
		}
		// select filters
		try {
			util.switchToIframe(iframeNotes);
			log("STEP 12: select the category", Status.PASS);
		} catch (Exception e) {
			log("STEP 12: select the category", Status.FAIL);
			throw new RuntimeException("Failed in step 12");
		}

		try {
			//current time is already added in description
			log("STEP 13: Enter the description", Status.PASS);
		} catch (Exception e) {
			log("STEP 13: Enter the description", Status.FAIL);
			throw new RuntimeException("Failed in step 13");
		}
		try {
			clickOnSaveNote();
			Assert.assertEquals(getNotesMessage(), "Note submitted and auto-approved.", "Success message is mismatch");
			log("STEP 14: Clicked on Save Note button", Status.PASS);
		} catch (Exception e) {
			log("STEP 14: Clicked on Save Note button", Status.FAIL);
			throw new RuntimeException("Failed in step 14");
		}
		try {
			util.switchToDefaultContent();
			util.switchToIframe(iframeApproval);
			clickOnCloseNote();
			clickOnRefreshNote();
			// Verify added record
			Assert.assertTrue(util.isElementPresent(addedApprovedNote), "Record is not added properly");
			log("STEP 15: Clicked on edit link", Status.PASS);
		} catch (Exception e) {
			log("STEP 15: Clicked on edit link", Status.FAIL);
			throw new RuntimeException("Failed in step 15");
		}

		try {
			ClickonLinkApprovaltab();
			log("STEP 16: Navigate to the Link Approval tab", Status.PASS);
		} catch (Exception e) {
			log("STEP 16: Navigate to the Link Approval tab", Status.FAIL);
			throw new RuntimeException("Failed in step 16");
		}

		try {
			Assert.assertTrue(util.isElementPresent(excelIcon), "Unable to CLick on Excel Download");
			util.click(excelIcon);
			log("STEP 17: Clicked on Download Excel file", Status.PASS);
		} catch (Exception e) {
			log("STEP 17: Clicked on Download Excel file", Status.FAIL);
			throw new RuntimeException("Failed in step 15");
		}

		try {
			// Associated record tab
			clickOnAssociatedRecordsTab();
			log("STEP 18: Navigate to the associated records tab", Status.PASS);
		} catch (Exception e) {
			log("STEP 18: Navigate to the associated records tab", Status.FAIL);
			throw new RuntimeException("Failed in step 16");
		}

		try {
			selectProject(map.get(Excel.ProjectName));
			clickOnGoProject();
			log("STEP 19: Selected a project from DD and click on GO button", Status.PASS);
		} catch (Exception e) {
			log("STEP 19: Selected a project from DD and click on GO button", Status.FAIL);
			throw new RuntimeException("Failed in step 17");
		}
		
		try {
			selectListOfTrack(map.get(Excel.ListOfTrack));
			Thread.sleep(5000);
			selectListOfLeases(map.get(Excel.ListOfLeases));
			refreshListOfTract();
			Thread.sleep(5000);
			clickOnListOfTract();
			Assert.assertEquals(getListOfTractMessage(), "Requested association created successfully",
					"Success message is mismatch");
			log("STEP 20: Select a Tract list from the DD and click on Add ", Status.PASS);
		} catch (Exception e) {
			log("STEP 20: Select a Tract list from the DD and click on Add ", Status.FAIL);
			throw new RuntimeException("Failed in step 18");
		}
		
		if (isAllSteps) {
			try {
				deleteListOfTract();
				Thread.sleep(5000);
				Assert.assertEquals(getListTractUsrMessage(), "Changes saved successfully!",
						"Success message is mismatch");
				log("STEP 21: Click on delete (X) icon", Status.PASS);
			} catch (Exception e) {
				log("STEP 21 Click on delete (X) icon", Status.FAIL);
				throw new RuntimeException("Failed in step 19");
			}
			try {
				util.switchToDefaultContent();
				log("STEP 22: Click on ok button", Status.PASS);
			} catch (Exception e) {
				log("STEP 22: Click on ok button", Status.FAIL);
				throw new RuntimeException("Failed in step 20");
			}
		}
		
	}

}
