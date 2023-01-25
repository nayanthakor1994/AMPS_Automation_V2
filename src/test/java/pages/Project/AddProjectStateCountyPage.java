package pages.Project;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.base.BasePage;
import com.util.CommonFunction;
import com.util.TestUtil;

public class AddProjectStateCountyPage extends BasePage {
	TestUtil util;
	CommonFunction commonFunction;

	public AddProjectStateCountyPage(WebDriver driver) {
		this.driver = driver;
		util = new TestUtil(driver);
		commonFunction = new CommonFunction(driver);

	}

	By btnAddNewRecord = By.xpath("(//img[contains(@id,'PROJECTSTATECOUNTY_radYALGridControl')])[1]");
	By drpState = By.xpath("//input[contains(@id,'EditFormControl_RadState_Input')]");
	By drpCounty = By.xpath("//input[contains(@id,'EditFormControl_RadCounty_Input')]");
	By btnInsert = By.xpath("//input[contains(@id,'EditFormControl_btnInsert')]");

	By btnEdit = By.xpath("//input[contains(@name,'EditButton')]");
	By EditState = By.xpath("//tr[contains(@id,'EditFormControl')]//input[contains(@name,'RadState')]");
	By EditCounty = By.xpath("(//tr[contains(@id,'EditFormControl')]//input[contains(@name,'RadCounty')])[1]");
	By btnUpdateCounty = By.xpath("//input[contains(@id,'btnUpdate')]");
	By tableCountyName = By.xpath("((//table[@class='rgMasterTable'])[1]//tbody)[last()]//tr[1]//td[5]");
	By tableStateName = By.xpath("((//table[@class='rgMasterTable'])[1]//tbody)[last()]//tr[1]//td[4]");
	By btnDeleteSTCT = By.xpath("//input[contains(@id,'DeleteAlignment')]");
	By deletePopupFrame = By.xpath("//iframe[contains(@name,'confirm')]");
	By deleteOk = By.xpath("//a[contains(@onClick,'confirm')]//span[text()='OK']");
	By changesSavedSuccessfully = By.xpath("//span[text()='Changes saved successfully!']");
	String tableValue = "//table//tbody//tr//td[text()='%s']";

	public void setState(String state) throws InterruptedException {
		Thread.sleep(2000);
	//	util.selectValueFromDropdown2(state, drpState);
		util.selectValueFromDropdown(drpState, state);
		util.pressENTERkey();
	}

	public void setCounty(String County) throws InterruptedException {
		Thread.sleep(2000);
		//util.selectValueFromDropdown2(County, drpCounty);
		util.selectValueFromDropdown(drpCounty, County);
		util.pressENTERkey();
	}

	public void updateState(String updateState) {
		util.selectDropDownValue(EditState, updateState);
		log("update State" + updateState);
	}

	public void updateCounty(String updateCity) {
		util.selectDropDownValue(EditCounty, updateCity);
		log("update County" + updateCity);
	}

	public void clickInsetButton() {
		util.click(btnInsert);
	}

	public void addStateAndCountyInformation(Map<String, String> map) throws InterruptedException {
		commonFunction.navigateToProjectDeails();
		util.waitUntilElementDisplay(btnAddNewRecord);
		try {
			util.click(btnAddNewRecord);
			log("STEP 1: The panel fields displays", Status.PASS);
		} catch (Exception e) {
			log("STEP 1: Project states and County information panel is not available", Status.FAIL);
			throw new RuntimeException("Failed in step 1 ");
		}
		try {
			setState(map.get("StateName"));
			log("STEP 2:  State added diplays in the state column    ", Status.PASS);
		} catch (InterruptedException e) {
			log("STEP 2: Added state is not displayed in the field", Status.FAIL);
			throw new RuntimeException("Failed in step 2 ");
		}
		util.dummyWait(10);
		try {
			setCounty(map.get("CountyName"));
			log("STEP 3: County added diplays in the column field", Status.PASS);
		} catch (InterruptedException e) {
			log("STEP 3:  Added county is not displayed in the field", Status.FAIL);
			throw new RuntimeException("Failed in step 3");
		}
		try {
			clickInsetButton();
			log("STEP 4: The added values displays under state and county column in the grid and ", Status.PASS);
		} catch (Exception e) {
			log("STEP 4: Added values does  not displayed in the grid", Status.FAIL);
			throw new RuntimeException("Failed in step 4 ");
		}
		util.dummyWait(10);
		Assert.assertTrue(util.isElementPresent(changesSavedSuccessfully), "Verify changes saved successfully.");
		if (util.isElementPresent(changesSavedSuccessfully)) {
			log(" State and County Information message displyed", Status.PASS);
		} else {
			log("State and County Information message not displayed", Status.FAIL);
			throw new RuntimeException("Failed in State and County Information message not displayed ");
		}
		if (util.isElementPresent(String.format(tableValue, map.get("CountyName")))) {
			log("State and County Information is added sucessfully", Status.PASS);
		} else {
			log("State and County Information is not added sucessfully", Status.FAIL);
			throw new RuntimeException("Failed in State and County Information is not added sucessfully ");
			
		}
	}

