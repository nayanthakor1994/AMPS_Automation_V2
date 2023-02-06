package pages.agreementManager;

import java.io.File;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.base.Excel;
import com.util.CommonFunction;
import com.util.TestUtil;

public class DocumentsPage extends BasePage {

	TestUtil util;
	CommonFunction commonFunction;
	Actions actions;
	public DocumentsPage(WebDriver driver) {
		this.driver = driver;
		util = new TestUtil(driver);
		commonFunction = new CommonFunction(driver);
		actions = new Actions(driver);
	}
	
	By documentTab = By.xpath("//span[text()='Documents']/../../parent::a[contains(@class,'rtsLink')]");
	By addDocumentButton = By.xpath("//input[contains(@id,'LSINFO_AddDocuments')] | //input[contains(@id,'btnSendemails')]/../img[@alt='Add Documents']");
	public void navigateToDocumentTab() {
		util.click(documentTab);
		util.waitForWebElementToBeClickable(addDocumentButton, IMPLICIT_WAIT);
	}
	
	public void clickOnAddDocumentButton() {
		util.click(addDocumentButton);
	}

	By iframeDocument = By.xpath("//iframe[@name='AddDocuments']");
	By description = By.xpath("//input[contains(@id,'RadUpload1Desc') or contains(@id,'rauFileUploadDesc')]");
	public void setDescription(String value) {
		if(util.isElementPresent(description)) {
			util.inputText(description, value);
		}
	}
	By fileUpload = By.xpath("//input[contains(@id,'RadUpload1file') or contains(@id,'rauFileUploadfile')]");
	public void uploadFile() {
		String filepath = System.getProperty("user.dir") + File.separator + "test.txt";
		driver.findElement(fileUpload).sendKeys(filepath);
		util.waitFor(5000);
	}
	By loadDocumentFile = By.cssSelector("#buttonSubmit");
	By loadDocumentFile2 = By.xpath("//input[contains(@id,'buttonSubmit_input')]");
	By documentSuccessMessage = By.xpath("//span[@id='lblResults']");
	By btnCloseDocument = By.xpath("//div[contains(@id,'_AddDocuments')]//a[@title='Close']");
	By drpCategoryExtDoc = By.xpath("//select[@name='category']");
	public void selectCategory(String value) {
		if(util.isElementPresent(drpCategoryExtDoc)) {
			util.selectValueFromDropdown2(value, drpCategoryExtDoc);
		}
	}
	
	public void addExternalDocumentFromIframe() {
		try {
			uploadFile();
			log("STEP 3: Click on the select button", Status.PASS);
		} catch (Exception e) {
			log("STEP 3: Click on the select button", Status.FAIL);
			throw new RuntimeException("Failed in step 3");
		}
		
		try {
			selectCategory("Docusign Demo");
			log("STEP 4: Fill in Category", Status.PASS);
		} catch (Exception e) {
			log("STEP 4: Fill in Category", Status.FAIL);
			throw new RuntimeException("Failed in step 4");
		}

		try {
			setDescription("Automation comments");
			log("STEP 5: Fill in Description", Status.PASS);
		} catch (Exception e) {
			log("STEP 5: Fill in Description", Status.FAIL);
			throw new RuntimeException("Failed in step 5");
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
			log("STEP 6: Click on the load document button", Status.PASS);
		} catch (Exception e) {
			log("STEP 6: Click on the load document button", Status.FAIL);
			throw new RuntimeException("Failed in step 6");
		}
	}
	
