package page.toolsMyDashboard;

import static org.testng.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.util.Assert;
import com.base.BasePage;
import com.base.Excel;
import com.util.CommonFunction;
import com.util.TestUtil;

import extentReports.ExtentManager;
import extentReports.ExtentTestManager;

public class MyDashboardPage extends BasePage {

	TestUtil util;
	CommonFunction commonFunction;

	public MyDashboardPage(WebDriver driver) {
		this.driver = driver;
		util = new TestUtil(driver);
		commonFunction = new CommonFunction(driver);
	}

	By navTool = By.xpath("*//a//span[@class='rmText rmExpandDown' and contains(text(),'Tools')] ");
	By navMyDashBoard = By.xpath("*//a//span[contains(text(),'Project Group') or contains(text(),'My Dashboard')] ");

	public void navigateToMyDashBoard() {
		util.waitUntilElementDisplay(navTool);
		util.click(navTool);
		util.waitUntilElementDisplay(navMyDashBoard);
		util.click(navMyDashBoard);
	}

	By firstCheckBox = By
			.xpath("//input[contains(@id,'ctl00_ConPHRightTop_YALCOM_InMessages_RadGrid1_ctl00_ctl04_CheckBox1')]");

	public void clickOnFirstCheckBox() {
		util.waitUntilElementDisplay(firstCheckBox);
		util.click(firstCheckBox);
	}

	By linkExcelIcon = By.xpath("//input[contains(@id,'ExportToExcelButton')]");

	public void clickOnExcelIcon() {
		util.waitUntilElementDisplay(linkExcelIcon);
		util.click(linkExcelIcon);
	}

	By linkCSVIcon = By.xpath("//input[contains(@id,'ExportToCsvButton')]");

	public void clickOnCSVIcon() {
		util.waitUntilElementDisplay(linkCSVIcon);
		util.click(linkCSVIcon);
	}

	By linkWordIcon = By.xpath("//input[contains(@id,'ExportToWordButton')]");

	public void clickOnWordIcon() {
		util.waitUntilElementDisplay(linkWordIcon);
		util.click(linkWordIcon);
	}

	By linkMessage = By.xpath("//tr[@id='ctl00_ConPHRightTop_YALCOM_InMessages_RadGrid1_ctl00__0']/td/strong/a");

	public void clickOnClickHereLink() {
		try {
			util.isElementPresent(linkMessage);
			util.click(linkMessage);
			ExtentTestManager.pass("Link is present inside Message");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExtentTestManager.pass("Link is not present inside Message");
		}

	}

	public void verifyInboxDetails(Map<String, String> map, String testcaseName) {
		try {
			navigateToMyDashBoard();
			log("STEP 1:  Navigate to Tool - My Dashboard", Status.PASS);
		} catch (Exception e) {
			log("STEP 1:   Navigate to Tool - My Dashboard", Status.FAIL);
			throw new RuntimeException("Failed in step 1: Popup up does not appear");
		}
		try {
			clickOnFirstCheckBox();
			log("STEP 2:  User can able to select the message information checkbox :", Status.PASS);
		} catch (Exception e) {
			log("STEP 2:  User not able to select the message information checkbox :", Status.FAIL);
			throw new RuntimeException("STEP 2:  User not able to select the message information checkbox :");
		}
		try {
			clickOnExcelIcon();
			log("STEP 3:  User can able to click on Excel icon :", Status.PASS);
		} catch (Exception e) {
			log("STEP 3:  User not able to click on Excel icon :", Status.FAIL);
			throw new RuntimeException("STEP 3:  User not able to click on Excel icon :");
		}
		try {
			clickOnCSVIcon();
			log("STEP 4:  User can able to click on CSV icon :", Status.PASS);
		} catch (Exception e) {
			log("STEP 4:  User not able to click on CSV icon :", Status.FAIL);
			throw new RuntimeException("STEP 4:  User not able to click on CSV icon :");
		}
		try {
			clickOnWordIcon();
			log("STEP 5:  User can able to click on Word icon :", Status.PASS);
		} catch (Exception e) {
			log("STEP 5:  User not able to click on Word icon :", Status.FAIL);
			throw new RuntimeException("STEP 5:  User not able to click on Word icon :");
		}
		try {
			clickOnClickHereLink();
			log("STEP 6:  User can able to click on Click here link :", Status.PASS);
		} catch (Exception e) {
			log("STEP 6:  User not able to click on Click here link  :", Status.FAIL);
			throw new RuntimeException("STEP 6:  User not able to click on Click here link  :");
		}
	}

	By btnAcknowledge = By.xpath("//input[contains(@id,'btnAcknowledge')]");
	By getAcknowledgeDateorg = By.xpath(
			"//tr[@id='ctl00_ConPHRightTop_YALCOM_InMessages_RadGrid1_ctl00__0']//*[text()='Acknowledgement : ']//br[2]//following::strong[1]");

	By getAcknowledgeDate = By.xpath(
			"//tr[@id='ctl00_ConPHRightTop_YALCOM_InMessages_RadGrid1_ctl00__0']//strong[3]");
	public void clickOnAcknowledge() {
		util.waitUntilElementDisplay(btnAcknowledge);
		util.click(btnAcknowledge);
		util.waitUntilElementDisplay(getAcknowledgeDate);
		String AcknowledegDate = util.getText(getAcknowledgeDate);
		DateFormat date = new SimpleDateFormat("MM/dd/yyyy");
		String ExpectedDate = date.format(new Date());
		assertEquals(AcknowledegDate, ExpectedDate, "Acknowledge Date match in Message details :");
		log("Acknowledge Date match in Message details :");
	}

