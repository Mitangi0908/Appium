package appiumautomation;
import java.text.DecimalFormat;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.EndToEndPageObject;
import Utility.BaseTest;


public class EndToEndTest extends BaseTest{
	EndToEndPageObject endtoendtest;
	@Test
	public void checkoutProduct() throws InterruptedException
	{
		endtoendtest = new EndToEndPageObject(driver);
		appLogin();
		
		endtoendtest.clickOnAddToCart().get(0).click();
		endtoendtest.scrollToElement(prop.getProperty("selectProduct"));
		//endtoendtest.clickOnAddToCart().get(1).click();
		for(int i=0; i<endtoendtest.getProduct().size();i++)
		{
			String productName = endtoendtest.getProduct().get(i).getText();
			if(productName.equalsIgnoreCase(prop.getProperty("selectProduct")))
			{
				endtoendtest.clickOnAddToCart().get(i).click();	
			}
		}
		
		endtoendtest.clickonCart();
		endtoendtest.scrollToElement("CHECKOUT");
		endtoendtest.clickonCheckout();
		endtoendtest.enterFirstname(prop.getProperty("firstname"));
		endtoendtest.enterLastname(prop.getProperty("lastname"));
		endtoendtest.enterZipcode(prop.getProperty("zipcode"));
		endtoendtest.clickonContinue();
			
		double totalPrice = 0;
		
		for(int i=0; i<endtoendtest.productPrices().size();i++)
		{
			String prices= endtoendtest.productPrices().get(i).getText().substring(1);
			double actualPrice = endtoendtest.stringToDouble(prices);
			totalPrice = totalPrice+actualPrice;
		}
		
		endtoendtest.scrollToElement("Shipping Information:");
		String total = endtoendtest.productTotalPrice().substring(13);
		Assert.assertEquals(totalPrice,  endtoendtest.stringToDouble(total));
		
		String tax = endtoendtest.tax().substring(6);
		String totalWithTax = endtoendtest.priceWithTax().substring(8);
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		double finalPrice = endtoendtest.stringToDouble(tax)+ endtoendtest.stringToDouble(total);
		Assert.assertEquals(df.format(finalPrice), totalWithTax);
		endtoendtest.clickOnFinish();
		String message = endtoendtest.thankYouMessage();
		Assert.assertEquals(message,prop.getProperty("thankyouMessage"));	
		
	}


}
