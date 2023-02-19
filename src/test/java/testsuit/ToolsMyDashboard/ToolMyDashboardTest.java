package testsuit.ToolsMyDashboard;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.base.BasePage;
import com.base.Excel;
import com.util.ExcelUtils;

import page.Common.LoginPage;
import page.toolsMyDashboard.MyDashboardPage;
import page.toolsMyDashboard.ToolMyDashboardPage;
@Listeners(com.listeners.MyListeners.class)
public class ToolMyDashboardTest extends BasePage {
	
	LoginPage objLogin;
	ToolMyDashboardPage objToolMyDashboard ;
	Map<String, String> map = new HashMap<String, String>();
	
	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		objToolMyDashboard = new ToolMyDashboardPage(driver);
	}

	@Test(priority = 1,enabled = true)
	public void verifyMyParcel_TC_01() throws Exception {
		log("TC01 : Verify MyParcel :");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.LOGIN_TEST_DATA), Excel.Login, environment);
		objLogin.login(map);
		String testcaseName = " Verify MyParcel" + environment;
		log("Data picked : " + testcaseName);
		objToolMyDashboard.verifyMyParcel(testcaseName);
	}
	@Test(priority = 2,enabled = true)
	public void deletDoc_TC_02() throws Exception {
		log("TC03 : Delect Doc :");
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.LOGIN_TEST_DATA), Excel.Login, environment);
		String testcaseName = "DeleteDoc" + environment;
		log("Data picked : " + testcaseName);
		objToolMyDashboard.deleteDoc(testcaseName);
	}
	@Test(priority = 3,enabled = true)
	public void verifyMyProject_TC_03() throws Exception {
		log("TC03 : Verify MyProject :");
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.LOGIN_TEST_DATA), Excel.Login, environment);
		String testcaseName = "VerifyMyProject" + environment;
		log("Data picked : " + testcaseName);
		objToolMyDashboard.verifyMyProject(testcaseName);
	}
	@Test(priority = 4,enabled = true)
	public void verifyNoteToReview_TC_04() throws Exception {
		log("TC04 : Verify NoteToReview :");
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.LOGIN_TEST_DATA), Excel.Login, environment);
		String testcaseName = "VerifyMyProject" + environment;
		log("Data picked : " + testcaseName);
		objToolMyDashboard.verifyNoteToReview( testcaseName);
	}
	@Test(priority = 5,enabled = true)
	public void editNoteToReview_TC_05() throws Exception {
		log("TC05 : Edit NoteToReview :");
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.LOGIN_TEST_DATA), Excel.Login, environment);
		String testcaseName = "EditNoteReview" + environment;
		log("Data picked : " + testcaseName);
		objToolMyDashboard.editNoteToReview(testcaseName);
	}

}
