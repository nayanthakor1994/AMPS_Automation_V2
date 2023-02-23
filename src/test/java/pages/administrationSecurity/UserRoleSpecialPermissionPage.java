package pages.administrationSecurity;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.base.Excel;
import com.util.CommonFunction;
import com.util.TestUtil;

public class UserRoleSpecialPermissionPage extends BasePage {
	
	TestUtil util;
	CommonFunction commonFunction;

	public UserRoleSpecialPermissionPage(WebDriver driver) {
		this.driver = driver;
		util = new TestUtil(driver);
		commonFunction = new CommonFunction(driver);
	}
	
	By navAddminitstration = By.xpath("//a//span[@class='rmText rmExpandDown' and contains(text(),'Administration')] ");
	By navSecurity = By.xpath("//a//span[@class='rmText rmExpandRight' and contains(text(),'Security')]");
	By navUserRole = By.xpath("//a//span[@class='rmText' and contains(text(),'User Role')]");
	By savePermission = By.xpath("//input[contains(@id,'savePermissions')]");

	public void navigateUserRoles() {
		util.waitUntilElementDisplay(navAddminitstration);
		util.click(navAddminitstration);
		util.waitUntilElementDisplay(navSecurity);
		util.click(navSecurity);
		util.waitUntilElementDisplay(navUserRole);
		util.click(navUserRole);
	}
	
	By drpRole = By.xpath("//input[contains(@id,'DISTRIBUTION_Input')]");
	public void selectRoleDD(String value) {
		if (!commonFunction.checkNA(value))
			util.selectDropDownValue(drpRole, value);
	}
	public void selectSpecialPermissionOption(String value) {
		if (!commonFunction.checkNA(value)) {
			By selectSpecialPermission = By.xpath("//*[text()='"+value+"']/../input[1]");
			util.click(selectSpecialPermission);
		}
	}
	
	By tabSpecialPermission = By.xpath("//span[text()='Special Permissions']");
	public void clickOnSpecialPermission() {
		util.click(tabSpecialPermission);

	}

	By btnSavePermission = By.xpath("//input[contains(@id,'savePermissions')]");
	public void clickOnSavePermission() {
		util.waitUntilElementDisplay(btnSavePermission);
		util.click(btnSavePermission);
	}
	
	By btnSave = By.xpath("//input[contains(@id,'btnSave_Duplicate')]");
	public void clickOnSave() {
		util.waitUntilElementDisplay(btnSave);
		util.click(btnSave);
	}
	
	By btnDuplicatePermission = By.xpath("//input[contains(@id,'btnDuplicatePermission')]");

	public void clickOnDuplication() {
		util.waitUntilElementDisplay(btnDuplicatePermission);
		util.click(btnDuplicatePermission);
	}
	