	public void verifyInboxAcknowledge(Map<String, String> map, String testcaseName) {
		try {
			navigateToMyDashBoard();
			log("STEP 1:  Navigate to Tool - My Dashboard", Status.PASS);
		} catch (Exception e) {
			log("STEP 1:   Navigate to Tool - My Dashboard", Status.FAIL);
			throw new RuntimeException("Failed in step 1: Popup up does not appear");
		}
		try {
			clickOnFirstCheckBox();
			log("STEP 2:  User can able to select the message information checkbox :", Status.PASS);
		} catch (Exception e) {
			log("STEP 2:  User not able to select the message information checkbox :", Status.FAIL);
			throw new RuntimeException("STEP 2:  User not able to select the message information checkbox :");
		}
		try {
			clickOnAcknowledge();
			log("STEP 3:  User can able to click on Acknowledge :", Status.PASS);
		} catch (Exception e) {
			log("STEP 3:  User not able to click on Acknowledge :", Status.FAIL);
			throw new RuntimeException("STEP 3:  User not able to click on Acknowledge :");
		}
	}

	By tabOutbox = By.xpath("//*[text()='Outbox']");

	public void clickOnOutBox() {
		util.waitUntilElementDisplay(tabOutbox);
		util.click(tabOutbox);
	}
	
	By txtClickHere = By.xpath("(//*[normalize-space()='here'])[1]");
	public void clickOnHere() {
		try {
			util.isElementPresent(txtClickHere);
			util.click(txtClickHere);
			ExtentTestManager.pass("Link is present inside Message");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExtentTestManager.pass("Link is not present inside Message");
		}
	}

	
	public void verifyOutbox(Map<String, String> map, String testcaseName) {
		try {
			navigateToMyDashBoard();
			clickOnOutBox();
			log("STEP 1:  Navigate to Tool - My Dashboard - OutBox", Status.PASS);
		} catch (Exception e) {
			log("STEP 1:   Navigate to Tool - My Dashboard - OutBox", Status.FAIL);
			throw new RuntimeException("Failed in step 1: Popup up does not appear");
		}
		try {
			clickOnHere();
			log("STEP 2:  User can able click on Click Here :", Status.PASS);
		} catch (Exception e) {
			log("STEP 2:  User can not able click on Click Here :", Status.FAIL);
			throw new RuntimeException("STEP 2:  User can not able click on Click Here :");
		}

	}
	
	By tabNewMessage = By.xpath("//*[text()='New Message']");
	public void clickOnNewMessage() {
		util.waitUntilElementDisplay(tabNewMessage);
		util.click(tabNewMessage);
	}
	
	By sendCommunication = By.xpath("");
	public void clickOnSendCommunication(String value) {
		By sendCommunication = By.xpath("//*[text()='"+value+"']/preceding-sibling::input");
		util.click(sendCommunication);
		
	}
	By selectOne = By.xpath("//input[@id='ctl00_ConPHRightTop_YALCOM_Message_Priority_ID_radYALDropDownList_Input']");
	public void setSelectOne(String value) {
		util.waitUntilElementDisplay(selectOne);
		util.inputTextAndPressTab(selectOne, value);
	}
	By switchContentFrame = By.xpath("//iframe[@id='ctl00_ConPHRightTop_YALCOM_Message_Text_contentIframe']");
	public void switchToContentFrame() {
		util.waitUntilElementDisplay(switchContentFrame);
		util.switchToIframe(switchContentFrame);
	}
	By txtSendMessage = By.xpath("//iframe[@id='ctl00_ConPHRightTop_YALCOM_Message_Text_contentIframe']//following-sibling::*");
	public void setSendMessage(String value) {
		util.waitUntilElementDisplay(txtSendMessage);
		util.inputText(txtSendMessage, value);
	}
	
	By btnPublish = By.xpath("//input[contains(@id,'SendCommunication')]");
	public void clickOnPublish() {
		util.waitUntilElementDisplay(btnPublish);
		util.click(btnPublish);
	}
	public void addNewMessage(Map<String, String> map, String testcaseName) {
		try {
			navigateToMyDashBoard();
			clickOnNewMessage();
			log("STEP 1:  Navigate to Tool - My Dashboard - NewMessage", Status.PASS);
		} catch (Exception e) {
			log("STEP 1:   Navigate to Tool - My Dashboard - NewMessage", Status.FAIL);
			throw new RuntimeException("Failed in step 1: Popup up does not appear");
		}
		try {
			clickOnSendCommunication(map.get(Excel.SendCommunication));
			log("STEP 2:  User can enter Send Communication :", Status.PASS);
		} catch (Exception e) {
			log("STEP 2:  User can not enter Send Communication :", Status.FAIL);
			throw new RuntimeException("STEP 2:  User can not enter Send Communication :");
		}
		try {
			setSelectOne(map.get(Excel.SelectOne));
			log("STEP 3:  User can able select SelectOne :", Status.PASS);
		} catch (Exception e) {
			log("STEP 3: User can not able  SelectOne :", Status.FAIL);
			throw new RuntimeException("STEP 3: User can not able  SelectOne :");
		}
//		try {
//			switchToContentFrame();
//			setSendMessage(map.get(Excel.Message));
//			log("STEP 4:  User can able enter Message :", Status.PASS);
//		} catch (Exception e) {
//			log("STEP 4: User can not able enter Message :", Status.FAIL);
//			throw new RuntimeException("STEP 4: User can not able enter Message :");
//		}
		try {
			clickOnPublish();
			log("STEP 5:  User can able click on Publish :", Status.PASS);
		} catch (Exception e) {
			log("STEP 5: User can not able click on Publish :", Status.FAIL);
			throw new RuntimeException("STEP 5: User can not able click on Publish :");
		}

	}

}
