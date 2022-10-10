package TestCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.*;

import Base.Utility;
import Pages.CartPage;
import Pages.CheckoutPage;
import Pages.HomePage;
import Pages.ItemPage;
import Pages.LoginPage;
import Pages.SearchPage;

public class CartTest extends Utility {

	@BeforeMethod
	public void BeforeTest()
	{
		Utility.launchBrowser("chrome");
		Utility.launchUrl("https:www.amazon.in");
	}
	
	@Test
	public void CartTC001()
	{
		SearchPage sp = homePage.search("shoes");
		ItemPage ip = sp.clickOnItem(1);
		String actual = ip.getTitle();
		ip.clickOnAddToCart();	
		ip.verifyCartAdded();
		CartPage cp = ip.clickOnCart();
		boolean actual1  = cp.verifyItemIsPresent(actual);
		Assert.assertEquals(actual1, true);
		
		
	}
	
	@Test
	public void CartTC003()
	{
		SearchPage sp = homePage.search("shoes");
		ItemPage ip = sp.clickOnItem(1);
		String actual = ip.getTitle();
		ip.clickOnAddToCart();	
		ip.verifyCartAdded();
		ip.goToItmePage();
		ip.clickOnAddToCart();	
		ip.verifyCartAdded();
		CartPage cp = ip.clickOnCart();
		String actual1  = cp.getQuantityOfItem(actual);
		System.out.println(actual1);
		
		Assert.assertEquals(actual1, "2");
		
		
	}
	
	
	@Test
	public void CartTC004()
	{
		CartPage cp = homePage.clickOnCart();
		String totalAmount = cp.getSubTotalPrice();
		System.out.println(totalAmount);

	}
	
	@Test
	public void CartTC05()
	{
		CartPage cp = homePage.clickOnCart();
		CheckoutPage cop = cp.clickOnProceedToBuy();
		String actual = cop.orderSummaryTable("Order Total:");
		System.err.println(actual);

	}
	
	@Ignore("This testcase accepting maximum no of quantity, its not showing any message")
	@Test
	public void CartTC006()
	{
		SearchPage sp = homePage.search("shoes");
		ItemPage ip = sp.clickOnItem(1);
		String actual = ip.getTitle();
		ip.selectLastQuantity();
		ip.clickOnAddToCart();	
		ip.verifyCartAdded();
		ip.goToItmePage();
//		ip.selectLastQuantity();
		ip.clickOnAddToCart();
		page1.close();	
	}
	
	
	
	@Test
	public void CartTC007() throws InterruptedException
	{
		SearchPage sp = homePage.search("shoes");
		ItemPage ip = sp.clickOnItem(1);
		String actual = ip.getTitle();
		ip.clickOnAddToCart();	
		ip.verifyCartAdded();
		CartPage cp = ip.clickOnCart();
		boolean actual2 = cp.clickOnDelete(actual);
		Assert.assertEquals(actual2, true);

	}

	@Test
	public void CartTC009() throws InterruptedException
	{
		SearchPage sp = homePage.search("shoes");
		ItemPage ip = sp.clickOnItem(1);
		String actual = ip.getTitle();
		ip.clickOnAddToCart();	
		ip.verifyCartAdded();
		LoginPage lp = ip.singOut();
		lp.setEmail("mohammadmustaq227@gmail.com");
		lp.clickOnContinueBtn();
		lp.setPassword("Mustaq@123");
		HomePage hp = lp.clickOnLoginBtn();
		CartPage cp = hp.clickOnCart();
		boolean actual2 = cp.verifyItemIsPresent(actual);
		Assert.assertEquals(actual2, true);		
		
	}
	
	@AfterMethod
	public void AfterTest() {
		page.context().browser().close();
	}
	
	
	
}
