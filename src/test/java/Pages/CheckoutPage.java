package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckoutPage {
	
	private Page page;
	private String orderSummaryTable		 = "#subtotals-marketplace-table tr";
	private String paymentOption			 = "//span[text()=' Another payment method']";
	private String deliveryAddress 			 = ".displayAddressLI.displayAddressCityStateOrRegionPostalCode";

	
	public CheckoutPage(Page page)
	{
		page.waitForLoadState();
		this.page = page;
	}
	
	
	
	public String orderSummaryTable(String cellText)
	{
		
		Locator orderSummaryRows = page.locator(orderSummaryTable);
		String totalAmount = orderSummaryRows.locator(":scope", new Locator.LocatorOptions().setHasText(cellText)).locator(".grand-total-price").innerText();
		return totalAmount;
	}
	
	
	public boolean verifyPaymentOption()
	{
		return page.locator(paymentOption).isVisible();
	}
	
	public boolean verifyDeliveryAddress()
	{
		System.out.println(page.innerText(deliveryAddress));
		return page.isVisible(deliveryAddress);
		
	}
	

}