	public void updateStateAndCountyInformation(Map<String, String> map) throws InterruptedException {
		util.waitUntilElementDisplay(btnEdit);
		util.click(btnEdit);
		util.dummyWait(10);
		try {
			updateState(map.get("EditStateName"));
			log("update value diplays in the field     ", Status.PASS);
		} catch (Exception e) {
			log("updated value does not displayed in the field. OR ", Status.FAIL);
			throw new RuntimeException("updated value does not displayed in the field ");

		}
		try {
			updateCounty(map.get("EditCountyName"));
			//log("STEP 1: The panel fields displays", Status.PASS);
		} catch (Exception e) {
			//log("STEP 1: Project states and County information panel is not available", Status.FAIL);
			// e.printStackTrace();
		}
		util.dummyWait(10);
		try {
			util.click(btnUpdateCounty);
			util.dummyWait(10);
			log("Click On update", Status.PASS);
		} catch (Exception e) {
			log("Upate button not clicable", Status.FAIL);
			throw new RuntimeException("Upate button not clicable ");
		}
		util.dummyWait(10);
		util.waitUntilElementDisappear(btnUpdateCounty);
		String CountyName = util.getText(tableCountyName);
		String stateName = util.getText(tableStateName);
		util.dummyWait(10);
		Assert.assertTrue(util.isElementPresent(changesSavedSuccessfully), "Changes saved successfully!");
		Assert.assertEquals(CountyName, map.get("EditCountyName"), "Verify County name");
		Assert.assertEquals(stateName, map.get("EditCountyAlias"), "Verify state name");
//		if(util.isElementPresent(changesSavedSuccessfully)){
//			ReportsClass.logStat(Status.PASS, "State and County Information is updated sucessfully !!!");
//		} else {
//			ReportsClass.logStat(Status.FAIL, "State and County Information is not updated sucessfully !!!");
//		}
		if (CountyName.equals(map.get("EditCountyName"))) {
			log("STEP 6: County information is updated sucessfully", Status.PASS);
		} else {
			log("STEP 6: County information is not  updated sucessfully", Status.FAIL);
			throw new RuntimeException("Faild in STEP 6 :County information is not  updated sucessfully ");
		}

		if (stateName.equals(map.get("EditCountyAlias"))) {
			log("STEP 6: State information is updated sucessfully", Status.PASS);
		} else {
			log("STEP 6: State information is not  updated sucessfully", Status.FAIL);
			throw new RuntimeException("Faild in STEP 6 :State information is not  updated sucessfully  ");
		}

		// Delete updated County-state
		util.dummyWait(10);
		util.waitUntilElementDisplay(btnDeleteSTCT);
		try {
			util.click(btnDeleteSTCT);
			util.dummyWait(10);
		} catch (Exception e) {
		}

		util.dummyWait(5);
		util.click(deleteOk);
		util.dummyWait(10);

		Assert.assertTrue(util.isElementPresent(changesSavedSuccessfully), "Verify changes saved successfully.");
		if (util.isElementPresent(changesSavedSuccessfully)) {
			log("STEP 7: State and County Information is deleted sucessfully", Status.PASS);
		} else {
			log("STEP 7: State and County Information is not deleted sucessfully", Status.FAIL);
			throw new RuntimeException("State and County Information is not deleted sucessfully ");
		}

	}


}