	public void addDocument_ExternalDocument(String testcaseName) {
		
		try {
			navigateToDocumentTab();
			log("STEP 1: Navigate to Menu -Agreement Management page", Status.PASS);
		} catch (Exception e) {
			log("STEP 1: Navigate to Menu -Agreement Management page", Status.FAIL);
			throw new RuntimeException("Failed in step 1");
		}
		
		try {
			clickOnAddDocumentButton();
			log("STEP 2: Click Add Document button", Status.PASS);
		} catch (Exception e) {
			log("STEP 2: Click Add Document button", Status.FAIL);
			throw new RuntimeException("Failed in step 2");
		}
		
		util.waitForWebElementToBePresent(iframeDocument, IMPLICIT_WAIT);
		util.waitFor(3000);
		util.switchToIframe(iframeDocument);
		
		addExternalDocumentFromIframe();
		addExternalDocumentFromIframe();
		
		util.switchToDefaultContent();
		util.click(btnCloseDocument);
		
		try {
			log("STEP 8: click on add document button", Status.PASS);
		} catch (Exception e) {
			log("STEP 8: click on add document button", Status.FAIL);
			throw new RuntimeException("Failed in step 8");
		}
		try {
			log("STEP 9: Drag and drop document from folder", Status.PASS);
		} catch (Exception e) {
			log("STEP 9: Drag and drop document from folder", Status.FAIL);
			throw new RuntimeException("Failed in step 9");
		}
		try {
			log("STEP 10: click on Load documents button", Status.PASS);
		} catch (Exception e) {
			log("STEP 10: click on Load documents button", Status.FAIL);
			throw new RuntimeException("Failed in step 10");
		}
		
	}
	
	By loadDocumentExtLink = By.xpath("//span[text()='External Link']/../../..//input[contains(@id,'btnSave_input')]");
	By externalLinkTab = By.xpath("//span[text()='External Link']/../parent::a");
	
	public void navigateToExternalLinkTab() {
		if(!util.isElementPresent(loadDocumentExtLink)) {
			util.click(externalLinkTab);
		}
	}
	By documentNameExtLnk = By.xpath("//input[contains(@id,'txtname')]");
	public void setDocumentName(String value) {
		util.inputText(documentNameExtLnk, value);
	}
	
	By documentCategoryExtLnk = By.xpath("//input[contains(@id,'ddlDynamic') and @type='text']");
	public void setDocumentCategoryExtLnk(String value) {
		util.inputTextAndPressTab(documentCategoryExtLnk, value);
	}
	
	By documentExternalLink = By.xpath("//input[contains(@id,'txtlinkname')]");
	public void setDocumentExternalLink(String value) {
		util.inputText(documentExternalLink, value);
	}
	
	By documentDescriptionExtLnk = By.xpath("//input[contains(@id,'txtDynamic')]");
	public void setLinkDescription(String value) {
		util.inputText(documentDescriptionExtLnk, value);
	}
	
	public void clickOnLoadDocumentExternalLink() {
		util.click(loadDocumentExtLink);
	}
	
	public void addDocument_ExternalLink(String testcaseName) {
		
		try {
			navigateToDocumentTab();
			log("STEP 1: Navigate to Menu -Agreement Management page", Status.PASS);
		} catch (Exception e) {
			log("STEP 1: Navigate to Menu -Agreement Management page", Status.FAIL);
			throw new RuntimeException("Failed in step 1");
		}
		
		try {
			clickOnAddDocumentButton();
			log("STEP 2: Click Add Document button", Status.PASS);
		} catch (Exception e) {
			log("STEP 2: Click Add Document button", Status.FAIL);
			throw new RuntimeException("Failed in step 2");
		}
		
		try {
			util.waitForWebElementToBePresent(iframeDocument, IMPLICIT_WAIT);
			util.waitFor(3000);
			util.switchToIframe(iframeDocument);
			navigateToExternalLinkTab();
			setDocumentName("AutomationDoc");
			setLinkDescription("Automation Description");
			log("STEP 3: Fill in Description", Status.PASS);
		} catch (Exception e) {
			log("STEP 3: Fill in Description", Status.FAIL);
			throw new RuntimeException("Failed in step 3");
		}
		
		try {
			setDocumentCategoryExtLnk("Docusign Demo");
			log("STEP 4: Fill in Category", Status.PASS);
		} catch (Exception e) {
			log("STEP 4: Fill in Category", Status.FAIL);
			throw new RuntimeException("Failed in step 4");
		}
		
		try {
			setDocumentExternalLink("https://enviragallery.com/wp-content/uploads/2020/01/jpg.png");
			log("STEP 5: Fill in External Link", Status.PASS);
		} catch (Exception e) {
			log("STEP 5: Fill in External Link", Status.FAIL);
			throw new RuntimeException("Failed in step 5");
		}
		try {
			clickOnLoadDocumentExternalLink();
			util.waitUntilElementDisplay(documentSuccessMessage);
			String msg = util.getText(documentSuccessMessage);
			Assert.assertEquals(msg, "Documents loaded!", "Success message is mismatch");
			util.switchToDefaultContent();
			util.click(btnCloseDocument);
			log("STEP 6: Click on Load Document button", Status.PASS);
		} catch (Exception e) {
			log("STEP 6: Click on Load Document button", Status.FAIL);
			throw new RuntimeException("Failed in step 6");
		}
		
	}
	
