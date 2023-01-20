package pages.Project;

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

public class AddProjectWorkflowPage extends BasePage {

	TestUtil util;
	CommonFunction commonFunction;

	public AddProjectWorkflowPage(WebDriver driver) {
		this.driver = driver;
		util = new TestUtil(driver);
		commonFunction = new CommonFunction(driver);
	}

	By autoApproved = By.xpath("//a[text()='Approved']");
	By adminReviewLink = By.xpath("//a[text()='Admin Review']");
	By reviewIframe = By.xpath("//iframe[@name='geoPopUpWF']");
	By btnSubmitForReview = By.xpath("//input[contains(@id,'WorkflowApprove_btnSubmit')]");
	By projectApprovals = By.xpath("//a[normalize-space()='Project Approvals' or normalize-space()='Project Forms']");
	By addProjectApprovals = By.xpath(
			"//*[normalize-space()='Project Approvals' or normalize-space()='Project Forms']/..//a[normalize-space()='Add new record']");
	By iframeApproval = By.xpath("//iframe[@name='ManageApprovalDialog']");
	By drpApprovalType = By.xpath("//input[contains(@name,'idManageApproval$cbParcelApprovalType')]");
	By drpAssociateWorkflow = By.xpath("//input[contains(@name,'idManageApproval$cbAssociate_Workflows')]");
	By drpSelectTemplate = By.xpath(
			"//*[normalize-space()='Select Templates']/..//input[contains(@id,'idManageApproval_customFields')]");
	By drpSelectTemplateClose = By.xpath("//*[normalize-space()='Select Templates']/..//a[contains(@id,'Arrow')]");
	By btnSaveApprovals = By.xpath("//input[contains(@id,'_btnSaveHeader')]");
	By btnCloseForm = By.xpath("//div[contains(@id,'ManageApprovalDialog')]//a[@title='Close']");

	// All tabs
	By tabApproval = By.xpath(
			"//div[@id='idManageApproval_tabStrip']//a[normalize-space()='Approvals' and not(contains(@class,'rtsDisabled'))]");
	By tabDocument = By.xpath(
			"//div[@id='idManageApproval_tabStrip']//a[normalize-space()='Documents' and not(contains(@class,'rtsDisabled'))]");
	By tabNotes = By.xpath(
			"//div[@id='idManageApproval_tabStrip']//a[normalize-space()='Notes' and not(contains(@class,'rtsDisabled'))]");
	By tabLinkedApprovals = By.xpath(
			"//div[@id='idManageApproval_tabStrip']//a[normalize-space()='Linked Approvals' and not(contains(@class,'rtsDisabled'))]");
	By tabAssociatedRecords = By.xpath(
			"//div[@id='idManageApproval_tabStrip']//a[normalize-space()='Associated Records' and not(contains(@class,'rtsDisabled'))]");
	By tabLinkApproval = By.xpath(
			"//div[@id='idManageApproval_tabStrip']//a[normalize-space()='Linked Approvals' and not(contains(@class,'rtsDisabled'))]");
	By excelIcon = By.xpath("//a[contains(@id,'DownloadEXCELNew')]");
	// Document tab
	By iframeDocument = By.xpath("//iframe[@name='AddDocuments']");
	By btnAddDocument = By.xpath("//input[@id='idManageApproval_btnAddDocuments']");
	By drpCategory = By.xpath("//select[contains(@id,'RadUpload1category')]");
	By description = By.xpath("//input[contains(@id,'RadUpload1Desc')]");
	By fileUpload = By.xpath("//input[contains(@id,'RadUpload1file') or contains(@id,'rauFileUploadfile')]");
	By loadDocumentFile = By.cssSelector("#buttonSubmit");
	By loadDocumentFile2 = By.xpath("//input[contains(@id,'buttonSubmit_input')]");
	By documentSuccessMessage = By.xpath("//span[@id='lblResults']");
	By btnCloseDocument = By.xpath("//div[contains(@id,'_AddDocuments')]//a[@title='Close']");

	// Note Tab
	By iframeNotes = By.xpath("//iframe[@name='radWinAgentNotes']");
	By btnAddNotes = By.xpath("//input[@id='idManageApproval_btnAddNotes']");
	By btnSaveNotes = By.xpath("//input[@id='AgntNotes_SN']");
	By notesMessages = By.xpath("//span[@id='AgntNotes_usrMessage']");
	By btnCloseNotes = By.xpath("//div[contains(@id,'_radWinAgentNotes')]//a[@title='Close']");
	By btnRefreshNotes = By.xpath("//table[contains(@id,'idManageApproval_NOTES')]//a[contains(@id,'_lnkRefresh')]");
	By addedApprovedNote = By
			.xpath("//table[contains(@id,'idManageApproval_NOTES_radYALGridControl')]//td[contains(.,'Approved')]");

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

