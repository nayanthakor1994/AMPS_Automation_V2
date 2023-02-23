package pages.agreementManager;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.base.CommonConstant;
import com.base.Excel;
import com.util.CommonFunction;
import com.util.TestUtil;

public class AddNewPayeeInfoPage extends BasePage {

	TestUtil util;
	CommonFunction commonFunction;

	public AddNewPayeeInfoPage(WebDriver driver) {
		this.driver = driver;
		util = new TestUtil(driver);
		commonFunction = new CommonFunction(driver);
	}
	

	By existingContact = By.xpath("//a[contains(@id,'LESSOR_Arrow')]");

	private void selectExistingContact(String value) {
		if (!commonFunction.checkNA(value)) {
			util.selectValueFromDropdown(existingContact, value);
			util.click(existingContact); // need to click on dropdown icon
		}
	}
	By addPayeeButton = By.xpath("//input[contains(@id,'btnAddLessor')]");
	private void clickOnAddButton() {
		util.click(addPayeeButton);
	}
	By successMessage = By.xpath("//span[contains(@id,'usrLesseeMessage')]");
	private void verifySuccessPayeeMessage(String env) {
		util.waitForWebElementToBePresent(successMessage, 20);
		String message = util.getText(successMessage);
		if(env.toLowerCase().contains("row")) {
			Assert.assertEquals(message, "Lessor has been added to Lease", "Lessor success message mismatched");
		} else {
			Assert.assertEquals(message, "Landowner has been added to Agreement", "Landowner success message mismatched");
		}
	}
	
	By addNewPayeeButton = By.xpath("//input[contains(@id,'btnAddLandowner')]");
	private void clickOnAddNewButton() {
		util.click(addNewPayeeButton);
	}
	By newLandOwnerIframe = By.xpath("//iframe[@name='AddLandowner']");
	private void switchToLandOwnerIframe() {
		util.waitForWebElementToBePresent(newLandOwnerIframe, IMPLICIT_WAIT);
		util.switchToIframe(newLandOwnerIframe);
	}
	By surfaceOwnership = By.xpath("//input[@name='Pct_Ownership']");
	private void addSurfaceOwnership(String value) {
		if(util.isElementPresent(surfaceOwnership)) {
			util.inputText(surfaceOwnership, value);
		}
	}
	By windOwnership = By.xpath("//input[@name='PCT_Ownership_Wind']");
	private void addWindOwnership(String value) {
		if(util.isElementPresent(windOwnership)) {
			util.inputText(windOwnership, value);
		}
	}
	By cropOwnership = By.xpath("//input[@name='PCT_Ownership_Crop']");
	private void addCropOwnership(String value) {
		if(util.isElementPresent(cropOwnership)) {
			util.inputText(cropOwnership, value);
		}
	}
	By contactType = By.xpath("//input[contains(@name,'Title_of_Rep') and @type='text']");
	private void selectContactType(String value) {
		if(util.isElementPresent(contactType)) {
			util.inputTextAndPressTab(contactType, value);
		}
	}
	By entityLastName = By.xpath("//input[@id='ENTITY_Last_Name']");
	private void enterEntityLastName(String value) {
		util.inputText(entityLastName, value);
	}
	By entityFirstName = By.xpath("//input[@id='ENTITY_First_Name']");
	private void enterEntityFirstName(String value) {
		util.inputText(entityFirstName, value);
	}
	By entityName = By.xpath("//input[@id='ENTITY_Corporation_Trust']");
	private void enterEntityName(String value) {
		util.inputText(entityName, value);
	}
	public void addLandOwnerDetails(Map<String, String> map) {
		addSurfaceOwnership(map.get(Excel.SurfaceOwnership));
		addWindOwnership(map.get(Excel.WindOwnership));
		addCropOwnership(map.get(Excel.CropOwnership));
		selectContactType(map.get(Excel.ContactType));
		enterEntityLastName(map.get(Excel.LastName));
		enterEntityFirstName(map.get(Excel.FirstName));
		enterEntityName(map.get(Excel.EntityName));
	}
	By iframeSaveButtonLO = By.xpath("//input[@id='btnSaveLO']");
	private void clickOnSaveButtonLandOwner() {
		util.click(iframeSaveButtonLO);
	}
	