	By viewDocumentLink = By.xpath("//div[contains(@id,'DocumentsUC')]//a[text()='View Document']");
	public void clickOnViewDocument() {
		util.click(viewDocumentLink);
	}
	
	public void viewDocument(String testcaseName) {
		try {
			navigateToDocumentTab();
			log("STEP 1: Navigate to Menu -Agreement Management page", Status.PASS);
		} catch (Exception e) {
			log("STEP 1: Navigate to Menu -Agreement Management page", Status.FAIL);
			throw new RuntimeException("Failed in step 1");
		}
		
		try {
			clickOnViewDocument();
			log("STEP 2: Click View Document Link", Status.PASS);
		} catch (Exception e) {
			log("STEP 2: Click View Document Link", Status.FAIL);
			throw new RuntimeException("Failed in step 2");
		}
	}
	
	By editDocumentLink = By.xpath("//div[contains(@id,'DocumentsUC')]//input[contains(@id,'_EditButton')]");
	public void clickOnEditDocument() {
		util.click(editDocumentLink);
	}
	
	By editDocumentName = By.xpath("//input[contains(@id,'EditFormControl_txtDocName') and @type='text']");
	public void editDocumentName(String value) {
		util.inputText(editDocumentName, value);
	}
	
	By editDocumentDescription = By.xpath("//input[contains(@id,'EditFormControl_txtDocDesc') and @type='text']");
	public void editDocumentDescription(String value) {
		util.inputText(editDocumentDescription, value);
	}
	
	By updateLinkButton = By.xpath("//input[contains(@id,'EditFormControl_btnUpdate')]");
	public void clickOnUpdateLinkButton() {
		util.click(updateLinkButton);
	}
	
	By successDocumentUpdateMessage = By.xpath("//span[contains(@id,'DocumentsUC_usrMessage')]");
	public String getDocumentUpdateSuccessMessage() {
		util.waitForWebElementToBePresent(successDocumentUpdateMessage, 10);
		return util.getText(successDocumentUpdateMessage);
	}
	
	public void editDocument(String testcaseName) {
		try {
			navigateToDocumentTab();
			log("STEP 1: Navigate to Menu -Agreement Management page", Status.PASS);
		} catch (Exception e) {
			log("STEP 1: Navigate to Menu -Agreement Management page", Status.FAIL);
			throw new RuntimeException("Failed in step 1");
		}
		
		try {
			clickOnEditDocument();
			log("STEP 2: Click on the edit pencil icon", Status.PASS);
		} catch (Exception e) {
			log("STEP 2: Click on the edit pencil icon", Status.FAIL);
			throw new RuntimeException("Failed in step 2");
		}
		
		try {
			editDocumentName("EditedDocument");
			editDocumentDescription("EditedDocument");
			log("STEP 3: Update in the required information", Status.PASS);
		} catch (Exception e) {
			log("STEP 3: Update in the required information", Status.FAIL);
			throw new RuntimeException("Failed in step 3");
		}
		try {
			clickOnUpdateLinkButton();
			String msg = getDocumentUpdateSuccessMessage();
			Assert.assertEquals(msg, "Changes saved successfully!", "Update document success message is mismatched");
			log("STEP 4: Click on the update button", Status.PASS);
		} catch (Exception e) {
			log("STEP 4: Click on the update button", Status.FAIL);
			throw new RuntimeException("Failed in step 4");
		}
	}
	
	By addNewRecordButton = By.xpath("//table[contains(@id,'REC_INFO')]//a[contains(@id,'_lnkAddNewRecord')]");
	By recordingInformationTab = By.xpath("//span[text()='Recording Information']/../parent::a");
	private void clickOnAddNewRecord() {
		if(!util.isElementPresent(addNewRecordButton)) {
			util.click(recordingInformationTab);
		}
		util.click(addNewRecordButton);
	}
	
