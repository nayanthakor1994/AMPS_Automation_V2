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

@Listeners(com.listeners.MyListeners.class)
public class AgreementInfo_AddNewInfo extends BasePage {

	LoginPage objLogin;
	AddNewInformationPage objAddInfo;
	ReadPropertyFile readPro = new ReadPropertyFile();
	Map<String, String> map = new HashMap<String, String>();

	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		objAddInfo = new AddNewInformationPage(driver);
	}

	@Test()
	public void add_Agreement_Information_TC_01() throws Exception {
		log("TC01 : Add a Agreement Information");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.TestCases, environment);
		objLogin.login(map);
		String testcaseName = "AddAgreementInformation" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.AgreementInfo,
				testcaseName);
		objAddInfo.addAgreementInformation(map, testcaseName);
	}

}
