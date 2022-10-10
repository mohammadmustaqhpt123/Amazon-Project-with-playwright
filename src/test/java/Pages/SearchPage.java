package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import Base.Config;

public class SearchPage  {
	
	private Page page;
	
	private String verifySearch         = ".sg-col-inner span.a-color-state.a-text-bold";
													//left-of
	private String noOfResultPresent    = "div.a-section.a-spacing-small.a-spacing-top-small span:left-of(.sg-col-inner span.a-color-state.a-text-bold)";
	private String searchResults        = "(//img[@class='s-image'])[1]";
	private String sortBy               = "//span[@class='a-button-inner']//span[text()='Featured']";
	private String fillterCategory      = "(//*[contains(@class,'a-unordered-list a-nostyle a-vertical a-spacing-medium') ])[1]//*[@class='a-spacing-micro']";
	private String navigationNextPage   = ".s-pagination-strip";
	private String nextPage             = ".s-pagination-item.s-pagination-next.s-pagination-button.s-pagination-separator";
	
	
	///1
	public SearchPage(Page page)
	{
		this.page = page;
	}
	
	
	///2
	public String verifySeacrch()
	{
		return page.innerText(verifySearch);
	}
	
	
	///3
	public ItemPage clickOnItem(int index) {
	    Config.page1 = page.waitForPopup(()->{searchResults = searchResults.replace("[1]", "["+index+"]");
		page.click(searchResults);});
	    Config.page1.waitForLoadState();
		return new ItemPage(Config.page1);
	}
	
	
	///4
	public ItemPage clickOnItem(String itemName)
	{
		Locator  items = page.locator(searchResults);
		for (int i = 0; i < items.count(); i++) {
			if(items.nth(i).textContent().contains(itemName)) {
				items.nth(i).click();
				break;
			}	
		}
		return new ItemPage(page);
	}
	
	
	///5
	public void sortBy(String sortBy) {
		page.hover(this.sortBy);
		page.click(this.sortBy);
		page.click(".a-dropdown-link:has-text('"+sortBy+"')");
	
	}
	
	
	///6
	public boolean verifyFilterCategory(){
		return page.locator(fillterCategory) != null;
	}
	
	
	///7
	public boolean verifyNavigationNextPage(){
		return page.isVisible(navigationNextPage);
	}
	
	///8
	public boolean verifyNextPage(){
		return page.isVisible(nextPage);
	}
	
	
	///9
	public boolean verifyNoOfResult(){
		System.out.println(page.locator(noOfResultPresent).first().innerText());
		return page.locator(noOfResultPresent).first().isVisible();
	}
	
}
