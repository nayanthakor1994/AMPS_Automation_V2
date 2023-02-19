package pages.administrationSecurity;

import java.io.File;
import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.base.Excel;
import com.util.CommonFunction;
import com.util.TestUtil;

public class ManageUserAddNewUserPage extends BasePage {

	
	TestUtil util;
	CommonFunction commonFunction;
	
	public ManageUserAddNewUserPage(WebDriver driver) {
		this.driver = driver;
		util = new TestUtil(driver);
		commonFunction = new CommonFunction(driver);
	}

	By addNewUser = By.xpath("//a[contains(@id,'lnkAddNewRecord')]");
	By txtNetworkID = By.xpath("//input[contains(@id,'EditFormControl_NetworkID') and @type='text']");
	By txtLastName = By.xpath("//input[contains(@id,'EditFormControl_Last_Name') and @type='text']");
	By txtFirstName = By.xpath("//input[contains(@id,'EditFormControl_First_Name') and @type='text']");
	By txtTitle = By.xpath("//input[contains(@id,'EditFormControl_Title') and @type='text']");
	By selectActive = By.xpath("//input[contains(@id,'Active_ind')]");
	By txtEmail = By.xpath("//input[contains(@id,'Email') and @type='text']");
	By drpContractCompany = By
			.xpath("//input[contains(@id,'_svc_Provider_ID_radYALDropDownList_Input') and @type='text']");

	By selectClient = By.xpath("//*[text()='geoAMPS Power Generation, LLC']/../input[@type='checkbox']");
	By selectRole = By.xpath("//*[text()='Administrator']/../input[@type='checkbox']");
	By txtHomePhone = By.xpath("//input[contains(@id,'EditFormControl_Home_Phone') and @type='text']");
	By txtCellPhone = By.xpath("//input[contains(@id,'EditFormControl_Cell_Phone') and @type='text']");
	By txtWorkPhone = By.xpath("//input[contains(@id,'EditFormControl_Work_Phone') and @type='text']");
	By drpDefaultProjectRole = By
			.xpath("//input[contains(@id,'Default_project_Role_ID_radYALDropDownList_Input') and @type='text']");
	By drpDegaulTrackAssignment = By
			.xpath("//input[contains(@id,'_Default_Tract_Role_ID_radYALDropDownList_Input') and @type='text']");
	By selectProjectAssignmentAre = By
			.xpath("//input[contains(@id,'_CanSeeAllProjectsofAssignedArea') and @type='checkbox']");
	By navAddminitstration = By.xpath("//a//span[@class='rmText rmExpandDown' and contains(text(),'Administration')] ");
	By navSecurity = By.xpath("//a//span[@class='rmText rmExpandRight' and contains(text(),'Security')]");
	By navManageuser = By.xpath("//a//span[@class='rmText' and contains(text(),'Manage Users')]");

	public void navigateManageUser() {
		util.waitUntilElementDisplay(navAddminitstration);
		util.click(navAddminitstration);
		util.waitUntilElementDisplay(navSecurity);
		util.click(navSecurity);
		util.waitUntilElementDisplay(navManageuser);
		util.click(navManageuser);
	}
	
	
	public void addNetworkID(String value) {
		if (!commonFunction.checkNA(value))
			util.inputTextAndPressTab(txtNetworkID, util.randomNumber());
	}

	public void addLastName(String value) {
		if (!commonFunction.checkNA(value))
			util.inputTextAndPressTab(txtLastName, value);
	}

	public void addFirstName(String value) {
		if (!commonFunction.checkNA(value))
			util.inputTextAndPressTab(txtFirstName, value);
	}

	public void addTitle(String value) {
		if (!commonFunction.checkNA(value))
			util.inputTextAndPressTab(txtTitle, value);
	}

	public void clickOnActive() {
		util.click(selectActive);
	}

	public void addEmail(String value) {
		if (!commonFunction.checkNA(value))
			util.inputTextAndPressTab(txtEmail, value);
	}

	public void addContractCompany(String value) {
		if (!commonFunction.checkNA(value))
			util.inputTextAndPressTab(drpContractCompany, value);
	}

	public void selectClient(String value) {
		if (!commonFunction.checkNA(value)) {
			By selectArea = By.xpath("//*[text()='" + value + "']/../input[@type='checkbox']");
			util.click(selectArea);
		}
	}

	public void selectRole(String value) {
		if (!commonFunction.checkNA(value)) {
			By selectRole = By.xpath("//*[text()='" + value + "']/../input[@type='checkbox']");
			util.click(selectRole);
		}
	}

	public void addHomePhone(String value) {
		if (!commonFunction.checkNA(value))
			util.inputTextAndPressTab(txtHomePhone, value);
	}

	public void addCellPhone(String value) {
		if (!commonFunction.checkNA(value))
			util.inputTextAndPressTab(txtCellPhone, value);
	}

