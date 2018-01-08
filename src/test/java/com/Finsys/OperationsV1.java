package com.Finsys;

import java.io.File;
import ez.testcases.finsys.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.ejagruti.generic.TextOperations;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.NetworkMode;

import freemarker.template.utility.HtmlEscape;

public class OperationsV1 {

	public static WebDriver driver = null;
	public static WebDriverWait wait = null;
	private int timeout = 10;
	private int counter = 1;
	protected static String path = System.getProperty("user.dir");
	private boolean isLogEnabled = false;
	private String LogFolderPath;
	protected String LogFilePath;
	public static OperationsV1 op = null;
	private boolean isReportEnable = false;
	static String ExtendReportFolerPath;
	static ExtentReports report = null;
	static ExtentTest logger;

	public OperationsV1() {

	}

	public OperationsV1(boolean isLogEnabled, String LogFolderPath) {
		this.isLogEnabled = isLogEnabled;
		this.LogFolderPath = LogFolderPath;
		if (isLogEnabled) {
			this.LogFilePath = LogFolderPath + "\\LOG-" + TextOperations.getDateTime("ddMMyyyyHHmmSSS") + ".txt";
			TextOperations.CreateTextFile(LogFilePath);
		}
	}

	public OperationsV1(String ExtendReportFolerPath, boolean isReportEnable) {
		this.isReportEnable = isReportEnable;
		this.ExtendReportFolerPath = ExtendReportFolerPath;

	}

