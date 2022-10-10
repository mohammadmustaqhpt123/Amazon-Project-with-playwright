package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Base.Utility;
import Pages.FreshPage;

public class FreshTest extends Utility {

	@org.testng.annotations.BeforeTest
	public void BeforeTest()
	{
		Utility.launchBrowser("chrome");
		Utility.launchUrl("https://www.amazon.in/");
	
	}
	
	@Test(groups = "Smoke")
	public void verifyFreshPageTitle() throws InterruptedException
	{
		FreshPage fg = 
				homePage.clickOnFresh();
		String actual = fg.getTitle();
		String expected = fg.getExpectedTitle();
		Assert.assertEquals(actual, expected);
		
	}
	
	
	@org.testng.annotations.AfterTest
	public void AfterTest() {
		page.context().browser().close();
	}
}
