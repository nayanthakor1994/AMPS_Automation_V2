package pages.projectGroup;

import java.io.File;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.base.Excel;
import com.util.CommonFunction;
import com.util.TestUtil;

public class AddDocumentVersionPage extends BasePage {

	TestUtil util;
	CommonFunction commonFunction;

	public AddDocumentVersionPage(WebDriver driver) {
		this.driver = driver;
		util = new TestUtil(driver);
		commonFunction = new CommonFunction(driver);
	}

	By navProjectMenu = By.xpath("*//a//span[@class='rmText rmExpandDown' and contains(text(),'Project')] ");
	By navProjectGroup = By
			.xpath("*//a//span[contains(text(),'Project Group') or contains(text(),'Group Information')] ");
	By iframeDocument = By.xpath("//iframe[@name='AddDocuments']");

	By documentSuccessMessage = By.xpath("//span[@id='lblResults']");
	By btnCloseDocument = By.xpath("//div[contains(@id,'_AddDocuments')]//a[@title='Close']");

	public void navigateToProjectGroup() {
		util.waitUntilElementDisplay(navProjectMenu);
		util.click(navProjectMenu);
		util.waitUntilElementDisplay(navProjectGroup);
		util.click(navProjectGroup);
	}

	By tabGroupDocument = By.xpath("(//span[text()='Group Documents'])[1]");

	public void clickOnGroupDocument() {
		util.click(tabGroupDocument);
	}

	By btnAddDocument = By.xpath("(//img[@alt='Add Documents'])[1]");

	public void clickOnAddDocument() {
		util.click(btnAddDocument);
	}

	By fileUpload = By.xpath("//input[contains(@id,'FileUploadfile')]");

	public void uploadFile() {
		String filepath = System.getProperty("user.dir") + File.separator + "test.txt";
		driver.findElement(fileUpload).sendKeys(filepath);
	}

	public void rightClickOnGrid(String value) {
		By clickOnDocument = By.xpath("//*[text()='" + value + "']");
		WebElement test = driver.findElement(clickOnDocument);
		util.click(clickOnDocument);
		util.Rcllick(test);
	}

	By btnAddVersion = By.xpath("//*[text()='Add Version']");

	public void clickOnAddVeriosn() {
		util.waitUntilElementDisplay(btnAddVersion);
		util.click(btnAddVersion);
	}

	By btnViewHistory = By.xpath("//*[text()='View History']");

	public void clickOnViewHistory() {
		util.waitUntilElementDisplay(btnViewHistory);
		util.click(btnViewHistory);
	}

	By btnRequestDelete = By.xpath("//*[text()='Request Delete']");

	public void clickOnRequestDelete() {
		util.waitUntilElementDisplay(btnRequestDelete);
		util.click(btnRequestDelete);
	}

	By sectionExternalLink = By.xpath("//*[text()='External Link']");

	public void openExternalLink() {
		util.click(sectionExternalLink);
	}

