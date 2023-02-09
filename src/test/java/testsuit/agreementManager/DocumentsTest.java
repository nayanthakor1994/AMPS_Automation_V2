package testsuit.agreementManager;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.base.BasePage;
import com.base.Excel;
import com.util.ExcelUtils;

import page.Common.LoginPage;
import pages.agreementManager.AddNewInformationPage;
import pages.agreementManager.DocumentGenerationPage;
import pages.agreementManager.DocumentsPage;

@Listeners(com.listeners.MyListeners.class)
public class DocumentsTest extends BasePage {

	LoginPage objLogin;
	AddNewInformationPage objAddInfo;
	DocumentsPage objDoc;
	Map<String, String> map = new HashMap<String, String>();
	Map<String, String> addDocumentMap = new HashMap<String, String>();
	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		objAddInfo = new AddNewInformationPage(driver);
		objDoc = new DocumentsPage(driver);
	}

	@Test(priority = 1,enabled = false)
	public void Documents_Add_A_Document_External_Document() throws Exception {
		log("TC01 : Add a Document - External Document");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.TestCases, environment);
		objLogin.login(map);
		String testcaseName = "AddDocument" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.AGREEEMENT_TEST_DATA), Excel.Documents, testcaseName);
		if(environment.toLowerCase().contains("row")) {
			objAddInfo.navigateToAgreementInformationROW();
		} else {
			objAddInfo.navigateToAgreementInformationALT();
		}
		objDoc.addDocument_ExternalDocument(map,testcaseName);
		
	}
	
	@Test(priority = 2,enabled = false)
	public void Documents_Add_A_Document_External_Link() throws Exception {
		log("TC02 : Add a Document - External Link");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.TestCases, environment);
		objLogin.login(map);
		String testcaseName = "AddExternalDoc" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.AGREEEMENT_TEST_DATA), Excel.Documents, testcaseName);
		if(environment.toLowerCase().contains("row")) {
			objAddInfo.navigateToAgreementInformationROW();
		} else {
			objAddInfo.navigateToAgreementInformationALT();
		}
		objDoc.addDocument_ExternalLink(map,testcaseName);
	}
	
	@Test(priority = 3,enabled = false)
	public void Documents_View_Document() throws Exception {
		log("TC03 : View Document");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.TestCases, environment);
		objLogin.login(map);
		if(environment.toLowerCase().contains("row")) {
			objAddInfo.navigateToAgreementInformationROW();
		} else {
			objAddInfo.navigateToAgreementInformationALT();
		}
		objDoc.viewDocument(environment);
	}

	@Test(priority = 4,enabled = false)
	public void Documents_Edit_Document() throws Exception {
		log("TC04 : Edit Document");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.TestCases, environment);
		objLogin.login(map);
		String testcaseName = "AddExternalDoc" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.AGREEEMENT_TEST_DATA), Excel.Documents, testcaseName);
		if(environment.toLowerCase().contains("row")) {
			objAddInfo.navigateToAgreementInformationROW();
		} else {
			objAddInfo.navigateToAgreementInformationALT();
		}
		objDoc.editDocument(map,testcaseName);
	}
	
	@Test(priority = 5,enabled = true)
	public void Documents_Add_New_Record() throws Exception {
		log("TC05 : Recording Information - Add New Record");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.TestCases, environment);
		objLogin.login(map);
		String testcaseName = "AddRecordingInfo" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.AGREEEMENT_TEST_DATA), Excel.RecordingInfo, testcaseName);
		if(environment.toLowerCase().contains("row")) {
			objAddInfo.navigateToAgreementInformationROW();
		} else {
			objAddInfo.navigateToAgreementInformationALT();
		}
		//objDoc.addNewRecordingInformation(map, testcaseName);
	}
	
	@Test(priority = 6,enabled = false)
	public void Documents_Edit_Record() throws Exception {
		log("TC06 : Recording Information - Edit Record");
		String testcaseName = "EditRecordingInfo" + environment;
		String AddtestcaseName = "AddRecordingInfo" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.AGREEEMENT_TEST_DATA), Excel.RecordingInfo, testcaseName);
		addDocumentMap = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.AGREEEMENT_TEST_DATA), Excel.RecordingInfo, AddtestcaseName);
		objDoc.editRecordingInformation(addDocumentMap,map, testcaseName);
	}
	
	@Test(priority = 7,enabled = false)
	public void Documents_Delete_Record() throws Exception {
		log("TC07 : Recording Information - Delete Record");
		String testcaseName = "EditRecordingInfo" + environment;
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.AGREEEMENT_TEST_DATA), Excel.RecordingInfo, testcaseName);
		objDoc.deleteRecordingInformation(map,environment);
	}

	@Test(priority = 8,enabled = false)
	public void Documents_History() throws Exception {
		log("TC08 : Documents History");
		objDoc.verifyDocumentHistory(environment);
	}

	@Test(priority = 9,enabled = true)
	public void Documents_Add_Document_Version() throws Exception {
		log("TC09 : Add Document Version");
		String testcaseName = "AddDocVersion" + environment;
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.AGREEEMENT_TEST_DATA), Excel.Documents, testcaseName);
		objDoc.addDocumentVersion(map,environment);
	}
}
