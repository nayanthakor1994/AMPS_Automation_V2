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

@Listeners(com.listeners.MyListeners.class)
public class ProjectImportTest extends BasePage {
	
	LoginPage objLogin;
	ProjectImportPage objProjectImportPage;
	ReadPropertyFile readPro = new ReadPropertyFile();
	Map<String, String> map = new HashMap<String, String>();
	
	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		objProjectImportPage = new ProjectImportPage(driver);
	}

	@Test()
	public void projectImport_TC_01() throws Exception {
		log("TC01 : View projectImport");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.TestCases, environment);
		objLogin.login(map);
		String testcaseName = "projectImport" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.SSRSReport, testcaseName);
		objProjectImportPage.projectImport(map, testcaseName);
	}
	
	

}
