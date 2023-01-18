package testsuit.ProjectImport;

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
import pages.projectImport.ProjectImportPage;
import pages.projectImport.ViewErrorLogAndExportPage;

@Listeners(com.listeners.MyListeners.class)
public class ViewErrorLogAndExportPageTest extends BasePage {
	
	LoginPage objLogin;
	ViewErrorLogAndExportPage objViewErrorLogAndExportPage;
	ReadPropertyFile readPro = new ReadPropertyFile();
	Map<String, String> map = new HashMap<String, String>();
	
	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		objViewErrorLogAndExportPage = new ViewErrorLogAndExportPage(driver);
	}
	
	@Test()
	public void projectImport_TC_02AndTC_03() throws Exception {
		log("TC02 : View Error log :");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.TestCases, environment);
		objLogin.login(map);
		String testcaseName = "projectImport" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.SSRSReport, testcaseName);
		objViewErrorLogAndExportPage.viewErrorLog(map, testcaseName);
		log("TC03 : Export :");
		objViewErrorLogAndExportPage.exportButton(map, testcaseName);
	}

}
