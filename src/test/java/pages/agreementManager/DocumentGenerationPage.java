package pages.agreementManager;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.base.Excel;
import com.util.CommonFunction;
import com.util.TestUtil;

public class DocumentGenerationPage extends BasePage {
	
	TestUtil util;
	CommonFunction commonFunction;
	AddNewInformationPage objAddInfo;
	public DocumentGenerationPage(WebDriver driver) {
		this.driver = driver;
		util = new TestUtil(driver);
		commonFunction = new CommonFunction(driver);
		objAddInfo = new AddNewInformationPage(driver);
	}
	
	By loaderIcon = By.xpath("//body/div[contains(@id,'Ajax')]");
	
//	By documentTab = By.xpath("//span[text()='Documents']/../../parent::a[contains(@class,'rtsLink')]");
//	By addDocumentButton = By.xpath("//input[contains(@id,'LSINFO_AddDocuments')] | //input[contains(@id,'btnSendemails')]/../img[@alt='Add Documents']");
//	public void navigateToDocumentTab() {
//		util.click(documentTab);
//		util.waitForWebElementToBeClickable(addDocumentButton, IMPLICIT_WAIT);
//	}
	
	By generateDocumentTab = By.xpath("//div[contains(@id,'ConPHLeftTop_RadDockZone2')]//*[text()='Document Generation']/..//a[@title='Expand']");
	public void navigateToDocumentGenerationTab() {
		if(!util.isElementVisible(generateDocumentButton)) {
			util.click(generateDocumentTab);
		}
	}
	
	By generateDocumentButton = By.xpath("//input[contains(@id,'DocGen_btnSubmit')]");
	By previewDocumentButton = By.xpath("//input[contains(@id,'DocGen_btnPreview')]");
	By templateSelect = By.xpath("//select[contains(@name,'listTemplate1')]");
	By adobePDFRadio = By.xpath("//input[contains(@id,'DocGen_rbPdf')]");
	By msWordRadio = By.xpath("//input[contains(@id,'DocGen_rbWord')]");
	By msExcelRadio = By.xpath("//input[contains(@id,'DocGen_rbSpread')]");
	
	
	private void selectTemplate(String value) {
		if (!commonFunction.checkNA(value)) {
			util.selectValueFromDropdown2(value, templateSelect);
			util.waitFor(10000);
			util.waitUntilElementDisappear(loaderIcon);
		}
	}
	
	private void clickOnPDFRadioButton() {
		util.waitUntilElementDisappear(loaderIcon);
		util.waitForWebElementToBeClickable(adobePDFRadio, PAGE_LOAD_TIMEOUT);
		util.click(adobePDFRadio);
	}
	
	private boolean isPDFRadioSelected() {
		try {
			return util.getAttributeValue(adobePDFRadio, "checked").equals("true");
		} catch (Exception e) {
			return false;
		}
	}
	
	private void clickOnWordRadioButton() {
		util.click(msWordRadio);
	}
	
	private boolean isWordRadioSelected() {
		try {
			return util.getAttributeValue(msWordRadio, "checked").equals("true");
		} catch (Exception e) {
			return false;
		}
	}
	
	private void clickOnExcelRadioButton() {
		util.click(msExcelRadio);
	}
	
	private boolean isExcelRadioSelected() {
		try {
			return util.getAttributeValue(msExcelRadio, "checked").equals("true");
		} catch (Exception e) {
			return false;
		}
	}
	
	private void clickOnGenerateDocumentButton() {
		util.click(generateDocumentButton);
		util.waitUntilElementDisappear(loaderIcon);
	}
	
	private void clickOnPreviewDocumentButton() {
		util.click(previewDocumentButton);
		util.waitUntilElementDisappear(loaderIcon);
	}
	
	private void verifyMultipleWindow() {
		String parentWindow = driver.getWindowHandle();
		Set<String> allWindow = driver.getWindowHandles();
		if(allWindow.size()>1) {
			for(String currentWindow : allWindow) {
				if(!currentWindow.equals(parentWindow)) {
					driver.switchTo().window(currentWindow);
					driver.close();
				}
			}
			driver.switchTo().window(parentWindow);
		}
	}

