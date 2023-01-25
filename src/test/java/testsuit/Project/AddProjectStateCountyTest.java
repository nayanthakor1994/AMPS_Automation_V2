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
import pages.Project.AddProjectStateCountyPage;

@Listeners(com.listeners.MyListeners.class)
public class AddProjectStateCountyTest extends BasePage {
	LoginPage objLogin;
	ReadPropertyFile readPro = new ReadPropertyFile();
	AddProjectStateCountyPage objStateCounty;
	Map<String, String> map = new HashMap<String, String>();

	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		objStateCounty = new AddProjectStateCountyPage(driver);
	}

	@Test(dataProvider = "data-provider")
	public void add_Project_State_Country_TC_02(String testName, String appURL, String env) throws Exception {
		log("TC02 : Add a Project State and County");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.Login, "1");
		objLogin.login(map);
		String testcaseName = "StateCounty" + env;
		log("Data picked : " + testcaseName);
		log("navigating to create City and State");
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.StateCounty, testcaseName);
		objStateCounty.addStateAndCountyInformation(map);
		objStateCounty.updateStateAndCountyInformation(map);

	}

}
