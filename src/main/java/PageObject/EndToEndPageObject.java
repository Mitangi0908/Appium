package PageObject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Util.AndroidActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
public class EndToEndPageObject{
	
	AndroidDriver driver;
	AndroidActions androidActions;
	
	public EndToEndPageObject(AndroidDriver driver) {
		//super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		androidActions = new AndroidActions(this.driver);
	}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
	private List<WebElement> addToCartButton;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='test-Item title']")
	private List<WebElement> countProducts;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Cart']")
	private WebElement cartButton;

	@AndroidFindBy(accessibility = "test-CHECKOUT")
	private WebElement checkoutButton;
	
	@AndroidFindBy(accessibility = "test-First Name")
	private WebElement firstnameTextbox;

	@AndroidFindBy(accessibility = "test-Last Name")
	private WebElement lastnameTextbox;

	@AndroidFindBy(accessibility = "test-Zip/Postal Code")
	private WebElement zipcodeTextbox;
	
	@AndroidFindBy(accessibility = "test-CONTINUE")
	private WebElement continueButton;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Price']/android.widget.TextView")
	private List<WebElement> productPrice;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Item total')]")
	private WebElement productTotalPrice;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Tax:')]")
	private WebElement tax;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Total:')]")
	private WebElement priceWithTax;
	
	@AndroidFindBy(accessibility = "test-FINISH")
	private WebElement finishButton;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'THANK YOU')]")
	private WebElement thankyouMessage;
	

	
	public List<WebElement> clickOnAddToCart()
	{
		return addToCartButton;
	}
	
	public void scrollToElement(String element)
	{
		androidActions.ScrollToText(element);
		//driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+element+"\"));"));
	}
	public List<WebElement> getProduct()
	{
		return countProducts;
	}
	
	public void clickonCart()
	{
		cartButton.click();
	}
	public void clickonCheckout()
	{
		checkoutButton.click();
	}
	public void enterFirstname(String name)
	{
		firstnameTextbox.sendKeys(name);
	}
	public void enterLastname(String name)
	{
		lastnameTextbox.sendKeys(name);
	}
	public void enterZipcode(String zip)
	{
		zipcodeTextbox.sendKeys(zip);
	}
	public void clickonContinue()
	{
		continueButton.click();
	}
	public List<WebElement> productPrices()
	{
		return productPrice;
	}
	public String productTotalPrice()
	{
		return productTotalPrice.getText();
	}
	public String tax()
	{
		return tax.getText();
	}
	public String priceWithTax()
	{
		return priceWithTax.getText();
	}
	public void clickOnFinish()
	{
		finishButton.click();
	}
	public String thankYouMessage()
	{
		return thankyouMessage.getText();
	}
	
	public double stringToDouble(String value)
	{
		return Double.parseDouble(value);
	}

}
