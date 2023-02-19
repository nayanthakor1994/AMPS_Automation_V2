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

import net.bytebuddy.build.Plugin.Factory.UsingReflection.Priority;
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
	
	@Test(priority=1,enabled=true)
	public void viewError_ProjectImport_TC_02() throws Exception {
		log("TC02 : View Error log :");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.LOGIN_TEST_DATA), Excel.Login, environment);
		objLogin.login(map);
		String testcaseName = "projectImport" + environment;
		log("Data picked : " + testcaseName);
		objViewErrorLogAndExportPage.viewErrorLog(testcaseName);
	}
	
	@Test(priority=2,enabled=true)
	public void verifyExport_ProjectImport_TC_03() throws Exception {
		log("TC03 : Verify Export :");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.LOGIN_TEST_DATA), Excel.Login, environment);
		objLogin.login(map);
		String testcaseName = "projectImport" + environment;
		log("Data picked : " + testcaseName);
		objViewErrorLogAndExportPage.exportButton(testcaseName);
	}

}
