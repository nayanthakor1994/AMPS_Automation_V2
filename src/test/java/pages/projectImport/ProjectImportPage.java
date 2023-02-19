package pages.projectImport;

import java.io.File;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.base.Excel;
import com.util.CommonFunction;
import com.util.TestUtil;

public class ProjectImportPage extends BasePage {

	TestUtil util;
	CommonFunction commonFunction;

	public ProjectImportPage(WebDriver driver) {
		this.driver = driver;
		util = new TestUtil(driver);
		commonFunction = new CommonFunction(driver);
	}

	By navProjectMenu = By.xpath("*//a//span[@class='rmText rmExpandDown' and contains(text(),'Project')] ");
	By navImfort = By.xpath("*//span[text()='Import']");

	public void navigateToImport() {
		util.waitUntilElementDisplay(navProjectMenu);
		util.click(navProjectMenu);
		util.waitUntilElementDisplay(navImfort);
		util.click(navImfort);

	}

	By viewErrorLog = By.xpath("//span[@id='ctl00_ConPHRightTop_idYalImport_spViewErrorLog']");
	By downlodPDF = By.xpath("//a[contains(@id,'DownloadPDF')]");
	By downloadExcel = By.xpath("//a[contains(@id,'DownloadEXCEL')]");

	By btnexport = By.xpath("//input[contains(@id,'btnSaveTract')]");

	
	By loadDocument = By.xpath("//input[contains(@id,'buttonSubmit')]");
	By fileUpload = By.xpath("//input[contains(@id,'RadUpload1file') or contains(@id,'rauFileUploadfile')]");
	
	public void uploadFile(String testcasename) {
		if(testcasename.contains("ALT")) {
			String filepath = System.getProperty("user.dir") + File.separator + "ALT-Consolidated_Project_Data_Import_With data.xlsx";
		//	String filepath = System.getProperty(Excel.ALT_PROJECTIMPORT_FILE);
			driver.findElement(fileUpload).sendKeys(filepath);
		}
		else {
			String filepath = System.getProperty("user.dir") + File.separator + "ROW-Consolidated Import Template.xlsx";
			//String filepath = System.getProperty(Excel.ROW_PROJECTIMPORT_FILE);
			driver.findElement(fileUpload).sendKeys(filepath);
		}
		util.dummyWait(5);
		log("Uploadfile ", Status.PASS);
		util.waitUntilElementDisplay(loadDocument);
		util.click(loadDocument);
		log("Click on load ", Status.PASS);
	}
	By drpWorkSheet = By.xpath("//input[contains(@id,'radcbSheets_Input')]");
	public void setWorkSheet(String value) {
		util.waitUntilElementDisplay(drpWorkSheet);
		util.inputText(drpWorkSheet, value);
	}
	By drpImportType = By.xpath("//input[contains(@id,'DDImportType')]");
	
	public void setImportType(String value) {
		util.waitUntilElementDisplay(drpImportType);
		util.inputText(drpImportType, value);
	}
	
	By drpProject = By.xpath("//input[contains(@id,'ProjectList')]");
	public void setProject(String value) {
		util.waitUntilElementDisplay(drpProject);
		util.inputText(drpProject, value);
	}
	By dropMapping = By.xpath("//input[contains(@id,'RadMyUploadMap')]");
	public void setMapping(String value) {
		util.waitUntilElementDisplay(dropMapping);
		util.inputText(dropMapping, value);
	}
	
	By importMessage = By.xpath("//*[text()='Data imported successfully']");

	By btnLoadDataProject = By.xpath("//input[contains(@id,'btnLoadTract')]");
	
	public void projectImport(Map<String, String> map, String testcaseName) {
			try {
				navigateToImport();
				log("STEP 1:  Navigate to Menu - Import", Status.PASS);
			} catch (Exception e) {
				log("STEP 1:  Navigate to Menu - Import", Status.FAIL);
				throw new RuntimeException("Failed in step 1: Popup up does not appear");
			}
			try {
				uploadFile(testcaseName);
				log("STEP 2:  User can Upload File", Status.PASS);
			} catch (Exception e) {
				log("STEP 2:  User can not Upload File", Status.FAIL);
				throw new RuntimeException("Failed in step 2: User can not Upload File");
			}
			try {
				setWorkSheet(map.get(Excel.WorkSheet));
				log("STEP 3:  User can able to select WorkSheet", Status.PASS);
			} catch (Exception e) {
				log("STEP 3:  User can not able to select WorkSheet", Status.FAIL);
				throw new RuntimeException("Failed in step 3:  User can not able to select WorkSheet");
			}
			try {
				setImportType(map.get(Excel.ImportType));
				log("STEP 4:  User can able to select ImportType", Status.PASS);
			} catch (Exception e) {
				log("STEP 4:  User can not able to select ImportType", Status.FAIL);
				throw new RuntimeException("Failed in step 4:  User can not able to select ImportType");
			}
			try {
				setProject(map.get(Excel.Project));
				log("STEP 5:  User can able to select Project", Status.PASS);
			} catch (Exception e) {
				log("STEP 5:  User can not able to select Project", Status.FAIL);
				throw new RuntimeException("Failed in step 5:  User can not able to select Project");
			}
			try {
				setMapping(map.get(Excel.Mapping));
				log("STEP 6:  User can able to select Mapping", Status.PASS);
			} catch (Exception e) {
				log("STEP 6:  User can not able to select Mapping", Status.FAIL);
				throw new RuntimeException("Failed in step 6:  User can not able to select Mapping");
			}
			try {
				util.click(btnLoadDataProject);
				log("STEP 7:  User can able to select Mapping", Status.PASS);
			} catch (Exception e) {
				log("STEP 7:  User can not able to select Mapping", Status.FAIL);
				throw new RuntimeException("Failed in step 6:  User can not able to select Mapping");
			}
			try {
				if(util.isElementPresent(importMessage)==true)
				log("STEP 8:  Import Successfully :", Status.PASS);
			} catch (Exception e) {
				log("STEP 8:  Import is not perfome Successfully :", Status.FAIL);
				throw new RuntimeException("Failed in step 8:  User can not able to select Mapping");
			}
			
			
		}

	

}
