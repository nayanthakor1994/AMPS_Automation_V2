package pages.SSRSReport;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.base.Excel;
import com.util.CommonFunction;
import com.util.TestUtil;

public class SSRSReportPage extends BasePage {

	
	TestUtil util;
	CommonFunction commonFunction;
	public SSRSReportPage(WebDriver driver) {
		this.driver = driver;
		util = new TestUtil(driver);
		commonFunction = new CommonFunction(driver);
		// TODO Auto-generated constructor stub
	}

	By navReport = By.xpath("//a//span[@class='rmText rmExpandDown' and contains(text(),'Reports')] ");
	By navSSRSReport = By.xpath("//a//span[@class='rmText' and contains(text(),'SSRS Dashboard')]");

	public void navigateToSSRSReport() {
		util.waitUntilElementDisplay(navReport);
		util.click(navReport);
		util.waitUntilElementDisplay(navSSRSReport);
		util.click(navSSRSReport);
	}
	
	By drpDDReport = By.xpath("(//div[contains(@id,'ddt_REPORTLIST_RadDropDownTree1')])[1]");
	public void setDDReport(String value) {
		if (!commonFunction.checkNA(value))
		util.click(drpDDReport);
		By entertxt = By.xpath("//input[contains(@class,'rddtFilterEmptyMessage')]");
		util.inputText(entertxt, value);
		By selectDD = By.xpath("//*[contains(text(),'"+value+"')]");
		util.click(selectDD);
		//util.clickAndInputTextwithEnterKey(drpDDReport,entertxt,value);
	}
	
	By drpProject = By.xpath("//input[contains(@id,'ctl00_ConPHLeftTop_RadDock3_C_134_Input')]");
	By drpCloaseProject = By.xpath("(//td[@class='rcbArrowCell rcbArrowCellRight'])[1]");
	public void setProjectName(String value) {
		if (!commonFunction.checkNA(value)) {
		util.waitUntilElementDisplay(drpProject);
		util.selectValueFromDropdownCheckbox(drpProject, value);}
		util.click(drpCloaseProject);
		}
	
	By drpAgreementType = By.xpath("//input[contains(@id,'ctl00_ConPHLeftTop_RadDock3_C_135_Input')]");
	By drpCloseAgreementType = By.xpath("(//td[@class='rcbArrowCell rcbArrowCellRight'])[2]");
	public void setAgreementType(String value) {
		if (!commonFunction.checkNA(value)) {
			util.waitUntilElementDisplay(drpAgreementType);
			util.scrollToElement(drpAgreementType);
			util.selectValueFromDropdownCheckbox(drpAgreementType, value);
			util.click(drpCloseAgreementType);
		}
	}
	
	By drpAgreementStatus = By.xpath("//input[contains(@id,'ctl00_ConPHLeftTop_RadDock3_C_158_Input')]");
	By drpCloseAgreementStatus = By.xpath("(//td[@class='rcbArrowCell rcbArrowCellRight'])[3]");
	public void setAgreementStatus (String value) {
		if (!commonFunction.checkNA(value)) {
		util.waitUntilElementDisplay(drpAgreementStatus);
		util.scrollToElement(drpAgreementStatus);
		util.selectValueFromDropdownCheckbox(drpAgreementStatus, value);
		util.click(drpCloseAgreementStatus);
		
	}}
	By txtStartDate = By.xpath("//input[contains(@id,'ctl00_ConPHLeftTop_RadDock3_C_131_dateInput')]");
	public void setStartDate(String value) {
		if (!commonFunction.checkNA(value)) {
		util.waitUntilElementDisplay(txtStartDate);
		util.inputTextAndPressTab(txtStartDate, value);
		}
	}
	By txtEndDate = By.xpath("//input[contains(@id,'ctl00_ConPHLeftTop_RadDock3_C_132_dateInput')]");
	public void setEndDate(String value) {
		if (!commonFunction.checkNA(value)) {
		util.waitUntilElementDisplay(txtEndDate);
		util.inputTextAndPressTab(txtEndDate, value);
		}}
	By btnView = By.xpath("(//input[contains(@type,'image')])[4]");
	public void clickOnView() {
		util.waitUntilElementDisplay(btnView);
		util.click(btnView);
	}
	
	public void isProjectNameListView(String value1) {
		String value="Workflow Name";
		if (!commonFunction.checkNA(value)) {
		By listOfProjectName = By.xpath("//*[text()='"+value+"']");
		util.isElementPresent(listOfProjectName);
		}
		
	}
	
	public void viewSSRSReport(Map<String, String> map, String testcaseName) {
		try {
			navigateToSSRSReport();
			log("STEP 1:  Navigate to Menu - SSRS Report", Status.PASS);
		} catch (Exception e) {
			log("STEP 1:  Navigate to Menu - SSRS Report", Status.FAIL);
			throw new RuntimeException("Failed in step 1: Popup up does not appear");
		}
		
		try {
			setDDReport(map.get(Excel.DDReport));
			log("STEP 2:  User can select DDReport", Status.PASS);
		} catch (Exception e) {
			log("STEP 2:  User can not enter DDReport", Status.FAIL);
			throw new RuntimeException("Failed in step 2: User can not enter DDReport");
		}
		try {
			setProjectName(map.get(Excel.ProjectName));
			log("STEP 3:  User can select ProjectName", Status.PASS);
		} catch (Exception e) {
			log("STEP 3:  User can not enter ProjectName", Status.FAIL);
			throw new RuntimeException("Failed in step 3: User can not enter ProjectName");
		}
		try {
			setAgreementType(map.get(Excel.AgreementType));
			log("STEP 4:  User can select AgreementType", Status.PASS);
		} catch (Exception e) {
			log("STEP 4:  User can not enter AgreementType", Status.FAIL);
			throw new RuntimeException("Failed in step 2: User can not enter AgreementType");
		}
		try {
			setAgreementStatus(map.get(Excel.AgreementStatus));
			log("STEP 5:  User can select AgreementStatus", Status.PASS);
		} catch (Exception e) {
			log("STEP 5:  User can not enter AgreementStatus", Status.FAIL);
			throw new RuntimeException("Failed in step 5: User can not enter AgreementStatus");
		}
		try {
			setStartDate(map.get(Excel.StartDate));
			log("STEP 5:  User can select StartDate", Status.PASS);
		} catch (Exception e) {
			log("STEP 5:  User can not enter StartDate", Status.FAIL);
			throw new RuntimeException("Failed in step 5: User can not enter StartDate");
		}
		try {
			setEndDate(map.get(Excel.EndDate));
			log("STEP 6:  User can select EndDate", Status.PASS);
		} catch (Exception e) {
			log("STEP 6:  User can not enter EndDate", Status.FAIL);
			throw new RuntimeException("Failed in step 5: User can not enter EndDate");
		}
		try {
			clickOnView();
			log("STEP 7:  User can click on View", Status.PASS);
		} catch (Exception e) {
			log("STEP 7:  User can not click on View", Status.FAIL);
			throw new RuntimeException("Failed in step 7: User can not click on View");
		}
		try {
			isProjectNameListView(map.get(Excel.ProjectName));
			log("STEP 8:  User can view SSRS Reports", Status.PASS);
		} catch (Exception e) {
			log("STEP 8:  User can not view SSRS Reports", Status.FAIL);
			throw new RuntimeException("Failed in step 8: User can not view SSRS Reports");
		}
		
		
		
	}

}
