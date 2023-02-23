package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.base.BasePage;

import extentReports.ExtentTestManager;

public class TestUtil extends BasePage {
	WebDriverWait wait;
	JavascriptExecutor jscriptExec;
	Actions action;

	public TestUtil(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		action = new Actions(driver);
	}

	protected enum Condition {
		isDisplayed, isClickable, isPresent, isNotVisible
	}

	public void resetImplicitWait(int seconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
	}

	/**
	 * This method is for hard wait in process execution
	 * 
	 * @param miliSecond
	 */
	public void waitFor(int miliSecond) {
		try {
			Thread.sleep(miliSecond);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will click on given xpath
	 * 
	 * @param xpath
	 */
	public void click(By xpath) {
		try {
			waitUntilElementDisplay(xpath);
			getElement(Condition.isClickable, xpath, 50).click();
		} catch (Exception e) {
			try {
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", driver.findElement(xpath));
			} catch (Exception e1) {
				throw new RuntimeException("Not able to click on element" + xpath.toString());
			}
		}
	}

	SimpleDateFormat sdf;
	Calendar calendar;
	Calendar now;
	Calendar sevenDaysAgo;
	Calendar endOfNow;
	Date effDate;

	public void EffectiveDate() {
		sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		calendar = Calendar.getInstance();
		now = Calendar.getInstance();
		endOfNow = Calendar.getInstance();
		sevenDaysAgo = Calendar.getInstance();
	}

	public String getNowDate() {
		now.add(Calendar.DATE, 0);
		return sdf.format(now.getTime());
	}

	public void jsClick(By by) {
		scrollToElement(by);
		try {
			jscriptExec.executeScript("arguments[0].click();", waitForWebElementToBePresentReturnElement(by));
		} catch (Exception e) {
			jscriptExec.executeScript("arguments[0].click();", waitForWebElementToBePresentReturnElement(by));
		}
	}

	public void jsClick(WebElement webElement) {
		try {
			jscriptExec.executeScript("arguments[0].click();", webElement);
		} catch (Exception e) {
			jscriptExec.executeScript("arguments[0].click();", webElement);
		}
	}

	public void Rcllick(WebElement xpath) {
		Actions act = new Actions(driver);
		act.contextClick(xpath).build().perform();
	}

	public void rightClick(By xpath) {
		try {
			waitUntilElementDisplay(xpath);
			getElement(Condition.isClickable, xpath, 50).click();
			Actions act = new Actions(driver);
			act.contextClick().build().perform();
		} catch (Exception e) {
			try {
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", driver.findElement(xpath));
			} catch (Exception e1) {
				throw new RuntimeException("Not able to click on element");
			}
		}
	}

	public void dummyWait(int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='dummywait']")));
		} catch (Exception e) {
		}
	}

	public void selectDropDownValue(By xpath, String value) {
		try {
			waitUntilElementDisplay(xpath);
			click(xpath);
			waitFor(2000);
			log("Selecting value from dropdown :" + value);
			By drpValue = By.xpath("//ul//li[text()='" + value + "']");
			if (!isElementPresent(drpValue)) {
				click(xpath);
				waitFor(2000);
			}
			click(drpValue);
			ExtentTestManager.info("Selected value : " + value + " in locator : " + xpath.toString());
		} catch (Exception e) {
			throw new RuntimeException("Not able to select value from drpdown : " + value);
		}

	}

	public void click(String xpath) {
		try {
			System.out.println("Waiting for : " + xpath);
			getElement(Condition.isClickable, By.xpath(xpath), 60).click();
		} catch (Exception e) {
			try {
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", getElement(Condition.isClickable, By.xpath(xpath), 60));
			} catch (Exception e1) {
				throw e1;
			}
		}
	}

	/**
	 * This method will enter text into textbox
	 * 
	 * @param inputField as webelement
	 * @param text
	 */
	public void inputText(WebElement inputField, String text) {
		try {
			inputField.click();
			clearInputField(inputField);
			// log("Enter value :"+text);
			inputField.sendKeys(text);
			ExtentTestManager.info("User typed: " + text + " in locator: " + inputField.toString());
		} catch (Exception e) {
			System.out.println("Element not present " + inputField);
			e.printStackTrace();
			throw new RuntimeException("Unable to Enter Text" + text);
		}
	}

