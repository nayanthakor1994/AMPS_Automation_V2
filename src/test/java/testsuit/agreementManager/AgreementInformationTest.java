package testsuit.agreementManager;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.base.Excel;
import com.util.CommonFunction;
import com.util.ExcelUtils;
import com.util.ReadPropertyFile;

import page.Common.LoginPage;
import pages.Project.AddProjectWorkflowPage;
import pages.agreementManager.AddNewInformationPage;
import pages.agreementManager.AddNewPayeeInfoPage;
import pages.agreementManager.AgreementInformationPage;
import pages.agreementManager.AgreementObligationsPage;
import pages.agreementManager.MilestoneDatePage;
import pages.tools.MyDashboardPage;

@Listeners(com.listeners.MyListeners.class)
public class AgreementInformationTest extends BasePage {

	LoginPage objLogin;
	AddNewInformationPage objAddInfo;
	AddNewPayeeInfoPage objAddPayee;
	MilestoneDatePage objMilestoneDate;
	AgreementInformationPage objAgreement;
	AddProjectWorkflowPage projectWorkflow;
	CommonFunction commonFunction;
	MyDashboardPage dashboardPage;
	AgreementObligationsPage objObligation;
	
	ReadPropertyFile readPro = new ReadPropertyFile();
	Map<String, String> map = new HashMap<String, String>();
	public static String AGREEMENT_NUMBER; 
	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		objAddInfo = new AddNewInformationPage(driver);
		objAddPayee = new AddNewPayeeInfoPage(driver);
		objMilestoneDate = new MilestoneDatePage(driver);
		objAgreement = new AgreementInformationPage(driver);
		projectWorkflow = new AddProjectWorkflowPage(driver);
		commonFunction = new CommonFunction(driver);
		dashboardPage = new MyDashboardPage(driver);
		objObligation = new AgreementObligationsPage(driver);
	}

	@Test(priority = 1)
	public void AgreementManager_Add_Agreement_Information_TC_01() throws Exception {
		log("TC01 : Add a Agreement Information");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.TestCases, environment);
		objLogin.login(map);
		String testcaseName = "AddAgreementInformation" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.AGREEEMENT_TEST_DATA), Excel.AgreementInfo, testcaseName);
		AGREEMENT_NUMBER = objAddInfo.addAgreementInformation(map, testcaseName);
	}

	@Test(priority = 2)
	public void AgreementManager_Add_Payee_Information_TC_02() throws Exception {
		log("TC02 : Add a Payee Information");
		String testcaseName = "AddPayeeInformation" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.AGREEEMENT_TEST_DATA), Excel.PayeeInfo, testcaseName);
		objAddPayee.addPayeeInformation(map, AGREEMENT_NUMBER, testcaseName);
	}
	
	@Test(priority = 3)
	public void AgreementManager_Add_Agreement_Information_TC_03() throws Exception {
		log("TC03 : Add a Agreement Information");
		if(!environment.toLowerCase().contains("row")) {
			objAddInfo.addAgreementInformation(environment);
		}
	}
	
	@Test(priority = 4)
	public void AgreementManager_Add_Milestone_Dates_TC_04() throws Exception {
		log("TC04 : Add a Milestone Date");
		if(!environment.toLowerCase().contains("row")) {
			objMilestoneDate.addMilestoneDate(environment);
		}
	}
	
	@Test(priority = 5)
	public void AgreementManager_Add_Agreement_Form_TC_05() throws Exception {
		log("TC05 : Add a Agreement Form");
		
		String testcaseName = "AgreementForm" + environment;
		log("Data picked : " + testcaseName);
		
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.AGREEEMENT_TEST_DATA), Excel.AgreementForm, testcaseName);
		
		projectWorkflow.addNewWorkflow(map, testcaseName, true);
		projectWorkflow.closeApprovalForm();
		projectWorkflow.addNewWorkflow(map, testcaseName, false);
		
		projectWorkflow.submitTheFormForReviewAgreement();
		projectWorkflow.closeApprovalForm();
		projectWorkflow.verifyStoredRecord(map.get(Excel.ApprovalType));
		
		try {
			commonFunction.navigateToMyDashboard();
			log("STEP 25: Click on submit for review on the form", Status.PASS);
		} catch (Exception e) {
			log("STEP 25: Not Click on submit for review on the form", Status.FAIL);
			throw new RuntimeException("Failed in step 23");
		}
		try {
			dashboardPage.navigateToRequestedDocumentTab();
			log("STEP 26: Click on the requested documents tab", Status.PASS);
		} catch (Exception e) {
			log("STEP 26: Not Click on the requested documents tab", Status.FAIL);
			throw new RuntimeException("Failed in step 24");
		}
		Assert.assertTrue(dashboardPage.isJobCreated("Requested"), "Job is not created");
		log("STEP 27: The Job created is listed under the tab", Status.PASS);
	}
	
	@Test(priority = 6)
	public void AgreementManager_Add_Agreement_Obligations_TC_06() throws Exception {
		log("TC06 : Add a Agreement Obligations");
		String testcaseName = "AddAgreementObligations" + environment;
		log("Data picked : " + testcaseName);
		
		if(testcaseName.toLowerCase().contains("row")) {
			objAddInfo.navigateToAgreementInformationROW();
		} else {
			objAddInfo.navigateToAgreementInformationALT();
		}
		
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.AGREEEMENT_TEST_DATA), Excel.AgreementObligations, testcaseName);
		objObligation.addAgreementObligations(map, testcaseName);
	}
	
	@Test(priority = 7)
	public void AgreementManager_Edit_Agreement_Obligations_TC_07() throws Exception {
		log("TC07 : Edit Agreement Obligations");
		String testcaseName = "EditAgreementObligations" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.AGREEEMENT_TEST_DATA), Excel.AgreementObligations, testcaseName);
		objObligation.editAgreementObligations(map, testcaseName);
	}
	
	@Test(priority = 8)
	public void AgreementManager_Delete_Agreement_Obligations_TC_08() throws Exception {
		log("TC08 : Delete Agreement Obligations");
		objObligation.deleteAgreementObligations(environment);
	}

}
