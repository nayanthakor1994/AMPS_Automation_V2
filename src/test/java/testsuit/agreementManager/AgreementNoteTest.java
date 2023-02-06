package testsuit.agreementManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.base.BasePage;

import page.Common.LoginPage;
import pages.agreementManager.AddNewInformationPage;
import pages.agreementManager.AgreementNotePage;
import pages.agreementManager.PaymentInformationPage;

@Listeners(com.listeners.MyListeners.class)
public class AgreementNoteTest extends BasePage {

	LoginPage objLogin;
	AddNewInformationPage objAddInfo;
	AgreementNotePage objNotes;
	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		objAddInfo = new AddNewInformationPage(driver);
		objNotes = new AgreementNotePage(driver);
	}
	
	@Test(priority = 1)
	public void AgreementNotes_Add_Agreement_Notes() throws Exception {
		log("TC01 : Add Agreement Notes");
		navigateToApplication(appURL);
		
		if(environment.toLowerCase().contains("row")) {
			objAddInfo.navigateToAgreementInformationROW();
		} else {
			objAddInfo.navigateToAgreementInformationALT();
		}

		objNotes.addNotes(environment);
	}
}
