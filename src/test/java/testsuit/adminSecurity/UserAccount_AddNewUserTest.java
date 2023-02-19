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
import pages.agreementManager.AddNewInformationPage;
@Listeners(com.listeners.MyListeners.class)
public class UserAccount_AddNewUserTest extends BasePage {
	LoginPage objLogin;
	AddNewUserUserAccountPage objAddUser;
	ReadPropertyFile readPro = new ReadPropertyFile();
	Map<String, String> map = new HashMap<String, String>();
	
	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		objAddUser = new AddNewUserUserAccountPage(driver);
	}
	
	@Test()
	public void add_NewUser_UserAccount_TC_01() throws Exception {
		log("TC01 : Add a New User");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.LOGIN_TEST_DATA), Excel.Login, environment);
		objLogin.login(map);
		String testcaseName = "AddNewUser" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.ADMINISTRATION_SECURITY_TEST_DATA), Excel.UserAccount,
				testcaseName);
		objAddUser.addNewUser(map, testcaseName);
		objAddUser.updateNewUser(map,testcaseName);
	}
	

}
