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
import pages.agreementManager.AgreementNotePage;

@Listeners(com.listeners.MyListeners.class)
public class AgreementNoteTest extends BasePage {

	LoginPage objLogin;
	AddNewInformationPage objAddInfo;
	AgreementNotePage objNotes;
	Map<String, String> map = new HashMap<String, String>();
	
	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		objAddInfo = new AddNewInformationPage(driver);
		objNotes = new AgreementNotePage(driver);
	}
	
	@Test(priority = 1)
	public void AgreementNotes_Add_Agreement_Notes() throws Exception {
		log("TC01 : Add Agreement Notes");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.LOGIN_TEST_DATA), Excel.Login, environment);
		objLogin.login(map);

		String testcaseName = "AddAgreementNote" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.AGREEEMENT_TEST_DATA), Excel.AgreementNote, testcaseName);
		
		if(environment.toLowerCase().contains("row")) {
			objAddInfo.navigateToAgreementInformationROW();
		} else {
			objAddInfo.navigateToAgreementInformationALT();
		}

		objNotes.addNotes(map, testcaseName);
	}
	
	@Test(priority = 2)
	public void AgreementNotes_Edit_Agreement_Notes() throws Exception {
		log("TC02 : Edit Agreement Notes");
		String testcaseName = "EditAgreementNote" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.AGREEEMENT_TEST_DATA), Excel.AgreementNote, testcaseName);
		objNotes.editNotes(map, testcaseName);
	}
}