	/**
	 * This method will enter text into textbox
	 * 
	 * @param locator as xpath string
	 * @param text
	 */
	public void inputText(By locator, String text) {
		try {
			waitUntilElementDisplay(locator);
			WebElement inputField = getElement(locator);
			inputField.click();
			clearInputField(inputField);
			inputField.sendKeys(text);
			ExtentTestManager.info("User typed: " + text + " in locator: " + locator.toString());
		} catch (Exception e) {
			throw new RuntimeException("Unable to Enter Text :" + text);
		}
	}

	/**
	 * This method will enter text into textbox and press tab key
	 * 
	 * @param locator as xpath string
	 * @param text
	 */
	public void inputTextAndPressTab(By locator, String text) {
		try {
			waitUntilElementDisplay(locator);
			WebElement inputField = getElement(locator);
			inputField.click();
			clearInputField(inputField);
			inputField.sendKeys(text);
			inputField.sendKeys(Keys.TAB);
			ExtentTestManager.info("User typed: " + text + " in locator: " + locator.toString());
		} catch (Exception e) {
			throw new RuntimeException("Unable to Enter Text :" + text);
		}
	}

	/**
	 * This method will clear all the text into textbox
	 * 
	 * @param ele
	 */
	public void clearInputField(WebElement ele) {
		ele.sendKeys(Keys.CONTROL + "a");
		waitFor(1000);
		ele.sendKeys(Keys.DELETE);
	}

	/**
	 * This method will clear all the text into textbox
	 * 
	 * @param locator
	 */
	public void clearInputField(By locator) {
		WebElement ele = getElement(locator);
		ele.sendKeys(Keys.CONTROL + "a");
		waitFor(1000);
		ele.sendKeys(Keys.DELETE);
	}

	/**
	 * This method will return WebElement for given xpath
	 * 
	 * @param value
	 * @return
	 */
	public WebElement getElement(By value) {
		WebElement ele = null;
		try {
			ele = getElement(Condition.isDisplayed, value, 60);
		} catch (Exception e) {
			throw e;
		}
		return ele;
	}

	/**
	 * This method will return true or false if element is displayed or not
	 * 
	 * @param xpath
	 * @return
	 */
	public boolean isElementPresent(By locator) {
		boolean isPresent = false;
		try {
//			isPresent = driver.findElement(locator).isDisplayed();
			if (driver.findElements(locator).size() > 0)
				isPresent = true;
			else
				isPresent = false;
		} catch (Exception e) {
			return false;
		}
		if (isPresent)
			highlightElement(driver.findElement(locator));
		return isPresent;
	}

	public boolean isElementPresent(String locator) throws InterruptedException {
		Thread.sleep(10000);
		boolean isPresent = false;
		try {
			isPresent = driver.findElement(By.xpath(locator)).isDisplayed();
//			if(driver.findElements(locator).size() > 0)
//				isPresent= true;
//			else
//				isPresent= false;
		} catch (Exception e) {
			return false;
		}
		if (isPresent)
			highlightElement(driver.findElement(By.xpath(locator)));
		return isPresent;
	}

	/**
	 * This method will return true or false if element is displayed or not
	 * 
	 * @param ele
	 * @return
	 */
	public boolean isElementPresent(WebElement ele) {
		boolean isPresent = false;
		try {
			isPresent = ele.isDisplayed();
		} catch (Exception e) {
			return false;
		}
		if (isPresent)
			highlightElement(ele);
		return isPresent;
	}