	private void verifySuccessMessageLO() {
		commonFunction.waitForSuccessMessage();
		if (!commonFunction.getSuccessMessage().equals("New landowner was created and added to the Agreement.") &&
				!commonFunction.getSuccessMessage().equals("New landowner was created and added to the lease.")) {
			throw new RuntimeException();
		}
	}
	By closeLandOwnerForm = By.xpath("//div[contains(@id,'AddLandowner')]//a[@title='Close']");
	private void closeLandOwnerIframe() {
		util.switchToDefaultContent();
		util.click(closeLandOwnerForm);
		util.waitFor(5000);
	}
	
	
	By editOwnershipButton = By.xpath("//img[@alt='Edit Ownership'] | //input[contains(@id,'btnEditPayStatus')]");
	private void clickOnEditOwnershipButton() {
		util.click(editOwnershipButton);
	}
	
	By editLandOwnerIframe = By.xpath("//iframe[@name = 'LOOwnership']");
	private void switchToEditLandOwnerIframe() {
		util.waitForWebElementToBePresent(editLandOwnerIframe, IMPLICIT_WAIT);
		util.switchToIframe(editLandOwnerIframe);
	}
	
	By surfacePercentage = By.xpath("//input[contains(@id,'Surface_Ownership_All_Tracts') and @type='text']");
	private void addAllSurfacePercentage(String value) {
		List<WebElement> allEle = util.getWebElements(surfacePercentage);
		for(WebElement wb : allEle) {
			wb.sendKeys(value);
		}
	}
	
	By windPercentage = By.xpath("//input[contains(@id,'Wind_Ownership_All_Tracts') and @type='text']");
	private void addAllWindPercentage(String value) {
		List<WebElement> allEle = util.getWebElements(windPercentage);
		for(WebElement wb : allEle) {
			wb.sendKeys(value);
		}
	}
	
	By cropPercentage = By.xpath("//input[contains(@id,'Crop_Ownership_All_Tracts') and @type='text']");
	private void addAllCropPercentage(String value) {
		List<WebElement> allEle = util.getWebElements(cropPercentage);
		for(WebElement wb : allEle) {
			wb.sendKeys(value);
		}
	}
	
	public void addSurfaceWindCropInEditOwner() {
		addAllSurfacePercentage("0.5");
		addAllWindPercentage("0.5");
		addAllCropPercentage("1");
	}
	
	By depositoryIDDRP = By.xpath("//input[contains(@id,'Depository_ID') and @type='text']");
	private void addDepositoryIDDRP(String value) {
		List<WebElement> allEle = util.getWebElements(depositoryIDDRP);
		for(WebElement wb : allEle) {
			wb.sendKeys(value);
		}
	}
	By payStatusDRP = By.xpath("//input[contains(@id,'_PayStatus') and @type='text']");
	private void addPayStatusDRP(String value) {
		List<WebElement> allEle = util.getWebElements(payStatusDRP);
		for(WebElement wb : allEle) {
			wb.sendKeys(value);
		}
	}
	By reasonDRP = By.xpath("//input[contains(@id,'ddl_Reason') and @type='text']");
	private void addReasonDRP(String value) {
		List<WebElement> allEle = util.getWebElements(reasonDRP);
		for(WebElement wb : allEle) {
			wb.sendKeys(value);
		}
	}
	
	By payStatusComments = By.xpath("//textarea[contains(@id,'txtComments')]");
	private void addPayStatusComments(String value) {
		List<WebElement> allEle = util.getWebElements(payStatusComments);
		for(WebElement wb : allEle) {
			wb.sendKeys(value);
		}
	}
	
	public void addPayStatusInEditOwner() {
		addDepositoryIDDRP("Sample Value");
		addPayStatusDRP("Sample Value");
		addReasonDRP("Sample Value");
		addPayStatusComments("Automation comment");
	}
	
	By saveButtonAllocationLandownerTab = By.xpath("//table[contains(@id,'OwnershipByEntity')]//input[contains(@id,'btnUpdateAll')]");
	private void clickOnSaveButtonAllocationLandOwnerTab() {
		util.click(saveButtonAllocationLandownerTab);
	}
	
	By allocationByParcelTab = By.xpath("//span[text()='Allocation By Parcel']");
	private void clickOnAllocationByParcelTab() {
		util.click(allocationByParcelTab);
	}
	By saveButtonParcelTab = By.xpath("//table[contains(@id,'ProjectDetails')]//input[contains(@id,'btnUpdateAll')]");
	private void clickOnSaveButtonParcelTab() {
		util.click(saveButtonParcelTab);
	}
	
	By turbineOwnershipTab = By.xpath("//span[text()='Turbine Ownership']");
	private void clickOnTurbineOwnershipTab() {
		util.click(turbineOwnershipTab);
	}
	
	By saveButtonTurbineTab = By.xpath("//table[contains(@id,'OwnershipByEntity')]//input[contains(@id,'btnUpdateAll')]");
	private void clickOnSaveButtonTurbineTab() {
		util.click(saveButtonTurbineTab);
	}
	
