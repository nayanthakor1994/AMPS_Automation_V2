package pages.agreementManager;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.base.Excel;
import com.util.CommonFunction;
import com.util.TestUtil;

public class PaymentInformationPage extends BasePage {

	TestUtil util;
	CommonFunction commonFunction;
	Actions action;
	public PaymentInformationPage(WebDriver driver) {
		this.driver = driver;
		action = new Actions(driver);
		util = new TestUtil(driver);
		commonFunction = new CommonFunction(driver);
	}
	By navProjectMenu = By.xpath("//a//span[@class='rmText rmExpandDown' and contains(text(),'Project')] ");
	By navAgreementManager = By.xpath("//a//span[contains(text(),'Agreement Manager')] ");
	By navLeaseManager = By.xpath("//a//span[contains(text(),'Lease Manager')] ");
	By navPaymentInformation = By.xpath("//a//span[contains(text(),'Payment Information')] ");
	By addDocumentButton = By.xpath("//div[contains(@id,'LSINFO_RadPageView3')]//img[@alt='Add Documents'] | //div[contains(@id,'LSINFO_RadPageView3')]//input[contains(@id,'_btnAddDoc')]");
	
	public void navigateToPaymentInformationALT() {
		util.waitUntilElementDisplay(navProjectMenu);
		util.click(navProjectMenu);
		util.waitUntilElementDisplay(navAgreementManager);
		util.click(navAgreementManager);
		util.waitUntilElementDisplay(navPaymentInformation);
		util.click(navPaymentInformation);
		util.waitForWebElementToBePresent(addDocumentButton, 20);
		if (!util.isElementPresent(addDocumentButton, 30)) {
			throw new RuntimeException();
		}
	}
	
	By addButton = By.xpath("//table[contains(@id,'LSLST_RadGridTracts')]//a[contains(@id,'_AddJob')]");
	By paymentInformationTab = By.xpath("//div[contains(@id,'LSINFO_RadPageView3')]//img[@alt='Add Documents'] | //div[contains(@id,'LSINFO_RadPageView3')]//input[contains(@id,'_btnAddDoc')]");
	public void navigateToPaymentInformationROW() {
		util.waitUntilElementDisplay(navProjectMenu);
		util.click(navProjectMenu);
		util.waitUntilElementDisplay(navLeaseManager);
		util.click(navLeaseManager);
		util.waitUntilElementDisplay(addButton);
		util.click(paymentInformationTab);
		if (!util.isElementPresent(addDocumentButton, 30)) {
			throw new RuntimeException();
		}
	}
	
	
	By paymentTemplateDRP = By.xpath("//input[contains(@id,'MSDropDown_Input')]/../..//a");
	
	private void selectPaymentTemplate(String value) {
		if (!commonFunction.checkNA(value)) {
			util.selectValueFromDropdownCheckbox(paymentTemplateDRP, value);
			util.click(paymentTemplateDRP); 
		}
	}
	
	By agreementTerm = By.xpath("//input[contains(@id,'Lease_Agreement_ID') and @type='text']");
	private void selectPaymentTerm(String value) {
		if (!commonFunction.checkNA(value)) {
			util.dummyWait(2);
			util.inputTextAndPressTab(agreementTerm, value);
		}
	}
	By startPaymentFrom = By.xpath("//input[contains(@id,'START_PAYMENTS_FROM_dateInput') and @type='text']");
	private void setPaymentFrom(String value) {
		if (!commonFunction.checkNA(value)) {
			util.inputTextAndPressTab(startPaymentFrom, value);
		}
	}
	By addNewButton = By.xpath("//input[contains(@id,'ImgAddPaymentTerms')]");
	private void clickOnAddNewButton() {
		util.click(addNewButton);
	}
	By paymentTermGrid= By.xpath("//div[contains(@id,'LSINFO_RadPageView3')]//table[contains(@id,'LEASE_PAYMENTS_radYALGridControl')]/tbody/tr/td[3]");
	private void rightClickOnPaymentTermGrid() {
		util.dummyWait(2);
		action.contextClick(util.waitForWebElementToBePresentReturnElement(paymentTermGrid)).build().perform();
	}
	
	By removePendingDoc = By.xpath("//span[text()='Remove pending payments']/parent::a");
	private void clickOnRemovePendingDocument() {
		util.click(removePendingDoc);
	}
	
	By recalculateOption = By.xpath("//span[text()='Recalculate']/parent::a");
	private void clickOnRecalculate() {
		util.click(recalculateOption);
	}
	By deletePaymentType= By.xpath("//div[contains(@id,'LSINFO_RadPageView3')]//input[contains(@id,'gbcDeleteAlignment') and @title='Delete']");
	private void clickOnDeletePaymentTerm() {
		util.click(deletePaymentType);
	}
	By deleteCancelButton= By.xpath("//div[contains(@id,'confirm')]//span[text()='Cancel']");
	private void clickOnDeleteCancelButton() {
		util.click(deleteCancelButton);
	}
	By deleteOkButton= By.xpath("//div[contains(@id,'confirm')]//span[text()='OK']");
	private void clickOnDeleteOkButton() {
		util.click(deleteOkButton);
	}
	By paymentHistorySection= By.xpath("//span[text()='Payment History']/../parent::a");
	private void clickOnPaymentHistoryPanel() {
		if(!util.isElementVisible(upcomingTab)) {
			util.waitUntilLoaderDisappear();
			util.click(paymentHistorySection);
		}
	}
	By upcomingTab= By.xpath("//div[contains(@id,'RadTabStripPaymentDetails')]//span[text()='Upcoming']");
	By pastTab= By.xpath("//div[contains(@id,'RadTabStripPaymentDetails')]//span[text()='Past']");
	By cancelledTab= By.xpath("//div[contains(@id,'RadTabStripPaymentDetails')]//span[text()='Cancelled']");
	
