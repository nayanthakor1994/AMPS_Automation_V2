package page.toolsMyDashboard;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.util.CommonFunction;
import com.util.TestUtil;

import extentReports.ExtentManager;
import extentReports.ExtentTestManager;

public class ToolMyDashboardPage extends BasePage {

	TestUtil util;
	CommonFunction commonFunction;

	public ToolMyDashboardPage(WebDriver driver) {
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

	By tabMyParcel = By.xpath("//*[text()='My Parcels']");

	public void clickOnMyParcel() {
		util.waitUntilElementDisplay(tabMyParcel);
		util.click(tabMyParcel);
	}

	By firstCheckBoxParcel = By
			.xpath("//input[contains(@id,'ctl00_ConPHRightTop_YALCOM_InMessages_RadGrid1_ctl00_ctl04_CheckBox1')]");

	public void clickOnFirstCheckBox() {
		util.waitUntilElementDisplay(firstCheckBoxParcel);
		util.click(firstCheckBoxParcel);
	}

	By btnPDF = By.xpath("(//a[contains(@id,'DownloadPDF')])[1]");

	public void clickOnPDF() {
		util.waitUntilElementDisplay(btnPDF);
		util.click(btnPDF);
	}

	public void verifyMyParcel( String testcaseName) {
		try {
			navigateToMyDashBoard();
			clickOnMyParcel();
			log("STEP 1:  Navigate to Tool - My Dashboard - My Parcels", Status.PASS);
		} catch (Exception e) {
			log("STEP 1:   Navigate to Tool - My Dashboard - My Parcels", Status.FAIL);
			throw new RuntimeException("Failed in step 1: Popup up does not appear");
		}
		try {
			clickOnFirstCheckBox();
			log("STEP 2:  User can able to select Parcel checkbox:", Status.PASS);
		} catch (Exception e) {
			log("STEP 2:  User not able to select Parcel checkbox :", Status.FAIL);
			throw new RuntimeException("STEP 2:  User not able to select Parcel checkbox :");
		}
		try {
			clickOnPDF();
			log("STEP 3:  User can able to click on PDF icon :", Status.PASS);
		} catch (Exception e) {
			log("STEP 3:  User not able to click on PDF icon :", Status.FAIL);
			throw new RuntimeException("STEP 3:  User not able to click on PDF icon :");
		}

	}

	By tabMyProject = By.xpath("//*[text()='My Projects']");

	public void clickOnMyProject() {
		util.waitUntilElementDisplay(tabMyProject);
		util.click(tabMyProject);
	}

	By selecFirsttRow = By.xpath("//table[@id='ctl00_ConPHRightTop_radGridAgents_ctl00']/tbody/tr[1]");

	public void clickOnFirstProjectRow() {
		util.click(selecFirsttRow);
		WebElement selecFirsttRow = driver.findElement(By.xpath("//table[@id='ctl00_ConPHRightTop_radGridAgents_ctl00']/tbody/tr[1]"));
		util.Rcllick(selecFirsttRow);
System.out.println();
	}

	By clickNavigate = By.xpath("//*[text()='Navigate to']");

	public void clickOnNavigate() {
		util.waitUntilElementDisplay(clickNavigate);
		util.click(clickNavigate);
	}

	By navProjectDetails = By.xpath("*//a//span[contains(text(),'Project Details')] ");

	public void isProjectDetialsPresent() {
		util.isElementPresent(navProjectDetails);
	}

	public void verifyMyProject(String testcaseName) {
		if (testcaseName.contains("DOT") || testcaseName.contains("ROW")) {
			log(" My Project is not  applicable for DOT and ROW", Status.SKIP);
		} else {
			try {
				navigateToMyDashBoard();
				log("STEP 1:  Navigate to Tool - My Dashboard", Status.PASS);
			} catch (Exception e) {
				log("STEP 1:   Navigate to Tool - My Dashboard", Status.FAIL);
				throw new RuntimeException("Failed in step 1: Popup up does not appear");
			}
			try {
				clickOnMyProject();
				log("STEP 2:  User can able to click on MyProject :", Status.PASS);
			} catch (Exception e) {
				log("STEP 2:  User not able to click on MyProject :", Status.FAIL);
				throw new RuntimeException("STEP 2:  User not able to click on MyProject :");
			}
			try {
				clickOnFirstProjectRow();
				log("STEP 3:  User can able to Right click on Project :", Status.PASS);
			} catch (Exception e) {
				log("STEP 3:  User not able to Right on Project :", Status.FAIL);
				throw new RuntimeException("STEP 3:  User not able to Right on Project :");
			}
			try {
				clickOnNavigate();
				log("STEP 4:  User can able to click on Navigate :", Status.PASS);
				isProjectDetialsPresent();
				log("Successfully navigate to Project details");
			} catch (Exception e) {
				log("STEP 4:  User not able to click on Navigate :", Status.FAIL);
				throw new RuntimeException("STEP 4:  User not able to click on Navigate :");
			}
		}
	}

	By tabNoteToReview = By.xpath("//*[text()='Notes to Review (0)']");

	public void clickNotesToReview() {
		util.waitUntilElementDisplay(tabNoteToReview);
		util.click(tabNoteToReview);
	}

	By selecNoteToReviewRow = By.xpath("//table[@id='ctl00_ConPHRightTop_radGridAgents_ctl00']/tbody/tr[1]");

	public void clickOnNoteToReviewRow() {
		util.waitUntilElementDisplay(selecNoteToReviewRow);
		util.click(selecNoteToReviewRow);
	}

	By clickHere = By.xpath("//a[contains(@id,'NotesToEdit')]");

	public void clickHere() {
		if (util.isElementPresent(clickHere)) {
			util.waitUntilElementDisplay(clickHere);
			util.click(clickHere);
			log("Click HerePresent :");
			util.switchToWindow();
		} else {
			log("Click Here is not present");
		}

	}

	By btnApprove = By.xpath("//input[contains(@id,'btnApprove')]");

	public void clickOnApprove() {
		util.waitUntilElementDisplay(btnApprove);
		util.click(btnApprove);
	}

	public void verifyNoteToReview(String testcaseName) {
		if (testcaseName.contains("ALT")) {
			log(" NoteToReview not applicable for ATL", Status.SKIP);
		} else {
			try {
				navigateToMyDashBoard();
				clickNotesToReview();
				log("STEP 1:  Navigate to Tool - My Dashboard- NoteToReview", Status.PASS);
			} catch (Exception e) {
				log("STEP 1:   Navigate to Tool - My Dashboard - NoteToReview", Status.FAIL);
				throw new RuntimeException("Failed in step 1: Popup up does not appear");
			}
			try {
				clickOnNoteToReviewRow();
				log("STEP 2:  User can able to select NoteToReview :", Status.PASS);
			} catch (Exception e) {
				log("STEP 2:  User not able to select on NoteToReview :", Status.FAIL);
				throw new RuntimeException("STEP 2:  User not able to select on NoteToReview :");
			}

			try {
				clickOnApprove();
				log("STEP 3:  User can able to click on Approve :", Status.PASS);
			} catch (Exception e) {
				log("STEP 3:  User not able to click on Approve :", Status.FAIL);
				throw new RuntimeException("STEP 3:  User not able to click on Approve  :");
			}
		}
	}

	By tabEditNote = By.xpath("//*[text()='Notes to Edit (0)']");

	public void clickEditNotesToReview() {
		util.waitUntilElementDisplay(tabEditNote);
		util.click(tabEditNote);
	}

	public void clickOnEditRow(String value) {
		By clickEditRow = By.xpath("*//td/strong[text()='" + value + "']//following-sibling::a[text()='Edit']");
		util.waitUntilElementDisappear(clickEditRow);
		util.click(clickEditRow);
	}

	public void editNoteToReview(String testcaseName) {
		if (testcaseName.contains("ALT")) {
			log(" Edit NoteToReview not applicable for ATL", Status.SKIP);
		} else {

			try {
				navigateToMyDashBoard();
				clickNotesToReview();
				log("STEP 1:  Navigate to Tool - My Dashboard- NoteToReview", Status.PASS);
			} catch (Exception e) {
				log("STEP 1:   Navigate to Tool - My Dashboard - NoteToReview", Status.FAIL);
				throw new RuntimeException("Failed in step 1: Popup up does not appear");
			}
			try {
				clickOnNoteToReviewRow();
				log("STEP 2:  User can able to select NoteToReview :", Status.PASS);
			} catch (Exception e) {
				log("STEP 2:  User not able to select on NoteToReview :", Status.FAIL);
				throw new RuntimeException("STEP 2:  User not able to select on NoteToReview :");
			}

			try {
				clickOnApprove();
				log("STEP 3:  User can able to click on Approve :", Status.PASS);
			} catch (Exception e) {
				log("STEP 3:  User not able to click on Approve :", Status.FAIL);
				throw new RuntimeException("STEP 3:  User not able to click on Approve  :");
			}
		}
	}

	By tabDeleteDoc = By.xpath("//*[text()='Deleted Doc(s) (0)']");

	public void clickDeleteDoc() {
		util.waitUntilElementDisplay(tabDeleteDoc);
		util.click(tabDeleteDoc);
	}

	public void deleteDoc(String testcaseName) {
		if (testcaseName.contains("DOT") || testcaseName.contains("ROW")) {
			log(" Delete Doc not applicable for DOT and ROW", Status.SKIP);
		} else {
			try {
				navigateToMyDashBoard();
				log("STEP 1:  Navigate to Tool - My Dashboard ", Status.PASS);
			} catch (Exception e) {
				log("STEP 1:   Not Navigate to Tool - My Dashboard ", Status.FAIL);
				throw new RuntimeException("Failed in step 1: Not Navigate to Tool - My Dashboard");
			}
			try {
				clickDeleteDoc();
				log("STEP 1:  Navigate to Delete Doc ", Status.PASS);
			} catch (Exception e) {
				log("STEP 1:   Not Navigate to Delete Doc :", Status.FAIL);
				throw new RuntimeException("Failed in step 2: Not Navigate to Delete Doc :");
			}

		}
	}
}
