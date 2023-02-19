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
import pages.administrationSecurity.ManageUserAddNewUserPage;
import pages.administrationSecurity.PasswordEncryptionPage;
@Listeners(com.listeners.MyListeners.class)
public class PasswordEncryptionTest extends BasePage {

	LoginPage objLogin;
	PasswordEncryptionPage objPasswordEncryption;
	ReadPropertyFile readPro = new ReadPropertyFile();
	Map<String, String> map = new HashMap<String, String>();
	
	
	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		objPasswordEncryption = new PasswordEncryptionPage(driver);
	}
	@Test()
	public void add_Password_Encryption_TC_01() throws Exception {
		log("TC01 : Add Password Encryption");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.LOGIN_TEST_DATA), Excel.Login, environment);
		objLogin.login(map);
		String testcaseName = "AddPasswordEncryption" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.ADMINISTRATION_SECURITY_TEST_DATA), Excel.PasswordEncryption,
				testcaseName);
		objPasswordEncryption.setNewEncryptionPassword(map, testcaseName);
	}
}