	public void addWorkPhone(String value) {
		if (!commonFunction.checkNA(value))
			util.inputTextAndPressTab(txtWorkPhone, value);
	}

	public void selectDefaultProjectRole(String value) {
		if (!commonFunction.checkNA(value))
			util.inputTextAndPressTab(drpDefaultProjectRole, value);
	}

	public void selectDefaultTractAssignment(String value) {
		if (!commonFunction.checkNA(value))
			util.inputTextAndPressTab(drpDegaulTrackAssignment, value);
	}

	By seeAllProjectAssigned = By.xpath("//input[contains(@id,'CanSeeAllProjectsofAssignedArea')]");

	public void selectSeeAllProject() {
		util.click(seeAllProjectAssigned);
	}

	By btnInsert = By.xpath("//input[contains(@id,'btnInsert')]");

	public void clickOnInsert() {
		util.click(btnInsert);
	}

	By btnCancle = By.xpath("//input[contains(@id,'btnInsert')]");

	public void clickOnCancle() {
		util.click(btnCancle);
	}

	By btnAddPircture = By.xpath("//img[@alt='Add Picture']");
	By selectPicture = By.xpath("//input[@class='ruButton ruBrowse']");
	By loadDocument = By.xpath("//span[contains(@id,'buttonSubmit')]");
	By successMessage = By.xpath("//span[@id='lblResults']");
	By fileUpload = By.xpath("//input[contains(@id,'Upload1file') or contains(@id,'Uploadfile')]");
	By iframeDocument = By.xpath("//iframe[@name='UserListDialog']");
	By close = By.xpath("//a[@title='Close']");