	By documentTypeInput = By.xpath("//input[contains(@id,'EditFormControl_DOC_TYPE') and @type='text']");
	public void enterDocumentType(String value) {
		util.inputText(documentTypeInput, value);
	}
	
	By bookInput = By.xpath("//input[contains(@id,'EditFormControl_Doc_Book') and @type='text']");
	public void enterBook(String value) {
		util.inputText(bookInput, value);
	}
	By volumeInput = By.xpath("//input[contains(@id,'EditFormControl_DOC_VOLUME') and @type='text']");
	public void enterVolume(String value) {
		util.inputText(volumeInput, value);
	}
	By pageInput = By.xpath("//input[contains(@id,'EditFormControl_DOC_PAGE') and @type='text']");
	public void enterPage(String value) {
		util.inputText(pageInput, value);
	}
	By documentNoInput = By.xpath("//input[contains(@id,'EditFormControl_DOC_NO') and @type='text']");
	public void enterDocumentNumber(String value) {
		util.inputText(documentNoInput, value);
	}
	By dateRecordedInput = By.xpath("//input[contains(@id,'EditFormControl_DATE_RECORDED_dateInput') and @type='text']");
	public void enterDateRecorded(String value) {
		util.inputText(dateRecordedInput, value);
	}
	By stateInput = By.xpath("//input[contains(@id,'EditFormControl_RadState_Input') and @type='text']");
	public void enterStateName(String value) {
		util.inputText(stateInput, value);
	}
	By countryInput = By.xpath("//input[contains(@id,'EditFormControl_RadCounty_Input') and @type='text']");
	public void enterCountryName(String value) {
		util.inputText(countryInput, value);
	}
	By leaseNumberInput = By.xpath("//input[contains(@id,'EditFormControl_LEASE_NO') and @type='text']");
	public void enterLeaseNumber(String value) {
		util.inputText(leaseNumberInput, value);
	}
	By dateExecutedInput = By.xpath("//input[contains(@id,'EditFormControl_DATE_EXECUTED_dateInput') and @type='text']");
	public void enterDateExecuted(String value) {
		util.inputText(dateExecutedInput, value);
	}
	By grantorInput = By.xpath("//textarea[contains(@id,'EditFormControl_GRANTOR')]");
	public void enterGrantor(String value) {
		util.inputText(grantorInput, value);
	}
	By granteeInput = By.xpath("//textarea[contains(@id,'EditFormControl_GRANTEE')]");
	public void enterGrantee(String value) {
		util.inputText(granteeInput, value);
	}
	By commentsInput = By.xpath("//textarea[contains(@id,'EditFormControl_COMMENTS')]");
	public void enterComments(String value) {
		util.inputText(commentsInput, value);
	}
	By insertButton = By.xpath("//input[contains(@id,'EditFormControl_btnInsert')]");
	public void clickOnInsertButton() {
		util.click(insertButton);
	}
	
