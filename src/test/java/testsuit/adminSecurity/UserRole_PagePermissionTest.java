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
import pages.administrationSecurity.UserRolePagePermissionPage;
@Listeners(com.listeners.MyListeners.class)
public class UserRole_PagePermissionTest extends BasePage {
	
	LoginPage objLogin;
	UserRolePagePermissionPage objPagePermission;
	ReadPropertyFile readPro = new ReadPropertyFile();
	Map<String, String> map = new HashMap<String, String>();

	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		objPagePermission = new UserRolePagePermissionPage(driver);
	}
	
	@Test()
	public void pagePermission_UserRole_TC_01() throws Exception {
		log("TC01 : Page Permission");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.LOGIN_TEST_DATA), Excel.Login, environment);
		objLogin.login(map);
		String testcaseName = "AddPagePermission" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.ADMINISTRATION_SECURITY_TEST_DATA), Excel.UserRolePagePermission,
				testcaseName);
		objPagePermission.selectRolePermission(map, testcaseName);
		objPagePermission.addNewPermission(map, testcaseName);
	}
}
