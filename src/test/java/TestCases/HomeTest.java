package TestCases;

import org.testng.Assert;
import org.testng.annotations.*;

import Base.*;
import Pages.YourAccountPage;

public class HomeTest extends Utility{
	
	@BeforeMethod
	public void BeforeMethod()
	{
		Utility.launchBrowser("chrome");
		startTracing();
		Utility.launchUrl("https:www.amazon.in");
	}
	
	@Test
	public void homePageTC001()
	{
		homePage.verifyHomePage();
		Utility.stopTracing("homePageTC001");
	}
	
	
	@Test
	public void homePageTC002()
	{
		homePage.verifyUserName("mustaq");
		Utility.stopTracing("homePageTC002");
	}
	
	
	@Test
	public void homePageTC004()
	{
		homePage.verifySeachTextBox();
		Utility.stopTracing("homePageTC004");
	}
	
	@Test
	public void homePageTC006()
	{
		double width = homePage.getWidthOfBox();
		double height = homePage.getHeightOfBox();
		
		System.out.println(height + "height");
		System.out.println(width);
		Utility.stopTracing("homePageTC006");
	}
	
	
	@Test
	public void homePageTC007()
	{
		boolean actual = homePage.clickOnItem();
		Assert.assertEquals(actual,true);
		Utility.stopTracing("homePageTC007");
		
	}
	
	
	@Test
	public void homePageTC008()
	{
		YourAccountPage yap = homePage.clickOnYourAccount();
		String actual = yap.getTitle();
		System.out.println(actual);
		Assert.assertEquals(actual, yap.title);
		Utility.stopTracing("homePageTC008");
	}
	
	
	
	@AfterMethod
	public void AfterMethod() {
		page.context().browser().close();
	}

}