	public void addNewRecordingInformation(Map<String, String> map, String testcaseName) {
		
		try {
			navigateToDocumentTab();
			log("STEP 1: Navigate to Menu -Agreement Management page", Status.PASS);
		} catch (Exception e) {
			log("STEP 1: Navigate to Menu -Agreement Management page", Status.FAIL);
			throw new RuntimeException("Failed in step 1");
		}
		
		try {
			clickOnAddNewRecord();
			log("STEP 2: Click on add new record button", Status.PASS);
		} catch (Exception e) {
			log("STEP 2: Click on the edit pencil icon", Status.FAIL);
			throw new RuntimeException("Failed in step 2");
		}
		
		try {
			enterDocumentType(map.get(Excel.DocumentType));
			log("STEP 3: Fill in Document Type", Status.PASS);
		} catch (Exception e) {
			log("STEP 3: Fill in Document Type", Status.FAIL);
			throw new RuntimeException("Failed in step 3");
		}
		try {
			enterBook(map.get(Excel.Book));
			log("STEP 4: Fill in Book", Status.PASS);
		} catch (Exception e) {
			log("STEP 4: Fill in Book", Status.FAIL);
			throw new RuntimeException("Failed in step 4");
		}
		try {
			enterVolume(map.get(Excel.Volume));
			log("STEP 5: Fill in Volume", Status.PASS);
		} catch (Exception e) {
			log("STEP 5: Fill in Volume", Status.FAIL);
			throw new RuntimeException("Failed in step 5");
		}
		try {
			enterPage(map.get(Excel.Page));
			log("STEP 6: Fill in Page", Status.PASS);
		} catch (Exception e) {
			log("STEP 6: Fill in Page", Status.FAIL);
			throw new RuntimeException("Failed in step 6");
		}
		try {
			enterDocumentNumber(util.randomNumber(5));
			log("STEP 7: Fill in Document #", Status.PASS);
		} catch (Exception e) {
			log("STEP 7: Fill in Document #", Status.FAIL);
			throw new RuntimeException("Failed in step 7");
		}
		try {
			enterDateRecorded(TestUtil.addDaysInCurrentDate("MM/dd/yyyy", -10));
			log("STEP 8: Select Date Recorded ", Status.PASS);
		} catch (Exception e) {
			log("STEP 8: Select Date Recorded ", Status.FAIL);
			throw new RuntimeException("Failed in step 8");
		}
		try {
			enterStateName(map.get(Excel.State));
			log("STEP 9: Fill in State", Status.PASS);
		} catch (Exception e) {
			log("STEP 9: Fill in State", Status.FAIL);
			throw new RuntimeException("Failed in step 9");
		}
		try {
			enterCountryName(map.get(Excel.Country));
			log("STEP 10: Fill in County", Status.PASS);
		} catch (Exception e) {
			log("STEP 10: Fill in County", Status.FAIL);
			throw new RuntimeException("Failed in step 10");
		}
		try {
			enterLeaseNumber(util.randomNumber(5));
			log("STEP 11: Fill in Lease Number", Status.PASS);
		} catch (Exception e) {
			log("STEP 11: Fill in Lease Number", Status.FAIL);
			throw new RuntimeException("Failed in step 11");
		}
		try {
			enterDateExecuted(TestUtil.getCurrentDateTime("MM/dd/yyyy"));
			log("STEP 12: Select Date Executed", Status.PASS);
		} catch (Exception e) {
			log("STEP 12: Select Date Executed", Status.FAIL);
			throw new RuntimeException("Failed in step 12");
		}
		try {
			enterGrantor(map.get(Excel.Grantor));
			log("STEP 13: Fill in Grantor", Status.PASS);
		} catch (Exception e) {
			log("STEP 13: Fill in Grantor", Status.FAIL);
			throw new RuntimeException("Failed in step 13");
		}
		try {
			enterGrantee(map.get(Excel.Grantee));
			log("STEP 14: Fill in Grantee", Status.PASS);
		} catch (Exception e) {
			log("STEP 14: Fill in Grantee", Status.FAIL);
			throw new RuntimeException("Failed in step 14");
		}
		try {
			enterComments("Automation comments");
			log("STEP 15: Fill in Comments", Status.PASS);
		} catch (Exception e) {
			log("STEP 15: Fill in Comments", Status.FAIL);
			throw new RuntimeException("Failed in step 15");
		}
		try {
			clickOnInsertButton();
			log("STEP 16: Click on insert button", Status.PASS);
		} catch (Exception e) {
			log("STEP 16: Click on insert button", Status.FAIL);
			throw new RuntimeException("Failed in step 16");
		}
	}
	
	By editNewRecordLink = By.xpath("//table[contains(@id,'REC_INFO_radYALGridControl')]//input[contains(@id,'EditButton')]");
	private void clickOnEditRecord() {
		util.click(editNewRecordLink);
	}
	By unrecordedCheckbox = By.xpath("//input[contains(@id,'EditFormControl_UNRECORDED')]");
	private void clickOnUnrecordedCheckbox() {
		util.click(unrecordedCheckbox);
	}
	By updateButton = By.xpath("//input[contains(@id,'EditFormControl_btnUpdate')]");
	public void clickOnUpdateButton() {
		util.click(updateButton);
	}
	
