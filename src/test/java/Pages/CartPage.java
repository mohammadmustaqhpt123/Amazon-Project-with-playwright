package Pages;

import java.util.Iterator;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CartPage {

	private Page   page;
	private String cartAddedItems 		= ".a-section.a-spacing-mini.sc-list-body.sc-java-remote-feature "
											+".a-link-normal.sc-product-link .a-truncate "
												+".a-truncate-full.a-offscreen";
	
	private String quantity 			= ".sc-action-quantity input";
	private String subAmount			= ".a-section.sc-buy-box-inner-box .sc-white-space-nowrap";
	private String proceedToBuy			= "input[name='proceedToRetailCheckout']";
	private String delete				= ".a-section.a-spacing-mini.sc-list-body.sc-java-remote-feature input[value='Delete']";
	
	public CartPage(Page page)
	{
		this.page = page;
	}
	
	
	
	public boolean verifyItemIsPresent(String itemTitle)
	{
		Locator cartItems = page.locator(cartAddedItems);
		
		for (int i = 0; i < cartItems.count(); i++) {
			System.out.println(cartItems.nth(i).innerText() +"\t"+"========"+"\t"+ itemTitle);
			if(itemTitle.contains(cartItems.nth(i).innerText()))
			{
				
				return true;
			}
			
		}
		return false;
	
	}
	
	
	public String getQuantityOfItem(String itemTitle)
	{
		Locator cartItems = page.locator(cartAddedItems);
		
		for (int i = 0; i < cartItems.count(); i++) {
			System.out.println(cartItems.nth(i).innerText() +"\t"+"========"+"\t"+ itemTitle);
			if(itemTitle.contains(cartItems.nth(i).innerText()))
			{
			
				return page.locator(quantity).nth(i).getAttribute( "value");
			}
			
		}
		return null;
	
	}
	
	
	
	public String getSubTotalPrice()
	{
		return page.innerText(subAmount);
	}
	
	
	
	public CheckoutPage clickOnProceedToBuy()
	{
		page.click(proceedToBuy);
		page.waitForLoadState();
		return new CheckoutPage(page);
		
	}
	
	
	public boolean clickOnDelete(String itemTitle)
	{
		Locator cartItems = page.locator(cartAddedItems);
		
		for (int i = 0; i < cartItems.count(); i++) {
			System.out.println(cartItems.nth(i).innerText() +"\t"+"========"+"\t"+ itemTitle);
			if(itemTitle.contains(cartItems.nth(i).innerText()))
			{
				page.locator(delete).nth(i).click();
				return true;
				
			}
			
		}
		return false;
	
	}
	
	
	
	
	
	
	
}
