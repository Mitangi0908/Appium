package Utility;
import java.io.FileNotFoundException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Allure;

public class AllureListener extends BaseTest implements ITestListener {
	@Override
	public void onTestStart(ITestResult result) {
		// Code to execute when a test starts
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// Code to execute when a test is successful
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Allure.addAttachment("Failure Screenshot", "image/png", ((BaseTest) result.getInstance()).getScreenshot(),
				"png");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// Code to execute when a test is skipped
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// Code to execute when a test fails but is within success percentage
	}

	@Override
	public void onStart(ITestContext context) {
		// Code to execute when test starts
	}

	@Override
	public void onFinish(ITestContext context) {
		// Code to execute when all tests are finished
	}

}