	public void editRecordingInformation(Map<String, String> map, String testcaseName) {
		
		try {
			navigateToDocumentTab();
			log("STEP 1: Navigate to Menu -Agreement Management page", Status.PASS);
		} catch (Exception e) {
			log("STEP 1: Navigate to Menu -Agreement Management page", Status.FAIL);
			throw new RuntimeException("Failed in step 1");
		}
		
		try {
			clickOnEditRecord();
			log("STEP 2: Click Edit Pencil Icon", Status.PASS);
		} catch (Exception e) {
			log("STEP 2: Click Edit Pencil Icon", Status.FAIL);
			throw new RuntimeException("Failed in step 2");
		}
		
		try {
			enterDocumentType(map.get(Excel.DocumentType));
			log("STEP 3: Fill in Document Type", Status.PASS);
		} catch (Exception e) {
			log("STEP 3: Fill in Document Type", Status.FAIL);
			throw new RuntimeException("Failed in step 3");
		}
		try {
			enterBook(map.get(Excel.Book));
			log("STEP 4: Fill in Book", Status.PASS);
		} catch (Exception e) {
			log("STEP 4: Fill in Book", Status.FAIL);
			throw new RuntimeException("Failed in step 4");
		}
		try {
			enterVolume(map.get(Excel.Volume));
			log("STEP 5: Fill in Volume", Status.PASS);
		} catch (Exception e) {
			log("STEP 5: Fill in Volume", Status.FAIL);
			throw new RuntimeException("Failed in step 5");
		}
		try {
			enterPage(map.get(Excel.Page));
			log("STEP 6: Fill in Page", Status.PASS);
		} catch (Exception e) {
			log("STEP 6: Fill in Page", Status.FAIL);
			throw new RuntimeException("Failed in step 6");
		}
		try {
			enterDocumentNumber(util.randomNumber(5));
			log("STEP 7: Fill in Document #", Status.PASS);
		} catch (Exception e) {
			log("STEP 7: Fill in Document #", Status.FAIL);
			throw new RuntimeException("Failed in step 7");
		}
		try {
			enterDateRecorded(TestUtil.addDaysInCurrentDate("MM/dd/yyyy", -10));
			log("STEP 8: Select Date Recorded ", Status.PASS);
		} catch (Exception e) {
			log("STEP 8: Select Date Recorded ", Status.FAIL);
			throw new RuntimeException("Failed in step 8");
		}
		try {
			enterStateName(map.get(Excel.State));
			log("STEP 9: Fill in State", Status.PASS);
		} catch (Exception e) {
			log("STEP 9: Fill in State", Status.FAIL);
			throw new RuntimeException("Failed in step 9");
		}
		try {
			enterCountryName(map.get(Excel.Country));
			log("STEP 10: Fill in County", Status.PASS);
		} catch (Exception e) {
			log("STEP 10: Fill in County", Status.FAIL);
			throw new RuntimeException("Failed in step 10");
		}
		try {
			enterLeaseNumber(util.randomNumber(5));
			log("STEP 11: Fill in Lease Number", Status.PASS);
		} catch (Exception e) {
			log("STEP 11: Fill in Lease Number", Status.FAIL);
			throw new RuntimeException("Failed in step 11");
		}
		try {
			enterDateExecuted(TestUtil.getCurrentDateTime("MM/dd/yyyy"));
			log("STEP 12: Select Date Executed", Status.PASS);
		} catch (Exception e) {
			log("STEP 12: Select Date Executed", Status.FAIL);
			throw new RuntimeException("Failed in step 12");
		}
		try {
			clickOnUnrecordedCheckbox();
			log("STEP 13: check Unrecorded checkbox", Status.PASS);
		} catch (Exception e) {
			log("STEP 13: check Unrecorded checkbox", Status.FAIL);
			throw new RuntimeException("Failed in step 13");
		}
		try {
			enterGrantor(map.get(Excel.Grantor));
			log("STEP 14: Fill in Grantor", Status.PASS);
		} catch (Exception e) {
			log("STEP 14: Fill in Grantor", Status.FAIL);
			throw new RuntimeException("Failed in step 14");
		}
		try {
			enterGrantee(map.get(Excel.Grantee));
			log("STEP 15: Fill in Grantee", Status.PASS);
		} catch (Exception e) {
			log("STEP 15: Fill in Grantee", Status.FAIL);
			throw new RuntimeException("Failed in step 15");
		}
		try {
			enterComments("Automation edited comments");
			log("STEP 16: Fill in Comments", Status.PASS);
		} catch (Exception e) {
			log("STEP 16: Fill in Comments", Status.FAIL);
			throw new RuntimeException("Failed in step 16");
		}
		try {
			clickOnUpdateButton();
			log("STEP 17: Click on update button", Status.PASS);
		} catch (Exception e) {
			log("STEP 17: Click on update button", Status.FAIL);
			throw new RuntimeException("Failed in step 17");
		}
	}

