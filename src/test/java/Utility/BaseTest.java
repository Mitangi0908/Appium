package Utility;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import PageObject.loginPagePageObject;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public Properties prop;

	public static Logger log = LogManager.getLogger(BaseTest.class);
	
	@BeforeClass
	public void ConfigureAppium() throws InterruptedException, IOException {
		PropertyConfigurator.configure(System.getProperty("user.dir") +"\\src\\test\\java\\Resources\\log4j.properties");


		// Load Properties File
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\Resources\\data.properties");
		prop.load(fis);

//		// Start Appium Server
//		service = new AppiumServiceBuilder()
//				.withAppiumJS(new File(
//						"C:\\Users\\MitangiPatel\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
//				.withIPAddress("127.0.0.1").usingPort(4723).withLogFile(new File("AppiumLog.txt"))
//				.withTimeout(Duration.ofSeconds(50)).build();
//		service.start();

		// Add UIAutomator2 Option
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("MitangiPhone");
		options.setApp(System.getProperty("user.dir") + "\\src\\test\\java\\Resources\\SauceLabs.apk");
		options.setAppActivity("com.swaglabsmobileapp.MainActivity");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		// Implicit Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterClass
	public void tearDown() {
		// Close Appium Server
		driver.quit();
		// Stop Service
		//service.stop();
	}
	 public void appLogin() throws InterruptedException
		{
			loginPagePageObject loginpage = new loginPagePageObject(driver);
			loginpage.enterUsername(prop.getProperty("Username"));
			loginpage.enterPassword(prop.getProperty("Password"));
			loginpage.clickLoginButton();
		}

	public String getScreenshotPath(String testCaseName, AndroidDriver driver) throws IOException {
		File source = driver.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;
	}

	

	public InputStream getScreenshot() {
		TakesScreenshot ts = (TakesScreenshot) driver;
		byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
		return new ByteArrayInputStream(screenshot);
	}
	
//	public InputStream getScreenshot() throws FileNotFoundException {
//		TakesScreenshot ts = (TakesScreenshot) driver;
//		File screenshot = ts.getScreenshotAs(OutputType.FILE);
//		return new FileInputStream(screenshot);
//	}

}