	private void clickOnUpcomingTab() {
		util.click(upcomingTab);
	}
	private void clickOnPastTab() {
		util.click(pastTab);
	}
	private void clickOnCancelledTab() {
		util.click(cancelledTab);
	}
	
	public void addNewPaymentInformation(Map<String, String> map, String testCaseName) {
		
		try {
			if(testCaseName.toLowerCase().contains("row")) {
				navigateToPaymentInformationROW();
			} else {
				navigateToPaymentInformationALT();
			}
			log("STEP 1: Navigate to Project >agreement manager >payment Information page", Status.PASS);
		} catch (Exception e) {
			log("STEP 1: Navigate to Project >agreement manager >payment Information page", Status.FAIL);
			throw new RuntimeException("Failed in step 1");
		}
		
		try {
			selectPaymentTemplate(map.get(Excel.PaymentTemplate));
			log("STEP 2: Fill in Add payment template dd", Status.PASS);
		} catch (Exception e) {
			log("STEP 2: Fill in Add payment template dd", Status.FAIL);
			throw new RuntimeException("Failed in step 2");
		}
		
		try {
			selectPaymentTerm(map.get(Excel.PaymentTerm));
			log("STEP 3: Fill in Agreement Term DD", Status.PASS);
		} catch (Exception e) {
			log("STEP 3: Fill in Agreement Term DD", Status.FAIL);
			throw new RuntimeException("Failed in step 3");
		}
		
		try {
			setPaymentFrom(TestUtil.addDaysInCurrentDate("MM/dd/yyyy", -10));
			log("STEP 4: Select Start Payments From", Status.PASS);
		} catch (Exception e) {
			log("STEP 4: Select Start Payments From", Status.FAIL);
			throw new RuntimeException("Failed in step 4");
		}
		
		try {
			clickOnAddNewButton();
			log("STEP 5: Click on Add New button", Status.PASS);
		} catch (Exception e) {
			log("STEP 5: Click on Add New button", Status.FAIL);
			throw new RuntimeException("Failed in step 5");
		}
		
		try {
			rightClickOnPaymentTermGrid();
			log("STEP 6: Right Click on Payment Term Grid", Status.PASS);
		} catch (Exception e) {
			log("STEP 6: Click on Add New button", Status.FAIL);
			throw new RuntimeException("Failed in step 6");
		}
		
		try {
			clickOnRemovePendingDocument();
			log("STEP 7: Click on 'Remove Pending Payments'", Status.PASS);
		} catch (Exception e) {
			log("STEP 7: Click on 'Remove Pending Payments'", Status.FAIL);
			throw new RuntimeException("Failed in step 7");
		}
		try {
			rightClickOnPaymentTermGrid();
			clickOnRecalculate();
			log("STEP 8: Click on 'ReCalculate'", Status.PASS);
		} catch (Exception e) {
			log("STEP 8: Click on 'ReCalculate'", Status.FAIL);
			throw new RuntimeException("Failed in step 8");
		}
		
		try {
			clickOnDeletePaymentTerm();
			log("STEP 9: Cick on the Delete button for the record in the Payment Terms panel.(Click on cross icon) ", Status.PASS);
		} catch (Exception e) {
			log("STEP 9: Cick on the Delete button for the record in the Payment Terms panel.(Click on cross icon) ", Status.FAIL);
			throw new RuntimeException("Failed in step 9");
		}
		try {
			clickOnDeleteCancelButton();
			log("STEP 10: click on the Cancel button in the   Delete popup", Status.PASS);
		} catch (Exception e) {
			log("STEP 10: click on the Cancel button in the   Delete popup", Status.FAIL);
			throw new RuntimeException("Failed in step 10");
		}
		try {
			clickOnDeletePaymentTerm();
			clickOnDeleteOkButton();
			log("STEP 11: click OK button in the Delete popup", Status.PASS);
		} catch (Exception e) {
			log("STEP 11: click OK button in the Delete popup", Status.FAIL);
			throw new RuntimeException("Failed in step 11");
		}
		try {
			clickOnPaymentHistoryPanel();
			log("STEP 12: Click on Payment History Panel", Status.PASS);
		} catch (Exception e) {
			log("STEP 12: Click on Payment History Panel", Status.FAIL);
			throw new RuntimeException("Failed in step 12");
		}
		try {
			clickOnUpcomingTab();
			log("STEP 13: Click on Upcoming ", Status.PASS);
		} catch (Exception e) {
			log("STEP 13: Click on Upcoming ", Status.FAIL);
			throw new RuntimeException("Failed in step 13");
		}
		try {
			clickOnPastTab();
			log("STEP 14: Click on Past", Status.PASS);
		} catch (Exception e) {
			log("STEP 14: Click on Past", Status.FAIL);
			throw new RuntimeException("Failed in step 14");
		}
		try {
			clickOnCancelledTab();
			log("STEP 15: Click on Cancelled", Status.PASS);
		} catch (Exception e) {
			log("STEP 15: Click on Cancelled", Status.FAIL);
			throw new RuntimeException("Failed in step 15");
		}
		try {
			log("STEP 16: Click on 'View Payment Directive' link", Status.PASS);
		} catch (Exception e) {
			log("STEP 16: Click on 'View Payment Directive' link", Status.FAIL);
			throw new RuntimeException("Failed in step 16");
		}
		
	}
	
	
}
