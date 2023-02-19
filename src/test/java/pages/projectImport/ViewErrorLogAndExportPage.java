package pages.projectImport;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.base.Excel;
import com.util.CommonFunction;
import com.util.TestUtil;

public class ViewErrorLogAndExportPage extends BasePage{

	
	TestUtil util;
	CommonFunction commonFunction;
	public ViewErrorLogAndExportPage(WebDriver driver) {
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
	By viewErrorLog=  By.xpath("//span[@id='ctl00_ConPHRightTop_idYalImport_spViewErrorLog']");
	By downlodPDF = By.xpath("//a[contains(@id,'DownloadPDF')]");
	By downloadExcel = By.xpath("//a[contains(@id,'DownloadEXCEL')]");
	
	By btnexport = By.xpath("//input[contains(@id,'btnSaveTract')]");
	
	
	public void viewErrorLog(String testcaseName) {
		try {
			navigateToImport();
			log("STEP 1:  Navigate to Menu - Import", Status.PASS);
		} catch (Exception e) {
			log("STEP 1:  Navigate to Menu - Import", Status.FAIL);
			throw new RuntimeException("Failed in step 1: Popup up does not appear");
		}
		
		try {
			util.click(viewErrorLog);
			log("STEP 2:  User can click on Error Log", Status.PASS);
		} catch (Exception e) {
			log("STEP 2:  User can not click on ErroLog", Status.FAIL);
			throw new RuntimeException("Failed in step 2: User can not click on ErroLog");
		}
		try {
			util.switchToWindow();
			util.click(downlodPDF);
			log("STEP 3 :  User can click on Download PDF", Status.PASS);
		} catch (Exception e) {
			log("STEP 3:  User can not click on Download PDF", Status.FAIL);
			throw new RuntimeException("Failed in step 3: User can not click on Download PDF");
		}
		try {
			util.click(downloadExcel);
			util.switchToDefaultContent();
			log("STEP 4:  User can click on Downlaod Excel", Status.PASS);
		} catch (Exception e) {
			log("STEP 4:  User can not click on Downlaod Excel", Status.FAIL);
			throw new RuntimeException("Failed in step 4: User can not click on Downlaod Excel");
		}
		
	}

	public void exportButton (String testcaseName) {
		if(testcaseName.contains("ALT")||testcaseName.contains("ROW")) {
			log("Export Button Not applicable for ALT  :", Status.PASS);
			
		}
		else {
			try {
				navigateToImport();
				log("STEP 1:  Navigate to Menu - Import", Status.PASS);
			} catch (Exception e) {
				log("STEP 1:  Navigate to Menu - Import", Status.FAIL);
				throw new RuntimeException("Failed in step 1: Popup up does not appear");
			}
			try {
				util.click(btnexport);
				log("STEP 2:  User can click on Export", Status.PASS);
			} catch (Exception e) {
				log("STEP 2:  User can not click on Export", Status.FAIL);
				throw new RuntimeException("Failed in step 2: User can not click on Export");
			}
		}
		
		
	}
	

}
