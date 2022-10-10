package Pages;

import com.microsoft.playwright.Page;

public class YourAccountPage {

	private Page page;
	
	public String title = "Your Account";
	
	public YourAccountPage(Page page)
	{
		this.page = page;

	}
	
	public String getTitle()
	{
		return page.title();
	}
	
}