	public void LaunchApplication(String BrowserName, String URL) throws IOException {
		try {
			if (BrowserName.equalsIgnoreCase("ff")) {
				System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
			}
			if (BrowserName.equalsIgnoreCase("ch")) {
				System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
				driver = new ChromeDriver();
			}
			if (BrowserName.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver", "drivers\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
			wait = new WebDriverWait(driver, timeout);
			driver.get(URL);
			driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			String message = TextOperations.getDateTime() + "----INFO---- Step Number:" + (counter++)
					+ " Able to Launch Browser " + BrowserName;
			System.out.println(message);
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);

			}
			if (isReportEnable) {
				String scrn = TakeScreenShot();
				StepDetails("PASS", "LaunchApplication",
						"Applicatino is Launched Using " + BrowserName, scrn);
			}
		} catch (Exception ex) {
			String message = TextOperations.getDateTime() + "----ERROR---- Step Number:" + (counter++)
					+ " Failed to Launch Browser " + BrowserName + "\n Exception;" + ex.getLocalizedMessage();
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();
				StepDetails("FAIL", "LaunchApplication",
						"Applicatino is NOT Launched Using " + BrowserName, scrn);
			}
			throw new WebDriverException(message);
		}
	}

	public static String TakeScreenShot() throws IOException {
		String imgPath = ExtendReportFolerPath + "\\" + TextOperations.getDateTime("ddMMyyyyHHmmSSS") + ".png";
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(imgPath);
		FileUtils.copyFile(src, dest);
		String s = dest.toString();
		return s;
	}

	public WebElement IsObjectExists(String xPath) {
		WebElement obj = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		return obj;
	}

	public String ObjectGetAttributeValue(String xPath, String AttributeName) throws IOException {
		try {
			WebElement obj = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
			String message = TextOperations.getDateTime() + "----INFO---- Step Number:" + (counter++)
					+ " Able to get Attribute Value of Object using xPath=" + xPath;
			System.out.println(message);
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);

			}
			if (isReportEnable) {
				String scrn = TakeScreenShot();
				StepDetails("PASS", "Object Get Attribute Value",
						"Able to get attribute value of Object " + AttributeName, scrn);
			}
			return obj.getAttribute(AttributeName);
		} catch (Exception ex) {
			String message = TextOperations.getDateTime() + "----ERROR---- Step Number:" + (counter++)
					+ " Failed to get Attribute Value of Object using xPath=" + xPath + "\n Exception;"
					+ ex.getLocalizedMessage();
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();

				StepDetails("FAIL", "Objet Get Attribute Value",
						"NOT Able to get attribute value of Object " + AttributeName, scrn);
			}
			throw new WebDriverException(message);

		}
	}

	// ###############Button######################
	public void ButtonClick(String xPath) throws IOException {
		try {
			WebElement obj = IsObjectExists(xPath);
			obj.click();
			String message = TextOperations.getDateTime() + "----INFO---- Step Number:" + (counter++)
					+ " Able to Click on Button using xPath=" + xPath;
			System.out.println(message);
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();
				StepDetails("PASS", "Button Click", "Able to Click on Button " + xPath, scrn);
			}
		} catch (Exception ex) {
			String message = TextOperations.getDateTime() + "----ERROR---- Step Number:" + (counter++)
					+ " Failed to Click on Button using xPath=" + xPath + "\n Exception;" + ex.getLocalizedMessage();
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();
				StepDetails("FAIL", "Button Click", "Fail to Click on Button " + xPath, scrn);
			}
			throw new WebDriverException(message);
		}

	}

	// ###############Button######################
	public void ButtonDoubleClick(String xPath) throws IOException {
		try {
			WebElement obj = IsObjectExists(xPath);
			Actions act = new Actions(driver);
			act.doubleClick(obj).build().perform();
			String message = TextOperations.getDateTime() + "----INFO---- Step Number:" + (counter++)
					+ " Able to do Double Click on Button using xPath=" + xPath;
			System.out.println(message);
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();

				StepDetails("PASS", "Button Double Click",
						"Able to Double Click on Button " + xPath, scrn);
			}
		} catch (Exception ex) {
			String message = TextOperations.getDateTime() + "----ERROR---- Step Number:" + (counter++)
					+ " Failed to Double Click on Button using xPath=" + xPath + "\n Exception; "
					+ ex.getLocalizedMessage();
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();

				StepDetails("FAIL", "Button Double Click",
						"Fail to Double Click on Button " + xPath, scrn);
			}
			throw new WebDriverException(message);
		}
	}

	public void ButtonRightClick(String xPath) throws IOException {
		try {
			WebElement obj = IsObjectExists(xPath);
			Actions act = new Actions(driver);
			String message = TextOperations.getDateTime() + "----INFO---- Step Number:" + (counter++)
					+ " Able to do Right Click on Button using xPath=" + xPath;
			System.out.println(message);
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();

				StepDetails("PASS", "Button Rigt Click", "Able to Right Click on Button " + xPath,
						scrn);
			}
		} catch (Exception ex) {
			String message = TextOperations.getDateTime() + "----ERROR---- Step Number:" + (counter++)
					+ " Failed to Right Click on Button using xPath=" + xPath + "\n Exception"
					+ ex.getLocalizedMessage();
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);

			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();

				StepDetails("FAIL", "Button Click", "Fail to Right Click on Button " + xPath, scrn);
			}
			throw new WebDriverException(message);
		}
	}

	// ###############TextBox######################
	public void TextBoxSetValue(String xPath, String Value) throws InterruptedException, IOException {
		try {
			WebElement obj = IsObjectExists(xPath);
			obj.clear();
			Thread.sleep(1000);
			obj.sendKeys(Value);
			String message = TextOperations.getDateTime() + "----INFO---- Step Number:" + (counter++)
					+ " Able to Set Value in TextBox using xPath=" + xPath;
			System.out.println(message);
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();

				StepDetails("PASS", "Text Box Set Value", "Able to Set Value for TextBox " + Value,
						scrn);
			}

		} catch (Exception ex) {
			String message = TextOperations.getDateTime() + "----ERROR---- Step Number:" + (counter++)
					+ " Failed to Set Value in TextBox using xPath=" + xPath + "/n Exception;"
					+ ex.getLocalizedMessage();
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();

				StepDetails("FAIL", "Text Box Set Value", "Fail to Set Value for TextBox " + Value,
						scrn);
			}
			throw new WebDriverException(message);
		}
	}

	public void TextBoxAppendValue(String xPath, String Value) throws IOException {
		try {
			WebElement obj = IsObjectExists(xPath);
			obj.sendKeys(Value);
			String message = TextOperations.getDateTime() + "----INFO---- Step Number:" + (counter++)
					+ " Able to Append Value in TexBox using xPath=" + xPath;
			System.out.println(message);
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();

				StepDetails("PASS", "Text Box Append Value",
						"Able to Append Value for TextBox " + Value, scrn);
			}
		} catch (Exception ex) {
			String message = TextOperations.getDateTime() + "----ERROR---- Step Number:" + (counter++)
					+ " Failed to Append Value in TexBox using xPath=" + xPath + "\n Exception;"
					+ ex.getLocalizedMessage();
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();

				StepDetails("FAIL", "Text Box Append Value",
						"Fail to Append Value for TextBox " + Value, scrn);
			}
			throw new WebDriverException(message);

		}
	}

	// ###############Link######################
	public void LinkClick(String xPath) throws IOException {
		try {
			WebElement obj = IsObjectExists(xPath);
			obj.click();
			String message = TextOperations.getDateTime() + "----INFO---- Step Number:" + (counter++)
					+ " Able to Click on Link using xPath=" + xPath;
			System.out.println(message);
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();

				StepDetails("PASS", "Link Click", "Able to Click on Link " + xPath, scrn);
			}
		} catch (Exception ex) {
			String message = TextOperations.getDateTime() + "----ERROR---- Step Number:" + (counter++)
					+ " Failed to Click on Link using xPath=" + xPath + "\n Exception;" + ex.getLocalizedMessage();
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();

				StepDetails("FAIL", "Link Click", "Fail to Click on Link " + xPath, scrn);
			}
			throw new WebDriverException(message);
		}
	}

	// #################Table#####################
	public int TableGetRowCount(String xPath) throws IOException {
		try {
			WebElement obj = IsObjectExists(xPath);
			String message = TextOperations.getDateTime() + "----INFO---- Step Number:" + (counter++)
					+ " Able to get Row Count from Table using xPath=" + xPath;
			System.out.println(message);
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();

				StepDetails("PASS", "Table Get Row Count", "Able to get Table Row Count " + xPath,
						scrn);
			}
			return obj.findElements(By.tagName("tr")).size();
		} catch (Exception ex) {
			String message = TextOperations.getDateTime() + "----ERROR---- Step Number:" + (counter++)
					+ " Failed to get Row Count from Table using xPath=" + xPath + "\n Exception;"
					+ ex.getLocalizedMessage();
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();

				StepDetails("FAIL", "Table Get Row Count", "Fail to get Table Row Count " + xPath,
						scrn);
			}
			throw new WebDriverException(message);
		}
	}

	public int TableGetColumnCount(String xPath, int RowNumber) throws IOException {
		try {
			WebElement obj = IsObjectExists(xPath);
			String message = TextOperations.getDateTime() + "----INFO---- Step Number:" + (counter++)
					+ " Able to get Column Count from Table using xPath=" + xPath;
			System.out.println(message);
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();

				StepDetails("PASS", "Table Get Column Count",
						"Able to get Table Column Count " + RowNumber, scrn);
			}
			return obj.findElements(By.tagName("tr")).get(RowNumber).findElements(By.tagName("td")).size();
		} catch (Exception ex) {
			String message = TextOperations.getDateTime() + "----ERROR---- Step Number:" + (counter++)
					+ " Failed to get Column Count from Table using xPath=" + xPath + "\n Exception;"
					+ ex.getLocalizedMessage();
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();

				StepDetails("FAIL", "Table Get Column Count",
						"Fail to get Table Column Count " + RowNumber, scrn);
			}
			throw new WebDriverException(message);
		}

	}

	public String TableGetCellValue(String xPath, int RowNumber, int ColumnNumber) throws IOException {
		try {
			WebElement obj = IsObjectExists(xPath);
			String message = TextOperations.getDateTime() + "----INFO---- Step Number:" + (counter++)
					+ " Able to get Cell Value from Table using xPath=" + xPath;
			System.out.println(message);
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();
				StepDetails("PASS", "Table Get Cell Value", "Able to get Table Cell Value", scrn);
			}
			return obj.findElements(By.tagName("tr")).get(RowNumber).findElements(By.tagName("td")).get(ColumnNumber)
					.getText();
		} catch (Exception ex) {
			String message = TextOperations.getDateTime() + "----ERROR---- Step Number:" + (counter++)
					+ " Failed to get Cell Value from Table using xPath=" + xPath + "\n Exception;"
					+ ex.getLocalizedMessage();
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();

				StepDetails("FAIL", "Table Get Cell Value", "Fail to get Table Cell Value", scrn);
			}
			throw new WebDriverException(message);
		}
	}

	// ######################Frame#################
	public WebDriver FrameSwitchByIndex(int Index) throws IOException {
		try {
			String message = TextOperations.getDateTime() + "----INFO---- Step Number:" + (counter++)
					+ " Able to Switch Frame by Index using Index=" + Index;
			System.out.println(message);
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();

				StepDetails("PASS", "Frame Switch By Index",
						"Able to Switch Frame by Index" + Index, scrn);
			}
			return driver.switchTo().frame(Index);
		} catch (Exception ex) {
			String message = TextOperations.getDateTime() + "----ERROR---- Step Number:" + (counter++)
					+ " Failed to Switch Frame by Index using Index=" + Index + "\n Exception;"
					+ ex.getLocalizedMessage();
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();

				StepDetails("FAIL", "Frame Switch By Index",
						"Fail to Switch Frame by Index" + Index, scrn);
			}
			throw new WebDriverException(message);
		}
	}

	public WebDriver FrameSwitchByXPath(String xPath) throws IOException {
		try {
			WebElement obj = driver.findElement(By.xpath(xPath));
			String message = TextOperations.getDateTime() + "----INFO---- Step Number:" + (counter++)
					+ " Able to get Switch Frame by Xpath using xPath=" + xPath;
			System.out.println(message);
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();
				StepDetails("PASS", "Frame Switch By Xpath",
						"Able to Switch Frame by Xpath" + xPath, scrn);
			}
			return driver.switchTo().frame(obj);
		} catch (Exception ex) {
			String message = TextOperations.getDateTime() + "----ERROR---- Step Number:" + (counter++)
					+ " Failed to get Switch Frame by Xpath using xPath=" + xPath + "\n Exception;"
					+ ex.getLocalizedMessage();
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();

				StepDetails("FAIL", "Frame Switch By Xpath",
						"Fail to Switch Frame by Xpath" + xPath, scrn);
			}
			throw new WebDriverException(message);
		}
	}

	public WebDriver FrameSwitchByName(String NameOfTheFrame) throws IOException {
		try {
			String message = TextOperations.getDateTime() + "----INFO---- Step Number:" + (counter++)
					+ " Able to get Switch Frame by Name using FrameName=" + NameOfTheFrame;
			System.out.println(message);
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();

				StepDetails("PASS", "Frame Switch By Name",
						"Able to Switch Frame by Name" + NameOfTheFrame, scrn);
			}
			return driver.switchTo().frame(NameOfTheFrame);
		} catch (Exception ex) {
			String message = TextOperations.getDateTime() + "----ERROR---- Step Number:" + (counter++)
					+ " Failed to get Switch Frame by Name using FrameName=" + NameOfTheFrame + "\n Exception;"
					+ ex.getLocalizedMessage();
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();

				StepDetails("FAIL", "Frame Switch By Name",
						"Fail to Switch Frame by Name" + NameOfTheFrame, scrn);
			}
			throw new WebDriverException(message);
		}
	}

	// ######################Drop Down#################
	public void DropDownSelectByVisibleText(String xPath, String Value) throws IOException {
		try {
			WebElement obj = IsObjectExists(xPath);
			Select sel = new Select(obj);
			sel.selectByVisibleText(Value);
			String message = TextOperations.getDateTime() + "----INFO---- Step Number:" + (counter++)
					+ " Able to Select Visible Text from Drop Down using xPath=" + xPath;
			System.out.println(message);
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();

				StepDetails("PASS", "Drop Down Select by Visible Text",
						"Able to Select Value from Dropdown by visible Text" + Value, scrn);
			}
		} catch (Exception ex) {
			String message = TextOperations.getDateTime() + "----ERROR---- Step Number:" + (counter++)
					+ " Failed to Select Visible Text from Drop Down using xPath=" + xPath + "\n Exception;"
					+ ex.getLocalizedMessage();
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();

				StepDetails("FAIL", "Drop Down Select by Visible Text",
						"Fail to Select Value from Dropdown by visible Text" + Value, scrn);
			}
			throw new WebDriverException(message);
		}
	}

	public void DropDownSelectByIndex(String xPath, int Index) throws IOException {
		try {
			WebElement obj = IsObjectExists(xPath);
			Select sel = new Select(obj);
			sel.selectByIndex(Index);
			String message = TextOperations.getDateTime() + "----INFO---- Step Number:" + (counter++)
					+ " Able to Select by Index Text from Drop Down using xPath=" + xPath;
			System.out.println(message);
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();

				StepDetails("PASS", "Drop Down Select by Index",
						"Able to Select Value from Dropdown by Index" + Index, scrn);
			}
		} catch (Exception ex) {
			String message = TextOperations.getDateTime() + "----ERROR---- Step Number:" + (counter++)
					+ " Failed to Select by Index from Drop Down using xPath=" + xPath + "\n Exception;"
					+ ex.getLocalizedMessage();
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();

				StepDetails("FAIL", "Drop Down Select by Index",
						"Fail to Select Value from Dropdown by Index" + Index, scrn);
			}
			throw new WebDriverException(message);
		}
	}

	public void DropDownSelectByOptionValue(String xPath, String OptionValue) throws IOException {
		try {
			WebElement obj = IsObjectExists(xPath);
			Select sel = new Select(obj);
			sel.selectByValue(OptionValue);
			String message = TextOperations.getDateTime() + "----INFO---- Step Number:" + (counter++)
					+ " Able to Select by Option Value from Drop Down using xPath=" + xPath;
			System.out.println(message);
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {
				String scrn = TakeScreenShot();
				StepDetails("PASS", "Drop Down Select by Option value",
						"Able to Select Value from Dropdown by Option value" + OptionValue, scrn);
			}
		} catch (Exception ex) {
			String message = TextOperations.getDateTime() + "----ERROR---- Step Number:" + (counter++)
					+ " Failed to Select by Option Value from Drop Down using xPath=" + xPath + "\n Exception;"
					+ ex.getLocalizedMessage();
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {
				String scrn = TakeScreenShot();

				StepDetails("FAIL", "Drop Down Select by Option value",
						"Fail to Select Value from Dropdown by Option Value" + OptionValue, scrn);
			}
			throw new WebDriverException(message);
		}
	}

	public String DropDownGetSelectedValue(String xPath) throws IOException {
		try {
			WebElement obj = IsObjectExists(xPath);
			Select sel = new Select(obj);
			String message = TextOperations.getDateTime() + "---INFO--- Step Number:" + (counter++)
					+ " Able to Get Selected Value from Drop Down using xPath=" + xPath;
			System.out.println(message);
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();

				StepDetails("PASS", "Drop Down Select by Selected value",
						"Able to Select Value from Dropdown by Selected Value" + xPath, scrn);
			}
			return sel.getFirstSelectedOption().getText();
		} catch (Exception ex) {
			String message = TextOperations.getDateTime() + "---ERROR--- Step Number:" + (counter++)
					+ " Failed to Get Selected Value from Drop Down using xPath=" + xPath + "\n Exception;"
					+ ex.getLocalizedMessage();
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();

				StepDetails("FAIL", "Drop Down Select by Selected value",
						"Fail to Select Value from Dropdown by Selected Value" + xPath, scrn);
			}
			throw new WebDriverException(message);
		}
	}

	public ArrayList<String> DropDownGetAllSelectedValue(String xPath) throws IOException {
		try {
			WebElement obj = IsObjectExists(xPath);
			Select sel = new Select(obj);
			ArrayList<String> allSelectedValue = new ArrayList<String>();
			for (WebElement ele : sel.getAllSelectedOptions()) {
				allSelectedValue.add(ele.getText());
			}
			String message = TextOperations.getDateTime() + "---INFO--- Step Number:" + (counter++)
					+ " Able to Get All Selected Value from Drop Down using xPath=" + xPath;
			System.out.println(message);
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {
				String scrn = TakeScreenShot();
				StepDetails("PASS", "Drop Down Select by ALL Selected value",
						"Able to Select Value from Dropdown by ALL Selected Value" + xPath, scrn);
			}
			return allSelectedValue;
		} catch (Exception ex) {
			String message = TextOperations.getDateTime() + "----ERROR---- Step Number:" + (counter++)
					+ " Failed to Get All Selected Value from Drop Down using xPath=" + xPath + "\n Exception;"
					+ ex.getLocalizedMessage();
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {

				String scrn = TakeScreenShot();

				StepDetails("FAIL", "Drop Down Select by ALL Selected value",
						"Fail to Select Value from Dropdown by ALL Selected Value" + xPath, scrn);
			}
			throw new WebDriverException(message);
		}
	}

	// ######################### Extend Report #################################3
	/*
	 * public static void TestSuiteStart(String ResultHTMLFilePath, String UserName)
	 * throws UnknownHostException { report = new ExtentReports(ResultHTMLFilePath,
	 * false, NetworkMode.OFFLINE);
	 * 
	 * report.addSystemInfo("Host Name",
	 * InetAddress.getLocalHost().getHostName()).addSystemInfo("Environment", "QA")
	 * .addSystemInfo("User Name", UserName); }
	 * 
	 * public static void TestSuiteEnd() { report.flush(); report.close(); }
	 * 
	 * public static void TestCaseStart(String TestName, String Description) {
	 * logger = report.startTest(TestName, Description); }
	 * 
	 * public static void TestCaseEnd() { report.endTest(logger); }
	 * 
	 * public static void StepDetails(String Status, String StepName, String
	 * StepDetails, String objectImagePath) { String tbl = StepDetails + "<br>" +
	 * logger.addScreenCapture(objectImagePath); if
	 * (Status.equalsIgnoreCase("pass")) { logger.log(LogStatus.PASS, StepName,
	 * tbl); } else if (Status.equalsIgnoreCase("fail")) {
	 * logger.log(LogStatus.FAIL, StepName, StepDetails); } else if
	 * (Status.equalsIgnoreCase("error")) { logger.log(LogStatus.ERROR, StepName,
	 * StepDetails); } else if (Status.equalsIgnoreCase("info")) {
	 * logger.log(LogStatus.INFO, StepName, StepDetails); } else {
	 * logger.log(LogStatus.INFO, StepName, StepDetails); } }
	 */
	
	// ############################## HTML Report ################3
	public static void TestSuiteStart(String ResultHTMLFilePath, String UserName) throws UnknownHostException {
		report = new ExtentReports(ResultHTMLFilePath, false, NetworkMode.OFFLINE);

		report.addSystemInfo("Host Name", InetAddress.getLocalHost().getHostName()).addSystemInfo("Environment", "QA")
				.addSystemInfo("User Name", UserName);
	}

	public static void TestSuiteEnd() {
		report.flush();
		report.close();
	}

	public static void TestCaseStart(String TestName, String Description) {
		logger = report.startTest(TestName, Description);
	}

	public static void TestCaseEnd() {
		report.endTest(logger);

	}

	public static void StepDetails(String Status, String StepName, String StepDetails, String objectImagePath) {
		String tbl = StepDetails + "<br>" + logger.addScreenCapture(objectImagePath);
		if (Status.equalsIgnoreCase("pass")) {
			logger.log(LogStatus.PASS, StepName, tbl);
		} else if (Status.equalsIgnoreCase("fail")) {
			logger.log(LogStatus.FAIL, StepName, tbl);
		} else if (Status.equalsIgnoreCase("error")) {
			logger.log(LogStatus.ERROR, StepName, tbl);
		} else if (Status.equalsIgnoreCase("info")) {
			logger.log(LogStatus.INFO, StepName, tbl);
		} else {
			logger.log(LogStatus.INFO, StepName, tbl);
		}
	}

	// ################################# Methods //
	// ######################################

	public void BeforeSuite(String suitename, String ownernme) throws UnknownHostException {
		TestSuiteStart(
				ExtendReportFolerPath + "\\RESULT_" + TextOperations.getDateTime("ddMMYYYYHHmmSSS") + ".html",
				ownernme);
	}

	public void BeforeTest(String testcaseid, String testcasetitle) {
		TestCaseStart(testcaseid, testcasetitle);
		// .TestCaseStart(TestName, Description);
	}

	public void AfterTest() {
		TestCaseEnd();
	}

	public void AfterSuite() {
		TestSuiteEnd();
	}

	public void CloseBrowser() {
		driver.close();
	}
	/*
	public static void main(String[] args) throws InterruptedException, IOException {
		op = new OperationsV1(path + "\\result", true); // call to report
		// op = new OperationsV1(true, path+"\\log"); // call to log
		op.BeforeSuite("regression", "aabidkar");
		op.BeforeTest("Login_Functionality", "Verify Login Functionality");
		// op.ValidLogin();
		op.TestCaseEnd();
		// op.InvalidLogin();
		op.BeforeTest("Add Company", "Verify Add Company Functionality");
		// op.CreateCompany();
		op.TestCaseEnd();
		op.CloseBrowser();
		op.TestSuiteEnd();
	}
	*/

}