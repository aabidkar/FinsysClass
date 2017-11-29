package com.Finsys;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.ejagruti.generic.TextOperations;
import org.openqa.selenium.By;
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

public class OperationsV1 extends HTMLReportGenerator {

	public static WebDriver driver = null;
	public static WebDriverWait wait = null;
	private int timeout = 10;
	private int counter = 1;
	private boolean isLogEnabled = false;
	private String LogFolderPath;
	private String LogFilePath;
	public static OperationsV1 op = null;
	static ExtentReports report = null;
	static ExtentTest logger;
	private boolean isReportEnable = false;
	private String ExtendReportFolerPath;

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

	public void LaunchApplication(String BrowserName, String URL) {
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
				HTMLReportGenerator.StepDetails("PASS", "LaunchApplication",
						"Applicatino is Launched Using " + BrowserName, "");
			}
		} catch (Exception ex) {
			String message = TextOperations.getDateTime() + "----ERROR---- Step Number:" + (counter++)
					+ " Failed to Launch Browser " + BrowserName + "\n Exception;" + ex.getLocalizedMessage();
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {
				HTMLReportGenerator.StepDetails("FAIL", "LaunchApplication",
						"Applicatino is NOT Launched Using " + BrowserName, "");
			}
			throw new WebDriverException(message);
		}
	}

	public WebElement IsObjectExists(String xPath) {
		WebElement obj = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		return obj;
	}

	public String ObjectGetAttributeValue(String xPath, String AttributeName) {
		try {
			WebElement obj = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
			String message = TextOperations.getDateTime() + "----INFO---- Step Number:" + (counter++)
					+ " Able to get Attribute Value of Object using xPath=" + xPath;
			System.out.println(message);
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);

			}
			if (isReportEnable) {
				HTMLReportGenerator.StepDetails("PASS", "Objet Get Attribute Value",
						"Able to get attribute value of Object " + AttributeName, "");
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
				HTMLReportGenerator.StepDetails("FAIL", "Objet Get Attribute Value",
						"NOT Able to get attribute value of Object " + AttributeName, "");
			}
			throw new WebDriverException(message);

		}
	}

	// ###############Button######################
	public void ButtonClick(String xPath) {
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
				HTMLReportGenerator.StepDetails("PASS", "Button Click", "Able to Click on Button " + xPath, "");
			}
		} catch (Exception ex) {
			String message = TextOperations.getDateTime() + "----ERROR---- Step Number:" + (counter++)
					+ " Failed to Click on Button using xPath=" + xPath + "\n Exception;" + ex.getLocalizedMessage();
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {
				HTMLReportGenerator.StepDetails("FAIL", "Button Click", "Fail to Click on Button " + xPath, "");
			}
			throw new WebDriverException(message);
		}

	}

	// ###############Button######################
	public void ButtonDoubleClick(String xPath) {
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
				HTMLReportGenerator.StepDetails("PASS", "Button Double Click",
						"Able to Double Click on Button " + xPath, "");
			}
		} catch (Exception ex) {
			String message = TextOperations.getDateTime() + "----ERROR---- Step Number:" + (counter++)
					+ " Failed to Double Click on Button using xPath=" + xPath + "\n Exception; "
					+ ex.getLocalizedMessage();
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {
				HTMLReportGenerator.StepDetails("FAIL", "Button Double Click",
						"Fail to Double Click on Button " + xPath, "");
			}
			throw new WebDriverException(message);
		}
	}

	public void ButtonRightClick(String xPath) {
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
				HTMLReportGenerator.StepDetails("PASS", "Button Rigt Click", "Able to Right Click on Button " + xPath,
						"");
			}
		} catch (Exception ex) {
			String message = TextOperations.getDateTime() + "----ERROR---- Step Number:" + (counter++)
					+ " Failed to Right Click on Button using xPath=" + xPath + "\n Exception"
					+ ex.getLocalizedMessage();
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);

			}
			if (isReportEnable) {
				HTMLReportGenerator.StepDetails("FAIL", "Button Click", "Fail to Right Click on Button " + xPath, "");
			}
			throw new WebDriverException(message);
		}
	}

	// ###############TextBox######################
	public void TextBoxSetValue(String xPath, String Value) throws InterruptedException {
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
				HTMLReportGenerator.StepDetails("PASS", "Text Box Set Value", "Able to Set Value for TextBox " + Value,
						"");
			}

		} catch (Exception ex) {
			String message = TextOperations.getDateTime() + "----ERROR---- Step Number:" + (counter++)
					+ " Failed to Set Value in TextBox using xPath=" + xPath + "/n Exception;"
					+ ex.getLocalizedMessage();
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {
				HTMLReportGenerator.StepDetails("FAIL", "Text Box Set Value", "Fail to Set Value for TextBox " + Value,
						"");
			}
			throw new WebDriverException(message);
		}
	}

	public void TextBoxAppendValue(String xPath, String Value) {
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
				HTMLReportGenerator.StepDetails("PASS", "Text Box Append Value",
						"Able to Append Value for TextBox " + Value, "");
			}
		} catch (Exception ex) {
			String message = TextOperations.getDateTime() + "----ERROR---- Step Number:" + (counter++)
					+ " Failed to Append Value in TexBox using xPath=" + xPath + "\n Exception;"
					+ ex.getLocalizedMessage();
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {
				HTMLReportGenerator.StepDetails("FAIL", "Text Box Append Value",
						"Fail to Append Value for TextBox " + Value, "");
			}
			throw new WebDriverException(message);

		}
	}

	// ###############Link######################
	public void LinkClick(String xPath) {
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
				HTMLReportGenerator.StepDetails("PASS", "Link Click", "Able to Click on Link " + xPath, "");
			}
		} catch (Exception ex) {
			String message = TextOperations.getDateTime() + "----ERROR---- Step Number:" + (counter++)
					+ " Failed to Click on Link using xPath=" + xPath + "\n Exception;" + ex.getLocalizedMessage();
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {
				HTMLReportGenerator.StepDetails("FAIL", "Link Click", "Fail to Click on Link " + xPath, "");
			}
			throw new WebDriverException(message);
		}
	}

	// #################Table#####################
	public int TableGetRowCount(String xPath) {
		try {
			WebElement obj = IsObjectExists(xPath);
			String message = TextOperations.getDateTime() + "----INFO---- Step Number:" + (counter++)
					+ " Able to get Row Count from Table using xPath=" + xPath;
			System.out.println(message);
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {
				HTMLReportGenerator.StepDetails("PASS", "Table Get Row Count", "Able to get Table Row Count " + xPath,
						"");
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
				HTMLReportGenerator.StepDetails("FAIL", "Table Get Row Count", "Fail to get Table Row Count " + xPath,
						"");
			}
			throw new WebDriverException(message);
		}
	}

	public int TableGetColumnCount(String xPath, int RowNumber) {
		try {
			WebElement obj = IsObjectExists(xPath);
			String message = TextOperations.getDateTime() + "----INFO---- Step Number:" + (counter++)
					+ " Able to get Column Count from Table using xPath=" + xPath;
			System.out.println(message);
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {
				HTMLReportGenerator.StepDetails("PASS", "Table Get Column Count",
						"Able to get Table Column Count " + RowNumber, "");
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
				HTMLReportGenerator.StepDetails("FAIL", "Table Get Column Count",
						"Fail to get Table Column Count " + RowNumber, "");
			}
			throw new WebDriverException(message);
		}

	}

	public String TableGetCellValue(String xPath, int RowNumber, int ColumnNumber) {
		try {
			WebElement obj = IsObjectExists(xPath);
			String message = TextOperations.getDateTime() + "----INFO---- Step Number:" + (counter++)
					+ " Able to get Cell Value from Table using xPath=" + xPath;
			System.out.println(message);
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {
				HTMLReportGenerator.StepDetails("PASS", "Table Get Cell Value", "Able to get Table Cell Value", "");
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
				HTMLReportGenerator.StepDetails("FAIL", "Table Get Cell Value", "Fail to get Table Cell Value", "");
			}
			throw new WebDriverException(message);
		}
	}

	// ######################Frame#################
	public WebDriver FrameSwitchByIndex(int Index) {
		try {
			String message = TextOperations.getDateTime() + "----INFO---- Step Number:" + (counter++)
					+ " Able to Switch Frame by Index using Index=" + Index;
			System.out.println(message);
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {
				HTMLReportGenerator.StepDetails("PASS", "Frame Switch By Index",
						"Able to Switch Frame by Index" + Index, "");
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
				HTMLReportGenerator.StepDetails("FAIL", "Frame Switch By Index",
						"Fail to Switch Frame by Index" + Index, "");
			}
			throw new WebDriverException(message);
		}
	}

	public WebDriver FrameSwitchByXPath(String xPath) {
		try {
			WebElement obj = driver.findElement(By.xpath(xPath));
			String message = TextOperations.getDateTime() + "----INFO---- Step Number:" + (counter++)
					+ " Able to get Switch Frame by Xpath using xPath=" + xPath;
			System.out.println(message);
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {
				HTMLReportGenerator.StepDetails("PASS", "Frame Switch By Xpath",
						"Able to Switch Frame by Xpath" + xPath, "");
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
				HTMLReportGenerator.StepDetails("FAIL", "Frame Switch By Xpath",
						"Fail to Switch Frame by Xpath" + xPath, "");
			}
			throw new WebDriverException(message);
		}
	}

	public WebDriver FrameSwitchByName(String NameOfTheFrame) {
		try {
			String message = TextOperations.getDateTime() + "----INFO---- Step Number:" + (counter++)
					+ " Able to get Switch Frame by Name using FrameName=" + NameOfTheFrame;
			System.out.println(message);
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {
				HTMLReportGenerator.StepDetails("PASS", "Frame Switch By Name",
						"Able to Switch Frame by Name" + NameOfTheFrame, "");
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
				HTMLReportGenerator.StepDetails("FAIL", "Frame Switch By Name",
						"Fail to Switch Frame by Name" + NameOfTheFrame, "");
			}
			throw new WebDriverException(message);
		}
	}

	// ######################Drop Down#################
	public void DropDownSelectByVisibleText(String xPath, String Value) {
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
				HTMLReportGenerator.StepDetails("PASS", "Drop Down Select by Visible Text",
						"Able to Select Value from Dropdown by visible Text" + Value, "");
			}
		} catch (Exception ex) {
			String message = TextOperations.getDateTime() + "----ERROR---- Step Number:" + (counter++)
					+ " Failed to Select Visible Text from Drop Down using xPath=" + xPath + "\n Exception;"
					+ ex.getLocalizedMessage();
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {
				HTMLReportGenerator.StepDetails("FAIL", "Drop Down Select by Visible Text",
						"Fail to Select Value from Dropdown by visible Text" + Value, "");
			}
			throw new WebDriverException(message);
		}
	}

	public void DropDownSelectByIndex(String xPath, int Index) {
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
				HTMLReportGenerator.StepDetails("PASS", "Drop Down Select by Index",
						"Able to Select Value from Dropdown by Index" + Index, "");
			}
		} catch (Exception ex) {
			String message = TextOperations.getDateTime() + "----ERROR---- Step Number:" + (counter++)
					+ " Failed to Select by Index from Drop Down using xPath=" + xPath + "\n Exception;"
					+ ex.getLocalizedMessage();
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {
				HTMLReportGenerator.StepDetails("FAIL", "Drop Down Select by Index",
						"Fail to Select Value from Dropdown by Index" + Index, "");
			}
			throw new WebDriverException(message);
		}
	}

	public void DropDownSelectByOptionValue(String xPath, String OptionValue) {
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
				HTMLReportGenerator.StepDetails("PASS", "Drop Down Select by Option value",
						"Able to Select Value from Dropdown by Option value" + OptionValue, "");
			}
		} catch (Exception ex) {
			String message = TextOperations.getDateTime() + "----ERROR---- Step Number:" + (counter++)
					+ " Failed to Select by Option Value from Drop Down using xPath=" + xPath + "\n Exception;"
					+ ex.getLocalizedMessage();
			if (isLogEnabled) {
				TextOperations.AppendTextFile(LogFilePath, message);
			}
			if (isReportEnable) {
				HTMLReportGenerator.StepDetails("FAIL", "Drop Down Select by Option value",
						"Fail to Select Value from Dropdown by Option Value" + OptionValue, "");
			}
			throw new WebDriverException(message);
		}
	}

	public String DropDownGetSelectedValue(String xPath) {
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
				HTMLReportGenerator.StepDetails("PASS", "Drop Down Select by Selected value",
						"Able to Select Value from Dropdown by Selected Value" + xPath, "");
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
				HTMLReportGenerator.StepDetails("FAIL", "Drop Down Select by Selected value",
						"Fail to Select Value from Dropdown by Selected Value" + xPath, "");
			}
			throw new WebDriverException(message);
		}
	}

	public ArrayList<String> DropDownGetAllSelectedValue(String xPath) {
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
				HTMLReportGenerator.StepDetails("PASS", "Drop Down Select by ALL Selected value",
						"Able to Select Value from Dropdown by ALL Selected Value" + xPath, "");
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
				HTMLReportGenerator.StepDetails("FAIL", "Drop Down Select by ALL Selected value",
						"Fail to Select Value from Dropdown by ALL Selected Value" + xPath, "");
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

	// ################################# Methods
	// ######################################
	
	
	private void BeforeSuite(String suitename, String ownernme) throws UnknownHostException {
		HTMLReportGenerator.TestSuiteStart(suitename, ownernme);
		//HTMLReportGenerator.TestSuiteStart(ResultHTMLFilePath, UserName);
	}
	
	private void BeforeTest(String testcaseid, String testcasetitle) {
		HTMLReportGenerator.TestCaseStart(testcaseid, testcasetitle);
		//HTMLReportGenerator.TestCaseStart(TestName, Description);
	}
	
	private void AfterTest() {
		HTMLReportGenerator.TestCaseEnd();
	}
	
	private void AfterSuite() {
		HTMLReportGenerator.TestSuiteEnd();
	}

	private void ValidLogin() throws InterruptedException {
		// op.LaunchApplication("ch", "http://localhost:90/finsys/login.html"); // for
		// Office User.
		op.LaunchApplication("ch", "http://localhost/finsys/login.html"); // for Home User.
		op.TextBoxSetValue("//input[@placeholder='Username']", "dummyfm");
		op.TextBoxSetValue("//input[@placeholder='Password']", "passw0rd");
		op.LinkClick("//span[.='Login']");
		String val = op.ObjectGetAttributeValue("//a[.='LOGOUT']", "innerText");
		if (val.equalsIgnoreCase("LOGOUT")) {
			System.out.println("User is Log-in Successfully. [PASS]");
			TextOperations.AppendTextFile(LogFilePath, "User is Log-in Successfully. [PASS]");
		} else {
			System.out.println("User Log-in is Failed. [FAIL]");
			TextOperations.AppendTextFile(LogFilePath, "User Log-in is Failed. [FAIL]");
		}
	}

	private void InvalidLogin() throws InterruptedException {

		op.LaunchApplication("ch", "http://localhost:90/finsys/login.html"); // for Office User.
		// op.LaunchApplication("ch", "http://localhost/finsys/login.html"); //for Home
		// User.
		op.TextBoxSetValue("//input[@placeholder='Username']", "dummyfm");
		op.TextBoxSetValue("//input[@placeholder='Password']", "passw0rdd");
		op.LinkClick("//span[.='Login']");
		String val = op.ObjectGetAttributeValue("//div[@id='error']", "innerText");
		if (val.equalsIgnoreCase("Please Enter Valid Username or Password!!!")) {
			System.out.println("User is log-in With Invalid Username & Invalid Password. [PASS]");
			TextOperations.AppendTextFile(LogFilePath,
					"User is log-in With Invalid Username & Invalid Password. [PASS]");
		} else {
			System.out.println("User is able to Log-in successfully. [FAIL]");
			TextOperations.AppendTextFile(LogFilePath, "User is able to Log-in successfully. [FAIL]");
		}
	}

	private void CreateCompany() throws InterruptedException {

		op.LinkClick("//a[@title='Manage Company']");
		op.FrameSwitchByName("actionid");
		op.LinkClick("//a[1]/span[@class=\"l-btn-left l-btn-icon-left\"]");
		String company = "finsys";
		op.TextBoxSetValue("//input[@name=\"name\"]", company);
		op.ButtonClick("//select[@id='companytype']");
		op.DropDownSelectByIndex("//select[@id='companytype']", 2);
		op.TextBoxSetValue("//input[@name=\"pan\"]", "9876543210");
		op.TextBoxSetValue("//input[@name=\"email\"]", "amitb@iprogrammer.com");
		op.TextBoxSetValue("//input[@name=\"tin\"]", "1234567890");
		op.ButtonClick("//select[@id=\"countryid\"]");
		op.DropDownSelectByOptionValue("//select[@id=\"countryid\"]", "IN");
		op.ButtonClick("//select[@id=\"stateidlist\"]");
		op.DropDownSelectByOptionValue("//select[@id=\"stateidlist\"]", "MAHARASHTRA");
		op.ButtonClick("//select[@id=\"citylist\"]");
		op.DropDownSelectByOptionValue("//select[@id=\"citylist\"]", "PUNE");
		op.LinkClick("//span[@class='l-btn-icon icon-save']");
		op.LinkClick("//span[@class='l-btn-icon pagination-load']");
		String val = op.ObjectGetAttributeValue("//tr[@id='datagrid-row-r1-2-0']/td[@field='name']", "innerText");
		String temp = val.replaceAll("\\‌​r|\\n", "");
		if (temp.equalsIgnoreCase(company)) {
			System.out.println(temp + " Company is added [PASS].");
			TextOperations.AppendTextFile(LogFilePath, temp + " Company is added [PASS].");
		} else {
			System.out.println("Invalid Company  " + val + "[FAIL]");
			TextOperations.AppendTextFile(LogFilePath, "Invalid Company " + val + "[FAIL]");
		}
	}

	private void tearDown() {
		driver.close();
	}

	public static void main(String[] args) throws InterruptedException, UnknownHostException {
		// op=new OperationsV1(true);
		op=new OperationsV1("result\\extentreports",true);
		op = new OperationsV1(true, "log");
		op.BeforeSuite("result\\extentreports\\aabidkar.html", "aabidkar");
		op.BeforeTest("Login_Functionality", "Verify Login Functionality");
		op.ValidLogin();
		// op.InvalidLogin();
		op.CreateCompany();
		op.tearDown();
		op.TestCaseEnd();
		op.TestSuiteEnd();
	}

}