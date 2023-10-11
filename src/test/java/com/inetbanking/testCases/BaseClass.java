package com.inetbanking.testCases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.ReadConfig;

import jdk.internal.org.jline.utils.Log;

public class BaseClass {

//	public String baseURL = "https://demo.guru99.com/V1/index.php";
//	public String username = "mngr525601";
//	public String password = "qUtEvEs";

	ReadConfig readConfig = new ReadConfig();
	public String baseURL = readConfig.getApplicationURL();
	public String username = readConfig.getUsername();
	public String password = readConfig.getPassword();

	public static WebDriver driver;
	public String Edgedriverpath = readConfig.getIEDriverPath();
	public String Chromedriverpath = readConfig.getChromeDriverPath();
	public String firefoxdriverpath = readConfig.getFirefoxDriverPath();

	// public static Logger logger=LogManager.getLogger(BaseClass.class.getName());
	// public static Logger logger = LogManager.getLogger("ebanking");
	public static Logger logger;

	@Parameters("browser")
	@BeforeClass
	public void setup(String browser) throws InterruptedException {

		logger = LogManager.getLogger(BaseClass.class);

		// System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
		// + "//Drivers//chromedriver.exe");
		// driver = new ChromeDriver();
		// System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") +
		// "//Drivers//EdgeDriver.exe");
		logger.info("Browser started");

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", Chromedriverpath);
			driver = new ChromeDriver();
			// added code
			ChromeOptions ChromeOptions = new ChromeOptions();
			ChromeOptions.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
			driver = new ChromeDriver(ChromeOptions);

			logger.info("Chrome Loaded");
			Thread.sleep(2000);

		}

		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", firefoxdriverpath);
			driver = new FirefoxDriver();
		}
		if (browser.equalsIgnoreCase("iexplorer")) {
			System.setProperty("webdriver.ie.driver", Edgedriverpath);
			driver = new EdgeDriver();
		}

		logger.info("TestExecution started");
		driver.get(baseURL);
		Thread.sleep(2000);

		// Frames--images
		driver.switchTo().frame("gdpr-consent-notice");
		WebElement accept = driver.findElement(By.xpath("//*[@id=\"save\"]/span[1]/div/span"));
		accept.click();
		Thread.sleep(2000);

		logger.info("Url is open");

	}

	@AfterClass
	public void teardown() {

		if (driver != null) {
			driver.quit();
		}
	}

	public void CaptureScreen(WebDriver driver, String tname) {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		try {
			FileUtils.copyFile(srcFile, destFile);
			System.out.println("Screenshot taken");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String datetime() {
		// sometimes unwanted letters needs to be formatted MM/dd/yyyy --to clear the
		// project path
		Date Currentdate = new Date();
		String timestamp = new SimpleDateFormat("dd-MM-yyyy:HH:mm:ss").format(Currentdate).replace("/", "")
				.replace(" ", "").replace(":", "-").trim();
		System.out.println(timestamp);
		return timestamp;
	}

	public boolean isAlertPresent() {
		try {
			Alert Alert = driver.switchTo().alert();
			Alert.accept();
			return true;
		} catch (org.openqa.selenium.NoAlertPresentException Ex) {
			return false;
		}
	}

}
