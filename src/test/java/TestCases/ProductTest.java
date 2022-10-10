package TestCases;

import org.testng.Assert;
import org.testng.annotations.*;

import Base.Utility;
import Pages.ItemPage;
import Pages.SearchPage;

public class ProductTest extends Utility {

	@BeforeMethod
	public void BeforeTest()
	{
		Utility.launchBrowser("chrome");
		Utility.launchUrl("https:www.amazon.in");
	}
	
	@Test
	public void productDetailsTC001()
	{
		SearchPage sp = homePage.search("shoes");
		ItemPage ip = sp.clickOnItem(1);
		boolean actual = ip.verifyProductImg();
		Assert.assertEquals(actual, true);
	}
	
	@Test
	public void productDetailsTC002()
	{
		SearchPage sp = homePage.search("shoes");
		ItemPage ip = sp.clickOnItem(1);
		boolean actual = ip.verifyPrice();
		Assert.assertEquals(actual, true);
	}
	
	@Test(groups = {"verification"})
	public void productDetailsTC003()
	{
		SearchPage sp = homePage.search("shoes");
		ItemPage ip = sp.clickOnItem(1);
		boolean actual = ip.verifyCustomerReview();
		Assert.assertEquals(actual, true);
	}
	
	
	@Test()
	public void productDetailsTC004() throws InterruptedException
	{
		SearchPage sp = homePage.search("shoes");
		ItemPage ip = sp.clickOnItem(1);
		boolean actual = ip.verifyProductDetails();
		
		Assert.assertEquals(actual, true);
	}
	
	@Test()
	public void productDetailsTC005()
	{
		SearchPage sp = homePage.search("mobile");
		ItemPage ip = sp.clickOnItem(1);
		boolean actual = ip.verifyStock();
		Assert.assertEquals(actual, true);	
	}
	
	@Test()
	public void productDetailsTC008()
	{
		SearchPage sp = homePage.search("mobile");
		ItemPage ip = sp.clickOnItem(1);
		boolean actual = ip.verifyProductDeliveryAddress();
		Assert.assertEquals(actual, true);	
	}
	
	@Test()
	public void productDetailsTC009()
	{
		SearchPage sp = homePage.search("mobile");
		ItemPage ip = sp.clickOnItem(1);
		boolean actual = ip.verifyPaymentMode();
		Assert.assertEquals(actual, true);	
	}
	
	
	@Test()
	public void productDetailsTC010()
	{
		SearchPage sp = homePage.search("mobile");
		ItemPage ip = sp.clickOnItem(1);
		boolean actual = ip.verifyRelatedProduct();
		Assert.assertEquals(actual, true);	
	}
	
	
	
	
	
	
	@AfterMethod
	public void AfterTest() {
		page.context().browser().close();
	}
}
