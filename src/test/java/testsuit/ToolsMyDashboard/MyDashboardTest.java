package testsuit.ToolsMyDashboard;

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
import page.toolsMyDashboard.MyDashboardPage;
import pages.projectGroup.AddDocExternalLinkPage;
import pages.projectGroup.AddDocumentPage;
import pages.projectGroup.AddDocumentVersionPage;
import pages.projectGroup.AddProjectGroupPage;

@Listeners(com.listeners.MyListeners.class)
public class MyDashboardTest extends BasePage{
	
	LoginPage objLogin;
	MyDashboardPage objMyDashboard ;
	Map<String, String> map = new HashMap<String, String>();
	
	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		objMyDashboard = new MyDashboardPage(driver);
	}

	@Test(priority = 1,enabled = true)
	public void verifyInboxMessage_TC_01() throws Exception {
		log("TC01 : Verify Inbox Inmprt/Export and Message details");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.LOGIN_TEST_DATA), Excel.Login, environment);
		objLogin.login(map);
		String testcaseName = "verifyInbox" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.ProjectGroup, testcaseName);
		objMyDashboard.verifyInboxDetails(map, testcaseName);
	}
	
	@Test(priority = 2,enabled = true)
	public void verifyAcknowledgeButton_TC_02() throws Exception {
		log("TC02 : Verify  Acknowledge and Remove button");
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.LOGIN_TEST_DATA), Excel.Login, environment);
		String testcaseName = "verifyInbox" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.MyDashboard, testcaseName);
		objMyDashboard.verifyInboxAcknowledge(map, testcaseName);
	}
	
	@Test(priority = 3,enabled = true)
	public void verifyOutbox_TC_03() throws Exception {
		log("TC03 : Verify  Outbox");
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.LOGIN_TEST_DATA), Excel.Login, environment);
		String testcaseName = "verifyInbox" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.MyDashboard, testcaseName);
		objMyDashboard.verifyOutbox(map, testcaseName);
	}
	
	@Test(priority = 4,enabled = true)
	public void addNewMessage_TC_04() throws Exception {
		log("TC04 : Verify  NewMessage :");
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.LOGIN_TEST_DATA), Excel.Login, environment);
		String testcaseName = "AddNewMessage" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.TOOL_TEST_DATA), Excel.MyDashboard, testcaseName);
		objMyDashboard.addNewMessage(map, testcaseName);
	}
	
	@Test(priority = 5,enabled = true)
	public void requestDocument_TC_05() throws Exception {
		log("TC05 : Request Document :");
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.LOGIN_TEST_DATA), Excel.Login, environment);
		String testcaseName = "AddNewMessage" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.TOOL_TEST_DATA), Excel.MyDashboard, testcaseName);
		objMyDashboard.addNewMessage(map, testcaseName);
	}

}