	public void verifyGenerateDocument(String environment) {
		try {
			if(environment.toLowerCase().contains("row")) {
				objAddInfo.navigateToAgreementInformationROW();
			} else {
				objAddInfo.navigateToAgreementInformationALT();
			}
			log("STEP 1: Navigate to Menu - Project>Agreement Manager>Agreement Information>Document Generation Panel", Status.PASS);
		} catch (Exception e) {
			log("STEP 1: Navigate to Menu - Project>Agreement Manager>Agreement Information>Document Generation Panel", Status.FAIL);
			throw new RuntimeException("Failed in step 1");
		}
		
		try {
			navigateToDocumentGenerationTab();
			log("STEP 2: Expand document generation panel", Status.PASS);
		} catch (Exception e) {
			log("STEP 2: Expand document generation panel", Status.FAIL);
			throw new RuntimeException("Failed in step 2");
		}
		
		try {
			if(environment.toLowerCase().contains("row")) {
				selectTemplate("Lease Summary");
			} else {
				selectTemplate("Project Summary");
			}
			log("STEP 3: Select any one template from dd", Status.PASS);
		} catch (Exception e) {
			log("STEP 3: Select any one template from dd", Status.FAIL);
			throw new RuntimeException("Failed in step 3");
		}
		
		try {
			clickOnPDFRadioButton();
			Assert.assertTrue(isPDFRadioSelected(), "PDF Radio button is not selected");
			log("STEP 4: select Adobe pdf report format radio buttton ", Status.PASS);
		} catch (Exception e) {
			log("STEP 4: select Adobe pdf report format radio buttton ", Status.FAIL);
			throw new RuntimeException("Failed in step 4");
		}
		
		try {
			clickOnWordRadioButton();
			Assert.assertTrue(isWordRadioSelected(), "Word Radio button is not selected");
			log("STEP 5: select Word 2007  & newer report format radio buttton", Status.PASS);
		} catch (Exception e) {
			log("STEP 5: select Word 2007  & newer report format radio buttton", Status.FAIL);
			throw new RuntimeException("Failed in step 5");
		}
		
		try {
			clickOnExcelRadioButton();
			Assert.assertTrue(isExcelRadioSelected(), "Word Radio button is not selected");
			log("STEP 6: select Excel 2007  & newer report format radio buttton", Status.PASS);
		} catch (Exception e) {
			log("STEP 6: select Excel 2007  & newer report format radio buttton", Status.FAIL);
			throw new RuntimeException("Failed in step 6");
		}
		
		try {
			clickOnPDFRadioButton();
			clickOnGenerateDocumentButton();
			verifyMultipleWindow();
			log("STEP 7: Click on Generate Document button", Status.PASS);
		} catch (Exception e) {
			log("STEP 7: Click on Generate Document button", Status.FAIL);
			throw new RuntimeException("Failed in step 7");
		}
	}
	
	public void verifyPreviewDocument(String environment) {
		try {
			log("STEP 1: Navigate to Menu - Project>Agreement Manager>Agreement Information>Document Generation Panel", Status.PASS);
		} catch (Exception e) {
			log("STEP 1: Navigate to Menu - Project>Agreement Manager>Agreement Information>Document Generation Panel", Status.FAIL);
			throw new RuntimeException("Failed in step 1");
		}
		
		try {
			navigateToDocumentGenerationTab();
			log("STEP 2: Expand document generation panel", Status.PASS);
		} catch (Exception e) {
			log("STEP 2: Expand document generation panel", Status.FAIL);
			throw new RuntimeException("Failed in step 2");
		}
		
		try {
			if(environment.toLowerCase().contains("row")) {
				selectTemplate("Lease Summary");
			} else {
				selectTemplate("Project Summary");
			}
			log("STEP 3: Select any one template from dd", Status.PASS);
		} catch (Exception e) {
			log("STEP 3: Select any one template from dd", Status.FAIL);
			throw new RuntimeException("Failed in step 3");
		}
		
		try {
			clickOnPDFRadioButton();
			Assert.assertTrue(isPDFRadioSelected(), "PDF Radio button is not selected");
			log("STEP 4: select Adobe pdf report format radio buttton ", Status.PASS);
		} catch (Exception e) {
			log("STEP 4: select Adobe pdf report format radio buttton ", Status.FAIL);
			throw new RuntimeException("Failed in step 4");
		}
		
		try {
			clickOnWordRadioButton();
			Assert.assertTrue(isWordRadioSelected(), "Word Radio button is not selected");
			log("STEP 5: select Word 2007  & newer report format radio buttton", Status.PASS);
		} catch (Exception e) {
			log("STEP 5: select Word 2007  & newer report format radio buttton", Status.FAIL);
			throw new RuntimeException("Failed in step 5");
		}
		
		try {
			clickOnExcelRadioButton();
			Assert.assertTrue(isExcelRadioSelected(), "Word Radio button is not selected");
			log("STEP 6: select Excel 2007  & newer report format radio buttton", Status.PASS);
		} catch (Exception e) {
			log("STEP 6: select Excel 2007  & newer report format radio buttton", Status.FAIL);
			throw new RuntimeException("Failed in step 6");
		}
		
		try {
			clickOnPDFRadioButton();
			clickOnPreviewDocumentButton();
			verifyMultipleWindow();
			log("STEP 7: Click on Preview Document button", Status.PASS);
		} catch (Exception e) {
			log("STEP 7: Click on Preview Document button", Status.FAIL);
			throw new RuntimeException("Failed in step 7");
		}
	}
}
