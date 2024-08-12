package Utility;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.android.AndroidDriver;

public class Listeners extends BaseTest implements ITestListener {

	ExtentReports extent = ExtentReporterNG.getReporterObject();
	ExtentTest test;
	AndroidDriver driver;

	public void onTestStart(ITestResult result) {
		// not implemented
		test = extent.createTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		// not implemented
		System.out.println("Test Pass");
		test.log(Status.PASS, "Test Passed");
	}

	public void onTestFailure(ITestResult result) {
		// not implemented
		System.out.println("Test Fail");
		test.fail(result.getThrowable());
		try {
			driver = (AndroidDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			test.addScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName(), driver),
					result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void onTestSkipped(ITestResult result) {
		// not implemented
	}
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// not implemented
	}
	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);
	}
	public void onStart(ITestContext context) {
		// not implemented
	}
	public void onFinish(ITestContext context) {
		// not implemented
		extent.flush();
	}
}