	/**
	 * This method will wait untill element is present
	 * 
	 * @param locator
	 */
	public void waitUntilElementPresent(By locator) {
		System.out.println("Waiting for : " + locator);
		try {
			getElement(Condition.isPresent, locator, 60);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * This method will wait untill element is displayed
	 * 
	 * @param xpath
	 */
	public void waitUntilElementDisplay(By locator) {
		try {
			System.out.println("Waiting for : " + locator);
			getElement(Condition.isDisplayed, locator, 30);
		} catch (Exception e) {
			System.out.println("Element not present");
			throw e;
		}
	}
	
	

	public List<WebElement> getWebElements(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(IMPLICIT_WAIT));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	public List<WebElement> getWebElements(By locator, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	public WebElement waitForWebElementToBeVisibleReturnElement(By locator) {
		WebElement visibleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return visibleElement;
	}

	public void waitForWebElementToBeVisible(By locator, int waitTime) {
		WebDriverWait waitVisible = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
		waitVisible.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement waitForWebElementToBeClickableReturnElement(By locator) {
		WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(locator));
		return clickableElement;
	}

	public WebElement waitForWebElementToBeClickableReturnElement(By locator, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
		WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(locator));
		return clickableElement;
	}

	public void waitForWebElementToBePresent(By locator) {
		scrollToElement(locator);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void waitForWebElementToBePresent(By locator, int variableWaitTime) {
		WebDriverWait variableWait = new WebDriverWait(driver, Duration.ofSeconds(variableWaitTime));
		scrollToElement(locator);
		variableWait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitForWebElementToBePresentReturn(By locator, int variableWaitTime) {
		WebDriverWait variableWait = new WebDriverWait(driver, Duration.ofSeconds(variableWaitTime));
		scrollToElement(locator);
		return variableWait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitForWebElementToBePresentReturnElement(By locator) {
		scrollToElement(locator);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void waitForWebElementToBeClickable(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void waitForWebElementToBeClickable(By locator, int time) {
		WebDriverWait variableWait = new WebDriverWait(driver, Duration.ofSeconds(time));
		variableWait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	/**
	 * This method will return WebElement based on condition, xpath and timeout
	 * 
	 * @param condition
	 * @param locator
	 * @param time
	 * @return
	 */
	private WebElement getElement(Condition condition, By by, int time) {
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));

		try {
			switch (condition) {
			case isClickable:
				element = (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(by));
				if (element == null) {
					return element;
				} else if (element.getAttribute("clickable") == null) {
					return element;
				} else if (element.getAttribute("clickable") != null) {
					element = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(by));
					return element;
				}
				break;
			case isDisplayed:
				element = (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(by));
				break;
			case isPresent:
				element = (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(by));
				break;
			default:
				break;
			}
			if (!isVisibleInViewport(element))
				scrollToElement(element);
		} catch (Exception e) {
			throw new RuntimeException("Unable to get Element :");
		}
		return highlightElement(element);
	}

	protected boolean isVisibleInViewport(WebElement element) {
		return ((Boolean) ((JavascriptExecutor) driver).executeScript(
				"var elem = arguments[0],box = elem.getBoundingClientRect(),cx = box.left + box.width / 2,cy = box.top + box.height / 2, e = document.elementFromPoint(cx, cy); for (; e; e = e.parentElement) {if (e === elem) return true;}return false;",
				new Object[] { element })).booleanValue();
	}

	/**
	 * 
	 * @param element
	 * @return desired element This method is used to mark red box around the
	 *         desired element
	 */
	public WebElement highlightElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='2px solid red'",
				new Object[] { element });
		return element;
	}

	/**
	 * This method will select the value from drop down
	 */
//	public void SelectDropDownValue(String Value, WebElement Element) {
//		Select authType = new Select(Element);
//		authType.selectByVisibleText(Value);
//	}

//	/**
//	 *  This method is move focus on the xpath
//	 * @param locator
//	 */
//	public void switchToBlock(By locator) {
//		WebElement wb = getElement(locator);
//		if (isChrome) {
//			Actions act = new Actions(driver);
//			act.moveToElement(wb).build().perform();
//		} else {
//			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", wb);
//		}
//	}

//	/**
//	 * This method is move focus on the WebElement
//	 * @param wb
//	 */
//	public void switchToBlock(WebElement wb) {
//		if (isChrome) {
//			Actions act = new Actions(driver);
//			act.moveToElement(wb).build().perform();
//		} else {
//			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", wb);
//		}
//	}

	/**
	 * This method will scroll down or up to element
	 * 
	 * @param element
	 */
	public void scrollToElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		if (!isVisibleInViewport(element)) {
			((JavascriptExecutor) driver).executeScript(
					"window.scrollTo(" + element.getLocation().x + "," + (element.getLocation().y - 80) + ");");
		}
	}

	public String randomNumber() {
		SecureRandom random = new SecureRandom();
		int num = random.nextInt(100000);
		String formatted = String.format("%05d", num);
		System.out.println(formatted);
		return formatted;
	}

	public String randomNumber(int number) {
		return RandomStringUtils.randomAlphanumeric(number).toUpperCase();
	}

	public void pressENTERkey() {
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER).build().perform();
	}

	public void pressDownkey() {
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ARROW_DOWN).build().perform();
	}