	By closeLandOwnershipForm = By.xpath("//div[contains(@id,'LOOwnership')]//a[@title='Close']");
	private void closeLandOwnershipIframe() {
		util.switchToDefaultContent();
		util.click(closeLandOwnershipForm);
		util.waitFor(5000);
	}
	
	By landownerInformationTab = By.xpath("//div[contains(@id,'RadTabStripLO')]//span[text()='Landowner Information']");
	private void clickOnLandOwnerInfoTab(){
		util.click(landownerInformationTab);
		util.dummyWait(2);
	}
	By parcelTractInformationTab = By.xpath("//div[contains(@id,'RadTabStripLO')]//span[text()='Parcel Information' or text()='Tract Information']");
	private void clickOnParcelOrTractInfoTab(){
		util.click(parcelTractInformationTab);
	}
	By availableTract = By.xpath("//div[contains(@id,'TRACTSPanel')]//*[contains(@id,'TRACTS_Arrow')]");
	private void selectAvailableTract(String value){
		util.selectValueFromDropdownCheckboxContains(availableTract, value);
		util.waitFor(1000);
		util.click(availableTract);
	}
	By availableTractInputbox = By.xpath("//input[contains(@id,'_TRACTS_Input')]");
	private void selectAvailableTractROW(String value){
		util.waitForWebElementToBePresentReturn(availableTractInputbox, 30).click();
		util.waitForWebElementToBePresentReturn(availableTractInputbox, 30).sendKeys(value);
		util.dummyWait(10);
		util.waitForWebElementToBePresentReturn(availableTractInputbox, 30).sendKeys(Keys.ENTER);
		util.waitForWebElementToBePresentReturn(availableTractInputbox, 30).sendKeys(Keys.TAB);
	}
	By addAvailableTract = By.xpath("//input[contains(@id,'_ImageButton2')]");
	private void clickOnAddButtonAvailableTract(){
		util.click(addAvailableTract);
	}
	By successMessageAvailableTract = By.xpath("//span[contains(@id,'usrTractMessage')]");
	private void verifySuccessMessageAvailableTract(){
		util.waitUntilLoaderDisappear();
		util.waitForWebElementToBePresent(successMessageAvailableTract, IMPLICIT_WAIT);
		Assert.assertTrue(util.getText(successMessageAvailableTract).toLowerCase().contains("selected parcel added to"),
				"Available tract is not added successfully.");
	}
	
