package testsuit.Project;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.base.BasePage;
import com.base.Excel;
import com.util.CommonFunction;
import com.util.ExcelUtils;
import com.util.ReadPropertyFile;

import page.Common.LoginPage;
import pages.Project.AddProjectPermitPage;
import pages.Project.AddProjectWorkflowPage;
import pages.tools.MyDashboardPage;

@Listeners(com.listeners.MyListeners.class)
public class AddProjectPermitTest extends BasePage {
	LoginPage objLogin;
	CommonFunction commonFunction;
	MyDashboardPage dashboardPage;
	ReadPropertyFile readPro = new ReadPropertyFile();
	AddProjectPermitPage projectPermit;
	Map<String, String> map = new HashMap<String, String>();

	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		projectPermit = new AddProjectPermitPage(driver);
		commonFunction = new CommonFunction(driver);
		dashboardPage = new MyDashboardPage(driver);
	}

	@Test()
	public void Add_Project_Permit_TC_07() throws Exception {
		log("TC07 : Add a Project Permit");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.LOGIN_TEST_DATA), Excel.Login, environment);
		objLogin.login(map);
		String testcaseName = "ProjectPermit" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.PROJECT_DETAILS_TEST_DATA), Excel.ProjectPermit,
				testcaseName);
		commonFunction.navigateToProjectDeails();
		projectPermit.addProjectPermit(map);
		projectPermit.updateProjectPermit(map);
		projectPermit.addDocument(map,testcaseName);
		projectPermit.deletProjectPermit(map);
	}

}