	By txtSaveAS = By.xpath("//input[contains(@id,'NEWDistribution_Duplicate')]");
	public void saveUserRole(String value) {
		util.waitUntilElementDisplay(txtSaveAS);
		util.inputTextAndPressTab(txtSaveAS, value);
	}
	public void selectSpecialPermission(Map<String, String> map, String testcaseName) {
		try {
			navigateUserRoles();
			log("STEP 1:  Navigate to Menu - User Roles", Status.PASS);
		} catch (Exception e) {
			log("STEP 1:  Not Navigate to Menu - User Roles", Status.FAIL);
			throw new RuntimeException("Failed in step 1:  Not Navigate to Menu - User Roles");
		}
		try {
			selectRoleDD(map.get(Excel.Roles));
			log("STEP 2:  User can select Role", Status.PASS);
		} catch (Exception e) {
			log("STEP 2:  User can not select Role", Status.FAIL);
			throw new RuntimeException("Failed in step 2:  User can not select Role");
		}
		try {
			clickOnSpecialPermission();
			log("STEP 3:  User can click on Special Permission", Status.PASS);
		} catch (Exception e) {
			log("STEP 3:  User can not click on Special Permission", Status.FAIL);
			throw new RuntimeException("Failed in step 3:  User can not click on Page Permission");
		}
		
		try {
			selectSpecialPermissionOption(map.get(Excel.SpecialPermissionOption));
			log("STEP 4:  User can select Special Permission option", Status.PASS);
		} catch (Exception e) {
			log("STEP 4:  User can not select Special Permission option", Status.FAIL);
			throw new RuntimeException("STEP 4:  User can not select Special Permission option");
		}
		try {
			clickOnSavePermission();
			log("STEP 5:  User can click on Save", Status.PASS);
		} catch (Exception e) {
			log("STEP 5:  User can not click on Save", Status.FAIL);
			throw new RuntimeException("Failed in step 5: User can not click on Save ");
		}
//		try {
//			navigateUserRoles();
//			selectRoleDD(map.get(Excel.Roles));
//			clickOnDuplication();
//			log("STEP 6:  User can click on Duplication Permission", Status.PASS);
//		} catch (Exception e) {
//			log("STEP 6:  User can not click on Duplication Permission", Status.FAIL);
//			throw new RuntimeException("Failed in step 6:  User can not click on Duplication Permission");
//		}
//		try {
//			saveUserRole(map.get(Excel.NewRoles));
//			log("STEP 7:  User can enter new user Role", Status.PASS);
//		} catch (Exception e) {
//			log("STEP 7:  User can not enter new user Role", Status.FAIL);
//			throw new RuntimeException("Failed in step 7:  User can not enter new user Role");
//		}
//		try {
//			clickOnSave();
//			log("STEP 8:  User can click on Save", Status.PASS);
//		} catch (Exception e) {
//			log("STEP 8:  User can not click on Save", Status.FAIL);
//			throw new RuntimeException("Failed in step 11: User can not click on Save ");
//		}

		
	}

	By btnAddNew = By.xpath("//input[contains(@id,'addNew')]");
	public void clickOnAddNew() {
		util.waitUntilElementDisplay(btnAddNew);
		util.click(btnAddNew);
	}
	By txtRoles = By.xpath("//input[contains(@id,'NEWDistribution')]");
	public void setNewRoles(String value) {
		util.waitUntilElementDisplay(txtRoles);
		util.inputTextAndPressTab(txtRoles, value);
	}
	
	By btnRemove = By.xpath("//input[contains(@id,'delete')]");
	public void clickOnRemove() {
		util.waitUntilElementDisplay(btnRemove);
		util.click(btnRemove);
	}
	By saveNewRole = By.xpath("//input[@id='ctl00_ConPHRightTop_save']");
	public void clickOnSaveNewRole() {
		util.waitUntilElementDisplay(saveNewRole);
		util.click(saveNewRole);
	}
	public void addNewPermission(Map<String, String> map, String testcaseName) {
		try {
			clickOnAddNew();
			log("STEP 9:  User can click on Add New", Status.PASS);
		} catch (Exception e) {
			log("STEP 9:  User can not click on Add New", Status.FAIL);
			throw new RuntimeException("Failed in step 9:User can not click on Add New ");
		}
		try {
			setNewRoles(map.get(Excel.NewRoles));
			log("STEP 10:  User can add New Roles", Status.PASS);
		} catch (Exception e) {
			log("STEP 10:  User can not add New Roles", Status.FAIL);
			throw new RuntimeException("Failed in step 10:User can not add New Roles  ");
		}
		try {
			clickOnSaveNewRole();
			log("STEP 11:  User can click on Save New Roles", Status.PASS);
		} catch (Exception e) {
			log("STEP 11:  User can not click on Save New Roles", Status.FAIL);
			throw new RuntimeException("Failed in step 11: User can not click on Save New Roles");
		}
		try {
			clickOnRemove();
			log("STEP 12:  User can click on Remove", Status.PASS);
		} catch (Exception e) {
			log("STEP 12:  User can not click on Remove", Status.FAIL);
			throw new RuntimeException("Failed in step 12:User can not click on Remove ");
		}
		
	}

}
