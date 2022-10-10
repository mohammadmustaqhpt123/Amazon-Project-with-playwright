package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.SelectOption;

import Base.Utility;
import Pages.ItemPage;
import Pages.SearchPage;

public class Search extends Utility {
	
	@org.testng.annotations.BeforeTest
	public void BeforeTest()
	{
		Utility.launchBrowser("chrome");
		Utility.launchUrl("https://www.amazon.in/");
	
	}
	
	
	@Test(groups = {"Smoke", "Functional"})
	public void Search() throws InterruptedException {
		SearchPage sp = homePage.search("shoes");
		String actual = sp.verifySeacrch();
		Assert.assertEquals(actual, "\"shoes\"");
		
	}
	
	@Test(groups="Functional")
	public void sortBy() {
		SearchPage sp = homePage.search("shoes");
		sp.sortBy("Price: Low to High");
	}
	
	
	@Test(groups = "Integration")
	public void item() throws InterruptedException {
		
		int count = homePage.getCartCount();

		SearchPage sp = homePage.search("shoes");
		
		sp.sortBy("Price: Low to High");
		ItemPage ip = sp.clickOnItem(1);
//		String actual = ip.getTitle();
//		String expected = ip.getExpectedTitle();
//		Assert.assertEquals(actual, expected);
		
		int quantity = 3;
		ip.selectQuantity(""+quantity);
		ip.clickOnAddToCart();
		ip.verifyCartAdded();
		int actualCount = ip.getCartCount();
		System.out.println(actualCount);
		Assert.assertEquals(actualCount, count+quantity);

	}
	
	
	
	
	
	
	
	
	@org.testng.annotations.AfterTest
	public void AfterTest() {
		page.context().browser().close();
	}
	
}
