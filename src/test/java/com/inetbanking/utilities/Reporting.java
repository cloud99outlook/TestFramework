package com.inetbanking.utilities;

import org.testng.ITestContext;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class Reporting extends TestListenerAdapter {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent = new ExtentReports();
	public ExtentTest logger;
	public ExtentSparkReporter sparkFail;

	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	String repName = "Test-Report-" + timeStamp + ".html";
	String FailRepName = "FailedTest-Report-" + timeStamp + ".html";

	public void onStart(ITestContext testContext) {

		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/" + repName);
		try {
			sparkReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}

		extent.attachReporter(sparkReporter, sparkFail);
		System.out.println("Extent ReportsLoading Completed");

		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Operating System", "Windows10");
		extent.setSystemInfo("Tested By", "Meera");
	}

	public void onTestSuccess(ITestResult tr) {

		logger = extent.createTest(tr.getName());
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
		System.out.println("ItestResult is Success");

	}

	public void onTestFailure(ITestResult tr) {

		logger = extent.createTest(tr.getName());
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));

		//check if the screenshot is taken and stored in the screenshots folder, so give the same path, with image name
		String screenshotpath = System.getProperty("user.dir") + "\\Screenshots\\" + tr.getName() + ".png";
		File f = new File(screenshotpath);

		if (f.exists()) {
			logger.fail("screenshot is below" + logger.addScreenCaptureFromPath(screenshotpath));
		}

				// Failed Reports
//				if (Status.FAIL != null) {
//					sparkFail = new ExtentSparkReporter(System.getProperty("user.dir") + "/fail-output/" + FailRepName).filter()
//							.statusFilter().as(new Status[] { Status.FAIL }).apply();
//
//					try {
//						sparkFail.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//				extent.attachReporter(sparkFail);
		
		
	}

	public void onTestSkipped(ITestResult tr) {

		logger = extent.createTest(tr.getName());
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

}