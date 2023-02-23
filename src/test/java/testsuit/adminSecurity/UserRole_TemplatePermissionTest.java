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
import pages.administrationSecurity.UserRolePagePermissionPage;
import pages.administrationSecurity.UserRoleTemplatePermissionPage;
@Listeners(com.listeners.MyListeners.class)

public class UserRole_TemplatePermissionTest extends BasePage{
	LoginPage objLogin;
	UserRoleTemplatePermissionPage objTemplatePermission;
	ReadPropertyFile readPro = new ReadPropertyFile();
	Map<String, String> map = new HashMap<String, String>();

	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		objTemplatePermission = new UserRoleTemplatePermissionPage(driver);
	}
	@Test()
	public void templatePermission_UserRole_TC_03() throws Exception {
		log("TC03 : Template Permission");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.LOGIN_TEST_DATA), Excel.Login, environment);
		objLogin.login(map);
		String testcaseName = "AddTemplatePermission" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.ADMINISTRATION_SECURITY_TEST_DATA), Excel.UserTemplatePagePermission,
				testcaseName);
		objTemplatePermission.selectTemplatePermission(map, testcaseName);
	//	objTemplatePermission.addNewPermission(map, testcaseName);
	}

}
