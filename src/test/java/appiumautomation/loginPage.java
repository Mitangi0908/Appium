package appiumautomation;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.loginPagePageObject;
import Utility.BaseTest;
import Utility.RetryAnalyzer;

public class loginPage extends BaseTest {

	loginPagePageObject loginpage;
	RetryAnalyzer retryAnalyzer = new RetryAnalyzer();

	@Test(priority = 1, retryAnalyzer = RetryAnalyzer.class)
	public void loginWithoutUsername() throws InterruptedException {
		loginpage = new loginPagePageObject(driver);
		log.info("Starting loginWithoutUsername test");
		loginpage.enterPassword(prop.getProperty("Password"));
		log.info("Clicking login button");
		loginpage.clickLoginButton();
		String errorMessage = loginpage.usernameErrorMessage();
		log.info("Capturing the error message" + errorMessage);
		Assert.assertEquals(errorMessage, prop.getProperty("requiredUsername"));
		log.info("Finished loginWithoutUsername test");
	}

	@Test(priority = 2)
	public void loginWithoutPassword() throws InterruptedException {
		loginpage = new loginPagePageObject(driver);
		log.info("Starting loginWithoutPassword test");
		log.info("Clear the password");
		loginpage.clearPassword();
		log.info("Entering Username");
		loginpage.enterUsername(prop.getProperty("Username"));
		log.info("Clicking login button");
		loginpage.clickLoginButton();
		String errorMessage = loginpage.passwordErrorMessage();
		log.info("Capturing the error message" + errorMessage);
		Assert.assertEquals(errorMessage, prop.getProperty("requiredPassword"));
		log.info("Finished loginWithoutPassword test");
	}

	@Test(priority = 3)
	public void loginWithIncorrectUsernameAndPassword() throws InterruptedException {
		loginpage = new loginPagePageObject(driver);
		log.info("Starting loginWithIncorrectUsernameAndPassword test");
		log.info("Entering Username");
		loginpage.enterUsername(prop.getProperty("wrongUsername"));
		log.info("Entering Password");
		loginpage.enterPassword(prop.getProperty("wrongPassword"));
		log.info("Clicking login button");
		loginpage.clickLoginButton();
		String errorMessage = loginpage.usernamePasswordErrorMessage();
		log.info("Capturing the error message" + errorMessage);
		Assert.assertEquals(errorMessage, prop.getProperty("wrongUsernamePassword"));
		log.info("Finished loginWithIncorrectUsernameAndPassword test");

	}

	@Test(priority = 4)
	public void loginWithCorrectUsernameAndPassword() throws InterruptedException {
		loginpage = new loginPagePageObject(driver);
		log.info("Starting loginWithCorrectUsernameAndPassword test");
		log.info("Entering Username");
		loginpage.enterUsername(prop.getProperty("Username"));
		log.info("Entering Password");
		loginpage.enterPassword(prop.getProperty("Password"));
		log.info("Clicking login button");
		loginpage.clickLoginButton();
		String productText = loginpage.productPageText();
		Assert.assertEquals(productText, prop.getProperty("productPageTitle"));
		log.info("Finished loginWithCorrectUsernameAndPassword test");

	}
}