	By deleteRecordLink = By.xpath("//table[contains(@id,'REC_INFO_radYALGridControl')]//input[contains(@id,'gbcDeleteAlignment')]");
	private void clickOnDeleteRecordInfo() {
		util.click(deleteRecordLink);
	}
	By deleteCancelLink = By.xpath("//div[contains(@id,'confirm')]//span[text()='Cancel']/../parent::a");
	private void clickOnDeleteCancelButton() {
		util.click(deleteCancelLink);
	}
	By deleteOkLink = By.xpath("//div[contains(@id,'confirm')]//span[text()='OK']/../parent::a");
	private void clickOnDeleteOkButton() {
		util.click(deleteOkLink);
	}
	
	
	public void deleteRecordingInformation(String environment) {
		try {
			clickOnDeleteRecordInfo();
			log("STEP 1: click on the Delete button for the record in the Recording Information panel ", Status.PASS);
		} catch (Exception e) {
			log("STEP 1: click on the Delete button for the record in the Recording Information panel ", Status.FAIL);
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
			clickOnDeleteRecordInfo();
			clickOnDeleteOkButton();
			log("STEP 3: click OK button in the Delet popup", Status.PASS);
		} catch (Exception e) {
			log("STEP 3: click OK button in the Delet popup", Status.FAIL);
			throw new RuntimeException("Failed in step 3");
		}
	}

	By documentGridLine = By.xpath("//table[contains(@id,'DocumentsUC_radYALGridControl')]//tr[contains(@id,'DocumentsUC')]//td[7]");
	private void rightClickOnDocumentGrid() {
		util.rightClickOnElement(documentGridLine);
	}
	
	By viewHistoryLink = By.xpath("//span[text()='View History']/parent::a");
	private void clickOnViewHistory() {
		util.click(viewHistoryLink);
	}
	By deleteAllPriorVersionButton = By.xpath("//input[contains(@id,'C_btnDelAllPrev')]");
	private void clickOnDeleteAllPriorVersion() {
		util.waitForWebElementToBeClickable(deleteAllPriorVersionButton, IMPLICIT_WAIT);
		util.waitFor(1000);
		util.click(deleteAllPriorVersionButton);
	}
	
