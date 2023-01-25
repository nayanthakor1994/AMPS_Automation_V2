package testsuit.ProjectGroup;

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
import pages.SSRSReport.SSRSReportPage;
import pages.projectGroup.AddDocExternalLinkPage;
import pages.projectGroup.AddDocumentPage;
import pages.projectGroup.AddDocumentVersionPage;
import pages.projectGroup.AddProjectGroupPage;

@Listeners(com.listeners.MyListeners.class)
public class AddProjectGroupTest extends BasePage{
	
	LoginPage objLogin;
	AddProjectGroupPage objAddProjectGroup;
	AddDocExternalLinkPage objAddDocExternalLink;
	AddDocumentPage objAddDocument;
	AddDocumentVersionPage objAddDocmentVersion;
	ReadPropertyFile readPro = new ReadPropertyFile();
	Map<String, String> map = new HashMap<String, String>();
	
	@BeforeClass
	public void setup() throws Exception {
		driver = getDriver();
		objLogin = new LoginPage(driver);
		objAddProjectGroup = new AddProjectGroupPage(driver);
		objAddDocument = new AddDocumentPage(driver);
		objAddDocExternalLink = new AddDocExternalLinkPage(driver);
		objAddDocmentVersion = new AddDocumentVersionPage(driver);
	}

	@Test(priority = 1,enabled = false)
	public void addProjectGroup_TC_01() throws Exception {
		log("TC01 : Add Project Group");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.TestCases, environment);
		objLogin.login(map);
		String testcaseName = "AddProjectGroup" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.ProjectGroup, testcaseName);
		objAddProjectGroup.addProjectGroup(map, testcaseName);
	}
	
	@Test(priority = 2,enabled = true)
	public void addDocument_TC_02() throws Exception {
		log("TC02 : Add Document in Project Group");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.TestCases, environment);
		objLogin.login(map);
		String testcaseName = "AddDocument" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.AddDocumentProjectGroup, testcaseName);
		objAddDocument.addDocument(map, testcaseName);
	}
	
	@Test(priority = 3,enabled = false)
	public void addDocumentAndExternalLink_TC_03() throws Exception {
		log("TC03 : Add Document and External Link");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.TestCases, environment);
		objLogin.login(map);
		String testcaseName = "AddDocumentExternalLink" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.AddDocumentProjectGroup, testcaseName);	
		objAddDocExternalLink.addDocumentAndExternalLink(map, testcaseName);
	}
	@Test(priority = 4,enabled = false)
	public void viewDocument_TC_04() throws Exception {
		log("TC04 : View Document");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.TestCases, environment);
		objLogin.login(map);
		String testcaseName = "AddDocument" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.AddDocumentProjectGroup, testcaseName);	
		objAddDocument.viewDocument(map, testcaseName);
	
	}
	@Test(priority = 5,enabled = false)
	public void addDocumentVersion_TC_05() throws Exception {
		log("TC05 : Add Document Version");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.TestCases, environment);
		objLogin.login(map);
		String testcaseName = "AddDocument" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.AddDocumentProjectGroup, testcaseName);	
		objAddDocmentVersion.addDocumentVersion(map, testcaseName);
	
	}
	@Test(priority = 6,enabled = false)
	public void viewDocumentHistory_TC_06() throws Exception {
		log("TC06 : View Document History");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.TestCases, environment);
		objLogin.login(map);
		String testcaseName = "AddDocument" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.AddDocumentProjectGroup, testcaseName);	
		objAddDocument.viewDocumentHistory(map, testcaseName);
	
	}
	@Test(priority = 7,enabled = false)
	public void editDocument_TC_07() throws Exception {
		log("TC07 : Edit Document");
		navigateToApplication(appURL);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.TestCases, environment);
		objLogin.login(map);
		String testcaseName = "AddDocument" + environment;
		log("Data picked : " + testcaseName);
		map = ExcelUtils.getRowFromRowNumber(prop.getProperty(Excel.excelFileName), Excel.AddDocumentProjectGroup, testcaseName);	
		objAddDocument.editDocument(map, testcaseName);
	
	}
	
	

}
