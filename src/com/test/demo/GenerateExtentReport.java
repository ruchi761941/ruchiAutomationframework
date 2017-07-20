package com.test.demo;

import java.io.File;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class GenerateExtentReport {
	ExtentReports extent;
	ExtentTest test;

	@BeforeTest
	public void startReport() {
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/myOwnReport.html", true);
		extent.addSystemInfo("HostName", "ruchi");
		extent.addSystemInfo("Environment", "QA");
		extent.loadConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));

	}

	@Test
	public void testDemoZReportPass() {
		test = extent.startTest("testDemoReportPass");
		Assert.assertTrue(true);
		test.log(LogStatus.PASS, "Assert pass as condition is true");
	}

	@Test
	public void testDemoReportfail() {
		test = extent.startTest("testDemoReportFail");
		Assert.assertTrue(false);
		test.log(LogStatus.FAIL, "Assert pass as condition is false");

	}

	@AfterMethod
	public void testGetresult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, result.getThrowable());
		}
		extent.endTest(test);

	}

	@AfterTest
	public void testEndReport() {
		extent.flush();
		extent.close();

	}

}
