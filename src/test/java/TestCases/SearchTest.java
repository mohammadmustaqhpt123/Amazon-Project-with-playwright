package TestCases;


import org.testng.Assert;
import org.testng.annotations.*;
import Base.Utility;
import Pages.SearchPage;

public class SearchTest extends Utility{
	
	@BeforeMethod
	public void BeforeTest()
	{
		Utility.launchBrowser("chrome");
		Utility.launchUrl("https:www.amazon.in");
	}
	
	@Test
	public void productSearchTC001()
	{
		homePage.search("shoe123#@#$");
		String actual = homePage.getSearchContent();
		Assert.assertEquals(actual, "shoe123#@#$");	
	}
	
	
	@Test
	public void productSearchTC002()
	{
		SearchPage sp = homePage.search("shoe123#@#$");
		String actual = sp.verifySeacrch();
		String expected = "\"shoe123#@#$\"";
		Assert.assertEquals(actual,expected);
		
	}
	
	@Test
	public void productSearchTC003()
	{
		SearchPage sp = homePage.search("shoes");
		
	}
	
	@Test
	public void productSearchTC006()
	{
		SearchPage sp = homePage.search("shoes");
		boolean  actual = sp.verifyFilterCategory();
		Assert.assertEquals(actual, true);
	}
	
	@Test
	public void productSearchTC007()
	{
		SearchPage sp = homePage.search("shoes");
		boolean actual = sp.verifyNoOfResult();
		Assert.assertEquals(actual, true);
	}
	
	
	
	@Test
	public void productSearchTC008()
	{
		SearchPage sp = homePage.search("shoes");
		boolean  actual = sp.verifyNextPage();
		Assert.assertEquals(actual, true);
	}

	
	@AfterMethod
	public void AfterTest() {
		page.context().browser().close();
	}
}
