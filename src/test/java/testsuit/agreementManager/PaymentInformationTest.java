package testsuit.agreementManager;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.base.BasePage;
import com.base.Excel;
import com.util.ExcelUtils;

import page.Common.LoginPage;
import pages.agreementManager.AddNewInformationPage;
import pages.agreementManager.DocumentGenerationPage;
import pages.agreementManager.PaymentInformationPage;

@Listeners(com.listeners.MyListeners.class)
public class PaymentInformationTest extends BasePage {
	
	LoginPage objLogin;
	AddNewInformationPage objAddInfo;
	PaymentInformationPage objPayment;
	Map<String, String> map = new HashMap<String, String>();
	
	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		objAddInfo = new AddNewInformationPage(driver);
		objPayment = new PaymentInformationPage(driver);
	}
	
	@Test(priority = 1)
	public void PaymentInformation_Add_Payment_Term() throws Exception {
		log("TC01 : Add Payment Term");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.TestCases, environment);
		objLogin.login(map);
		
		if(environment.toLowerCase().contains("row")) {
			objAddInfo.navigateToAgreementInformationROW();
		} else {
			objAddInfo.navigateToAgreementInformationALT();
		}
		objPayment.addNewPaymentInformation(null, NA);
		
	}
}
