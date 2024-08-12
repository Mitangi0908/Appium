package PageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class loginPagePageObject{
		
	AndroidDriver driver;

	public loginPagePageObject(AndroidDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(accessibility = "test-Username")
	private WebElement usernameTextbox;
	
	@AndroidFindBy(accessibility = "test-Password")
	private WebElement passwordTextbox;

	@AndroidFindBy(accessibility = "test-LOGIN")
	private WebElement loginButton;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Username is required']")
	private WebElement usernameErrorMessage;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Password is required']")
	private WebElement passwordErrorMessage;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Username and password do not match any')]")
	private WebElement usernamePasswordErrorMessage;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='PRODUCTS']")
	private WebElement productPageText;

	public void enterUsername(String username)
	{
		usernameTextbox.clear();
		usernameTextbox.sendKeys(username);
	}
	
	public void enterPassword(String password)
	{
		passwordTextbox.clear();
		passwordTextbox.sendKeys(password);
	}
	
	public void clearPassword()
	{
		passwordTextbox.clear();
	}
	public void clickLoginButton()
	{
		loginButton.click();
	}
	
	public String usernameErrorMessage()
	{
		 return usernameErrorMessage.getText();
	}
	
	public String passwordErrorMessage()
	{
		 return passwordErrorMessage.getText();
	}
	public String usernamePasswordErrorMessage()
	{
		 return usernamePasswordErrorMessage.getText();
	}
	public String productPageText()
	{
		 return productPageText.getText();
	}

}