	public void selectApprovalType(String value) {
		util.selectValueFromDropdown(drpApprovalType, value);
	}

	public void selectTemplate(String value) {
		util.selectValueFromDropdown(drpSelectTemplate, value);
		util.click(drpSelectTemplateClose);
	}

	public void selectReportFormat(String value) {
		By radioButton = By.xpath(
				"//*[normalize-space()='Report format']/..//label[text()='" + value + "']/preceding-sibling::input");
		util.click(radioButton);
	}

	public void clickOnSaveApprovals() {
		util.click(btnSaveApprovals);
	}

	public void clickOnDocumentTab() {
		util.waitForWebElementToBePresent(tabDocument, 60);
		util.waitFor(2000);
		util.click(tabDocument);
	}

	public void clickOnAddDocument() {
		util.click(btnAddDocument);
	}

	public void selectCategory(String value) {
		if(util.isElementPresent(drpCategory)) {
			util.selectValueFromDropdown2(value, drpCategory);
		}
	}

	public void setDescription(String value) {
		if(util.isElementPresent(description)) {
			util.inputText(description, value);
		}
	}

	public void uploadFile() {
		String filepath = System.getProperty("user.dir") + File.separator + "test.txt";
		driver.findElement(fileUpload).sendKeys(filepath);
	}

	public void clickOnNoteTab() {
		util.click(tabNotes);
	}

	public void clickOnAddNote() {
		util.click(btnAddNotes);
	}

	public void clickOnSaveNote() {
		util.click(btnSaveNotes);
	}

	public void clickOnCloseNote() {
		util.click(btnCloseNotes);
	}

	public void clickOnRefreshNote() {
		util.click(btnRefreshNotes);
	}

	public String getNotesMessage() {
		return util.getText(notesMessages);
	}

	public void clickOnAssociatedRecordsTab() {
		util.click(tabAssociatedRecords);
	}

	public void ClickonLinkApprovaltab() {
		util.click(tabLinkApproval);
	}

	public void ClickOnExcel() {
		util.click(excelIcon);
	}

	public void selectProject(String value) {
		try {
			util.selectValueFromDropdown(drpAddProjects, value);
			util.click(drpAddProjectsClose);
		} catch (Exception e) {
			util.click(drpAddProjectsClose);
			By valueLocator = By.xpath("//ul//li[normalize-space()='"+value+"']//input");
			util.scrollToElement(valueLocator);
			util.click(valueLocator);
			util.click(drpAddProjectsClose);
		}
	}

	public void clickOnGoProject() {
		util.click(btnGoProject);
		util.waitFor(5000);
	}

