package Pages;

import org.testng.Assert;

import com.microsoft.playwright.Page;

import Base.Utility;

public class HomePage {
	private Page page;
	private String title        = 	"Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
	private String userName     = 	"div.nav-line-1-container span";
	private String login        = 	"#nav-link-accountList";
	private String singUp       = 	"";
	private String search       = 	"input[aria-label='Search']";
	private String hamburger    = 	"#nav-hamburger-menu";
	private String fresh        = 	"a[href='/fresh?ref_=nav_cs_fresh']";
	private String box          = 	".fluid-quad-image-label";
	private String boxFirst     = 	"((//*[contains(@class, 'fluid-quad-image-label')])[1]/div/div[1])/div[1]";
	private String yourAccount  = 	"a span:text('Your Account'):below(#nav-al-your-account .nav-title)";
	private String cart			=   "//*[@id='nav-cart-count']";
	
	
	public HomePage(Page page) {
	
		this.page = page;
	}
	
	
	public void verifyHomePage()
	{
		String actual = page.title();
		Assert.assertEquals(actual, title);
	}
	
	public void verifyUserName(String expectedName)
	{
		String actual = page.innerText(userName);
		System.out.println(actual);
		Assert.assertEquals(actual.toLowerCase().contains(expectedName), true);
		
	}
	
	public void verifySeachTextBox()
	{
		boolean actual = page.isEnabled(search);
		Assert.assertEquals(actual, true);
	
	}
	
	
	public YourAccountPage clickOnYourAccount()
	{
		page.hover(login);
		page.click(yourAccount);
		page.waitForLoadState();
		return new YourAccountPage(page);
	}
	
	
	public double getWidthOfBox()
	{
		return page.locator(box).first().boundingBox().width;
	}
	
	
	public double getHeightOfBox()
	{
		return page.locator(box).first().boundingBox().height;
	}
	
	public LoginPage clickOnLogin() {
		page.click(login);
		
		return new LoginPage(page);
	}
	
	public void enterSearchContent(String searchContent)
	{
		page.click(search);
		page.fill(search, searchContent);
	}
	
	public String getSearchContent() {
		return page.getAttribute(search, "value");
	}
	
	public SearchPage search(String searchContent) {
		page.click(search);
		page.fill(search, searchContent);
		page.click("#nav-search-submit-button");
		page.waitForLoadState();
		return new SearchPage(page);
	}
	
	public void hamburger() {
		page.click(hamburger);
		
	}
	
	public boolean clickOnItem()
	{
		
		page.click(boxFirst);
		page.waitForLoadState();
		return  true;
		
	}
	
	
	public FreshPage clickOnFresh() {
		page.click(fresh);
		return new FreshPage(page);
		
	}
	
	public int getCartCount()
	{
		int count = Integer.parseInt(page.locator("//*[@id='nav-cart-count']").innerText());
		return count;
	}
	
	public CartPage clickOnCart()
	{
		page.click(cart);
		page.waitForLoadState();
		return new CartPage(page);
	}
	
	
	
	
	
	
	
	
	

}