	public void switchToWindow() {
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String handle : allWindowHandles) {
			System.out.println("Switching to window - > " + handle);
			driver.switchTo().window(handle);
		}
	}

	By selectCategory_ExternalLink = By.xpath("//input[contains(@id,'YALDropDownList_Input')]");

	public void selectCategoryExternalLink(String value) {
		if (!commonFunction.checkNA(value))
		util.waitUntilElementDisplay(selectCategory_ExternalLink);
		util.inputTextAndPressTab(selectCategory_ExternalLink, value);
	}

	By selectCategory_ExternalLinkDOT = By.xpath("//div[@id='radReportConfigPanels_i0_i0_XYZ1_RadDropDownTree1']");

	public void selectCategoryExternalLinkDOT(String value) {
		if (!commonFunction.checkNA(value))
		util.waitUntilElementDisplay(selectCategory_ExternalLinkDOT);
		util.inputTextAndPressTab(selectCategory_ExternalLinkDOT, value);
	}

	By ExternalLink = By.xpath("//input[contains(@id,'RadPanelBar2_i0_i0_txtlinkname1')]");

	public void setExternalLink(String value) {
		if (!commonFunction.checkNA(value))
		util.waitUntilElementDisplay(ExternalLink);
		util.inputTextAndPressTab(ExternalLink, value);
	}

	By txtSharePointLink = By.xpath("//input[contains(@id,'link1')]");

	public void setSharePointLInk(String value) {
		if (!commonFunction.checkNA(value))
		util.waitUntilElementDisplay(txtSharePointLink);
		util.inputTextAndPressTab(txtSharePointLink, value);
	}

	By txtDocumentName = By.xpath("//input[contains(@id,'txtDynamic1')]");

	public void setDocumentName(String value) {
		if (!commonFunction.checkNA(value))
		util.waitUntilElementDisplay(txtDocumentName);
		util.inputTextAndPressTab(txtDocumentName, value);
	}

	By txtDescriptionExternalLink = By.xpath("//input[contains(@id,'txtDynamic1')]");

	public void setDescriptionExternalLink(String value) {
		if (!commonFunction.checkNA(value))
		util.waitUntilElementDisplay(txtDescriptionExternalLink);
		util.inputTextAndPressTab(txtDescriptionExternalLink, value);
	}

	By btnLoadDocument_ExternalLink = By
			.xpath("//div[(@id='radReportConfigPanels')]//following::span[@id='radReportConfigPanels_i0_i0_btnSave']");

	public void clickOnLoadDocumentExternalLInk() {
		util.click(btnLoadDocument_ExternalLink);
	}

	public void addDocumentVersion(Map<String, String> map, String testcaseName) {
		try {
			navigateToProjectGroup();
			log("STEP 1:  Navigate to Menu - Project Group", Status.PASS);
		} catch (Exception e) {
			log("STEP 1:  Navigate to Menu - Project Group", Status.FAIL);
			throw new RuntimeException("Failed in step 1: Popup up does not appear");
		}

		try {
			clickOnGroupDocument();
			log("STEP 2:  User can Click on Group Document", Status.PASS);
		} catch (Exception e) {
			log("STEP 2:  User can not Click on Group Document", Status.FAIL);
			throw new RuntimeException("STEP 2:  User can not Click on Group Document");
		}
		try {
			rightClickOnGrid(map.get(Excel.DocumentName));
			log("STEP 3:  User can Right Click on Group Document Grid ", Status.PASS);
		} catch (Exception e) {
			log("STEP 3:  User can not Right Click on Group Document grid ", Status.FAIL);
			throw new RuntimeException("STEP 2:  User can not Click on Group Document");
		}
		try {
			clickOnAddVeriosn();
			log("STEP 4:  User can Click on Add Version ", Status.PASS);
		} catch (Exception e) {
			log("STEP 4:  User can not Click on Add Version", Status.FAIL);
			throw new RuntimeException("STEP 4:  User can not Click on Group Document");
		}

		try {
			// util.switchToIframe(iframeDocument);
			switchToWindow();
			openExternalLink();
			if (testcaseName.contains("ALT")) {
				selectCategoryExternalLink(map.get(Excel.Category));
			} else {
				selectCategoryExternalLinkDOT(map.get(Excel.Category));
			}
			log("STEP 4:  User can select Category", Status.PASS);
		} catch (Exception e) {
			log("STEP 4:  User can not select Category", Status.FAIL);
			throw new RuntimeException("STEP 4:  User can not select Category");
		}
		if (testcaseName.contains("ALT")) {
			try {
				setExternalLink(map.get(Excel.ExternalLink));
				log("STEP 5:  User can enter ExternalLink ", Status.PASS);
			} catch (Exception e) {
				log("STEP 5:  User can not enter ExternalLink", Status.FAIL);
				throw new RuntimeException("STEP 5:  User can not enter ExternalLink");
			}
		} else {
			try {
				setSharePointLInk(map.get(Excel.SharePointLink));
				log("STEP 5:  User can enter SharePointLink ", Status.PASS);
			} catch (Exception e) {
				log("STEP 5:  User can not enter SharePointLink", Status.FAIL);
				throw new RuntimeException("STEP 5:  User can not enter SharePointLink");
			}
		}
		try {
			setDocumentName(map.get(Excel.DocumentName));
			log("STEP 6:  User can enter DocumentName ", Status.PASS);
		} catch (Exception e) {
			log("STEP 6:  User can not enter DocumentName", Status.FAIL);
			throw new RuntimeException("STEP 6:  User can not enter DocumentName");
		}
		try {
			clickOnLoadDocumentExternalLInk();
			util.waitUntilElementDisplay(documentSuccessMessage);
			String msg = util.getText(documentSuccessMessage);
			driver.close();
			// util.click(btnCloseDocument);
			// util.switchToDefaultContent();
			log("STEP 7:  User can click on LoadDocument ", Status.PASS);
		} catch (Exception e) {
			log("STEP 7:  User can not click LoadDocument", Status.FAIL);
			throw new RuntimeException("STEP 7:  User can not click LoadDocument");
		}

	}

}