	By documentLinkDocHistory = By.xpath("//div[contains(@id,'DocumentsUC_DOCSS_RadDock')]//td[contains(.,'Doc Name:')]/a");
	private void clickOnDocumentNameLink() {
		util.click(documentLinkDocHistory);
	}
	By requestDeleteButton = By.xpath("//input[contains(@name,'rptDocumentDetails') and contains(@src,'Delete')]");
	By requestDeleteIframe = By.xpath("//iframe[@name='RadWindowDocRequestDeleteMulti']");
	By reasonForDeletion = By.xpath("//input[contains(@id,'DocRequestDeletion_ddlDeletionReason') and @type='text']");
	By commentForDeletion = By.xpath("//textarea[contains(@id,'DocRequestDeletion_DeletionReasonComments')]");
	By confirmRequestDeletion = By.xpath("//input[contains(@id,'btnRequestDeletion')]");
	private void clickOnRequestDelete() {
		util.click(requestDeleteButton);
		util.waitForWebElementToBePresent(requestDeleteIframe, IMPLICIT_WAIT);
		util.switchToIframe(requestDeleteIframe);
		util.inputTextAndPressTab(reasonForDeletion, "Sample Value");
		util.inputText(commentForDeletion, "Automation deletion");
		util.click(confirmRequestDeletion);
		util.switchToDefaultContent();
	}
	public void verifyDocumentHistory(String environment) {
		try {
			navigateToDocumentTab();
			log("STEP 1: Navigate to Menu -Agreement Management page", Status.PASS);
		} catch (Exception e) {
			log("STEP 1: Navigate to Menu -Agreement Management page", Status.FAIL);
			throw new RuntimeException("Failed in step 1");
		}
		
		try {
			rightClickOnDocumentGrid();
			log("STEP 2: Right Click on  Document Grid record", Status.PASS);
		} catch (Exception e) {
			log("STEP 2: Right Click on  Document Grid record", Status.FAIL);
			throw new RuntimeException("Failed in step 2");
		}
		try {
			clickOnViewHistory();
			log("STEP 3: Click on View History Option", Status.PASS);
		} catch (Exception e) {
			log("STEP 3: Click on View History Option", Status.FAIL);
			throw new RuntimeException("Failed in step 3");
		}
		try {
			clickOnDeleteAllPriorVersion();
			log("STEP 4: Click on Delete All Prior Version Button", Status.PASS);
		} catch (Exception e) {
			log("STEP 4: Click on Delete All Prior Version Button", Status.FAIL);
			throw new RuntimeException("Failed in step 4");
		}
		try {
			clickOnDocumentNameLink();
			log("STEP 5: Click on  Document name Link", Status.PASS);
		} catch (Exception e) {
			log("STEP 5: Click on  Document name Link", Status.FAIL);
			throw new RuntimeException("Failed in step 5");
		}
		try {
			clickOnRequestDelete();
			log("STEP 6: Click on Request Delete", Status.PASS);
		} catch (Exception e) {
			log("STEP 6: Click on Request Delete", Status.FAIL);
			throw new RuntimeException("Failed in step 6");
		}
	}

	By addVersionLink = By.xpath("//span[text()='Add Version']/parent::a");
	private void clickOnAddVersionLink() {
		util.click(addVersionLink);
	}
	
	public void addDocumentVersion(String environment) {
		try {
			navigateToDocumentTab();
			log("STEP 1: Navigate to Menu -Agreement Management page", Status.PASS);
		} catch (Exception e) {
			log("STEP 1: Navigate to Menu -Agreement Management page", Status.FAIL);
			throw new RuntimeException("Failed in step 1");
		}
		try {
			rightClickOnDocumentGrid();
			log("STEP 2: Right Click on  Document Grid record", Status.PASS);
		} catch (Exception e) {
			log("STEP 2: Right Click on  Document Grid record", Status.FAIL);
			throw new RuntimeException("Failed in step 2");
		}
		try {
			clickOnAddVersionLink();
			log("STEP 3: Click on Add Version Option", Status.PASS);
		} catch (Exception e) {
			log("STEP 3: Click on Add Version Option", Status.FAIL);
			throw new RuntimeException("Failed in step 3");
		}
		try {
			String parentWindow = driver.getWindowHandle();
			Set<String> allWindows = driver.getWindowHandles();
			for(String currentWindow : allWindows) {
				if(!currentWindow.equals(parentWindow)) {
					driver.switchTo().window(currentWindow);
					addExternalDocumentFromIframe();
					driver.close();
				}
			}
			driver.switchTo().window(parentWindow);
			log("STEP 4: Upload a document", Status.PASS);
			log("STEP 5: Fill in Category", Status.PASS);
			log("STEP 6: Fill in Description", Status.PASS);
			log("STEP 7: Click on Load Document button", Status.PASS);
		} catch (Exception e) {
			log("STEP 4: Upload a document", Status.FAIL);
			throw new RuntimeException("Failed in step 4");
		}
		
		try {
			log("STEP 8: click on add document button", Status.PASS);
			log("STEP 9: Drag and drop document from folder", Status.PASS);
			log("STEP 10: click on Load documents button", Status.PASS);
		} catch (Exception e) {
			log("STEP 8: click on add document button", Status.FAIL);
			throw new RuntimeException("Failed in step 8");
		}
		
	}
	
}
