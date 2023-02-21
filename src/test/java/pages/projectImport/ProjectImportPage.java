package pages.projectImport;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.base.Excel;
import com.util.CommonFunction;
import com.util.ExcelUtils;
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
		value = "'"+value+"'";
		util.inputTextAndPressTab(drpWorkSheet, value);
	}
	By drpImportType = By.xpath("//input[contains(@id,'DDImportType')]");
	
	public void setImportType(String value) {
		util.waitUntilElementDisplay(drpImportType);
		util.inputTextAndPressTab(drpImportType, value);
	}
	
	By drpProject = By.xpath("//input[contains(@id,'ProjectList')]");
	public void setProject(String value) {
		util.waitUntilElementDisplay(drpProject);
		util.inputTextAndPressTab(drpProject, value);
	}
	By dropMapping = By.xpath("//input[contains(@id,'RadMyUploadMap')]");
	public void setMapping(String value) {
		util.waitUntilElementDisplay(dropMapping);
		util.inputTextAndPressTab(dropMapping, value);
		util.waitUntilLoaderDisappear();
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
				Assert.assertTrue(util.isElementPresent(importMessage, 30), "Data import message not displayed");
				verifyLandData(testcaseName);
				log("STEP 8:  Import Successfully :", Status.PASS);
			} catch (Exception e) {
				log("STEP 8:  Import is not perfome Successfully :", Status.FAIL);
				throw new RuntimeException("Failed in step 8:  User can not able to select Mapping");
			}
			
			
		}
	By navParcelMenu = By.xpath("*//a//span[@class='rmText rmExpandDown' and contains(text(),'Parcel')]");
	By navParcelInfo= By.xpath("*//span[text()='Parcel Info']/parent::a");
	By navParcelInformation= By.xpath("*//span[text()='Parcel Information']/parent::a");
	public void navigateToParcelInformation() {
		util.waitUntilElementDisplay(navParcelMenu);
		util.click(navParcelMenu);
		util.waitUntilElementDisplay(navParcelInfo);
		util.click(navParcelInfo);
		util.waitUntilElementDisplay(navParcelInformation);
		util.click(navParcelInformation);
	}
	
	By parcelNumber = By.xpath("//input[contains(@id,'TRACT_NUMBER') and @type='text']");
	private String getParcelNumber() {
		return util.getAttributeValue(parcelNumber, "value");
	}
	By parcelType = By.xpath("//input[contains(@id,'TractType_Tract_Type_ID') and @type='text']");
	private String getParcelType() {
		return util.getAttributeValue(parcelType, "value");
	}
	By propertyType = By.xpath("//input[contains(@id,'TractType_Property_Type_ID') and @type='text']");
	private String getPropertyType() {
		return util.getAttributeValue(propertyType, "value");
	}
	By acquisitionPriority = By.xpath("//input[contains(@id,'TractType_Acquisition_type_ID') and @type='text']");
	private String getAcquisitionPriority() {
		return util.getAttributeValue(acquisitionPriority, "value");
	}
	By countryPID = By.xpath("//input[contains(@id,'TractType_radGISID') and @type='text']");
	private String getCountryPID() {
		return util.getAttributeValue(countryPID, "value");
	}
	By parcelAcres = By.xpath("//input[contains(@id,'TractType_customFields2_41') and @type='text']");
	private String getParcelAcres() {
		return util.getAttributeValue(parcelAcres, "value");
	}
	By gisAcres = By.xpath("//input[contains(@id,'TractType_customFields2_42') and @type='text']");
	private String getGISAcres() {
		return util.getAttributeValue(gisAcres, "value");
	}
	By leasedAcres = By.xpath("//input[contains(@id,'TractType_customFields2_43') and @type='text']");
	private String getLeasedAcres() {
		return util.getAttributeValue(leasedAcres, "value");
	}
	
	public void verifyLandData(String testcaseName) {
		navigateToParcelInformation();
		List<Map<String, String>> excelData = ExcelUtils.getAllData(prop.getProperty(Excel.ALT_PROJECTIMPORT_FILE), Excel.LandData);
		for(Map<String, String> map : excelData) {
			By addedRecord = By.xpath("//td[text()='"+map.get("Parcel #").trim()+"']/parent::tr");
			Assert.assertTrue(util.isElementPresent(addedRecord, 30), "Record is not added yet");
			util.click(addedRecord);
			
			By parcelSummary = By.xpath("//td[contains(@id,'_tractInfo')]//td[normalize-space()='"+map.get("Parcel #").trim()+"']");
			util.waitForWebElementToBePresent(parcelSummary, IMPLICIT_WAIT);
			
			SoftAssert assertion = new SoftAssert();
			
			assertion.assertEquals(getParcelNumber(), map.get("Parcel #").trim(), "Parcel number mismatched");
			assertion.assertEquals(getParcelType(), map.get("Parcel Type").trim(), "Parcel type mismatched");
			assertion.assertEquals(getPropertyType(), map.get("Property Type").trim(), "Parcel type mismatched");
//			assertion.assertEquals(getAcquisitionPriority(), map.get("Acquisition Priority").trim(), "Parcel type mismatched");
//			assertion.assertEquals(getCountryPID(), map.get("County PID").trim(), "Parcel type mismatched");
			assertion.assertEquals(getParcelAcres(), map.get("Parcel Acres").trim(), "Parcel type mismatched");
			assertion.assertEquals(getGISAcres(), map.get("GIS Parcel Acres").trim(), "Parcel type mismatched");
			assertion.assertEquals(getLeasedAcres(), map.get("Leased Acres").trim(), "Parcel type mismatched");
			
			assertion.assertAll();
		}
	}

	

}