	public void addPicture() {
		util.waitForWebElementToBePresent(btnAddPircture, 60);
		util.dummyWait(5);
		util.click(btnAddPircture);
		log("Add Picuture clicked : ", Status.PASS);
		util.waitUntilElementDisplay(iframeDocument);
		util.switchToIframe(iframeDocument);
		util.dummyWait(2);
		log("Switch to frame ", Status.PASS);
		String filepath = System.getProperty("user.dir") + File.separator + "Image2.jpg";
		driver.findElement(fileUpload).sendKeys(filepath);
		util.dummyWait(5);
		log("Uploadfile ", Status.PASS);
		util.waitUntilElementDisplay(loadDocument);
		util.click(loadDocument);
		log("Click on load ", Status.PASS);
		util.dummyWait(10);
		util.waitUntilElementDisplay(successMessage);
		log("Waitfor Message ", Status.PASS);
		util.switchToDefaultContent();
		util.click(close);
	}
	public void addNewUser(Map<String, String> map, String testcaseName) {
		try {
			navigateManageUser();
			commonFunction.clickOnNewRecord();
			log("STEP 1:  Navigate to Menu - Manage User Account", Status.PASS);
		} catch (Exception e) {
			log("STEP 1:  Navigate to Menu - User Account", Status.FAIL);
			throw new RuntimeException("Failed in step 1: Popup up does not appear");
		}

		try {
			addNetworkID(map.get(Excel.NetworkID));
			log("STEP 2:  User can entered NetworkID :", Status.PASS);
		} catch (Exception e) {
			log("STEP 2:  User can not enter NetworkID", Status.FAIL);
			throw new RuntimeException("Failed in step 2");
		}
		try {
			addLastName(map.get(Excel.LastName));
			log("STEP 3:  Enter LastName", Status.PASS);
		} catch (Exception e) {
			log("STEP 3:  User can not enter LastName", Status.FAIL);
			throw new RuntimeException("Failed in step 3");
		}
		try {
			addFirstName(map.get(Excel.FirstName));
			log("STEP 4:  Enter FirstName", Status.PASS);
		} catch (Exception e) {
			log("STEP 4:  User can not enter FirstName", Status.FAIL);
			throw new RuntimeException("Failed in step 4");
		}
		try {
			addTitle(map.get(Excel.Title));
			log("STEP 5:  Enter Title", Status.PASS);
		} catch (Exception e) {
			log("STEP 5:  User can not enter Title", Status.FAIL);
			throw new RuntimeException("Failed in step 5");
		}
		try {
			clickOnActive();
			log("STEP 6:  Click on Active Checkbox", Status.PASS);
		} catch (Exception e) {
			log("STEP 6: Not able to Clink on Active Checkbox", Status.FAIL);
			throw new RuntimeException("Failed in step 6");
		}

		try {
			addEmail(map.get(Excel.Email));
			log("STEP 7:  Enter Email", Status.PASS);
		} catch (Exception e) {
			log("STEP 7:  User can not enter Email", Status.FAIL);
			throw new RuntimeException("Failed in step 7");
		}
		try {
			addContractCompany(map.get(Excel.ContractorCompany));
			log("STEP 8:  Enter ContractorCompany", Status.PASS);
		} catch (Exception e) {
			log("STEP 8:  User can not enter ContractorCompany", Status.FAIL);
			throw new RuntimeException("Failed in step 8");
		}
		try {
			addHomePhone(map.get(Excel.HomePhone));
			log("STEP 9:  Enter HomePhone", Status.PASS);
		} catch (Exception e) {
			log("STEP 9:  User can not enter HomePhone", Status.FAIL);
			throw new RuntimeException("Failed in step 9");
		}
		try {
			addCellPhone(map.get(Excel.CellPhone));
			log("STEP 10:  Enter CellPhone", Status.PASS);
		} catch (Exception e) {
			log("STEP 10:  User can not enter CellPhone", Status.FAIL);
			throw new RuntimeException("Failed in step 10");
		}
		try {
			addWorkPhone(map.get(Excel.WorkPhone));
			log("STEP 11:  Enter WorkPhone", Status.PASS);
		} catch (Exception e) {
			log("STEP 11:  User can not enter WorkPhone", Status.FAIL);
			throw new RuntimeException("Failed in step 11");
		}
		try {
			selectClient(map.get(Excel.Client));
			log("STEP 12:  Select Client", Status.PASS);
		} catch (Exception e) {
			log("STEP 12:  User can not select Client", Status.FAIL);
			throw new RuntimeException("Failed in step 12");
		}
		try {
			selectRole(map.get(Excel.Roles));
			log("STEP 13:  Select Roles", Status.PASS);
		} catch (Exception e) {
			log("STEP 13:  User can not select Roles", Status.FAIL);
			throw new RuntimeException("Failed in step 13");
		}
		try {
			selectDefaultProjectRole(map.get(Excel.DefaultProjectRole));
			log("STEP 14:  Select DefaultProjectRole", Status.PASS);
		} catch (Exception e) {
			log("STEP 14:  User can not select DefaultProjectRole", Status.FAIL);
			throw new RuntimeException("Failed in step 14");
		}
		try {
			selectDefaultTractAssignment(map.get(Excel.DefaultTractAssignment));
			log("STEP 15:  Select DefaultTractAssignment", Status.PASS);
		} catch (Exception e) {
			log("STEP 15:  User can not select DefaultTractAssignment", Status.FAIL);
			throw new RuntimeException("Failed in step 15");
		}
		try {
			selectSeeAllProject();
			log("STEP 16:  Select See All Project Assigned Area", Status.PASS);
		} catch (Exception e) {
			log("STEP 16:  User can not select All Project Assigned Area", Status.FAIL);
			throw new RuntimeException("Failed in step 16");
		}
		try {
			clickOnInsert();
			util.waitUntilElementDisplay(updateMessage);
			log("STEP 17:  Click On Insert", Status.PASS);
		} catch (Exception e) {
			log("STEP 17:  User can not Click On Insert", Status.FAIL);
			throw new RuntimeException("Failed in step 17");
		}

//		try {
//			clickOnCancle();
//			log("STEP 18:  Click On Cancle", Status.PASS);
//		} catch (Exception e) {
//			log("STEP 18:  User can not Click On Cancle", Status.FAIL);
//			throw new RuntimeException("Failed in step 18");
//		}
		try {
			editNewUser(map.get(Excel.LastName));
			log("Existing User Open ", Status.PASS);
			addPicture();
			log("STEP 19:  User can Add Pitcure", Status.PASS);
		} catch (Exception e) {
			log("STEP 19:  User can not Add Pitcure", Status.FAIL);
			throw new RuntimeException("Failed in step 19");
		}
		
		
		
	}

	public void editNewUser(String value) {
		By editNewUser = By.xpath("//*[contains(text(),'" + value + "')]/..//td[1]");
		util.dummyWait(2);
		util.waitForWebElementToBeClickableReturnElement(editNewUser, IMPLICIT_WAIT);
		util.click(editNewUser);
	}

	By btnUpdate = By.xpath("//input[contains(@id,'btnUpdate')]");
	By updateMessage = By.xpath("//*[text()='Changes saved successfully!']");

	public void clickOnUpdate() {
		util.click(btnUpdate);
		
	}

	public void updateNewUser(Map<String, String> map, String testcaseName) {
		editNewUser(map.get(Excel.LastName));
		try {
			log("STEP 20:  Existing User Open ", Status.PASS);
		} catch (Exception e) {
			log("STEP 20:  Existing User not Open", Status.FAIL);
			throw new RuntimeException("Failed in step 20");
		}
		try {
			util.dummyWait(10);
			selectDefaultTractAssignment(map.get(Excel.DefaultTractAssignment));
			util.dummyWait(5);
			clickOnUpdate();
			util.waitUntilElementDisplay(updateMessage);
			log("STEP 21:  User can Update New User ", Status.PASS);
		} catch (Exception e) {
			log("STEP 21:  User can not Update New User", Status.FAIL);
			throw new RuntimeException("Failed in step 21");
		}
		
	}

}
