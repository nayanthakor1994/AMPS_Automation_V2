package testsuit.adminSecurity;

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
import pages.administrationSecurity.AddNewUserUserAccountPage;
import pages.administrationSecurity.ManageUserAddNewUserPage;
@Listeners(com.listeners.MyListeners.class)
public class ManageUser_AddNewUserTest extends BasePage {
	LoginPage objLogin;
	ManageUserAddNewUserPage objAddNewUser;
	ReadPropertyFile readPro = new ReadPropertyFile();
	Map<String, String> map = new HashMap<String, String>();
	
	
	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		objAddNewUser = new ManageUserAddNewUserPage(driver);
	}

	@Test()
	public void add_NewUser_ManageUser_TC_01() throws Exception {
		log("TC01 : Add a New User");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.LOGIN_TEST_DATA), Excel.Login, environment);
		objLogin.login(map);
		String testcaseName = "AddNewUser" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.ADMINISTRATION_SECURITY_TEST_DATA), Excel.ManageUser,
				testcaseName);
		objAddNewUser.addNewUser(map, testcaseName);
		objAddNewUser.updateNewUser(map,testcaseName);
	}
}
