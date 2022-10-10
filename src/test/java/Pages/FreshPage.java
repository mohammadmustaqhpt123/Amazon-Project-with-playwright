package Pages;

import com.microsoft.playwright.Page;

public class FreshPage {

	private Page   page;
	private String expectedTitle 		= "Amazon.in: Amazon Fresh";
	
	
	public FreshPage(Page page) {
		this.page = page;
	}
	
	public String getTitle() {
		page.waitForLoadState();
		return page.title();
	}
	
	public String getExpectedTitle() {
		return expectedTitle;
		
	}
	
}