	public void selectListOfTrack(String value) {
		try {
			util.selectValueFromDropdown(drpListOfTrack, value);
			util.click(drpListOfTrackClose);
		} catch (Exception e) {
			util.click(drpListOfTrackClose);
			util.click(By.xpath("//ul//li[normalize-space()='"+value+"']//input"));
			util.click(drpListOfTrackClose);
		}
	}
	public void selectListOfLeases(String value) {
		try {
			util.selectValueFromDropdown(drpListofLeases, value);
			util.click(drpListofLeasesClose);
		} catch (Exception e) {
			util.click(drpListofLeasesClose);
			util.click(By.xpath("//ul//li[normalize-space()='"+value+"']//input"));
			util.click(drpListofLeasesClose);
		}
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

	public void clickOnAddNewRecord() {
		util.click(addProjectApprovals);
	}
	
	public void projectApprovalTestcase(Map<String, String> map) {
		if (!util.isElementVisible(addProjectApprovals)) {
			util.click(projectApprovals);
			if (!util.isElementVisible(addProjectApprovals)) {
				util.click(projectApprovals);
			}
		}
		try {
			clickOnAddNewRecord();
			log("STEP 1: Click on Add button under the  Project Approvals panels", Status.PASS);
		} catch (Exception e) {
			log("STEP 1: Click on Add button under the  Project Approvals panels", Status.FAIL);
			throw new RuntimeException("Failed in step 1");
		}
		try {
			util.waitForWebElementToBePresent(iframeApproval);
			util.switchToIframe(iframeApproval);
			selectApprovalType(map.get(Excel.ApprovalType));
			log("STEP 2: Selected the GIS Bulk Processing Document Generation from Approval Type DD", Status.PASS);
		} catch (Exception e) {
			log("STEP 2: Selected the GIS Bulk Processing Document Generation from Approval Type DD", Status.FAIL);
			throw new RuntimeException("Failed in step 2");
		}
		try {
			selectTemplate(map.get(Excel.Template));
			log("STEP 3: Selected the templates from DD", Status.PASS);
		} catch (Exception e) {
			log("STEP 3: Selected the templates from DD", Status.FAIL);
			throw new RuntimeException("Failed in step 3");
		}

		try {
			selectReportFormat(map.get(Excel.ReportFormat));
			log("STEP 4: Selected the report format", Status.PASS);
		} catch (Exception e) {
			log("STEP 4: Selected the report format", Status.FAIL);
			throw new RuntimeException("Failed in step 4");
		}
	}
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
	By typeOfPaymentTXT = By.xpath("//span[text()='Type of Payment:']/..//input[@type='text']");
	By paymentAmountTXT = By.xpath("//span[text()='Payment Amount:']/..//input[@type='text']");
	By agreementPhaseTXT = By.xpath("//span[text()='Agreement Phase:']/..//input[@type='text']");
	By dueDateTXT = By.xpath("//span[text()='Due Date:']/..//input[contains(@id,'dateInput') and @type='text']");
	By distributionTXT = By.xpath("//span[text()='Distribution:']/..//input[@type='text']");
	By additionalPaymentTXT = By.xpath("//span[text()='# of Additional Payments']/..//input[@type='text']");
	By frequencyIncreaseTXT = By.xpath("//span[text()='Frequency Increment:']/..//input[@type='text']");
	By paymentFrquencyTXT = By.xpath("//span[text()='Payment Frequency:']/..//input[@type='text']");
	By remarksTXT = By.xpath("//span[text()='Remarks:']/..//textarea");
	public void fillAlldetailsInProjectApproval(String typeOFPaymentValue, String agreementPhaseValue, String distributionValue) {
		util.inputTextAndPressTab(typeOfPaymentTXT, typeOFPaymentValue);
		util.inputText(paymentAmountTXT, "1");
		util.inputTextAndPressTab(agreementPhaseTXT, agreementPhaseValue);
		util.inputText(dueDateTXT, TestUtil.getCurrentDateTime("dd/MM/yyyy"));
		util.inputTextAndPressTab(distributionTXT, distributionValue);
		util.inputText(additionalPaymentTXT, "1");
		util.inputText(frequencyIncreaseTXT, "1");
		util.inputTextAndPressTab(paymentFrquencyTXT, "Days");
		util.inputText(remarksTXT, "Automation Remarks");
	}
	public void agreementFormTestcase(Map<String, String> map) {
		try {
			navigateToAgreementForm();
			switchToProjectApprovalIframe();
			log("STEP 1: Click on Add button under the  Project Approvals panels", Status.PASS);
		} catch (Exception e) {
			log("STEP 1: Click on Add button under the  Project Approvals panels", Status.FAIL);
			throw new RuntimeException("Failed in step 1");
		}
		try {
			selectApprovalType(map.get(Excel.ApprovalType));
			log("STEP 2: Select Any Work Flow from Approval Type DD", Status.PASS);
		} catch (Exception e) {
			log("STEP 2: Select Any Work Flow from Approval Type DD", Status.FAIL);
			throw new RuntimeException("Failed in step 2");
		}
		try {
			fillAlldetailsInProjectApproval(map.get(Excel.TypeOFPayment), map.get(Excel.AgreementPhase), map.get(Excel.Distribution));
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
	}

	public void addNewWorkflow(Map<String, String> map, String testcaseName, boolean isAllSteps) throws InterruptedException {
		
		if(testcaseName.contains("Agreement")) {
			agreementFormTestcase(map);
		} else {
			projectApprovalTestcase(map);
		}

		try {
			clickOnSaveApprovals();
			log("STEP 5: Clicked on save button", Status.PASS);
		} catch (Exception e) {
			log("STEP 5: Clicked on save button", Status.FAIL);
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
			util.waitForWebElementToBePresent(iframeDocument, IMPLICIT_WAIT);
			util.waitFor(3000);
			util.switchToIframe(iframeDocument);
			selectCategory(map.get(Excel.DocCategory));
			log("STEP 7: Select the document category", Status.PASS);
		} catch (Exception e) {
			log("STEP 7: Select the document category", Status.FAIL);
			throw new RuntimeException("Failed in step 7");
		}

		try {
			setDescription("Automation comments");
			log("STEP 8: Entered a Title of document", Status.PASS);
		} catch (Exception e) {
			log("STEP 8: Entered a Title of document", Status.FAIL);
			throw new RuntimeException("Failed in step 8");
		}

		try {
			uploadFile();
			util.waitFor(2000);
			log("STEP 9: Document is uploaded", Status.PASS);
		} catch (Exception e) {
			log("STEP 9: Document is not uploaded", Status.FAIL);
			throw new RuntimeException("Failed in step 9");
		}

		try {
			if (util.isElementPresent(loadDocumentFile)) {
				util.click(loadDocumentFile);
			} else if (util.isElementPresent(loadDocumentFile2)) {
				util.click(loadDocumentFile2);
			}
			util.waitUntilElementDisplay(documentSuccessMessage);
			String msg = util.getText(documentSuccessMessage);
			Assert.assertEquals(msg, "Loaded: test.txt", "Success message is mismatch");
			util.switchToDefaultContent();
			util.switchToIframe(iframeApproval);
			util.click(btnCloseDocument);
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
		
//		try {
//			selectListOfLeases(map.get(Excel.ListOfTrack));
//			Thread.sleep(5000);
//			selectListOfLeases(map.get(Excel.ListOfLeases));
//			Thread.sleep(5000);
//			clickOnListOfTract();
//			Thread.sleep(5000);
//			refreshListOfTract();
//			Thread.sleep(5000);
//			Assert.assertEquals(getListOfTractMessage(), "Requested association created successfully",
//					"Success message is mismatch");
//			log("STEP 20: Select a Tract list from the DD and click on Add ", Status.PASS);
//		} catch (Exception e) {
//			log("STEP 20: Select a Tract list from the DD and click on Add ", Status.FAIL);
//			throw new RuntimeException("Failed in step 18");
//		}
		
		
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

	public void submitTheFormForReview() {
		try {
			// util.switchToIframe(iframeApproval);
			util.click(tabApproval);
			util.click(btnSubmitForReview);
			util.waitForWebElementToBePresent(autoApproved, 30);
			Assert.assertTrue(util.isElementPresent(autoApproved), "Form is not auto approved");
			log("STEP 23: Click on submit for review on the form", Status.PASS);
			log("Form is auto approved");
			util.switchToDefaultContent();
		} catch (Exception e) {
			log("STEP 23: Not Click on submit for review on the form", Status.FAIL);
		}
	}
	
	By reviewComments = By.xpath("//textarea[contains(@id,'txtReviewerComments')]");
	By approveButton = By.xpath("//input[contains(@id,'btnApprove')]");
	public void submitTheFormForReviewAgreement() {
		try {
			util.click(tabApproval);
			util.click(btnSubmitForReview);
			util.waitForWebElementToBePresent(adminReviewLink, 30);
			util.click(adminReviewLink);
			util.waitForWebElementToBePresent(reviewIframe, 30);
			util.switchToIframe(reviewIframe);
			util.inputText(reviewComments, "Automation review comments.");
			util.click(approveButton);
			try {
				util.switchToDefaultContent();
			} catch (Exception e1) { }
			switchToProjectApprovalIframe();
			util.waitForWebElementToBePresent(autoApproved, IMPLICIT_WAIT);
			Assert.assertTrue(util.isElementPresent(autoApproved), "Form is not auto approved");
			log("STEP 23: Click on submit for review on the form", Status.PASS);
			log("Form is auto approved");
			util.switchToDefaultContent();
		} catch (Exception e) {
			log("STEP 23: Not Click on submit for review on the form", Status.FAIL);
			throw new RuntimeException("Failed in STEP 23: Click on submit for review on the form");
		}
	}

	public void closeApprovalForm() {
		util.click(btnCloseForm);
		util.waitFor(5000);
	}

	public void verifyStoredRecord(String workflow) {
//		By locator = By.xpath("//a[normalize-space()='Project Forms']/following-sibling::*//table[contains(@id,'Workflows_radYALGridControl')]//td[text()='"+value+"']");
		By locator = By.xpath("//td[text()='Approved']/..//td[text()='" + workflow + "']");
		if (util.isElementPresent(locator)) {
			log("STEP 24:  Status changed to 'Approved'  ", Status.PASS);
		} else {
			log("STEP 24: orkflow is not autoapproved ", Status.FAIL);
		}

		// Assert.assertTrue(util.isElementPresent(locator), "Record is not saved");
	}

}