	public void pressEscapkey() {
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ESCAPE).build().perform();
	}

	public void pressRightClick() {
		Actions act = new Actions(driver);
		act.contextClick().build().perform();
	}

	/**
	 * This method will scroll down or up to element
	 * 
	 * @param locator
	 */
	public void scrollToElement(By locator) {
		WebElement element = getElement(locator);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		if (!isVisibleInViewport(element)) {
			((JavascriptExecutor) driver).executeScript(
					"window.scrollTo(" + element.getLocation().x + "," + (element.getLocation().y - 80) + ");");
		}
	}

	/**
	 * This method will return the current Date and time in fixed format
	 * 
	 * @return
	 */
	public static String getCurrentDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String dateTime = dateFormat.format(date);
		return dateTime;
	}

	/**
	 * This method will return the text of given locator
	 * 
	 * @param by is locator
	 * @return
	 */
	public String getText(By locator) {
		return getElement(locator).getText();
	}

	/**
	 * This method will return the attribute value specified in parameter
	 * 
	 * @param locator
	 * @param attributeName
	 * @return
	 */
	public String getAttributeValue(By locator, String attributeName) {
		return getElement(locator).getAttribute(attributeName);
	}

	/**
	 * This method will return the text of given WebElement
	 * 
	 * @param element
	 * @return
	 */
	public String getText(WebElement element) {
		return element.getText();
	}

	/**
	 * This method will wait(60 sec) until element will be disappeared
	 * 
	 * @param locator
	 */
	public void waitUntilElementDisappear(By locator) {
		try {
			new WebDriverWait(driver, Duration.ofSeconds(120))
					.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		} catch (Exception e) {
		}
	}
	public void waitUntilElementDisappear(By locator, int seconds) {
		try {
			new WebDriverWait(driver, Duration.ofSeconds(seconds))
			.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		} catch (Exception e) {
		}
	}

	/**
	 * This method will return status of button
	 * 
	 * @param locator
	 * @return
	 */
	public boolean isButtonEnabled(By locator) {
		return getElement(locator).isEnabled();
	}

	/**
	 * This method will return name of file which having given extension
	 * 
	 * @param filepath
	 * @param extension
	 * @return
	 */
	public String getFileFromExtension(String filepath, String extension) {
		String pdfFile = null;
		File directoryPath = new File(filepath);
		String contents[] = directoryPath.list();
		for (int i = 0; i < contents.length; i++) {
			if (contents[i].contains(extension)) {
				pdfFile = contents[i];
				break;
			}
		}
		return pdfFile;
	}

	/**
	 * This method will return true if file is deleted else false
	 * 
	 * @param filePath
	 * @return
	 */
	public boolean deleteFile(String filePath) {
		File file = new File(filePath);
		if (file.delete()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method will return Date&Time in given format
	 * 
	 * @param format
	 * @return
	 */
	public static String getCurrentDateTime(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		String dateTime = dateFormat.format(date);
		return dateTime;
	}

	/**
	 * This method will return Date&Time in given format
	 * 
	 * @param format
	 * @return
	 */
	public static String addDaysInCurrentDate(String format, int days) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, days);
		String dateTime = dateFormat.format(c.getTime());
		return dateTime;
	}

	/**
	 * This method will copy one folder from source to destination
	 * 
	 * @param source
	 * @param destination
	 */
	public static void copyFolder(File source, File destination) {
		if (source.isDirectory()) {
			if (!destination.exists()) {
				destination.mkdirs();
			}
			String files[] = source.list();
			for (String file : files) {
				File srcFile = new File(source, file);
				File destFile = new File(destination, file);
				copyFolder(srcFile, destFile);
			}
		} else {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new FileInputStream(source);
				out = new FileOutputStream(destination);

				byte[] buffer = new byte[1024];

				int length;
				while ((length = in.read(buffer)) > 0) {
					out.write(buffer, 0, length);
				}
			} catch (Exception e) {
				try {
					in.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				try {
					out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public void scrollDownToPage() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)");
	}

	public void reloadPage() {
		driver.navigate().refresh();
	}

	public void selectValueFromDropdown(By locator, String value) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		String drpSelectName = "//ul//li[normalize-space()='" + value + "']";
		try {
			waitFor(1000);
			waitUntilElementDisplay(locator);
			click(locator);
			waitFor(1000);
			if (!isElementVisible(By.xpath(drpSelectName))) {
				click(locator);
				waitFor(1000);
			}
			click(By.xpath(drpSelectName));
			ExtentTestManager.info("User select value: " + value + " in locator: " + locator.toString());
		} catch (Exception e) {
			try {
				click(locator);
				waitUntilElementDisplay(By.xpath(drpSelectName));
				click(By.xpath(drpSelectName));
			} catch (Exception e1) {
				throw new RuntimeException("Unable to select :" + value);
			}
		}
	}
	
	public void selectValueFromDropdownCheckbox(By locator, String value) {
		String drpSelectName = "//ul//li[normalize-space()='" + value + "']//input";
		try {
			waitFor(1000);
			waitUntilElementDisplay(locator);
			click(locator);
			waitFor(1000);
			if (!isElementVisible(By.xpath(drpSelectName))) {
				click(locator);
				waitFor(1000);
			}
			click(By.xpath(drpSelectName));
			ExtentTestManager.info("User select value: " + value + " in locator: " + locator.toString());
		} catch (Exception e) {
			try {
				click(locator);
				waitUntilElementDisplay(By.xpath(drpSelectName));
				click(By.xpath(drpSelectName));
			} catch (Exception e1) {
				throw new RuntimeException("Unable to select :" + value);
			}
		}
	}
	
	public void selectValueFromDropdownCheckboxContains(By locator, String value) {
		String drpSelectName = "//ul//li[contains(.,'"+value+"')]//input";
		try {
			waitFor(1000);
			waitUntilElementDisplay(locator);
			click(locator);
			waitFor(1000);
			if (!isElementVisible(By.xpath(drpSelectName))) {
				click(locator);
				waitFor(1000);
			}
			click(By.xpath(drpSelectName));
			ExtentTestManager.info("User select value: " + value + " in locator: " + locator.toString());
		} catch (Exception e) {
			try {
				click(locator);
				waitUntilElementDisplay(By.xpath(drpSelectName));
				click(By.xpath(drpSelectName));
			} catch (Exception e1) {
				throw new RuntimeException("Unable to select :" + value);
			}
		}
	}

	public void switchToIframe(By locator) {
		waitUntilElementDisplay(locator);
		driver.switchTo().frame(driver.findElement(locator));
		ExtentTestManager.info("Switch to Frame:" + locator);
	}

	public void switchToDefaultContent() {
		try {
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectValueFromDropdown2(String Value, By locator) {
		try {
			waitUntilElementDisplay(locator);
			Select selct = new Select(driver.findElement(locator));
			selct.selectByVisibleText(Value);
		} catch (Exception e) {
			throw new RuntimeException("Unable to select :" + Value);
		}
	}

	public boolean isElementVisible(By by) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	public boolean isElementVisible(By by, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	public boolean isElementPresent(By by, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void switchToWindow() {
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String handle : allWindowHandles) {
			System.out.println("Switching to window - > " + handle);
			driver.switchTo().window(handle);
		}
	}

	public void rightClickOnElement(By locator) {
		action.contextClick(waitForWebElementToBePresentReturnElement(locator)).build().perform();
	}

	public void waitUntilLoaderDisappear() {
		By loaderIcon = By.xpath("//body/div[contains(@id,'Ajax')]");
		waitUntilElementDisappear(loaderIcon);
	}
	
	public void hoverOnElement(By by) {
		action.moveToElement(waitForWebElementToBePresentReturnElement(by)).build().perform();
	}
}