	By deleteAvailableTractButton = By.xpath("//tr[contains(@id,'RadGridTract1_radYALGridControl')]//input[contains(@id,'gbcDeleteAlignment')]");
	private void clickOnDeleteButtonAvailableTract(){
		util.click(deleteAvailableTractButton);
	}
	By deleteOkButtonIframe = By.xpath("//div[contains(@id,'confirm')]//span[text()='OK']/parent::*/parent::a");
	private void clickOnDeleteOkButtonIframe() {
		util.click(deleteOkButtonIframe);
	}
	By deleteMessage = By.xpath("//div[contains(@id,'RadGridTract1Panel')]//span[contains(@id,'usrMessage')]");
	private void verifyDeleteMessage() {
		util.waitUntilLoaderDisappear();
		util.waitForWebElementToBePresent(deleteMessage, IMPLICIT_WAIT);
		Assert.assertEquals(util.getText(deleteMessage), "Changes saved successfully!",
				"Delete parcel message mismatched");
	}
	
	
	public void editOwnership(String testCaseName) {
		try {
			clickOnLandOwnerInfoTab();
			clickOnEditOwnershipButton();
			switchToEditLandOwnerIframe();
			log("STEP 7: click on Edit Ownership button", Status.PASS);
		} catch (Exception e) {
			log("STEP 7: click on Edit Ownership button", Status.FAIL);
			throw new RuntimeException("Failed in step 7");
		}
		
		try {
			if(testCaseName.toLowerCase().contains(CommonConstant.row)) {
				addPayStatusInEditOwner();
			} else {
				addSurfaceWindCropInEditOwner();
			}
			log("STEP 8: Fill the value in 'Surface % , Wind % , Crop%' field for 'Allocation by Landowner", Status.PASS);
		} catch (Exception e) {
			log("STEP 8: Fill the value in 'Surface % , Wind % , Crop%' field for 'Allocation by Landowner", Status.FAIL);
			throw new RuntimeException("Failed in step 8");
		}
		
		try {
			clickOnSaveButtonAllocationLandOwnerTab();
			log("STEP 9: click on Save button", Status.PASS);
		} catch (Exception e) {
			log("STEP 9: click on Save button", Status.FAIL);
			throw new RuntimeException("Failed in step 9");
		}
		
		if(testCaseName.toLowerCase().contains(CommonConstant.alt)) {
			try {
				clickOnAllocationByParcelTab();
				log("STEP 10: Fill the value in 'Allocation By Parcel' tab fields", Status.PASS);
			} catch (Exception e) {
				log("STEP 10: Fill the value in 'Allocation By Parcel' tab fields", Status.FAIL);
				throw new RuntimeException("Failed in step 10");
			}
			
			try {
				clickOnSaveButtonParcelTab();
				log("STEP 11: click on Save button", Status.PASS);
			} catch (Exception e) {
				log("STEP 11: click on Save button", Status.FAIL);
				throw new RuntimeException("Failed in step 11");
			}
			
			try {
				//No Turbine tab found
				clickOnTurbineOwnershipTab();
				log("STEP 12: Fill the value in 'Turbine Ownership' tab fields", Status.PASS);
			} catch (Exception e) {
				log("STEP 12: Fill the value in 'Turbine Ownership' tab fields", Status.FAIL);
				throw new RuntimeException("Failed in step 12");
			}
			try {
				clickOnSaveButtonTurbineTab();
				closeLandOwnershipIframe();
				log("STEP 13: click on Save button", Status.PASS);
			} catch (Exception e) {
				log("STEP 13: click on Save button", Status.FAIL);
				throw new RuntimeException("Failed in step 13");
			}
		}
	}
	
	
	public void addPayeeInformation(Map<String, String> map, String AGREEMENT_NUMBER, String testCaseName) {
		
		try {
			selectExistingContact(map.get(Excel.LandownerName));
			log("STEP 1: Fill in Landowner DD", Status.PASS);
		} catch (Exception e) {
			log("STEP 1: Fill in Landowner DD", Status.FAIL);
			throw new RuntimeException("Failed in step 1");
		}
		
		try {
			clickOnAddButton();
			verifySuccessPayeeMessage(environment);
			log("STEP 2: Click on Add", Status.PASS);
		} catch (Exception e) {
			log("STEP 2: Click on Add", Status.FAIL);
			throw new RuntimeException("Failed in step 2");
		}
		
		try {
			clickOnAddNewButton();
			log("STEP 3: Click on Add New", Status.PASS);
		} catch (Exception e) {
			log("STEP 3: Click on Add New", Status.FAIL);
			throw new RuntimeException("Failed in step 3");
		}
		
		try {
			switchToLandOwnerIframe();	
			addLandOwnerDetails(map);
			log("STEP 4: Fill the value in all fields", Status.PASS);
		} catch (Exception e) {
			log("STEP 4: Fill the value in all fields", Status.FAIL);
			throw new RuntimeException("Failed in step 4");
		}
		
		try {
			clickOnSaveButtonLandOwner();
			verifySuccessMessageLO();
			log("STEP 5: click on Save button", Status.PASS);
		} catch (Exception e) {
			log("STEP 5: click on Save button", Status.FAIL);
			throw new RuntimeException("Failed in step 5");
		}
		
		try {
			closeLandOwnerIframe();
			log("STEP 6: click on Close (X) icon", Status.PASS);
		} catch (Exception e) {
			log("STEP 6: click on Close (X) icon", Status.FAIL);
			throw new RuntimeException("Failed in step 6");
		}
		
		// Parcel Information tab
		try {
			clickOnParcelOrTractInfoTab();
			if(testCaseName.toLowerCase().contains(row)) {
				selectAvailableTractROW(map.get(Excel.AvailableTract));
			} else {
				selectAvailableTract(map.get(Excel.AvailableTract));
			}
			log("STEP 14: Fill in Available Parcel(s) DD", Status.PASS);
		} catch (Exception e) {
			log("STEP 14: Fill in Available Parcel(s) DD", Status.FAIL);
			throw new RuntimeException("Failed in step 14");
		}
		try {
			clickOnAddButtonAvailableTract();
			verifySuccessMessageAvailableTract();
			log("STEP 15: click on Add button", Status.PASS);
		} catch (Exception e) {
			log("STEP 15: click on Add button", Status.FAIL);
			throw new RuntimeException("Failed in step 15");
		}
		try {
			clickOnDeleteButtonAvailableTract();
			clickOnDeleteOkButtonIframe();
			verifyDeleteMessage();
			log("STEP 16: click on Delete button", Status.PASS);
		} catch (Exception e) {
			log("STEP 16: click on Delete button", Status.FAIL);
			throw new RuntimeException("Failed in step 16");
		}
		
		editOwnership(testCaseName);

	}


}
