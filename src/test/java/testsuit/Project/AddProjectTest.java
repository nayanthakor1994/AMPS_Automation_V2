package testsuit.Project;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.base.BasePage;
import com.base.Excel;
import com.util.ExcelUtils;
import com.util.ReadPropertyFile;

import page.Common.LoginPage;
import pages.Project.AddProjectPage;
import pages.Project.AddProjectStateCountyPage;

@Listeners(com.listeners.MyListeners.class)
public class AddProjectTest extends BasePage {
	LoginPage objLogin;
	AddProjectPage objAddProject;
	ReadPropertyFile readPro = new ReadPropertyFile();
	AddProjectStateCountyPage objStateCounty;
	Map<String, String> map = new HashMap<String, String>();

	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		objAddProject = new AddProjectPage(driver);
		objStateCounty = new AddProjectStateCountyPage(driver);
	}

	@Test(enabled = true,priority = 1)
	public void add_Project_TC_01() throws Exception {
		log("TC01 : Add a Project");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.LOGIN_TEST_DATA), Excel.Login, environment);
		objLogin.login(map);
		String testcaseName = "AddProject" + environment;
		log("Data picked : " + testcaseName);
		log("navigating to create new Project");
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.PROJECT_DETAILS_TEST_DATA), Excel.ProjectInformation,
				testcaseName);
		objAddProject.addProjectInformation(map, testcaseName);
	}
	
	@Test(enabled = true,priority = 2)
	public void add_Project_State_County_TC_02() throws Exception {
		log("TC02 : Add a Project State and County");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.LOGIN_TEST_DATA), Excel.Login, environment);
		objLogin.login(map);
		String testcaseName = "AddStateCounty" + environment;
		log("Data picked : " + testcaseName);
		log("navigating to create State and County");
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.PROJECT_DETAILS_TEST_DATA), Excel.StateCounty, testcaseName);
		objStateCounty.addStateAndCountyInformation(map);
		objStateCounty.updateStateAndCountyInformation(map);
	}

}
