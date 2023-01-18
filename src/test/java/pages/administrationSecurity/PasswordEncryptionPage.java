package pages.administrationSecurity;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.base.Excel;
import com.util.CommonFunction;
import com.util.TestUtil;

public class PasswordEncryptionPage extends BasePage {

	TestUtil util;
	CommonFunction commonFunction;

	public PasswordEncryptionPage(WebDriver driver) {
		this.driver = driver;
		util = new TestUtil(driver);
		commonFunction = new CommonFunction(driver);
	}

	By navAddminitstration = By.xpath("//a//span[@class='rmText rmExpandDown' and contains(text(),'Administration')] ");
	By navSecurity = By.xpath("//a//span[@class='rmText rmExpandRight' and contains(text(),'Security')]");
	By navPasswordEncryption = By.xpath("//a//span[@class='rmText' and contains(text(),'Password Encryption')]");

	public void navigateToPasswordEncryption() {
		util.waitUntilElementDisplay(navAddminitstration);
		util.click(navAddminitstration);
		util.waitUntilElementDisplay(navSecurity);
		util.click(navSecurity);
		util.waitUntilElementDisplay(navPasswordEncryption);
		util.click(navPasswordEncryption);
	}

	By txtPassword = By.xpath("//input[contains(@name,'idPassEncryption$txtPass')]");

	public void setPassword(String value) {
		util.waitUntilElementDisplay(txtPassword);
		util.inputTextAndPressTab(txtPassword, value);
	}

	By btnEncrypt = By.xpath("//input[contains(@name,'idPassEncryption$btnEncrypt')]");

	public void clickOnEncrypt() {
		util.click(btnEncrypt);
	}

	By txtEncryptedPassword = By.xpath("//input[contains(@name,'idPassEncryption$txtEncryptedPass')]");

	public void getEncryptedPassword() {
		String EncryptedPassword = util.getAttributeValue(txtEncryptedPassword, "value");
		log("Encrypted Password :" + EncryptedPassword);
	}

	public void setNewEncryptionPassword(Map<String, String> map, String testcaseName) {
		try {
			navigateToPasswordEncryption();
			log("STEP 1:  Navigate to Menu - Password Encryption", Status.PASS);
		} catch (Exception e) {
			log("STEP 1:  Navigate to Menu - Password Encryption", Status.FAIL);
			throw new RuntimeException("Failed in step 1: Popup up does not appear");
		}
		Set<String> windowId = driver.getWindowHandles(); // get window id of current window
		Iterator<String> itererator = windowId.iterator();

		String mainWinID = itererator.next();
		String newAdwinID = itererator.next();

		driver.switchTo().window(newAdwinID);
		System.out.println(driver.getTitle());

		try {
			setPassword(map.get(Excel.Password));
			log("STEP 2:  User can Enter Password ", Status.PASS);
		} catch (Exception e) {
			log("STEP 2:  User can not Enter Password", Status.FAIL);
			throw new RuntimeException("STEP 1:  User can not Enter Password");
		}
		try {
			clickOnEncrypt();
			log("STEP 3:  User can Click on Encrypt button ", Status.PASS);
		} catch (Exception e) {
			log("STEP 3:  User can not Click on Encrypt button", Status.FAIL);
			throw new RuntimeException("STEP 3:  User can not Click on Encrypt button");
		}
		try {
			getEncryptedPassword();
			log("STEP 4:  User can get Encrypt password ", Status.PASS);
		} catch (Exception e) {
			log("STEP 4:  User can not get Encrypt password", Status.FAIL);
			throw new RuntimeException("STEP 4:  User can not get Encrypt password");
		}

		driver.close();

		driver.switchTo().window(mainWinID);
		System.out.println(driver.getTitle());

	}

}
