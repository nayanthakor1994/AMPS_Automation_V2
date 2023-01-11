package testsuit.agreementManager;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.base.BasePage;
import com.base.Excel;
import com.util.ExcelUtils;
import com.util.ReadPropertyFile;

import page.Common.LoginPage;
import pages.agreementManager.AddNewInformationPage;
import pages.agreementManager.AddNewPayeeInfoPage;
import pages.agreementManager.MilestoneDatePage;

@Listeners(com.listeners.MyListeners.class)
public class AgreementInformationTest extends BasePage {

	LoginPage objLogin;
	AddNewInformationPage objAddInfo;
	AddNewPayeeInfoPage objAddPayee;
	MilestoneDatePage objMilestoneDate;
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
	}

	@Test(priority = 1)
	public void AgreementManager_Add_Agreement_Information_TC_01() throws Exception {
		log("TC01 : Add a Agreement Information");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.TestCases, environment);
		objLogin.login(map);
		String testcaseName = "AddAgreementInformation" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.AgreementInfo, testcaseName);
		AGREEMENT_NUMBER = objAddInfo.addAgreementInformation(map, testcaseName);
	}

	@Test(priority = 2)
	public void AgreementManager_Add_Payee_Information_TC_02() throws Exception {
		log("TC02 : Add a Payee Information");
		String testcaseName = "AddPayeeInformation" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.PayeeInfo, testcaseName);
		objAddPayee.addPayeeInformation(map, AGREEMENT_NUMBER, testcaseName);
	}
	
	@Test(priority = 3)
	public void AgreementManager_Add_Agreement_Information_TC_03() throws Exception {
		log("TC03 : Add a Agreement Information");
		objAddInfo.addAgreementInformation(environment);
	}
	
	@Test(priority = 4)
	public void AgreementManager_Add_Milestone_Dates_TC_04() throws Exception {
		log("TC04 : Add a Milestone Date");
		objMilestoneDate.addMilestoneDate(environment);
	}

}
