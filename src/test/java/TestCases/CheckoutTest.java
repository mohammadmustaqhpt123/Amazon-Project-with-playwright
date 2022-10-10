package TestCases;

import org.testng.Assert;
import org.testng.annotations.*;
import Base.*;
import Pages.*;


public class CheckoutTest extends Utility {

	@BeforeMethod
	public void BeforeTest()
	{
		Utility.launchBrowser("chrome");
		Utility.launchUrl("https:www.amazon.in");
	}
	
	@Test
	public void checkout001() throws InterruptedException
	{
		SearchPage sp = homePage.search("shoes");
		ItemPage ip = sp.clickOnItem(1);
//		String actual = ip.getTitle();
		ip.clickOnAddToCart();	
		ip.verifyCartAdded();
		CartPage cp = ip.clickOnCart();
		CheckoutPage cop = cp.clickOnProceedToBuy();
		boolean actual = cop.verifyPaymentOption();
		Assert.assertEquals(actual, true);
	}
	
	@Test
	public void checkout002() 
	{
		SearchPage sp = homePage.search("shoes");
		ItemPage ip = sp.clickOnItem(1);
//		String actual = ip.getTitle();
		ip.clickOnAddToCart();	
		ip.verifyCartAdded();
		CartPage cp = ip.clickOnCart();
		CheckoutPage cop = cp.clickOnProceedToBuy();
		boolean actual = cop.verifyDeliveryAddress();
		Assert.assertEquals(actual, true);
	}
	
	@AfterMethod
	public void AfterTest() {
		page.context().browser().close();
	}
}
