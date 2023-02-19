package testsuit.SSRSReports;

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
import pages.SSRSReport.SSRSReportPage;

@Listeners(com.listeners.MyListeners.class)
public class SSRSReportsTest extends BasePage {

	LoginPage objLogin;
	SSRSReportPage objSSRSReport;
	ReadPropertyFile readPro = new ReadPropertyFile();
	Map<String, String> map = new HashMap<String, String>();

	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		objSSRSReport = new SSRSReportPage(driver);
	}

	@Test()
	public void viewReport_TC_01() throws Exception {
		log("TC01 : View SSRS Report");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.LOGIN_TEST_DATA), Excel.Login, environment);
		objLogin.login(map);
		String testcaseName = "AddReport" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.REPORT_TEST_DATA), Excel.SSRSReport, testcaseName);
		objSSRSReport.viewSSRSReport(map, testcaseName);
	}

}
